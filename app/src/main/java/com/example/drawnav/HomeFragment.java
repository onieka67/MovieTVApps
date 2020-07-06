package com.example.drawnav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class HomeFragment extends Fragment {
    View view;
    private TabLayout tb;
    private ViewPager vp;
    private ViewPagerAdapter a;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        tb = view.findViewById(R.id.idtab);
        vp = view.findViewById(R.id.idviewpg);
        //Return the FragmentManager for interacting with fragments associated with this activity
//        a = new ViewPagerAdapter(getFragmentManager());
        a = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        //add fragment
        a.addfragment(new FragmentMovie(),getResources().getString(R.string.movie));
        a.addfragment(new FragmentTv(),getResources().getString(R.string.tv));

        //Sets the data behind this ListView.
        vp.setAdapter(a);
        //If you're using a ViewPager together with this layout, you can call setupWithViewPager(ViewPager) to link the two together.
        tb.setupWithViewPager(vp);
        tb.getTabAt(0).setIcon(R.drawable.ic_movie);
        tb.getTabAt(1).setIcon(R.drawable.ic_tv);
        return view;
    }
}
