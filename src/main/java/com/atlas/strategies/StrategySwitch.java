package com.atlas.strategies;

import com.atlas.interfaces.ShippingStrategy;

/*
The test cases, from what I gather, make it seem like there is a 'tag' name 'type' that we can use to help identify what
we should be charging customers/packages. The issue with that is that it would require a really long if/else block or a
switch statement with a default, defaulting to the standard shipping price.
 */
public class StrategySwitch {
    private StrategySwitch() {}

    public static ShippingStrategy fromType(String type)  { // as the units seem to be in string instead of numbers
        if (type == null) return new StandardShipping(); // can't have a null, so we return normal

        return switch (type.trim().toLowerCase()) { // need to trim stuff and make everything homogenous
            case "standard" -> new StandardShipping();
            case "express" -> new ExpressShipping();
            case "fragile" -> new FragileShipping();
            default -> new StandardShipping(); // in case the first if statment somehow didn't catch everything
        };
    }
}
