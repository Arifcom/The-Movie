package directory.themovie.modules.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import directory.themovie.R;
import directory.themovie.modules.adapters.MovieAdapter;
import directory.themovie.modules.loader.NowPlayingAsyncTaskLoader;
import directory.themovie.modules.models.MovieModel;

public class NowPlayingFragment extends Fragment implements View.OnClickListener, LoaderManager.LoaderCallbacks<ArrayList<MovieModel>> {
    GridView grid_view;
    MovieAdapter adapter;
    private ProgressBar progress_bar;
    public NowPlayingFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.movie_grid, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MovieAdapter(getActivity().getApplicationContext());
        adapter.notifyDataSetChanged();
        grid_view = (GridView) view.findViewById(R.id.movie_grid);
        progress_bar = (ProgressBar) view.findViewById(R.id.progress_bar);
        grid_view.setAdapter(adapter);
        Bundle bundle = new Bundle();
        getLoaderManager().initLoader(0, bundle, this);
        progress_bar.setVisibility(View.VISIBLE);
        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                MovieModel item = (MovieModel) parent.getItemAtPosition(position);
//                Toast.makeText(getActivity().getApplicationContext(), "Movie original name : " +item.getOriginal_title(), Toast.LENGTH_LONG ).show();
                DetailFragment detail = new DetailFragment();
                Bundle object_bundle = new Bundle();
                object_bundle.putString(DetailFragment.EXTRA_ORIGINAL_TITLE, item.getOriginal_title());
                object_bundle.putString(DetailFragment.EXTRA_POSTER, item.getPoster_path());
                object_bundle.putString(DetailFragment.EXTRA_RELEASE_DATE, item.getRelease_date());
                object_bundle.putString(DetailFragment.EXTRA_OVERVIEW, item.getOverview());
                detail.setArguments(object_bundle);
                FragmentManager fragment_manager = getFragmentManager();
                if (fragment_manager!= null) {
                    FragmentTransaction fragment_transaction = fragment_manager.beginTransaction();
                    fragment_transaction.replace(R.id.movie_container, detail, DetailFragment.class.getSimpleName());
                    fragment_transaction.addToBackStack(null);
                    fragment_transaction.commit();
                }
            }
        });
    }
    @Override
    public Loader<ArrayList<MovieModel>> onCreateLoader(int id, Bundle args) {
        return new NowPlayingAsyncTaskLoader(getActivity().getApplicationContext());
    }
    @Override
    public void onLoadFinished(Loader<ArrayList<MovieModel>> loader, ArrayList<MovieModel> data) {
        adapter.setData(data);
        progress_bar.setVisibility(View.GONE);
    }
    @Override
    public void onLoaderReset(Loader<ArrayList<MovieModel>> loader) {
        adapter.setData(null);
    }
    @Override
    public void onClick(View view) {

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView search_view = (SearchView) MenuItemCompat.getActionView(searchItem);
        search_view.setQueryHint(getResources().getString(R.string.search_hint));
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchFragment search = new SearchFragment();
                Bundle object_bundle = new Bundle();
                object_bundle.putString(SearchFragment.EXTRA_QUERY, query);
                search.setArguments(object_bundle);
                FragmentManager fragment_manager = getFragmentManager();
                if (fragment_manager!= null) {
                    FragmentTransaction fragment_transaction = fragment_manager.beginTransaction();
                    fragment_transaction.replace(R.id.movie_container, search, SearchFragment.class.getSimpleName());
                    fragment_transaction.addToBackStack(null);
                    fragment_transaction.commit();
                }
                search_view.clearFocus();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.setting){
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
