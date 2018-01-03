package project.cm.mediatracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    TextView username;
    Button signout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.perfil_user);
        signout = findViewById(R.id.signout);

        //check if user is logged
        if(mAuth.getCurrentUser() == null){
            finish();
            Intent intent=new Intent(ProfileActivity.this,LoginActivity.class);
            startActivity(intent);
        }


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();

            username.setText(name);

            String uid = user.getUid();
        }




        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                Intent intent=new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
