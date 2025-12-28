# Solid Principles

SOLID principles are five fundamental guidelines for object-oriented design that promote maintainable, scalable, and flexible code. Introduced by Robert C. Martin, they help avoid common pitfalls like rigid or fragile systems.

The five SOLID principles are:

## S - Single Responsibility Principle 

A class should have one and only one reason to change.

```java
class AccountService {
    private AccountRepository accountRepository;
    private NotificationService notificationService;

    public AccountService(AccountRepository accountRepository, NotificationService notificationService) {
        this.accountRepository = accountRepository;
        this.notificationService = notificationService;
    }

    public void createAccount(User user) {
        accountRepository.save(user);
        notificationService.sendWelcomeEmail(user);
    }
}
```

How Single Responsibility Principle Is Applied Here?

* AccountService: Coordinates the high-level process of creating an account — it doesn’t concern itself with how data is stored or how emails are sent.
* AccountRepository: Takes care solely of database save operations.
* NotificationService: Manages email content and delivery.

If you change your database logic or email provider, you don’t have to modify the AccountService class — only the respective component.

Key benefits: 
* Maintainability: Each class can be modified independently without affecting others.
* Reusability: NotificationService can be reused for other notifications, and AccountRepository for other database operations.
* Testability: You can mock dependencies (e.g., test AccountService without a real database).

## O - Open Closed Principle 

The Open–Closed Principle states that a software module (class, function, etc.) should be open for extension but closed for modification.
That means you should be able to add new behavior to existing code without changing the already tested and working code.

```java
    interface ValueComparator {
        int compare(int value1, int value2);
    }

    class ArrayUtil {
        public static final void sort(int a[], ValueComparator comparator) {
            for(int i = 0 ; i < a.length; i++) {
                for( int j = i + 1; j < a.length; j++) {
                    if (comparator.compare(a[i], a[j]) > 0) {
                        int temp = a[i];
                        a[i] = a[j];
                        a[j] = temp;
                    }
                }
            }
        }
    }
```

In the ArrayUtil class, the sort() method sorts an array — but importantly, it depends on the abstract interface ValueComparator, not on any specific comparison logic.

We can pass different comparator implementations (ascending, descending, even custom comparisons) without changing a single line of code in ArrayUtil

How It Follows the Open–Closed Principle?

* Closed for modification: The ArrayUtil class is complete and does not need editing if we need new sorting orders.
* Open for extension: We can extend functionality by adding new classes that implement ValueComparator.

For example:

```java
    class AscComparator implements ValueComparator {
        public int compare(int value1, int value2) {
            return value1 - value2;
        }
    }

    class DescComparator implements ValueComparator {
        public int compare(int value1, int value2) {
            return value2 - value1;
        }
    }
```

Key Benefits:
* Flexibility: New comparison strategies can be added without touching existing code.
* Maintainability: Existing code remains stable and tested, reducing the risk of introducing bugs.

## L - Liskov Substitution Principle

Liskov Substitution Principle (LSP) — the "L" in SOLID. LSP states that objects of a superclass should be replaceable by objects of a subclass without altering the correctness of the program. Subclasses must honor the base class contract without introducing unexpected behavior.

```java
    class Vehicle {
        public void start() { System.out.println("starting a vehicle"); }
        public void stop() { System.out.println("stopping a vehicle"); }
    }

    class Bike extends Vehicle {
        public void start() { System.out.println("starting a bike"); }
        public void stop() { System.out.println("stopping a bike"); }
    }

    class Car extends Vehicle {
        public void start() { 
            System.out.println("starting a car");
        }
        public void stop() { System.out.println("stopping a car"); }
    }

    class VechicleDemo {
        static void testDrive(Vehicle vehicle) {
            vehicle.start();  // Expects this to work reliably
            vehicle.stop();
        }
    }
```

The testDrive() method works with any Vehicle. It calls:

```java
    testDrive(new Vehicle());  // "starting a vehicle" → "stopping a vehicle"
    testDrive(new Bike());     // "starting a bike" → "stopping a bike" 
    testDrive(new Car());      // "starting a car" → "stopping a car" 
```

This example follows LSP because:

* Bike and Car substitute perfectly for Vehicle in testDrive().
* Both override methods but preserve expected behavior — start() succeeds, stop() succeeds.
* testDrive() behaves predictably regardless of the concrete type passed.


## I - Interface Segregation Principle

Interface Segregation Principle (ISP) — the "I" in SOLID. ISP states that clients should not be forced to depend on interfaces they do not use. Instead of one large, "fat" interface, create multiple small, specific interfaces.

```java 
    interface VegRestaurant {
        void vegMeals();
    }

    interface NonVegRestaurant {
        void nonVegMeals();
    }

    class ABCVegRestaurant implements VegRestaurant {  // Only veg interface
        public void vegMeals() {
            System.out.println("provide veg meals");
        }
    }

    class XYZRestaurant implements VegRestaurant, NonVegRestaurant {  // Both interfaces
        public void vegMeals() {
            System.out.println("provide veg meals");
        }
        public void nonVegMeals() {
            System.out.println("provide non veg meals");
        }
    }
```

This design follows ISP because:

* Small, focused interfaces: Each interface has one specific role (veg meals vs non-veg meals).
* No forced implementation: ABCVegRestaurant (pure veg restaurant) is not forced to implement non-veg methods.
* Client-specific: Classes implement only what they need.

Bad design (fat interface):

```java
    interface Restaurant {
        void vegMeals();
        void nonVegMeals();
    }

    class ABCVegRestaurant implements Restaurant {  // Forced to implement non-veg
        public void vegMeals() {
            System.out.println("provide veg meals");
        }
        public void nonVegMeals() {
            throw new UnsupportedOperationException("No non-veg meals");
        }
    }
```

Key Benefits:
* Flexibility: Easier to change or extend specific parts of the system.
* Maintainability: Changes in one interface do not affect unrelated clients.
* Clearer contracts: Each interface clearly defines its purpose.
* Segregated interfaces (VegRestaurant, NonVegRestaurant).
* No forced dependencies — each class implements only relevant methods.

The result is loosely coupled, maintainable code where classes stay true to their actual responsibilities.

## D - Dependency Inversion Principle

Dependency Inversion Principle (DIP) — the "D" in SOLID. DIP states that high-level modules should not depend on low-level modules; both should depend on abstractions. Abstractions should not depend on details — details depend on abstractions.


```java
    class MyMessenger {  // HIGH-LEVEL
        ProtocolHandler handler;  // ← DEPENDS ON ABSTRACTION (not concrete classes)
        
        public MyMessenger(String protocol) {
            handler = ProtocolHandlerFactory.getProtocolHandler(protocol);  // Factory provides concrete impl
        }
        
        public void send(String to, String message) {
            handler.sendMessage(...);  // Uses abstraction
        }
    }

    interface ProtocolHandler {  // ABSTRACTION
        void sendMessage(String message);
    }
```

BAD DESIGN (VIOLATES DIP):

```text
    MyMessenger → TCPProtocolHandler  (direct concrete dependency - VIOLATION)
```

DIP-compliant flow (GOOD):

```text
    MyMessenger → ProtocolHandler ← TCPProtocolHandler, UDPProtocolHandler
```

In this design:

* MyMessenger depends on the ProtocolHandler interface, not on concrete implementations.
* Concrete protocol handlers implement ProtocolHandler, allowing MyMessenger to work with any protocol without modification.

Key Benefits:

* Flexibility: Easily switch or add new protocols without changing MyMessenger.
* Maintainability: High-level logic is decoupled from low-level details.
* Testability: You can mock ProtocolHandler for unit testing MyMessenger.

## Summary

SOLID principles are five guidelines for object-oriented design that create maintainable, scalable code. Each principle from the provided examples demonstrates separation of concerns, flexibility, and reliability.

* Single Responsibility (SRP) : Classes should have one reason to change. 
* Open-Closed (OCP) : Modules open for extension, closed for modification. 
* Liskov Substitution (LSP) : Subclasses replaceable for base classes without breaking behavior. 
* Interface Segregation (ISP) : Clients depend only on needed interfaces.
* Dependency Inversion (DIP) : High-level modules depend on abstractions.