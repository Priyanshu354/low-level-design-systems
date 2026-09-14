🚀 DECORATOR PATTERN — INTERVIEW NOTES

🔹 What is Decorator Pattern?

“Decorator pattern is a structural design pattern that allows us to dynamically add new behavior or responsibilities to an object without modifying its original class.”

👉 In simple words:
- Start with a base object
- Wrap it with a decorator
- Each decorator adds extra behavior
- Multiple decorators can be stacked
- Original class does not need to be modified

🧠 Core Idea:

Instead of creating many subclasses for every possible combination:

Coffee
├── MilkCoffee
├── SugarCoffee
├── MilkSugarCoffee
└── MilkSugarCreamCoffee

👉 Use decorators:

SimpleCoffee
    ↓
MilkDecorator
    ↓
SugarDecorator
    ↓
CreamDecorator

🔧 Structure:

- Component Interface → common interface for the original object and decorators
- Concrete Component → original/basic object
- Base Decorator → contains a reference to the Component
- Concrete Decorators → add specific behavior

🧠 Example:

Component:
Coffee

Concrete Component:
SimpleCoffee

Decorators:
MilkDecorator
SugarDecorator
CreamDecorator

Example:

Coffee coffee =
    new SugarDecorator(
        new MilkDecorator(
            new SimpleCoffee()
        )
    );

👉 Every decorator wraps the previous object and adds its own behavior.

🔹 Why is it used?

- To add behavior dynamically
- To avoid class explosion
- To combine multiple optional features
- To follow Open/Closed Principle
- To prefer composition over inheritance
- To keep the original class unchanged

🔹 Important Concept:

Decorator uses COMPOSITION instead of relying only on inheritance.

MilkDecorator HAS-A Coffee.

This allows:

SimpleCoffee
    ↓
MilkDecorator
    ↓
SugarDecorator
    ↓
CreamDecorator

Each layer delegates to the wrapped object and then adds its own behavior.

🔹 Pros ✅

- Add behavior dynamically
- Avoid class explosion
- Flexible feature combinations
- Follows Open/Closed Principle
- Uses composition over inheritance
- Original object remains unchanged
- Decorators can be stacked

🔹 Cons ❌

- Can create many small decorator classes
- Deeply nested decorators can become difficult to understand
- Debugging can become harder
- Order of decorators may affect behavior
- Adds an extra layer of abstraction

🔹 When NOT to use 🚫

- When there is only one simple optional behavior
- When dynamic combinations are not required
- When wrapping makes the design unnecessarily complex
- When a simple method/class change is enough
- For very small systems where the abstraction provides no real benefit

🔹 Real-World Examples:

- Java I/O Streams
- BufferedInputStream wrapping InputStream
- GZIPInputStream wrapping another InputStream
- Logging wrappers
- Caching wrappers
- Authentication/authorization wrappers
- Compression
- Encryption
- Validation
- Middleware/filter chains

🔹 Decorator vs Inheritance:

Inheritance:
- Behavior is fixed through the class hierarchy
- Can lead to many subclasses for combinations

Decorator:
- Behavior can be added dynamically
- Multiple behaviors can be composed
- Uses composition

👉 Easy interview line:

“Inheritance gives us fixed behavior through subclasses, whereas Decorator allows us to dynamically compose additional behavior around an object.”

🔹 Decorator vs Strategy:

Decorator:
- Adds responsibilities/features
- Wraps an object
- Multiple decorators can be stacked

Strategy:
- Changes/selects an algorithm or behavior
- Usually one strategy is selected
- Does not primarily wrap the original object

👉 Easy way to remember:

Strategy = “HOW should I do this?”

Decorator = “WHAT extra behavior should I add?”

🔹 Interview Question:

Q: Why use Decorator instead of inheritance?

A:
“Decorator uses composition instead of inheritance, so we can dynamically combine multiple behaviors and avoid creating a large number of subclasses for every possible combination.”

🔹 Rattne Ka Tarika:

Remember these 5 steps:

1. Create a common Component interface
2. Create the Concrete Component
3. Create a Base Decorator containing the Component
4. Create Concrete Decorators
5. Wrap decorators around the object

Mental Model:

Component
   ↓
Decorator
   ↓
Decorator
   ↓
Decorator

🎯 INTERVIEW ONE-LINER:

“Decorator Pattern is a structural design pattern that dynamically adds responsibilities to an object by wrapping it with decorator objects, without modifying the original class.”
