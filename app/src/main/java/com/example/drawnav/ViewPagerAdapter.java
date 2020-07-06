package com.example.drawnav;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> listfragment = new ArrayList<>();
    private final List<String> liststring = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //Get the data item associated with the specified position in the data set
    @Override
    public Fragment getItem(int position) {
        return listfragment.get(position);
    }

    //How many items are in the data set represented by this Adapter
    @Override
    public int getCount() {
        return liststring.size();
    }

    public CharSequence getPageTitle(int position) {
        return liststring.get(position);
    }

    public void addfragment (Fragment fragment, String title){
        listfragment.add(fragment);
        liststring.add(title);
    }
}
