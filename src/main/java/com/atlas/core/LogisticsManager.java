package com.atlas.core;

import java.util.ArrayList;
import java.util.List;
import com.atlas.models.Shipment;
import com.atlas.strategies.StrategySwitch;

// Some boilerplate code needs to be generated.

public class LogisticsManager {

/*
BELOW is a chunk of code I THINK is needed to satisfy the test code so it can actually run. All it is, is initialising a
masterList of shipments and its 'abilities'
 */
    // I need to add an 'addShipment' so the tests that use those can run
    private final List<Shipment> masterList = new ArrayList<>(); // initialising it

    public void addShipment(Shipment shipment) { // creating a method to add shipments to the masterList
        masterList.add(shipment);
    }

    public List<Shipment> getMasterShipmentList() { // creating a method to call the masterList
        return masterList;
    }

    // we need to create a new object testing for null and other things
    public double calculateSingleCost(Shipment s) { // changed the name to match the test
        if (s == null) throw new NullPointerException("Shipment cannot be null, does not make sense"); // null test
        return StrategySwitch.fromType(s.getType()).calculateCost(s); // use the calculateCost after returning the types
    }

    // TODO: CHANGE BELOW as it is temporary code so I can run the test units
    public List<String> getUniqueDestinationsSorted() { // theoretically it should run empty?
        ArrayList<String> MyList = new ArrayList<String>();
        return MyList;
    }

    public List<Shipment> getHighValueShipments(Number n) { // TODO:this needs to return a list of shipments above £500.00 via STREAMS
        return null;
    }

    public double calculateAverageShippingCost() { // TODO:this needs to return the average shipping cost (N.B. UNSURE OF IF IT IS STREAMS/LAMBDA)
        return 0.0;
    }

    public void processShipment (Shipment s) { // TODO:this needs to process shipments in a multitude of ways, LEAVE TILL LAST??
        return;
    }

    /**
     * MLO8: Use of Streams to filter and aggregate data.
     * Find the total weight of all shipments for a specific destination.
     */
    // return the sum weight for a specific destination but must be: case insensitive, return 0.0 if no matches USING STREAMS
    public double getTotalWeightForDestination(String city) {

        if (city == null) return 0.0;
        else return masterList.stream()
                .filter(s->s != null && s.getDestination() != null) // despite the if statement, it's a double check
                .filter(s->s.getDestination().equalsIgnoreCase(city)) // ignores UPPERcases
                .mapToDouble(Shipment::getWeight) // we only need the weight of the shipment
                .sum();
    }

}
