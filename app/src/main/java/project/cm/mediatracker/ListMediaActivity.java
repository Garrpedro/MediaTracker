package project.cm.mediatracker;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import project.cm.mediatracker.Model.Content;
import project.cm.mediatracker.Model.MediaContent;

public class ListMediaActivity extends AppCompatActivity {

    ListView listView;
    MediaContent mediaContent = new MediaContent();
    ListMediaAdapter listMediaAdapter;
    List<Content> content = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_media);

        listView = findViewById(R.id.listViewListMedia);
        listMediaAdapter  = new ListMediaAdapter();
        listView.setAdapter(listMediaAdapter);

    }


    @Override
    public void onResume() {
        super.onResume();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("mediaTracker");
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                content.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    for (DataSnapshot d1 : d.getChildren()) {
                        MediaContent mediaContent = d1.getValue(MediaContent.class);
                        SearchMediaContent(mediaContent.getImdbId());
                    }
                }

                listMediaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    public void SearchMediaContent(String imdbId)
    {
        // Send data
        try
        {
            String url = "http://www.omdbapi.com/?apikey=675629b3&i=" + imdbId;

            JsonObjectRequest jsonRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // the response is already constructed as a JSONObject!
                            try {
                                //response = response.getJSONObject("args");
                                String title = response.getString("Title");
                                String year = response.getString("Year");
                                String poster = response.getString("Poster");

                                content.add(new Content(title, year, poster));

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

    class ListMediaAdapter extends BaseAdapter {

        LayoutInflater layoutInflater;

        public ListMediaAdapter() {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return content.size();
        }

        @Override
        public Object getItem(int i) {
            return content.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = layoutInflater.inflate(R.layout.list_media_row, null);
            }

            TextView textViewName = view.findViewById(R.id.textViewTitle);
            TextView textViewYear = view.findViewById(R.id.textViewYear);
            ImageView imgPoster = view.findViewById(R.id.imgPoster);

            textViewName.setText(content.get(i).getTitle());
            textViewYear.setText(content.get(i).getYear());

            if (!content.get(i).getPoster().equals("N/A"))
                Picasso.with(getApplicationContext()).load(content.get(i).getPoster()).into(imgPoster);
            else
                imgPoster.setImageResource(R.drawable.missing_image);

            view.setTag(new Integer(i));
            view.setClickable(true);

            return view;

        }
    }

}
