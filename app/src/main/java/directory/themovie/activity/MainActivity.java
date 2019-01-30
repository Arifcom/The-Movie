package directory.themovie.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import directory.themovie.R;
import directory.themovie.modules.adapters.PagerAdapter;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tab_layout);
        setSupportActionBar(toolbar);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.popular)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.top_rated)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.up_coming)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.now_playing)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.favorite)));
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
    }
}
