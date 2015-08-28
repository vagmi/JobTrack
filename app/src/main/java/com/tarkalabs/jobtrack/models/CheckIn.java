package com.tarkalabs.jobtrack.models;

import org.joda.time.DateTime;

import java.util.Date;

public class CheckIn {
    private Employee employee;
    private DateTime checkInDate;

    public CheckIn() {
    }

    public CheckIn(Employee employee, DateTime checkInDate) {
        this.employee = employee;
        this.checkInDate = checkInDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public DateTime getCheckInDate() {
        return checkInDate;
    }
}
