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

public class LoginActivity extends AppCompatActivity {

    public static final String USERNAME = "username";

    private static final String TAG = "LoginActivity";

    EditText editText_email;
    EditText editText_password;
    Button btn_login;
    Button btn_create_acc;
    Button btn_forgot_pass;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        btn_login = findViewById(R.id.btn_login);
        btn_create_acc= findViewById(R.id.btn_create_acc);
        btn_forgot_pass = findViewById(R.id.btn_forgot_pass);

        mAuth = FirebaseAuth.getInstance();


        //check if user is logged
        if(mAuth.getCurrentUser() != null){
            finish();
            Intent intent = new Intent(LoginActivity.this, SecondaryActivity.class);
            startActivity(intent);
        }


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btn_create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });


    }


    void login () {

        String email = editText_email.getText().toString();
        String password = editText_password.getText().toString();

        if(email.isEmpty()){
            editText_email.setError("Insira um email");
            editText_email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editText_password.setError("insira uma password");
            editText_password.requestFocus();
            return;
        }else {
            mAuth.signInWithEmailAndPassword(editText_email.getText().toString(), editText_password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                //updateUI(user);
                                Intent intent = new Intent(LoginActivity.this, SecondaryActivity.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }

}
