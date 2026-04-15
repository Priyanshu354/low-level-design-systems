package lldPatterns.singleton_pattern;

// ==============================
// WHY SINGLETON PATTERN?
// ==============================
// 1. When we need ONLY ONE INSTANCE of a class in entire application
// 2. Avoid unnecessary object creation
// 3. Maintain consistent state (logs, DB connection, config, etc.)

// Example:
// Database Connection → should be single instance


// ==============================
// WITHOUT SINGLETON
// ==============================
// Problems:
// 1. Multiple DB connections → memory wastage
// 2. Inconsistent logs (different files)
// 3. No centralized transaction handling

class DatabaseConnectionWithoutSingleton {

    private static int counter = 0;
    private int id;
    private String logFile;

    public DatabaseConnectionWithoutSingleton() {
        id = ++counter;
        this.logFile = "db-log-" + id + ".txt";
        System.out.println("DB Connection #" + id + " created. Logging to: " + logFile);
    }

    public void query(String sql) {
        System.out.println("[" + logFile + "] Executing: " + sql);
    }
}


// Services creating their own DB connection
class UserServiceWithoutSingleton {
    public void fetchUser() {
        DatabaseConnectionWithoutSingleton db = new DatabaseConnectionWithoutSingleton();
        db.query("SELECT * FROM users");
    }
}


// ==============================
// WITH SINGLETON
// ==============================

class DatabaseConnection {

    // STEP 1: static instance (only one object)
    private static DatabaseConnection instance;

    private static int counter = 0;
    private int id;
    private String logFile;

    // STEP 2: private constructor (no external object creation)
    private DatabaseConnection() {
        id = ++counter;
        this.logFile = "db-log-singleton.txt";
        System.out.println("✅ Singleton DB Connection #" + id + " created");
    }

    // STEP 3: global access point
    public static DatabaseConnection getInstance() {

        // Lazy initialization
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void query(String sql) {
        System.out.println("[" + logFile + "] Executing: " + sql);
    }
}


// ==============================
// SERVICES USING SINGLETON
// ==============================

class UserService {
    public void fetchUser() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.query("SELECT * FROM users WHERE id = 123");
    }
}

class CartService {
    public void fetchCart() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.query("SELECT * FROM cart WHERE user_id = 123");
    }
}

class OrderService {
    public void createOrder() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.query("INSERT INTO orders VALUES (...)");
    }
}


// ==============================
// MAIN CLASS
// ==============================

class SingletonDemo {

    public static void main(String[] args) {

        System.out.println("---- Using Singleton ----");

        UserService user = new UserService();
        CartService cart = new CartService();
        OrderService order = new OrderService();

        user.fetchUser();
        cart.fetchCart();
        order.createOrder();
    }
}