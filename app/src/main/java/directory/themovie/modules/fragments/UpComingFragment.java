package directory.themovie.modules.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import directory.themovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpComingFragment extends Fragment {


    public UpComingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.up_coming_grid, container, false);
    }

}
