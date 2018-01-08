package project.cm.mediatracker;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {


    String mediaType;


    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        Button btSearch = view.findViewById(R.id.btSearch);
        Button btWantWatch = view.findViewById(R.id.btWantWatch);
        Button btWatching = view.findViewById(R.id.btWatching);
        Button btGivenUP = view.findViewById(R.id.btGivenUP);
        Button btWatched = view.findViewById(R.id.btWatched);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mediaType = getArguments().getString("media_type");
        }


        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchMediaActivity.class);
                intent.putExtra("media_type", mediaType);
                startActivity(intent);
            }
        });

        btWantWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListMediaActivity.class);

                intent.putExtra("media_type", mediaType);
                intent.putExtra("option", "wantWatch");
                startActivity(intent);
            }
        });
        btWatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListMediaActivity.class);
                intent.putExtra("media_type", mediaType);
                intent.putExtra("option", "watching");
                startActivity(intent);
            }
        });
        btGivenUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListMediaActivity.class);
                intent.putExtra("media_type", mediaType);
                intent.putExtra("option", "givepUP");
                startActivity(intent);
            }
        });
        btWatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListMediaActivity.class);
                intent.putExtra("media_type", mediaType);
                intent.putExtra("option", "watched");
                ;
                startActivity(intent);
            }
        });


        return view;
    }


}
