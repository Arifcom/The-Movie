package directory.themoviefavorite;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import directory.themoviefavorite.db.DatabaseContract;
import directory.themoviefavorite.modules.adapter.FavoriteAdapter;
import static directory.themoviefavorite.db.DatabaseContract.FavoriteColumns.CONTENT_URI;


public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {
    private FavoriteAdapter favoriteAdapter;
    ListView lvFavorite;
    private final int LOAD_FAVORITE_ID = 110;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_card);
        getSupportActionBar().setTitle("The Movie Favorite List");
        lvFavorite = (ListView) findViewById(R.id.lv_favorite);
        favoriteAdapter = new FavoriteAdapter(this, null, true);
        lvFavorite.setAdapter(favoriteAdapter);
        getSupportLoaderManager().initLoader(LOAD_FAVORITE_ID, null, this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(LOAD_FAVORITE_ID, null, this);
    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, CONTENT_URI, null, null, null, null);
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        favoriteAdapter.swapCursor(data);
    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        favoriteAdapter.swapCursor(null);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}