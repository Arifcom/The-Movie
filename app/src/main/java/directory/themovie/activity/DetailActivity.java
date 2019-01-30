package directory.themovie.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import directory.themovie.BuildConfig;
import directory.themovie.R;
import directory.themovie.modules.helper.FavoriteHelper;
import directory.themovie.modules.entity.Favorite;
import directory.themovie.modules.models.MovieModel;

import static directory.themovie.db.DatabaseContract.FavoriteColumns.CONTENT_URI;
import static directory.themovie.db.DatabaseContract.FavoriteColumns.ORIGINAL_TITLE;
import static directory.themovie.db.DatabaseContract.FavoriteColumns.OVERVIEW;
import static directory.themovie.db.DatabaseContract.FavoriteColumns.POSTER_PATH;
import static directory.themovie.db.DatabaseContract.FavoriteColumns.RELEASE_DATE;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    ImageView poster;
    TextView original_title;
    TextView release_date;
    TextView overview;
    private Favorite favorite;
    private FavoriteHelper favoriteHelper;
    public static int REQUEST_ADD = 100;
    public static int RESULT_ADD = 101;
    public static int REQUEST_UPDATE = 200;
    public static int RESULT_UPDATE = 201;
    public static int RESULT_DELETE = 301;
    public static String EXTRA_MOVIE = "extra_movie";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        toolbar = findViewById(R.id.toolbar);
        original_title = findViewById(R.id.original_title);
        poster = findViewById(R.id.poster);
        release_date = findViewById(R.id.release_date);
        overview = findViewById(R.id.overview);
        setSupportActionBar(toolbar);
        MovieModel movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        original_title.setText(movie.getOriginal_title());
        Picasso.with(this).load(BuildConfig.Image_TMDB + "w185" + movie.getPoster_path()).into(poster);
        release_date.setText(movie.getRelease_date());
        overview.setText(movie.getOverview());
    }
    @Override
    public void onClick(View view) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.favorite){
            Uri uri = getIntent().getData();
            favoriteHelper = new FavoriteHelper(this);
            favoriteHelper.open();
            if (uri != null) {
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) favorite = new Favorite(cursor);
                    cursor.close();
                }
            }
            ContentValues values = new ContentValues();
            MovieModel movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
            values.put(ORIGINAL_TITLE, movie.getOriginal_title());
            values.put(POSTER_PATH, movie.getPoster_path());
            values.put(RELEASE_DATE, movie.getRelease_date());
            values.put(OVERVIEW, movie.getOverview());
            getContentResolver().insert(CONTENT_URI, values);
            setResult(RESULT_ADD);
            item.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_star_red));
        }
        return super.onOptionsItemSelected(item);
    }
}
