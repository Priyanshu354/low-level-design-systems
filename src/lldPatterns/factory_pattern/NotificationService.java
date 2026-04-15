package lldPatterns.factory_pattern;

// ==============================
// WHY FACTORY PATTERN?
// ==============================
// 1. To centralize object creation
// 2. Avoid writing "new" everywhere
// 3. Remove if-else logic from business code

// Works BEST with Strategy Pattern


// ==============================
// STEP 1: Strategy Interface
// ==============================

interface Notification {
    void send(String message);
}


// ==============================
// STEP 2: Concrete Classes
// ==============================

class SMSNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("📱 SMS: " + message);
    }
}

class EmailNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("📧 Email: " + message);
    }
}

class PushNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("🔔 Push: " + message);
    }
}


// ==============================
// STEP 3: Factory Class
// ==============================
// Responsible ONLY for object creation

class NotificationFactory {

    public static Notification createNotification(String type) {

        if (type.equalsIgnoreCase("sms")) {
            storeS3();
            return new SMSNotification();

        } else if (type.equalsIgnoreCase("email")) {
            storeDynamo();
            return new EmailNotification();

        } else if (type.equalsIgnoreCase("push")) {
            storeVector();
            return new PushNotification();

        } else {
            throw new IllegalArgumentException("Invalid type");
        }
    }

    // Supporting logic (side-effects)
    private static void storeS3() {
        System.out.println("📦 Storing in S3");
    }

    private static void storeDynamo() {
        System.out.println("📦 Storing in DynamoDB");
    }

    private static void storeVector() {
        System.out.println("📦 Storing in Vector DB");
    }
}


// ==============================
// STEP 4: Client Class
// ==============================
// NO if-else here

class NotificationService {

    public void sendNotification(String type, String message) {

        // Factory decides which object to create
        Notification notification = NotificationFactory.createNotification(type);

        // Strategy executes behavior
        notification.send(message);
    }
}


// ==============================
// MAIN CLASS
// ==============================

class FactoryPatternDemo {

    public static void main(String[] args) {

        NotificationService service = new NotificationService();

        service.sendNotification("sms", "OTP is 1234");
        service.sendNotification("email", "Welcome!");
        service.sendNotification("push", "New alert!");
    }
}