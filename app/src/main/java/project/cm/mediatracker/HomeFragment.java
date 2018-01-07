package project.cm.mediatracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        Button btMovies = view.findViewById(R.id.btMovies);
        Button btSeries = view.findViewById(R.id.btSeries);
        Button btAnimes = view.findViewById(R.id.btAnimes);


        int width = getResources().getDisplayMetrics().widthPixels;
        int hei = getResources().getDisplayMetrics().heightPixels / 3;

        /*btMovies.setLayoutParams(new RelativeLayout.LayoutParams(width, hei));
        btSeries.setLayoutParams(new RelativeLayout.LayoutParams(width, hei));
        btAnimes.setLayoutParams(new RelativeLayout.LayoutParams(width, hei));*/

        btMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuFragment menuFragment = new MenuFragment();
                Bundle arguments = new Bundle();
                arguments.putString("media_type", "movie");
                menuFragment.setArguments(arguments);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, menuFragment)
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(null)
                        .commit();

            }
        });

        btSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuFragment menuFragment = new MenuFragment();
                Bundle arguments = new Bundle();
                arguments.putString("media_type", "series");
                menuFragment.setArguments(arguments);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, menuFragment)
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(null)
                        .commit();
            }
        });

        btAnimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuFragment menuFragment = new MenuFragment();
                Bundle arguments = new Bundle();
                arguments.putString("media_type", "series");
                menuFragment.setArguments(arguments);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, menuFragment)
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(null)
                        .commit();

            }
        });
        return view;
    }

}
