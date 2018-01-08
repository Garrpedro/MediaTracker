package project.cm.mediatracker;

import android.content.Context;
import android.content.Intent;
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

    List<MediaContent> mediaContentList = new ArrayList<>();
    ListView listView;
    ListMediaAdapter listMediaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_media);

        listView = findViewById(R.id.listViewListMedia);
        listMediaAdapter = new ListMediaAdapter();

    }


    @Override
    public void onResume() {
        super.onResume();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("mediaTracker");
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mediaContentList.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    //Log.d("test", d.toString());
                    for (DataSnapshot d1 : d.getChildren()) {
                        MediaContent mediaContent = d1.getValue(MediaContent.class);
                        mediaContentList.add(mediaContent);
                    }
                }

                listView.setAdapter(listMediaAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    class ListMediaAdapter extends BaseAdapter {

        LayoutInflater layoutInflater;

        public ListMediaAdapter() {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            if (view == null) {
                view = layoutInflater.inflate(R.layout.list_media_row, null);
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

            return view;
        }
    }

}
