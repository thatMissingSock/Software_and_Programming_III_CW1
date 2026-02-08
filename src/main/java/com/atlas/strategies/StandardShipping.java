package com.atlas.strategies;

import com.atlas.interfaces.ShippingStrategy;
import com.atlas.models.Shipment;

// creating a strategy to be used in the switch case for sorting in "StrategySwitch" class
public class StandardShipping implements ShippingStrategy {
    @Override
    public double calculateCost(Shipment s) {
        return (s.getWeight() * 0.5) + (s.getDistance() * 0.1); // simple formulae since it's just base shipping (i.e. no rules)
    }
}