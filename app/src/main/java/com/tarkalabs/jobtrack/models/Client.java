package com.tarkalabs.jobtrack.models;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Client {
    private String name;

    public static List<Client> CLIENTS = Arrays.asList(
            new Client("Los Pollos Hermanos"),
            new Client("Acme Inc."),
            new Client("Nestle Spa."),
            new Client("Kissan foods."),
            new Client("Vamanos Pest Control"),
            new Client("Madrigal Electromotive GmbH"),
            new Client("Lavandería Brillante")
    );

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Client randomClient() {
        Random rand = new Random(System.currentTimeMillis()); // would make this static to the class
        return CLIENTS.get(rand.nextInt(CLIENTS.size()));
    }
}
