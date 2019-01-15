package directory.themovie.modules.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import directory.themovie.R;
import directory.themovie.modules.adapters.PopularAdapter;
import directory.themovie.modules.loader.PopularAsyncTaskLoader;
import directory.themovie.modules.models.PopularModel;

public class PopularFragment extends Fragment implements View.OnClickListener, LoaderManager.LoaderCallbacks<ArrayList<PopularModel>> {

    GridView grid_view;
    PopularAdapter adapter;

    public PopularFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.popular_grid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new PopularAdapter(getActivity().getApplicationContext());
        adapter.notifyDataSetChanged();
        grid_view = (GridView) view.findViewById(R.id.popular_grid);

        grid_view.setAdapter(adapter);

        Bundle bundle = new Bundle();

        getLoaderManager().initLoader(0, bundle, this);
    }

    //Fungsi ini yang akan menjalankan proses myasynctaskloader
    @Override
    public Loader<ArrayList<PopularModel>> onCreateLoader(int id, Bundle args) {
        return new PopularAsyncTaskLoader(getActivity().getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<PopularModel>> loader, ArrayList<PopularModel> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<PopularModel>> loader) {
        adapter.setData(null);

    }

    @Override
    public void onClick(View view) {

    }
}
