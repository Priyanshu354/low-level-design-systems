package lldPatterns.strategy_pattern;

// ==============================
// WHY STRATEGY PATTERN?
// ==============================
// 1. Whenever system has multiple service classes (not entities)
//    that perform similar behavior → we use Strategy Pattern
// 2. Avoid long if-else / switch-case
// 3. Follow Open/Closed Principle (easy to extend, no modification)

// Example:
// Notification system → Email, SMS, Push, WhatsApp


// ==============================
// WITHOUT STRATEGY PATTERN
// ==============================
// Problem:
// - Long if-else
// - Hard to extend
// - Violates Open/Closed Principle

class NotificationServiceWithoutStrategy {

    public void sendNotification(String type, String message, int userId) {

        if (type.equals("EMAIL")) {
            System.out.println("Sending EMAIL: " + message);

        } else if (type.equals("SMS")) {
            System.out.println("Sending SMS: " + message);

        } else if (type.equals("PUSH")) {
            System.out.println("Sending PUSH Notification: " + message);

        } else {
            throw new IllegalArgumentException("Unsupported notification type");
        }
    }
}


// ==============================
// WITH STRATEGY PATTERN
// ==============================


// STEP 1: Strategy Interface
// Common behavior for all strategies
interface NotificationStrategy {

    void send(String message);
}


// STEP 2: Concrete Strategy Classes
// Each class implements its own behavior

class EmailNotification implements NotificationStrategy {

    @Override
    public void send(String message) {
        System.out.println("📧 Sending Email: " + message);
    }
}

class SmsNotification implements NotificationStrategy {

    @Override
    public void send(String message) {
        System.out.println("📱 Sending SMS: " + message);
    }
}

class PushNotification implements NotificationStrategy {

    @Override
    public void send(String message) {
        System.out.println("🔔 Sending Push Notification: " + message);
    }
}

class WhatsAppNotification implements NotificationStrategy {

    @Override
    public void send(String message) {
        // WhatsApp specific logic (encryption, API call, etc.)
        System.out.println("💬 Sending WhatsApp: " + message);
    }
}


// STEP 3: Context Class
// This class uses a strategy object
class NotificationService {

    private NotificationStrategy strategy;

    // Inject strategy via constructor
    public NotificationService(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    // Delegates work to strategy
    public void send(String message) {
        strategy.send(message);
    }
}


// STEP 4: Main Class (Driver Code)
// Only ONE public class per file
class NotificationStrategyDemo {

    public static void main(String[] args) {

        // Different behaviors at runtime

        NotificationService emailService =
                new NotificationService(new EmailNotification());
        emailService.send("Welcome to our platform!");

        NotificationService smsService =
                new NotificationService(new SmsNotification());
        smsService.send("Your OTP is 123456");

        NotificationService pushService =
                new NotificationService(new PushNotification());
        pushService.send("You have a new friend request!");

        NotificationService whatsappService =
                new NotificationService(new WhatsAppNotification());
        whatsappService.send("Hello from WhatsApp!");
    }
}