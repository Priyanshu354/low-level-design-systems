package lldPatterns.observer_pattern;
import java.util.*;


// ==========================================================
// 🚀 OBSERVER PATTERN — ORDER SERVICE EXAMPLE
// ==========================================================

// ==========================================================
// ❌ BAD DESIGN (TIGHT COUPLING)
// ==========================================================

/*
PROBLEM:
- OrderService directly depends on all services
- Hard to scale
- Violates Open Closed Principle
*/


class BadEmailService {

    public void sendEmail(String item) {

        System.out.println(
                "[EmailService] Email sent for: "
                        + item
        );
    }
}



class BadInventoryService {

    public void updateStock(String item) {

        System.out.println(
                "[InventoryService] Stock updated for: "
                        + item
        );
    }
}



class BadAnalyticsService {

    public void recordOrder(String item) {

        System.out.println(
                "[AnalyticsService] Analytics recorded for: "
                        + item
        );
    }
}



class BadOrderService {

    private BadEmailService emailService =
            new BadEmailService();

    private BadInventoryService inventoryService =
            new BadInventoryService();

    private BadAnalyticsService analyticsService =
            new BadAnalyticsService();



    public void placeOrder(String item) {

        System.out.println(
                "\n[BadOrderService] Order placed for: "
                        + item
        );

        // Tight Coupling

        emailService.sendEmail(item);

        inventoryService.updateStock(item);

        analyticsService.recordOrder(item);
    }
}

/*
❌ Problems:
1. Tight coupling
2. Hard to add new services
3. Modifying old code repeatedly
4. Difficult testing
*/

// ==========================================================
// ✅ GOOD DESIGN — OBSERVER PATTERN
// ==========================================================

/*
CORE IDEA:

👉 OrderService should ONLY emit event
👉 Other services decide:
   "What to do when order is placed?"
*/


// ==========================================================
// STEP 1: CREATE OBSERVER INTERFACE
// ==========================================================

interface OrderPlacedObserver {

    void onOrderPlaced(String item);
}


// ==========================================================
// STEP 2: CREATE OBSERVER CLASSES
// ==========================================================


// ==========================================================
// Email Service
// ==========================================================

class EmailService implements OrderPlacedObserver {

    @Override
    public void onOrderPlaced(String item) {

        // Email logic

        System.out.println(
                "[EmailService] Email sent for item: "
                        + item
        );
    }
}

// ==========================================================
// Inventory Service
// ==========================================================

class InventoryService
        implements OrderPlacedObserver {

    @Override
    public void onOrderPlaced(String item) {

        // Inventory logic

        System.out.println(
                "[InventoryService] Inventory updated for item: "
                        + item
        );
    }
}




// ==========================================================
// Analytics Service
// ==========================================================

class AnalyticsService
        implements OrderPlacedObserver {

    @Override
    public void onOrderPlaced(String item) {

        // Analytics logic

        System.out.println(
                "[AnalyticsService] Analytics recorded for item: "
                        + item
        );
    }
}




// ==========================================================
// STEP 3: CREATE OBSERVABLE / SUBJECT CLASS
// ==========================================================

class OrderService {

    // Store all observers

    private List<OrderPlacedObserver> observers =
            new ArrayList<>();



    // ======================================================
    // Subscribe Observer
    // ======================================================

    public void addObserver(
            OrderPlacedObserver observer
    ) {

        observers.add(observer);

        System.out.println(
                "✅ New Observer Added"
        );
    }



    // ======================================================
    // Remove Observer
    // ======================================================

    public void removeObserver(
            OrderPlacedObserver observer
    ) {

        observers.remove(observer);

        System.out.println(
                "❌ Observer Removed"
        );
    }



    // ======================================================
    // Business Logic
    // ======================================================

    public void placeOrder(String item) {

        System.out.println(
                "\n[OrderService] Order placed for: "
                        + item
        );

        // Emit Event

        notifyAllObservers(item);
    }



    // ======================================================
    // Notify All Observers
    // ======================================================

    private void notifyAllObservers(String item) {

        for (OrderPlacedObserver observer : observers) {

            observer.onOrderPlaced(item);
        }
    }
}




// ==========================================================
// MAIN CLASS
// ==========================================================

public class observer_pattern {

    public static void main(String[] args) {

        // Create Observable Class

        OrderService orderService =
                new OrderService();



        // Add Observers / Subscribers

        orderService.addObserver(
                new EmailService()
        );

        orderService.addObserver(
                new InventoryService()
        );

        orderService.addObserver(
                new AnalyticsService()
        );



        // Emit Events
        orderService.placeOrder("MacBook Pro");
        orderService.placeOrder("iPhone 15");
    }
}
