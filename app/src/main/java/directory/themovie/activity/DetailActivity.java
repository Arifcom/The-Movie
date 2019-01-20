package directory.themovie.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import directory.themovie.BuildConfig;
import directory.themovie.R;
import directory.themovie.modules.adapters.PagerAdapter;
import directory.themovie.modules.models.MovieModel;


public class DetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
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
        tabLayout = findViewById(R.id.tab_layout);
        original_title = findViewById(R.id.original_title);
        poster = findViewById(R.id.poster);
        release_date = findViewById(R.id.release_date);
        overview = findViewById(R.id.overview);
        setSupportActionBar(toolbar);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.popular)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.top_rated)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.up_coming)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.now_playing)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        MovieModel movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        original_title.setText(movie.getOriginal_title());
        Picasso.with(this).load(BuildConfig.Image_TMDB + "w185" + movie.getPoster_path()).into(poster);
        release_date.setText(movie.getRelease_date());
        overview.setText(movie.getOverview());
    }
}
