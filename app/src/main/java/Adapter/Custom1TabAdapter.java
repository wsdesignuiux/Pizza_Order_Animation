package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.BestSellerFragment;

public class Custom1TabAdapter extends FragmentPagerAdapter {

    int mNumOfTabs;

    public Custom1TabAdapter(FragmentManager fm, int i) {
        super(fm);
        this.mNumOfTabs = i;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                BestSellerFragment tab1 = new BestSellerFragment();
                return tab1;
            case 1:
                BestSellerFragment tab2 = new BestSellerFragment();
                return tab2;
            case 2:
                BestSellerFragment tab3 = new BestSellerFragment();
                return tab3;
            case 3:
                BestSellerFragment tab4 = new BestSellerFragment();
                return tab4;

                default:
                    return null;

        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
