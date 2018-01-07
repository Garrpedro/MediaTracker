package project.cm.mediatracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class SecondaryActivity extends FragmentActivity implements AppCompatCallback {

    ImageButton logout;
    String user;
    private AppCompatDelegate delegate;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        //check if user is logged
        if(mAuth.getCurrentUser() == null){
            finish();
            Intent intent = new Intent(SecondaryActivity.this, LoginActivity.class);
            startActivity(intent);
        }

//let's create the delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //we need to call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //we use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_secondary);

        //Finally, let's add the Toolbar
        Toolbar toolbar = findViewById(R.id.tool_bar_home);
        delegate.setSupportActionBar(toolbar);
        delegate.getSupportActionBar().setTitle(null);


        user = getIntent().getStringExtra(LoginActivity.USERNAME);

        logout = findViewById(R.id.tool_bar_signout_button);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                Intent intent = new Intent(SecondaryActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });




        HomeFragment homeFragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, homeFragment)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }


    @Override
    public void onSupportActionModeStarted(ActionMode mode) {
        //let's leave this empty, for now
    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {
        // let's leave this empty, for now
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }
}
