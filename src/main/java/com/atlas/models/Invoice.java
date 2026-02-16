package com.atlas.models;

import lombok.Value;

@Value
public class Invoice {
    String name;
    double weight;
    double distance;
    String destination;
    String type;
}
