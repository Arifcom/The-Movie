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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import directory.themovie.R;
import directory.themovie.activity.DetailActivity;
import directory.themovie.modules.adapters.MovieAdapter;
import directory.themovie.modules.decoration.SpacingItemDecoration;
import directory.themovie.modules.loader.TopRatedAsyncTaskLoader;
import directory.themovie.modules.models.MovieModel;
import directory.themovie.modules.supports.ItemClickSupport;

public class TopRatedFragment extends Fragment implements View.OnClickListener, LoaderManager.LoaderCallbacks<ArrayList<MovieModel>> {
    private RecyclerView grid_view;
    private ArrayList<MovieModel> list = new ArrayList<>();
    private ProgressBar progress_bar;
    public TopRatedFragment() {

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
        grid_view = view.findViewById(R.id.movie_grid);
        grid_view.setHasFixedSize(true);
        progress_bar = view.findViewById(R.id.progress_bar);
        Bundle bundle = new Bundle();
        getLoaderManager().initLoader(0, bundle, this);
        progress_bar.setVisibility(View.VISIBLE);

    }
    @Override
    public Loader<ArrayList<MovieModel>> onCreateLoader(int id, Bundle args) {
        return new TopRatedAsyncTaskLoader(getActivity().getApplicationContext());
    }
    @Override
    public void onLoadFinished(Loader<ArrayList<MovieModel>> loader, ArrayList<MovieModel> data) {
        list.addAll(data);
        showRecyclerList();
        progress_bar.setVisibility(View.GONE);
    }
    @Override
    public void onLoaderReset(Loader<ArrayList<MovieModel>> loader) {
        list.addAll(null);
        showRecyclerList();
    }
    private void showRecyclerList(){
        grid_view.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 3));
        grid_view.addItemDecoration(new SpacingItemDecoration(3, 10, true, 0));
        MovieAdapter listMovieAdapter = new MovieAdapter(getActivity().getApplicationContext());
        listMovieAdapter.setListMovie(list);
        grid_view.setAdapter(listMovieAdapter);
        ItemClickSupport.addTo(grid_view).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                MovieModel item = list.get(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, item);
                startActivity(intent);
            }
        });
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