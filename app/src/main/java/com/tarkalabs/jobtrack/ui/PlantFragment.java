package com.tarkalabs.jobtrack.ui;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tarkalabs.jobtrack.R;
import com.tarkalabs.jobtrack.adapters.JobOrdersAdapter;
import com.tarkalabs.jobtrack.adapters.ShiftsAdapter;
import com.tarkalabs.jobtrack.models.Plant;

import static android.support.v7.widget.LinearLayoutManager.*;

public class PlantFragment extends Fragment {

    private Plant plant;
    private RecyclerView jobOrdersView;
    private RecyclerView shiftsView;

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.plant_fragment, container, false);

        jobOrdersView = (RecyclerView) baseView.findViewById(R.id.job_order_list);
        jobOrdersView.setAdapter(new JobOrdersAdapter(getPlant().getJobOrders()));
        jobOrdersView.setLayoutManager(horizontalLayout());

        shiftsView = (RecyclerView) baseView.findViewById(R.id.shift_list);
        shiftsView.setAdapter(new ShiftsAdapter(plant.getShifts()));
        shiftsView.setLayoutManager(verticalLayout());

        return baseView;
    }

    @NonNull
    private LinearLayoutManager horizontalLayout() {
        return new LinearLayoutManager(null, HORIZONTAL, false);
    }

    @NonNull
    private LinearLayoutManager verticalLayout() {
        return new LinearLayoutManager(null, VERTICAL, false);
    }

    public static PlantFragment instanceFor(Plant plant) {
        PlantFragment fragment = new PlantFragment();
        fragment.setPlant(plant);
        return fragment;
    }
}
