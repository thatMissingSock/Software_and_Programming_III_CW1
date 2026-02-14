# The Atlas Logistics Optimisation System

## Overview

In this assignment, you will complete the implementation of `Atlas`, a centralised logistics management system designed for shipment processing and fleet auditing. `Atlas` is designed to calculate optimal shipping routes, manage a diverse fleet of vehicles, and generate billing reports based on cargo weight and distance. This project demonstrates the application of Object-Oriented Design Patterns and Functional Programming in Java 25+, using the `Gradle` build system.

+ You are provided with a skeleton project. 
+ Your goal is to apply Object-Oriented Programming (OOP) principles.
+ Utilise Java Lambdas and Java Streams to make the system functional.

## Targeted Module Learning Outcomes (MLOs)

+ MLO4 & MLO5: Realise a complex problem specification into a functional Java program.
+ MLO6: Produce OO artefacts (UML diagrams and documentation).
+ MLO7: Develop within a professional IDE (IntelliJ/Visual Studio Code) using `Gradle`.
+ MLO8: Utilise Design Patterns (Strategy, and Template Method).
+ MLO10: Demonstrate proficiency in construction and unit testing (`JUnit 5`).

## ACHIEVED Module Learning Outcomes (MLOs and where to find them)
+ "MLO4 & MLO5: Realise a complex problem specification into a functional Java program."
  + if you take a look at the whole project, you will see that I have added 3 new strategies classes as well as implemented a new factory class to help identify and sort shipments that come in regardless of upper and lower class. 
  + Additionally, to help with inventory, querying and logistic questions akin to those one may ask in SQL, I have utilised a variety of streams and lambas (found in `LogisticsManager`).
+ "MLO6: Produce OO artefacts (UML diagrams and documentation)."
  + I have written this in the .README as well as many indepth notes to help the examiners understand why I have written the code with the logic that I did.
  + Whenever I committed to the remote repository, I would write the reason for this commit to help leave a "breadcrumb" of evidence
  + I have also leave UML diagrams(s) to help explain how the atlas project works
+ "MLO7: Develop within a professional IDE (IntelliJ/Visual Studio Code) using `Gradle`."
  + I have managed to download the files from CODIO and safely extract it to my local machine, coding it on intelliJ IDEA. 
  + I have been using JAVA 25+ and GRADLE build 8.0 (of which I have needed redownload for this project)
+ "MLO8: Utilise Design Patterns (Strategy, and Template Method)."
  + This projects intention has many rules and regulations, some examples of these regulations are:
    + The weight must be between 0-1000KG
    + Destination MUST NOT be empty/null
    + If the type is FRAGIlE than the weight < 50KG
  + The project utlises strategies, templates and interfaces such as `ShippingStrategy`, this interface has the usage of:
    + "calculateCost" method which is called upon many times
    + "addProcessingFee" which is a STATIC fee, of which is not mentioned how much but can be figured out via the unit tests (5 units).
  + The project also can take in inputs of types such as `Express`, `Fragile` and `Standard`. It will then use this types to sort out the pricing using a formulae for this shipments.
    + It does this using a "factory" found in `StrategySwitchFactory` that takes in inputs, trims them and ensures that it is case insensitive. It also has a fallback if it is null (retorts to standard).
    + It will then work through each inputted string based on if they match and return the accompanying class: `ExpressShipping`, `FragileShipping` and `StandardShipping`. 
    + In these respective shipping strategy classes it will apply the formulae in which it will apply the correct pricing and return. For example with `StandardShipping`, it applies the BaseRate ((weight * 0.5) + (Distance * 0.1)) and returns.
+ "MLO10: Demonstrate proficiency in construction and unit testing (JUnit 5)."
  + The generated unit testing did not run unhindered. They required a call to the "manager" object (which was just a creation of the LogisticsManager class). 
  + Additionally, I could not run test #1 or test #2 without fixing or creating an object for test #14 despite me not even working on it. This meant creating a whole new object or even class, making it return 0.0 or making the object VOID so the unit test would 'shut up'. I believe this makes me proficient as I understand that JAVA ALWAYS needs to compile first hence why it was not letting me run a single test when there was a missing object below.
  + I HAVE YET TO CREATE A UNIT TEST




  
    


## Project Architecture

`Atlas` relies on a single-threaded, deterministic engine. You must implement the following:

A. The Pricing Engine (Strategy Pattern)

The system must calculate shipping costs based on cargo type.

Task: Implement the `ShippingStrategy` interface for different modes: 

`StandardShipping`, `ExpressShipping`, and `FragileShipping`.

Logic: Use a *Factory* to return the correct strategy based on a string input (e.g., `"EXPRESS"`).

B. Data Processing (Functional Java)

The system holds a large `List<Shipment>`. You must process this list using *Java Streams and Lambdas* (No manual `for` loops allowed for these tasks):

+ Filtering: Extract all shipments destined for a specific region.
+ Transformation: Convert a list of `Shipment` objects into a list of `Invoice` objects.
+ Aggregation: Calculate the total revenue generated by `"Express"` shipments in the 
last thirty days.

## Setup Instructions
1. **Prerequisites**: Ensure you have **JDK 25+** installed. You will also need **Gradle** and **Project Lombok**. (The provided `build.gradle` file already includes these dependencies.)

2. **Import to an IDE**: 

   - **IntelliJ**: File > Open > Select the project folder. It will detect the `build.gradle` file automatically.
   - **Eclipse**: File > Import > Gradle > Existing Gradle Project.

3. **Build**: Run the following command in your terminal:

   ```bash
   ./gradlew build
   `````

4. **Run**: To run the main simulation logic:

```bash
./gradlew run
```

## Implementation Highlights

+ Strategy Pattern: Decouples pricing logic (`Standard` vs. `Express`).
+ Streams & Lambdas: Replaces imperative loops in `LogisticsManager.java` to fulfill MLO8.

## Provided Skeleton Code

+    `com.atlas.models`: Contains `Shipment` POJO.
+    `com.atlas.interfaces`: Contains `ShippingStrategy`.
+    `com.atlas.core`: The `LogisticsManager`.
+.    `src/test`: A suite of several unit tests.

📂 File Navigation Map

+ `src/main/java`: Where you implement the classes.
  + `strategies/`: Implement `Standard`, `Express`, and `Fragile` logic here.
  + `core/`: Implement the `LogisticsManager` (Streams)
+ `src/test/java`: The validation suite.
+ `build.gradle.kts`: The Gradle Kotlin script.

## Shipping Strategy Logic

Students must implement the `calculateCost(Shipment s)` method for three distinct strategies. 
The Base Rate is calculated as:

*BaseRate=(Weight×0.50)+(Distance×0.10)*

| Strategy	| Logic / Formula	| Additional Rules |
|:--------|:-------------|:--------------|
|Standard	| BaseRate	|No additional multipliers.|
|Express	| BaseRate×1.75	| Must include the `addProcessingFee()` default method.|
|Fragile	|(BaseRate×1.2)+20.00	| Only applicable if the item weight is under 50kg.|

### Validation Rules (Lambdas)

Before a shipment can be processed, it must pass a series of validations. 
Students should implement these using Lambdas assigned to the `Validator<Shipment>` 
functional interface.

+    Weight Check: Weight must be between 0 and 1000 kg.
+    Destination Check: The destination string must not be null or empty.
+    Safety Check: If the strategy is "Fragile," the weight must not exceed 50 kg.

### Data Analytics (Streams)

In the `LogisticsManager` class, students must implement reporting features using the 
Java Streams API. These rules define the expected output for the unit tests:

A. High-Value Shipments

Identify all shipments where the calculated cost exceeds £500.00.

 + Stream Pipeline: `filter` by cost → `collect` to a `List`.

B. Regional Volume

Calculate the total weight of all shipments for a specific "Region" property found in 
the `Location` object.

  + Stream Pipeline: `filter` by region → `mapToDouble` weight → sum.

C. Unique Destinations

Provide a list of all unique cities currently in the shipping queue, sorted alphabetically.

  + Stream Pipeline: `map` to city name → `distinct` → `sorted` → `collect`.


## Testing

To execute the `JUnit 5` test suite and generate a report:

```bash
./gradlew test
```
Test reports are available at `build/reports/tests/test/index.html`.


## Effort Estimation (Nine Hours)

+ 1.5 Hours: Environment setup, reading the skeleton code, and documentation.
+ 5 Hours: Implementing strategies and Stream-based logic.
+ 2.5 Hours: Fixing failing tests, writing new tests, and final report documentation.