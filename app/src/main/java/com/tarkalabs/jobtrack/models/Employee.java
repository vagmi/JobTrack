package com.tarkalabs.jobtrack.models;

import com.tarkalabs.jobtrack.R;

import org.hashids.Hashids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Employee {
    private final int drawable;
    private String name;
    private String employeeCode;

    public static List<Employee> employeeList = Arrays.asList(
            new Employee("Walter White", R.mipmap.walter_white),
            new Employee("Jesse Pinkman", R.mipmap.jesse_pinkman),
            new Employee("Saul Goodman", R.mipmap.saul_goodman),
            new Employee("Gustavo Fring", R.mipmap.gus_fring),
            new Employee("Skyler White", R.mipmap.skyler_white),
            new Employee("Hank Schrader", R.mipmap.hank_schrader),
            new Employee("Mike Ehrmantraut", R.mipmap.mike_ehrmantraut)
    );

    public Employee(String name, int drawable) {
        this.name = name;
        Hashids hashids = new Hashids("a very complicated salt");
        Integer id = new Random().nextInt(500) + 1234;
        this.employeeCode = "I" + id.toString();
        this.drawable = drawable;
    }

    public int getDrawable() {
        return drawable;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public static List<Employee> sampleEmployees(int size) {
        ArrayList<Employee> clonedList = new ArrayList<Employee>(employeeList.size());
        for(Employee employee: employeeList) { clonedList.add(employee); }

        Random rand = new Random(System.currentTimeMillis()); // would make this static to the class
        List subsetList = new ArrayList<Employee>(size);
        for (int i = 0; i < size; i++) {
            subsetList.add(clonedList.remove(rand.nextInt(clonedList.size())));
        }

        return subsetList;
    }
}
