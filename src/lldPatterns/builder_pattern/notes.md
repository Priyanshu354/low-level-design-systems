# 🚀 BUILDER PATTERN — INTERVIEW NOTES

## 🔹 What is Builder Pattern?

> “Builder Pattern is a creational design pattern used to construct complex objects step-by-step, especially when an object has many optional parameters.”

### In simple words

- Object has many fields
- Some fields are mandatory
- Many fields are optional
- Constructor becomes difficult to read/manage
- Builder lets us create the object step-by-step

---

## 🧠 REAL INTERVIEW UNDERSTANDING

Whenever you see a class with:

- Many constructor parameters
- Many optional fields
- Different combinations of fields
- Telescoping constructors

👉 **Think Builder Pattern.**

---

## 🔥 PROBLEM: TELESCOPING CONSTRUCTOR

Without Builder:

```java
User user = new User(
    "Priyanshu",
    "priyanshu@gmail.com",
    22,
    "Bhopal",
    "India",
    "Developer",
    true
);
```

Problems:

❌ Hard to understand  
❌ Easy to pass values in the wrong order  
❌ Constructor becomes huge  
❌ Adding optional fields creates more constructors

This is called the:

👉 **Telescoping Constructor Problem**

---

## 🔧 BUILDER STRUCTURE

```text
User
 ↑
 |
UserBuilder
 ├── name()
 ├── email()
 ├── age()
 ├── city()
 └── build()
```

Example:

```java
User user = new User.Builder()
        .name("Priyanshu")
        .email("priyanshu@gmail.com")
        .age(22)
        .city("Bhopal")
        .build();
```

Flow:

```text
Builder
   ↓
set name
   ↓
set email
   ↓
set age
   ↓
set city
   ↓
build()
   ↓
User object
```

---

## 🔹 WHY BUILDER PATTERN?

1. Handle many optional parameters
2. Avoid telescoping constructors
3. Improve readability
4. Make object creation step-by-step
5. Make immutable objects easier to construct
6. Avoid parameter-order confusion

---

## 🔥 IMPORTANT CONCEPT — FLUENT API

Builder normally uses a **fluent API**.

```java
.name("Priyanshu")
.email("abc@gmail.com")
.age(22)
```

Each method returns the Builder itself:

```java
return this;
```

This allows method chaining.

---

## 🔹 MANDATORY vs OPTIONAL FIELDS

Example:

```text
User
├── name       → mandatory
├── email      → mandatory
├── age        → optional
├── city       → optional
└── phone      → optional
```

Builder can validate mandatory fields inside `build()`.

```java
if (name == null || email == null) {
    throw new IllegalArgumentException("Required fields missing");
}
```

---

## 🔹 IMMUTABILITY WITH BUILDER

A common Builder implementation creates an immutable final object.

```text
Builder
 ├── name
 ├── email
 ├── age
 └── city
       ↓
     build()
       ↓
User
 ├── final name
 ├── final email
 ├── final age
 └── final city
```

👉 **Builder = mutable construction object**

👉 **Product = immutable final object**

---

## 🔥 BUILDER vs CONSTRUCTOR

### Constructor

```java
new User(
    "Priyanshu",
    "abc@gmail.com",
    22,
    "Bhopal"
);
```

### Builder

```java
new User.Builder()
    .name("Priyanshu")
    .email("abc@gmail.com")
    .age(22)
    .city("Bhopal")
    .build();
```

👉 Builder is more readable when there are many parameters.

---

## 🔥 BUILDER vs SETTERS

### Setters

```java
User user = new User();

user.setName("Priyanshu");
user.setEmail("abc@gmail.com");
user.setAge(22);
```

Problems:

❌ Object may remain partially initialized  
❌ Object is usually mutable  
❌ Validation can be scattered

### Builder

```java
User user = new User.Builder()
    .name("Priyanshu")
    .email("abc@gmail.com")
    .age(22)
    .build();
```

👉 `build()` gives a clear point where the final object is created and validated.

---

## 🔹 PROS ✅

- Readable object creation
- Handles many optional parameters
- Avoids telescoping constructors
- Supports method chaining
- Can validate before object creation
- Works well with immutable objects
- Easy to add new optional fields
- Avoids constructor parameter-order confusion

---

## ❌ CONS

- More code/classes
- Overkill for simple objects
- Builder must be maintained when fields change
- Adds complexity if there are only a few fields

---

## 🚫 WHEN NOT TO USE

Don't use Builder when:

- Object has only 2–3 simple fields
- Almost all fields are mandatory
- Object creation is very simple
- Builder adds more complexity than value

---

## 🔥 REAL-WORLD EXAMPLES

Builder pattern is commonly seen in:

- HTTP request builders
- SQL/query builders
- Configuration objects
- DTO creation
- Complex domain objects
- Lombok `@Builder`

Example:

```java
User user = User.builder()
        .name("Priyanshu")
        .email("abc@gmail.com")
        .age(22)
        .build();
```

---

## 🧠 BUILDER vs FACTORY

| Builder | Factory |
|---|---|
| Constructs a complex object | Creates an object based on type/input |
| Focuses on **HOW** to construct | Focuses on **WHICH** object to create |
| Step-by-step construction | Centralized object creation |
| Useful for many optional fields | Useful for different implementations |
| Creational pattern | Creational pattern |

### Easy way to remember

👉 **Factory = “Which object should I create?”**

👉 **Builder = “How should I construct this object?”**

---

## 🔥 COMMON INTERVIEW QUESTIONS

### Q: Why not use a constructor?

> “If the object has many optional parameters, constructors can become difficult to read and can lead to telescoping constructors. Builder provides a readable, step-by-step way to construct the object.”

### Q: Why does Builder return `this`?

> “To support method chaining or a fluent API. Each builder method modifies the builder and returns the same builder instance.”

### Q: Where should validation happen?

> “Validation can be performed inside `build()` before creating the final object, especially for required fields or cross-field validation.”

### Q: Is Builder immutable?

> “No. The Builder itself is usually mutable. The final object can be made immutable by copying the Builder's values into final fields during `build()`.”

---

## 🎯 RATTNE KA TARIKA

Remember these 5 steps:

1. Create the Product class
2. Create a Builder
3. Add builder methods for fields
4. Return `this` from builder methods
5. `build()` creates the final object

Mental Model:

```text
Builder
  ↓
Set values
  ↓
Validate
  ↓
build()
  ↓
Immutable Product
```

---

## 🎯 INTERVIEW ONE-LINER

> **“Builder Pattern is a creational design pattern that constructs a complex object step-by-step, providing a readable way to handle optional parameters and avoiding telescoping constructors.”**
