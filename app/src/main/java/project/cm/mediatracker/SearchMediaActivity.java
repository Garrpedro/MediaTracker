package project.cm.mediatracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class SearchMediaActivity extends AppCompatActivity {
    TextView teste;
    Button ola;
    String olateste = "fodasse";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_media);

        olateste = getIntent().getStringExtra("media_type");
        teste = findViewById(R.id.textViewTest);
        teste.setText(olateste);
        ola = findViewById(R.id.button2);
        ola.setText(olateste);
    }
}
