package project.cm.mediatracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    EditText text_register_nome;
    EditText text_register_email;
    EditText text_register_pass;
    EditText text_register_rpt_pass;

    Button btn_register;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        text_register_nome = findViewById(R.id.text_register_nome);
        text_register_email = findViewById(R.id.text_register_email);
        text_register_pass = findViewById(R.id.text_register_pass);
        text_register_rpt_pass = findViewById(R.id.text_register_rpt_pass);
        btn_register = findViewById(R.id.btn_register);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    void register(){

        String nome = text_register_nome.getText().toString();
        String email = text_register_email.getText().toString();
        String password = text_register_pass.getText().toString();
        String password_rpt = text_register_rpt_pass.getText().toString();

        if(nome.isEmpty()){
            text_register_nome.setError("Insira um nome");
            text_register_nome.requestFocus();
            return;
        }
        if(email.isEmpty()){
            text_register_email.setError("Insira um email");
            text_register_email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            text_register_pass.setError("Insira uma password");
            text_register_pass.requestFocus();
            return;
        }

        if (password.compareTo(password_rpt) != 0){
            text_register_rpt_pass.setError("as passwords nao coincidem");
            text_register_rpt_pass.requestFocus();
            return;
        }else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(text_register_nome.getText().toString())
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                startActivity(intent);
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "User profile updated.");
                                                }
                                            }
                                        });
                                //updateUI(user);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        }

    }


}
