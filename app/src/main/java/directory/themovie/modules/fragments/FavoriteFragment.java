package directory.themovie.modules.fragments;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import directory.themovie.R;
import directory.themovie.adapter.FavoriteAdapter;
import static directory.themovie.db.DatabaseContract.FavoriteColumns.CONTENT_URI;

public class FavoriteFragment extends Fragment {
    RecyclerView rvFavorite;
    ProgressBar progressBar;
    private Cursor list;
    private FavoriteAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.favorite_card, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavorite = (RecyclerView) view.findViewById(R.id.rv_favorite);
        rvFavorite.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        rvFavorite.setHasFixedSize(true);
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        adapter = new FavoriteAdapter(getActivity());
        adapter.setListNotes(list);
        rvFavorite.setAdapter(adapter);
        new LoadNoteAsync().execute();
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    private class LoadNoteAsync extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }
        @Override
        protected Cursor doInBackground(Void... voids) {
            return getActivity().getContentResolver().query(CONTENT_URI, null, null, null, null);
        }
        @Override
        protected void onPostExecute(Cursor notes) {
            super.onPostExecute(notes);
            progressBar.setVisibility(View.GONE);

            list = notes;
            adapter.setListNotes(list);
            adapter.notifyDataSetChanged();

            if (list.getCount() == 0) {
                showSnackbarMessage("Tidak ada data saat ini");
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    private void showSnackbarMessage(String message) {
        Snackbar.make(rvFavorite, message, Snackbar.LENGTH_SHORT).show();
    }
}
