package directory.themovie.modules.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import directory.themovie.R;

public class DetailFragment extends Fragment {

    ImageView poster;
    TextView original_title;
    TextView release_date;
    TextView overview;

    public static String EXTRA_ORIGINAL_TITLE = "extra_original_title";
    public static String EXTRA_POSTER = "extra_poster";
    public static String EXTRA_RELEASE_DATE = "extra_release_date";
    public static String EXTRA_OVERVIEW = "extra_overview";

    public DetailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        original_title = (TextView) view.findViewById(R.id.original_title);
        poster = (ImageView) view.findViewById(R.id.poster);
        release_date = (TextView) view.findViewById(R.id.release_date);
        overview = (TextView) view.findViewById(R.id.overview);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        original_title.setText(getArguments().getString(EXTRA_ORIGINAL_TITLE));
        Picasso.with(getActivity().getApplicationContext()).load("https://image.tmdb.org/t/p/w185" + getArguments().getString(EXTRA_POSTER)).into(poster);
        release_date.setText(getArguments().getString(EXTRA_RELEASE_DATE));
        overview.setText(getArguments().getString(EXTRA_OVERVIEW));
    }

}
