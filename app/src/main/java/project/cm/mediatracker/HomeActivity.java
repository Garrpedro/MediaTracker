package project.cm.mediatracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends Activity implements AppCompatCallback {
    public static final String USERNAME = "username";
    public static final String MEDIA_TYPE = "tipo_media";
    ImageButton perfilToolbar;
    TextView title;
    String user;
    Button btMovies;
    Button btSeries;
    Button btAnimes;
    private AppCompatDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        user = getIntent().getStringExtra(LoginActivity.USERNAME);

        //let's create the delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create(this, this);

        //we need to call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //we use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_home);

        //Finally, let's add the Toolbar
        Toolbar toolbar = findViewById(R.id.tool_bar);
        delegate.setSupportActionBar(toolbar);
        delegate.getSupportActionBar().setTitle(null);

        title = findViewById(R.id.toolbar_title);
        title.setText(user);

        perfilToolbar = findViewById(R.id.tool_bar_user_button);
        perfilToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PerfilActivity.class);
                intent.putExtra(USERNAME, user);
                startActivity(intent);
            }
        });
        Button btMovies = findViewById(R.id.btMovies);
        Button btSeries = findViewById(R.id.btSeries);
        Button btAnimes = findViewById(R.id.btAnimes);

        btMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MovieActivity.class);
                intent.putExtra(USERNAME, user);
                intent.putExtra(MEDIA_TYPE, 1);
                startActivity(intent);
            }
        });

        btSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SerieAnimeActivity.class);
                intent.putExtra(USERNAME, user);
                intent.putExtra(MEDIA_TYPE, 2);
                startActivity(intent);
            }
        });

        btAnimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SerieAnimeActivity.class);
                intent.putExtra(USERNAME, user);
                intent.putExtra(MEDIA_TYPE, 3);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);

                break;
            case R.id.action_user:
                intent = new Intent(HomeActivity.this, PerfilActivity.class);
                intent.putExtra(USERNAME, user);
                startActivity(intent);
                break;

        }
        return true;
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
