package com.lucky.sweet.model.wholedishes.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.lucky.sweet.model.wholedishes.fragments.TravelExpandingFragment;
import com.lucky.sweet.model.wholedishes.lib.ExpandingViewPagerAdapter;
import com.lucky.sweet.model.wholedishes.model.Travel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Qs on 16/5/30.
 */
public class TravelViewPagerAdapter extends ExpandingViewPagerAdapter {

    List<Travel> travels;

    public TravelViewPagerAdapter(FragmentManager fm) {
        super(fm);
        travels = new ArrayList<>();
    }

    public void addAll(List<Travel> travels) {
        this.travels.addAll(travels);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        Travel travel = travels.get(position);
        return TravelExpandingFragment.newInstance(travel);
    }

    @Override
    public int getCount() {
        return travels.size();
    }

}
