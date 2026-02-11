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

    public List<String> getUniqueDestinationsSorted() { // theoretically it should run empty?
        return masterList.stream() //starting the stream
                .map(Shipment::getDestination) // we only need the city local
                .distinct()
                .sorted() // should do it in ASC
                .toList(); // collect the results into a list
    }

    public List<Shipment> getHighValueShipments(double n) { // I did originally mapToDouble but this seemed easier, if
        // you filter and use the 'Shipment' method of calculating a cost. You can then just add to a list
        return masterList.stream() // start the stream
                .filter(s->calculateSingleCost(s)>n)
                .toList();
    }

    public double calculateAverageShippingCost() {
        return masterList.stream()
                .mapToDouble(this::calculateSingleCost) // for every "instance" (shipment) we call upon the method
                .average() // calcs the average
                .orElse(0.0); // else returns the 0.0 incase the list is empty
    }

    public void processShipment (Shipment s) { // TODO:this needs to process shipments in a multitude of ways, LEAVE TILL LAST??
        /*
        The main 'point' of this method is to ensure this whole 'program' does not burn and crash so this needs to be
        split into parts:
        1. throw an error in case the string is null
        2. ensuring that the weight is between 0-1000KG
        3. use LAMBDAs to check the destination and ensure IT is not empty/null
        4. ensure that if the String type == FRAGILE (case dont matter) then it must be 50KG else throw an illegalArg error
        5. IF it passes all of these then we add it to the queue, question is do I do it as a switch, if else or just seperate statements?
         */

        // 1.
        if (s == null) throw new IllegalArgumentException("Shipment cannot be null, please check details.");

        // 2.
        double weight =s.getWeight();
        if (weight < 0.0 || weight > 1000.0) { // check for weights on both sides of the spectrum
            throw  new IllegalArgumentException("Weight is invalid, it must be between 0-1000KG. Please double check details.");
        }

        // 3.
        String destination = s.getDestination();
        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Destination cannot be null or empty. Please check your details.");
        }

        // 4.
        String type = s.getType(); // asigns the same type before hand
        if (type != null && type.equalsIgnoreCase("Fragile") && weight > 50.0) { // checks to make sure the type is not null (if it was checking for null then it would not be effective), is fragile and above 50KG
            throw new IllegalArgumentException("Fragile shipments must be under 50KG.");
        }
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
