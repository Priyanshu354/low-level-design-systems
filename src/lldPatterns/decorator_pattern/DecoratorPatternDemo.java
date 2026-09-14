package lldPatterns.decorator_pattern;

// ==============================
// WHY DECORATOR PATTERN?
// ==============================
// 1. Add behavior dynamically
// 2. Avoid class explosion
// 3. Combine multiple optional features
// 4. Prefer composition over inheritance
// 5. Keep the original class unchanged
//
// Mental Model:
//
// SimpleCoffee
//      ↓
// MilkDecorator
//      ↓
// SugarDecorator
//      ↓
// CreamDecorator
//
// Each decorator wraps the previous object
// and adds its own behavior.


// ==============================
// STEP 1: Component Interface
// ==============================
// Common interface for both the original
// object and all decorators.

interface Coffee {

    double getCost();

    String getDescription();
}


// ==============================
// STEP 2: Concrete Component
// ==============================
// The original/basic object.

class SimpleCoffee implements Coffee {

    @Override
    public double getCost() {
        return 50;
    }

    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
}


// ==============================
// STEP 3: Base Decorator
// ==============================
// Holds a reference to the Component.
//
// IMPORTANT:
// Decorator HAS-A Coffee
//
// This is composition.

abstract class CoffeeDecorator implements Coffee {

    protected Coffee coffee;

    CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}


// ==============================
// STEP 4: Concrete Decorators
// ==============================
// Each decorator adds its own behavior.

class MilkDecorator extends CoffeeDecorator {

    MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 20;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + Milk";
    }
}


class SugarDecorator extends CoffeeDecorator {

    SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 10;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + Sugar";
    }
}


class CreamDecorator extends CoffeeDecorator {

    CreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 30;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + Cream";
    }
}


// ==============================
// STEP 5: CLIENT / MAIN CLASS
// ==============================
// Client can dynamically combine
// multiple decorators.
//
// No need to create classes like:
// MilkSugarCreamCoffee
//
// Instead, we simply wrap objects.

class DecoratorPatternDemo {

    public static void main(String[] args) {

        // Basic coffee
        Coffee coffee = new SimpleCoffee();

        System.out.println(coffee.getDescription());
        System.out.println("Cost: ₹" + coffee.getCost());


        // Add Milk
        coffee = new MilkDecorator(coffee);

        System.out.println("\n" + coffee.getDescription());
        System.out.println("Cost: ₹" + coffee.getCost());


        // Add Sugar
        coffee = new SugarDecorator(coffee);

        System.out.println("\n" + coffee.getDescription());
        System.out.println("Cost: ₹" + coffee.getCost());


        // Add Cream
        coffee = new CreamDecorator(coffee);

        System.out.println("\n" + coffee.getDescription());
        System.out.println("Cost: ₹" + coffee.getCost());


        // Final result:
        //
        // Simple Coffee
        // + Milk
        // + Sugar
        // + Cream
        //
        // ₹50 + ₹20 + ₹10 + ₹30 = ₹110
    }
}
