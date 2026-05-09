package SOLID;

// ==========================================================
// 🚀 SOLID PRINCIPLES — BAD vs GOOD DESIGN EXAMPLES
// ==========================================================

// ==========================================================
// 🔹 S → SINGLE RESPONSIBILITY PRINCIPLE (SRP)
// ==========================================================

/*
📌 Definition:
“A class should have only One responsibility.”

👉 One class = One responsibility
*/


// ❌ BAD EXAMPLE

class BadUserService {

    public void saveUser() {
        System.out.println("Saving user");
    }

    public void sendEmail() {
        System.out.println("Sending email");
    }
}

/*
❌ Problem:
- BadUserService handles:
    1. User logic
    2. Email logic

👉 Multiple responsibilities
*/


// ✅ GOOD EXAMPLE

class UserService {

    public void saveUser() {
        System.out.println("Saving user");
    }
}

class EmailService {

    public void sendEmail() {
        System.out.println("Sending email");
    }
}

/*
✅ Now:
- UserService → only user logic
- EmailService → only email logic
*/




// ==========================================================
// 🔹 O → OPEN CLOSED PRINCIPLE (OCP)
// ==========================================================

/*
📌 Definition:
“Software entities should be OPEN for extension
but CLOSED for modification.”

👉 Add new behavior WITHOUT changing old code.
*/


// ❌ BAD EXAMPLE

class BadPaymentService {

    public void pay(String type) {

        if(type.equals("UPI")) {
            System.out.println("UPI Payment");

        } else if(type.equals("CARD")) {
            System.out.println("Card Payment");
        }
    }
}

/*
❌ Problem:
- Every new payment method
  requires modifying old code

👉 Violates OCP
*/


// ✅ GOOD EXAMPLE

interface PaymentStrategy {
    void pay();
}

class UpiPayment implements PaymentStrategy {

    public void pay() {
        System.out.println("UPI Payment");
    }
}

class CardPayment implements PaymentStrategy {

    public void pay() {
        System.out.println("Card Payment");
    }
}

class GoodPaymentService {

    public void processPayment(PaymentStrategy payment) {
        payment.pay();
    }
}

/*
✅ Benefits:
- Add new payment methods easily
- No need to modify existing code

👉 Open for extension
👉 Closed for modification
*/




// ==========================================================
// 🔹 L → LISKOV SUBSTITUTION PRINCIPLE (LSP)
// ==========================================================

/*
📌 Definition:
“Child classes should be able to replace
parent classes without breaking behavior.”

👉 Subclass should behave properly like parent.
*/


// ❌ BAD EXAMPLE

class Bird {

    void fly() {
        System.out.println("Flying");
    }
}

class Penguin extends Bird {

    void fly() {
        throw new RuntimeException("Penguins cannot fly");
    }
}

/*
❌ Problem:
- Penguin cannot properly behave like Bird

👉 Breaks parent behavior
👉 Violates LSP
*/


// ✅ GOOD EXAMPLE

interface Animal {
}

interface FlyingBird {
    void fly();
}

class Sparrow implements Animal, FlyingBird {

    public void fly() {
        System.out.println("Flying");
    }
}

class GoodPenguin implements Animal {

    public void swim() {
        System.out.println("Swimming");
    }
}

/*
✅ Benefits:
- Proper hierarchy
- No forced behavior

👉 LSP followed correctly
*/




// ==========================================================
// 🔹 I → INTERFACE SEGREGATION PRINCIPLE (ISP)
// ==========================================================

/*
📌 Definition:
“Clients should not be forced to implement
methods they do not use.”

👉 Small interfaces are better than huge interfaces.
*/


// ❌ BAD EXAMPLE

interface Worker {

    void work();
    void eat();
}

class BadRobot implements Worker {

    public void work() {
        System.out.println("Working");
    }

    public void eat() {

        // Robot cannot eat
    }
}

/*
❌ Problem:
- Robot forced to implement
  unnecessary method

👉 Violates ISP
*/


// ✅ GOOD EXAMPLE

interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {

    public void work() {
        System.out.println("Working");
    }

    public void eat() {
        System.out.println("Eating");
    }
}

class GoodRobot implements Workable {

    public void work() {
        System.out.println("Robot Working");
    }
}

/*
✅ Benefits:
- No unnecessary methods
- Small focused interfaces

👉 ISP followed correctly
*/




// ==========================================================
// 🔹 D → DEPENDENCY INVERSION PRINCIPLE (DIP)
// ==========================================================

/*
📌 Definition:
“High-level modules should not depend
on low-level modules.

Both should depend on abstractions.”

👉 Depend on interface, not implementation.
*/


// ❌ BAD EXAMPLE

class BadMySQLDatabase {

    public void connect() {
        System.out.println("MySQL Connected");
    }
}

class BadUserRepository {

    private BadMySQLDatabase db = new BadMySQLDatabase();

    public void save() {
        db.connect();
    }
}

/*
❌ Problem:
- Tight coupling
- Cannot easily switch database

👉 Violates DIP
*/


// ✅ GOOD EXAMPLE

interface Database {
    void connect();
}

class MySQLDatabase implements Database {

    public void connect() {
        System.out.println("MySQL Connected");
    }
}

class PostgreSQLDatabase implements Database {

    public void connect() {
        System.out.println("PostgreSQL Connected");
    }
}

class GoodUserRepository {

    private Database db;

    public GoodUserRepository(Database db) {
        this.db = db;
    }

    public void save() {
        db.connect();
    }
}

/*
✅ Benefits:
- Loose coupling
- Easy database replacement
- Better testing

👉 Depends on abstraction
*/




// ==========================================================
// 🚀 MAIN CLASS
// ==========================================================

public class solid_principles {

    public static void main(String[] args) {

        // ======================
        // SRP
        // ======================

        UserService userService = new UserService();
        userService.saveUser();

        EmailService emailService = new EmailService();
        emailService.sendEmail();


        // ======================
        // OCP
        // ======================

        GoodPaymentService paymentService = new GoodPaymentService();

        PaymentStrategy upi = new UpiPayment();
        paymentService.processPayment(upi);


        // ======================
        // LSP
        // ======================

        FlyingBird sparrow = new Sparrow();
        sparrow.fly();


        // ======================
        // ISP
        // ======================

        Workable robot = new GoodRobot();
        robot.work();


        // ======================
        // DIP
        // ======================

        Database database = new MySQLDatabase();

        GoodUserRepository repo =
                new GoodUserRepository(database);

        repo.save();
    }
}
