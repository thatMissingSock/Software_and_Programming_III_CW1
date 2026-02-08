package com.atlas.core;

import java.util.List;
import com.atlas.models.Shipment;
import com.atlas.strategies.StrategySwitch;

// Some boilerplate code needs to be generated.

public class LogisticsManager {

    // we need to create a new object testing for null and other things
    public double calculateSingleCost(Shipment s) { // changed the name to match the test
        if (s == null) throw new NullPointerException("Shipment cannot be null, does not make sense"); // null test
        return StrategySwitch.fromType(s.getType()).calculateCost(s); // use the calculateCost after returning the types
    }

    //


    private List<Shipment> masterShipmentList;

    /**
     * MLO8: Use of Streams to filter and aggregate data.
     * Find the total weight of all shipments for a specific destination.
     */
    public double getTotalWeightForDestination(String city) {
        //TODO
        return 0.0;
    }

}
