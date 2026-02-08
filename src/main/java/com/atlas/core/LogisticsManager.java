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

    public List<Shipment> getMasterList() { // creating a method to call the masterList
        return masterList;
    }

    // we need to create a new object testing for null and other things
    public double calculateSingleCost(Shipment s) { // changed the name to match the test
        if (s == null) throw new NullPointerException("Shipment cannot be null, does not make sense"); // null test
        return StrategySwitch.fromType(s.getType()).calculateCost(s); // use the calculateCost after returning the types
    }


    /**
     * MLO8: Use of Streams to filter and aggregate data.
     * Find the total weight of all shipments for a specific destination.
     */
    public double getTotalWeightForDestination(String city) {
        //TODO
        return 0.0;
    }

}
