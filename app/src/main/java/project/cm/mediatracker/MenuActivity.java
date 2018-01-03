package project.cm.mediatracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {


    Button btSearch;
    Button btWantWatch;
    Button btWatching;
    Button btGivenUP;
    Button btWatched;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final String mediaType = getIntent().getStringExtra(HomeActivity.MEDIA_TYPE);
        final Button btSearch = findViewById(R.id.btSearch);

        Button btWantWatch = findViewById(R.id.btWantWatch);
        Button btWatching = findViewById(R.id.btWatching);
        Button btGivenUP = findViewById(R.id.btGivenUP);
        Button btWatched = findViewById(R.id.btWatched);

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SearchMediaActivity.class);
                intent.putExtra(HomeActivity.MEDIA_TYPE, mediaType);
                startActivity(intent);
            }
        });
        btWantWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ListMediaActivity.class);

                intent.putExtra(HomeActivity.MEDIA_TYPE, mediaType);
                intent.putExtra("option", "wantWatch");
                startActivity(intent);
            }
        });
        btWatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ListMediaActivity.class);
                intent.putExtra(HomeActivity.MEDIA_TYPE, mediaType);
                intent.putExtra("option", "watching");
                startActivity(intent);
            }
        });
        btGivenUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ListMediaActivity.class);
                intent.putExtra(HomeActivity.MEDIA_TYPE, mediaType);
                intent.putExtra("option", "givepUP");
                startActivity(intent);
            }
        });
        btWatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ListMediaActivity.class);
                intent.putExtra(HomeActivity.MEDIA_TYPE, mediaType);
                intent.putExtra("option", "watched");
                ;
                startActivity(intent);
            }
        });





    }
}
