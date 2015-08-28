package com.tarkalabs.jobtrack.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Plant {
    private String name;
    private List<JobOrder> jobOrders;
    private List<Shift> shifts;

    public Plant() {
        jobOrders = new ArrayList<JobOrder>();
        shifts = new ArrayList<Shift>();
    }

    public Plant(String name) {
        this();
        this.name = name;
    }

    public Plant(String name, List<JobOrder> jobOrders, List<Shift> shifts) {
        this(name);
        this.jobOrders = jobOrders;
        this.shifts = shifts;
    }

    public String getName() {
        return name;
    }

    public List<JobOrder> getJobOrders() {
        return jobOrders;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public static List<Plant> sampleData() {
        List<Plant> plants = Arrays.asList(
                new Plant("Sheet Metal", randomJobOrders(), Shift.generateShifts(5)),
                new Plant("Die Cast", randomJobOrders(), Shift.generateShifts(5)),
                new Plant("Painting", randomJobOrders(), Shift.generateShifts(5)),
                new Plant("Assembly", randomJobOrders(), Shift.generateShifts(5))
        );

        return plants;
    }

    private static List<JobOrder> randomJobOrders() {
        List<JobOrder> jobOrders;
        jobOrders = Arrays.asList(JobOrder.randomJobOrder(), JobOrder.randomJobOrder(), JobOrder.randomJobOrder(), JobOrder.randomJobOrder(), JobOrder.randomJobOrder());
        Collections.sort(jobOrders, new Comparator<JobOrder>() {
            @Override
            public int compare(JobOrder lhs, JobOrder rhs) {
                return lhs.getStartDate().compareTo(rhs.getStartDate());
            }
        });
        return jobOrders;
    }
}
