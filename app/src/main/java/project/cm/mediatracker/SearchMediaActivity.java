package project.cm.mediatracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import project.cm.mediatracker.Model.Content;

public class SearchMediaActivity extends AppCompatActivity {

    public static final String MEDIA_CONTENT_IMDBID = "media_content_imdbid";
    //the URL having the json data
    String JSON_URL = "http://www.omdbapi.com/?apikey=675629b3&type=movie&s=";
    //listview object
    ListView listView;
    //the mediaContent list where we will store all the mediaContent objects after parsing json
    List<Content> mediaContentList = new ArrayList<>();
    MediaContentAdapter mediaContentAdapter;
    private AppCompatDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_media);
        Toolbar myToolbar = findViewById(R.id.tool_bar);
        delegate.getSupportActionBar().setTitle(null);
        setSupportActionBar(myToolbar);

        //initializing listview and mediaContent list
        listView = findViewById(R.id.listViewMovies);
        mediaContentAdapter = new MediaContentAdapter();

    }

    private void loadHeroList() {
        //getting the progressbar
        final ProgressBar progressBar = findViewById(R.id.progressBar);

        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray mediaContentArray = obj.getJSONArray("Search");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < mediaContentArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject mediaContentObject = mediaContentArray.getJSONObject(i);

                                    //creating a hero object and giving them the values from json object
                                            Content mediaContent = new Content(mediaContentObject.getString("imdbID"),
                                            mediaContentObject.getString("Title"),
                                            mediaContentObject.getString("Year"),
                                            mediaContentObject.getString("Poster"));

                                    //adding the hero to mediaContentList
                                    mediaContentList.add(mediaContent);
                                }

                            //adding the adapter to listview
                            listView.setAdapter(mediaContentAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_med, menu);

        MenuItem ourSearchItem = menu.findItem(R.id.app_bar_search);

        final SearchView sv = (SearchView) ourSearchItem.getActionView();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                JSON_URL = "http://www.omdbapi.com/?apikey=675629b3&type=" + getIntent().getStringExtra("media_type") + "&s=";

                String search = sv.getQuery().toString();
                search = search.replace(" ", "+");
                JSON_URL += search;
                mediaContentList = new ArrayList<>();
                //this method will fetch and parse the data
                loadHeroList();
                sv.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    class MediaContentAdapter extends BaseAdapter implements View.OnClickListener{

        LayoutInflater layoutInflater;

        public MediaContentAdapter(){
            layoutInflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mediaContentList.size();
        }

        @Override
        public Object getItem(int i) {
            return mediaContentList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=layoutInflater.inflate(R.layout.list_media_row,null);
            }

            TextView textViewName = view.findViewById(R.id.textViewTitle);
            TextView textViewYear = view.findViewById(R.id.textViewYear);
            ImageView imgPoster = view.findViewById(R.id.imgPoster);

            textViewName.setText(mediaContentList.get(i).getTitle());
            textViewYear.setText(mediaContentList.get(i).getYear());

            if (!mediaContentList.get(i).getPoster().equals("N/A"))
                Picasso.with(getApplicationContext()).load(mediaContentList.get(i).getPoster()).into(imgPoster);
            else
                imgPoster.setImageResource(R.drawable.missing_image);

            view.setTag(new Integer(i));
            view.setClickable(true);
            view.setOnClickListener(this);

            return view;
        }

        @Override
        public void onClick(View view) {
            int position = (Integer) view.getTag();


            Intent intent = new Intent(SearchMediaActivity.this, MediaContentActivity.class);
            intent.putExtra(MEDIA_CONTENT_IMDBID, mediaContentList.get(position).getCodContent());
            startActivity(intent);

        }
    }

}
