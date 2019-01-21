package directory.themovie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import directory.themovie.BuildConfig;
import directory.themovie.R;
import directory.themovie.modules.models.MovieModel;


public class DetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView poster;
    TextView original_title;
    TextView release_date;
    TextView overview;
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
}
