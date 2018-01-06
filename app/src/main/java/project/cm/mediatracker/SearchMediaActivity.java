package project.cm.mediatracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchMediaActivity extends AppCompatActivity {
    String olateste = "fodasse";

    TextView txtViewTitleSearch, txtViewYearSearch, txtViewGenreSearch, txtViewReleasedSearch, txtViewPlotSearch;
    ImageView imgViewPosterSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_media);

        txtViewTitleSearch = findViewById(R.id.txtViewTitleSearch);
        txtViewYearSearch = findViewById(R.id.txtViewYearSearch);
        txtViewGenreSearch = findViewById(R.id.txtViewGenreSearch);
        txtViewReleasedSearch = findViewById(R.id.txtViewReleasedSearch);
        txtViewPlotSearch = findViewById(R.id.txtViewPlotSearch);

        imgViewPosterSearch = findViewById(R.id.imgViewPosterSearch);

        SearchMediaContent();

        olateste = getIntent().getStringExtra("media_type");
    }

    public  void  SearchMediaContent()
    {
        // Send data
        try
        {
            String url = "http://www.omdbapi.com/?apikey=675629b3&i=";

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

                                txtViewTitleSearch.setText(title);
                                txtViewYearSearch.setText(year);
                                txtViewGenreSearch.setText(genre);
                                txtViewReleasedSearch.setText(released);
                                txtViewPlotSearch.setText(plot);

                                if (!poster.equals("N/A"))
                                    Picasso.with(getApplicationContext()).load(poster).into(imgViewPosterSearch);
                                else
                                    imgViewPosterSearch.setImageResource(R.drawable.missing_image);

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
}
