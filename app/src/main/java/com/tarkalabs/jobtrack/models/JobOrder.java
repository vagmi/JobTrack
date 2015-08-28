package com.tarkalabs.jobtrack.models;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class JobOrder {
    private String jobId;
    private Client client;
    private DateTime startDate;
    private DateTime plannedEndDate;
    private String description;

    public JobOrder(String jobId, Client client, String description, DateTime startDate, DateTime plannedEndDate) {
        this.jobId = jobId;
        this.client = client;
        this.startDate = startDate;
        this.description = description;
        this.plannedEndDate = plannedEndDate;
    }

    public JobOrder() {
    }

    public String getDescription() {
        return description;
    }

    public Client getClient() {
        return client;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getPlannedEndDate() {
        return plannedEndDate;
    }

    public static JobOrder randomJobOrder() {
        List<String> parts = Arrays.asList("Hammer Mill DFZC",
                "Impact machine Matador MJZH",
                "Calvin Transmorgifier",
                "Warp drive",
                "BFG 10K",
                "Scotty BMU Transporter",
                "PylonMet color vacuum coater",
                "Magnetic Separator MMUA");
        Client client = Client.randomClient();
        DateTime startDate= DateTime.now().plusDays(getRandom().nextInt(7));
        DateTime endDate = startDate.plusDays(getRandom().nextInt(3));
        String part = parts.get(getRandom().nextInt(parts.size()));
        Integer id = getRandom().nextInt(200) + 1234;
        String jobId = "J" + id.toString();
        return new JobOrder(jobId, client, part, startDate,endDate);
    }

    public String getJobId() {
        return jobId;
    }

    @NonNull
    private static Random getRandom() {
        return new Random();
    }
}