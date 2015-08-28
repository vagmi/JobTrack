package com.tarkalabs.jobtrack.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tarkalabs.jobtrack.models.Plant;
import com.tarkalabs.jobtrack.ui.PlantFragment;

import java.util.List;


public class PlantFragmentPageAdapter extends FragmentPagerAdapter {
    static List<Plant> plants = Plant.sampleData();
    private final Context context;

    public PlantFragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PlantFragment.instanceFor(plants.get(position));
    }

    @Override
    public int getCount() {
        return plants.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return plants.get(position).getName();
    }
}
