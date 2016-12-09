package unifytech.foodbox.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import unifytech.foodbox.FoodItemList;
import unifytech.foodbox.data.FoodItems;

public class FoodMenuPagerAdapter extends FragmentStatePagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[]{"Recommended", "Veg", "Non-Veg", "Drinks & snakes"};

    public FoodMenuPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return FoodItemList.newInstance(FoodItems.RECOMMENDED_ITEMS);
        } else if (position == 1) {
            return FoodItemList.newInstance(FoodItems.VEG_ITEMS);
        } else if (position == 2) {
            return FoodItemList.newInstance(FoodItems.NONVEG_ITEMS);
        } else {
            return FoodItemList.newInstance(FoodItems.SNACKS_ITEMS);
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
