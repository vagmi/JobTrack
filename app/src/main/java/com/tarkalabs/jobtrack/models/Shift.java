package com.tarkalabs.jobtrack.models;


import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Shift {
    private DateTime shiftDate;
    private int shiftNumber;
    private List<CheckIn> checkIns;

    public Shift() {
    }

    public Shift(DateTime shiftDate, int shiftNumber) {
        this.shiftDate = shiftDate;
        this.shiftNumber = shiftNumber;
    }

    public DateTime getShiftDate() {
        return shiftDate;
    }

    public int getShiftNumber() {
        return shiftNumber;
    }

    public List<CheckIn> getCheckIns() {
        return checkIns;
    }

    public void setCheckIns(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public static List<Shift> generateShifts(int days) {
        List<Shift> shifts = new ArrayList<Shift>();
        for(int i=0; i<days; i++) {
            DateTime shiftDate = DateTime.now().plusDays(i-1).withTimeAtStartOfDay();
            for(int j=0; j<2; j++) {
                List<Employee> employees = Employee.sampleEmployees(3);
                List<CheckIn> checkIns = new ArrayList<CheckIn>();


                for (Employee e : employees) {
                    if (shiftDate.isBefore(DateTime.now())) {
                        DateTime checkInTime = shiftDate.plusHours((j + 1) * 6);
                        checkIns.add(new CheckIn(e, checkInTime));
                    } else {
                        checkIns.add(new CheckIn(e, null));
                    }
                }

                Shift shift = new Shift(shiftDate, j+1);
                shift.setCheckIns(checkIns);
                shifts.add(shift);
            }
        }
        return shifts;
    }
}
