package directory.themovie.modules.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import directory.themovie.modules.fragments.PopularFragment;
import directory.themovie.modules.fragments.TopRatedFragment;
import directory.themovie.modules.fragments.UpComingFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int NumberOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.NumberOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PopularFragment tab1 = new PopularFragment();
                return tab1;
            case 1:
                TopRatedFragment tab2 = new TopRatedFragment();
                return tab2;
            case 2:
                UpComingFragment tab3 = new UpComingFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NumberOfTabs;
    }
}