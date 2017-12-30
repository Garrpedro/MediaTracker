package project.cm.mediatracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PerfilActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    TextView username;
    Button signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.perfil_user);
        signout = findViewById(R.id.signout);

        //check if user is logged
        if(mAuth.getCurrentUser() == null){
            finish();
            Intent intent=new Intent(PerfilActivity.this,LoginActivity.class);
            startActivity(intent);
        }

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            username.setText(user.getDisplayName());
        }


        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                Intent intent=new Intent(PerfilActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
