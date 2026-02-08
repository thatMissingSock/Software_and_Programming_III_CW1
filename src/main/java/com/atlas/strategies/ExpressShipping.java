package com.atlas.strategies;

import com.atlas.interfaces.ShippingStrategy;
import com.atlas.models.Shipment;

// creating a strategy to be used in the switch case for sorting in "StrategySwitch" class
public class ExpressShipping implements ShippingStrategy {
    @Override
    public double calculateCost(Shipment s) {
        double base = (s.getWeight() * 0.5) + (s.getDistance() * 0.1); // initial formulae of BR
        double cost = base * 1.75; // multiplying by 1.75 since its express as per the README
        return addProcessingFee(cost); // add the fee since its express
    }
}
