package lldPatterns.builder_pattern;

// ==============================
// WHY BUILDER PATTERN?
// ==============================
// 1. Handle many optional parameters
// 2. Avoid telescoping constructors
// 3. Improve readability
// 4. Construct objects step-by-step
// 5. Make immutable objects easier to create
//
// Mental Model:
//
// Builder
//    ↓
// set values
//    ↓
// validate
//    ↓
// build()
//    ↓
// User object


// ==============================
// STEP 1: Product Class
// ==============================
// This is the final object we want to create.
//
// Fields are final, so the object is immutable
// after construction.

class User {

    private final String name;
    private final String email;
    private final int age;
    private final String city;
    private final String country;
    private final String phone;


    // ==============================
    // STEP 2: PRIVATE CONSTRUCTOR
    // ==============================
    // Only Builder can create User objects.

    private User(Builder builder) {

        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.city = builder.city;
        this.country = builder.country;
        this.phone = builder.phone;
    }


    // ==============================
    // STEP 3: BUILDER CLASS
    // ==============================
    // Builder stores values while the object
    // is being constructed.

    static class Builder {

        private String name;
        private String email;
        private int age;
        private String city;
        private String country;
        private String phone;


        // ==============================
        // STEP 4: BUILDER METHODS
        // ==============================
        // Each method:
        // 1. Sets a value
        // 2. Returns this
        //
        // Returning this enables method chaining.

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }


        // ==============================
        // STEP 5: BUILD METHOD
        // ==============================
        // Responsible for:
        // 1. Validation
        // 2. Creating final User object

        public User build() {

            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name is required");
            }

            if (email == null || email.isBlank()) {
                throw new IllegalArgumentException("Email is required");
            }

            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }

            return new User(this);
        }
    }


    // ==============================
    // GETTERS
    // ==============================

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}


// ==============================
// STEP 6: CLIENT / MAIN CLASS
// ==============================
// Client creates the object step-by-step.
//
// No huge constructor.
// No parameter-order confusion.
// Optional fields can be skipped.

class BuilderPatternDemo {

    public static void main(String[] args) {

        User user = new User.Builder()
                .name("Priyanshu")
                // Builder
                .email("priyanshu@gmail.com")
                .age(22)
                .city("Bhopal")
                .country("India")
                .phone("9999999999")
                .build();

        System.out.println(user);


        // Optional fields can be skipped.

        User anotherUser = new User.Builder()
                .name("Rahul")
                .email("rahul@gmail.com")
                .build();

        System.out.println(anotherUser);


        // The final User object cannot be modified
        // because its fields are private and final.
    }
}
