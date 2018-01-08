package project.cm.mediatracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

import project.cm.mediatracker.Model.Content;
import project.cm.mediatracker.Model.MediaContent;

public class MediaContentActivity extends AppCompatActivity {

    Content content;

    TextView txtViewTitleSearch, txtViewYearSearch, txtViewGenreSearch, txtViewReleasedSearch, txtViewPlotSearch;
    ImageView imgViewPosterSearch;
    Button btnWatched, btnWantWatch, btnGivenUp, btnWatching;


    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_content);

        databaseReference = FirebaseDatabase.getInstance().getReference("mediaTracker");

        btnGivenUp = findViewById(R.id.btnAddToGivenUp);
        btnWatched = findViewById(R.id.btnAddToWatched);
        btnWantWatch = findViewById(R.id.btnAddToWantToWatch);
        btnWatching = findViewById(R.id.btnAddToWatching);

        txtViewTitleSearch = findViewById(R.id.txtViewTitleSearch);
        txtViewYearSearch = findViewById(R.id.txtViewYearSearch);
        txtViewGenreSearch = findViewById(R.id.txtViewGenreSearch);
        txtViewReleasedSearch = findViewById(R.id.txtViewReleasedSearch);
        txtViewPlotSearch = findViewById(R.id.txtViewPlotSearch);

        final String id  = UUID.randomUUID().toString();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        imgViewPosterSearch = findViewById(R.id.imgViewPosterSearch);

        btnWatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaContent md = new MediaContent(content.getTitle(), content.getYear(),content.getPoster() , "movie", "watching");

                databaseReference.child(user.getUid()).child(id).setValue(md);
            }
        });

        btnWantWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaContent md = new MediaContent(content.getTitle(), content.getYear(),content.getPoster() , "movie", "wantwatch");


                databaseReference.child(user.getUid()).child(id).setValue(md);
            }
        });

        btnWatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaContent md = new MediaContent(content.getTitle(), content.getYear(),content.getPoster() , "movie", "watched");

                databaseReference.child(user.getUid()).child(id).setValue(md);

            }
        });

        btnGivenUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaContent md = new MediaContent(content.getTitle(), content.getYear(),content.getPoster() , "movie", "givenup");

                databaseReference.child(user.getUid()).child(id).setValue(md);
            }
        });

        SearchMediaContent();

    }

    public  void  SearchMediaContent()
    {
        // Send data
        try
        {
            String url = "http://www.omdbapi.com/?apikey=675629b3&i=" + getIntent().getStringExtra("media_content_imdbid");

            JsonObjectRequest jsonRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // the response is already constructed as a JSONObject!
                            try {
                                //response = response.getJSONObject("args");
                                String title = response.getString("Title");
                                String year = response.getString("Year");
                                String genre = response.getString("Genre");
                                String released = response.getString("Released");
                                String plot = response.getString("Plot");
                                String poster = response.getString("Poster");

                                content = new Content(title, year, genre, released, plot, poster);

                                displayAll();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            Volley.newRequestQueue(this).add(jsonRequest);
        }
        catch(Exception ex)
        {
        }
        finally
        {
        }
    }

    public void displayAll()
    {
        txtViewTitleSearch.setText(content.getTitle());
        txtViewYearSearch.setText(content.getYear());
        txtViewGenreSearch.setText(content.getGenre());
        txtViewReleasedSearch.setText(content.getReleased());
        txtViewPlotSearch.setText(content.getPlot());

        if (!content.getPoster().equals("N/A"))
            Picasso.with(getApplicationContext()).load(content.getPoster()).into(imgViewPosterSearch);
        else
            imgViewPosterSearch.setImageResource(R.drawable.missing_image);

    }

}
