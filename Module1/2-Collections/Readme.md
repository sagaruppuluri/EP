# Collections 

Java Collections Framework provides resizable data structures for storing and manipulating groups of objects. 
​
## Collection Interface

Collection is the root interface for most collection classes, defining basic operations like add, remove, size, and iteration. It serves as the foundation for List and Set. 

Some of the mainly used methods in Collection interface are:

- `boolean add(E e)`: Adds an element to the collection.
- `boolean remove(Object o)`: Removes a single instance of the specified element from the collection, if present.
- `int size()`: Returns the number of elements in the collection.
- `boolean isEmpty()`: Checks if the collection is empty.
- `boolean contains(Object o)`: Checks if the collection contains the specified element.
- `Iterator<E> iterator()`: Returns an iterator over the elements in the collection.

```plaintext

Java Collections Hierarchy:

Iterable (root)
└── Collection (root interface)
    ├── List
    │   ├── AbstractList
    │   │   ├── ArrayList
    │   │   └── Vector
    │   └── AbstractSequentialList
    │       └── LinkedList
    ├── Set
    │   ├── AbstractSet
    │   │   ├── HashSet
    │   │   └── TreeSet
    │   └── LinkedHashSet
    └── Queue
        ├── AbstractQueue
        │   └── PriorityQueue
        └── Deque
            └── LinkedList (also implements List)

Map (separate hierarchy)
├── AbstractMap
│   ├── HashMap
│   ├── LinkedHashMap
│   └── TreeMap (also implements SortedMap)

```

## List Interface

List maintains insertion order and allows duplicate elements with indexed access. ArrayList and LinkedList implement are some wellknown classes which implement the List interface. 

Some of the mainly used methods in List interface are:

- `void add(int index, E element)`: Inserts the specified element at the specified position in the list.
- `E get(int index)`: Returns the element at the specified position in the list.
- `E remove(int index)`: Removes the element at the specified position in the list.
- `int indexOf(Object o)`: Returns the index of the first occurrence of the specified element in the list, or -1 if not found.
- `List<E> subList(int fromIndex, int toIndex)`: Returns a view of the portion of the list between the specified indices.
​
### ArrayList (Class)

ArrayList uses dynamic arrays for fast random access but slower insertions/deletions in the middle. It grows automatically as elements are added.
​
```java

import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(15);
        numbers.add(20);
        System.out.println(numbers); // [10, 15, 20]
        System.out.println(numbers.get(0)); // 10
    }
}
```

### LinkedList (Class)

LinkedList uses doubly-linked nodes for efficient insertions/deletions anywhere but slower random access. It also implements Queue and Deque interfaces.

Some of the mainly used methods in LinkedList class are:

- `void addFirst(E e)`: Inserts the specified element at the beginning of the list.
- `void addLast(E e)`: Appends the specified element to the end of the list.
- `E getFirst()`: Returns the first element in the list.
- `E getLast()`: Returns the last element in the list.
- `E removeFirst()`: Removes and returns the first element from the list.
- `E removeLast()`: Removes and returns the last element from the list.

​
```java

import java.util.LinkedList;

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<String> animals = new LinkedList<>();
        animals.add("Dog");
        animals.add("Cat");
        animals.addFirst("Lion");
        System.out.println(animals); // [Lion, Dog, Cat]
    }
}

```

## Set Interface

Set stores unique elements without duplicates. HashSet, LinkedHashSet, and TreeSet provide different ordering guarantees.

Some of the mainly used methods in Set interface are:

- `boolean add(E e)`: Adds the specified element to the set if it is not already present.
- `boolean remove(Object o)`: Removes the specified element from the set if it is present.
- `boolean contains(Object o)`: Checks if the set contains the specified element.
- `int size()`: Returns the number of elements in the set.
- `void clear()`: Removes all elements from the set.
​
### HashSet (Class)

HashSet stores elements in hash table with no guaranteed order and constant-time performance for basic operations. Duplicates are automatically prevented.
​
```java

import java.util.HashSet;

public class HashSetExample {
    public static void main(String[] args) {
        HashSet<String> cars = new HashSet<>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("BMW"); // Duplicate ignored
        cars.add("Ford");
        System.out.println(cars); // [Volvo, BMW, Ford] (order not guaranteed)
    }
}
```

While using hashed collections, it's important to ensure that the objects stored in them properly override the `hashCode()` and `equals()` methods. This ensures that the uniqueness constraint of the Set is maintained correctly.

```java
    import java.util.HashSet;

    class Fruit {
        private String name;

        public Fruit(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Fruit fruit = (Fruit) obj;
            return name.equals(fruit.name);
        }
    }

    class Main {
        public static void main(String[] args) {
            HashSet<Fruit> fruitSet = new HashSet<>();
            fruitSet.add(new Fruit("Apple"));
            fruitSet.add(new Fruit("Banana"));
            fruitSet.add(new Fruit("Apple")); // Duplicate based on name
            System.out.println(fruitSet.size()); // Output: 2
        }
    }
```

### LinkedHashSet (Class)

LinkedHashSet maintains insertion order using hash table and linked list while ensuring uniqueness. It offers predictable iteration order.
​
```java

import java.util.LinkedHashSet;

public class LinkedHashSetExample {
    public static void main(String[] args) {
        LinkedHashSet<String> cars = new LinkedHashSet<>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("BMW"); // Duplicate ignored
        System.out.println(cars); // [Volvo, BMW, Ford]
    }
}

```

## SortedSet Interface

SortedSet maintains elements in ascending order with additional methods like first() and last(). TreeSet implements this interface.

### TreeSet (Class)

TreeSet uses red-black tree for sorted storage, automatic uniqueness, and log(n) performance. Elements must be comparable or provide Comparator.

```java

import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        TreeSet<String> cars = new TreeSet<>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("BMW"); // Duplicate ignored
        System.out.println(cars); // [BMW, Ford, Volvo]
    }
}

```

Custom objects stored in a TreeSet must implement the Comparable interface or be provided with a Comparator to define their natural ordering.

```java
    import java.util.TreeSet;

    class Person implements Comparable<Person> {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person other) {
            return Integer.compare(this.age, other.age); // Sort by age
        }

        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }

    class Main {
        public static void main(String[] args) {
            TreeSet<Person> people = new TreeSet<>();
            people.add(new Person("A", 30));
            people.add(new Person("B", 25));
            people.add(new Person("C", 35));
            System.out.println(people); // Output: [B (25), A (30), C (35)]
        }
    }
```

The same applies when using a Comparator:

```java
    import java.util.Comparator;
    import java.util.TreeSet;

    class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }

    class AgeComparator implements Comparator<Person> {

        @Override
        public int compare(Person p1, Person p2) {
            return Integer.compare(p1.age, p2.age); // Sort by age
        }

    }

    class Main {
        public static void main(String[] args) {
            TreeSet<Person> people = new TreeSet<>(new AgeComparator());
            people.add(new Person("A", 30));
            people.add(new Person("B", 25));
            people.add(new Person("C", 35));
            System.out.println(people); // Output: [B (25), A (30), C (35)]
        }
    }

```

## Map Interface

Map stores key-value pairs with unique keys. HashMap, LinkedHashMap, and TreeMap offer different performance and ordering characteristics.
​
### HashMap (Class)

HashMap provides fast key-based access using hash table with no order guarantee. Keys must be unique; null keys/values allowed.
​
```java

import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, Integer> fruits = new HashMap<>();
        fruits.put("Apple", 3);
        fruits.put("Banana", 5);
        fruits.put("Apple", 4); // Overwrites previous value
        System.out.println(fruits.get("Apple")); // 4
    }
}

```

### LinkedHashMap (Class)

LinkedHashMap maintains insertion order (or access order) while providing hash table performance. Ideal for caches requiring predictable iteration.

```java

import java.util.LinkedHashMap;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        LinkedHashMap<String, Integer> numbers = new LinkedHashMap<>();
        numbers.put("One", 1);
        numbers.put("Two", 2);
        numbers.put("Three", 3);
        System.out.println(numbers); // {One=1, Two=2, Three=3}
    }
}

```

## SortedMap Interface

SortedMap maintains keys in ascending order with additional methods like firstKey() and lastKey(). TreeMap implements this interface.

### TreeMap (Class)

TreeMap uses red-black tree for sorted keys and log(n) operations. Keys must implement Comparable or provide Comparator.
​
```java

import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<String, Integer> numbers = new TreeMap<>();
        numbers.put("Second", 2);
        numbers.put("First", 1);
        numbers.put("Third", 3);
        System.out.println(numbers); // {First=1, Second=2, Third=3}
    }
}

```

## Iterator Interface

Iterator provides a way to traverse collections sequentially without exposing the underlying structure. It supports safe element removal during iteration.

Some of the mainly used methods in Iterator interface are:

- `boolean hasNext()`: Returns true if there are more elements to iterate over.
- `E next()`: Returns the next element in the iteration.
- `void remove()`: Removes the last element returned by the iterator from the underlying collection.

Example of using Iterator:

```java

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorExample {
    public static void main(String[] args) {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");

        Iterator<String> iterator = colors.iterator();
        while (iterator.hasNext()) {
            String color = iterator.next();
            System.out.println(color);
            if (color.equals("Green")) {
                iterator.remove(); // Safely remove "Green"
            }
        }

        System.out.println("After removal: " + colors); // [Red, Blue]
    }
}

For each loop can also be used as a simpler alternative to Iterator:

```java

import java.util.ArrayList;

public class ForEachExample {
    public static void main(String[] args) {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");

        for (String color : colors) {
            System.out.println(color);
        }
    }
}

```


## Shortcuts -

* List - Allows duplicates
* Set - No duplicates
* Hash - Uses hashing ( insertion order not preserved)
* Linked - Insertion order preserved.
* Tree - Sorted