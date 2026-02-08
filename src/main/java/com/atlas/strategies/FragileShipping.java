package com.atlas.strategies;

import com.atlas.interfaces.ShippingStrategy;
import com.atlas.models.Shipment;

// creating a strategy to be used in the switch case for sorting in "StrategySwitch" class
public class FragileShipping implements ShippingStrategy {
    @Override
    public double calculateCost(Shipment s) {
        double base = (s.getWeight() * 0.5) + (s.getDistance() * 0.1); // standard formulae
        return (base * 1.2) + 20.0; // base rate multiplied by 20% then + 20 units
    }
}