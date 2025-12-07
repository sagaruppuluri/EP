import java.util.*;

/**
 * Collections - Prebuilt Data structure implementations.
 *
 * I - Interface
 * C - Class
 *
 *    Collection - (I)
 *       List - (I)
 *         ArrayList - (C)
 *         LinkedList - (C)
 *       Set - (I)
 *         HashSet - (C)
 *         LinkedHashSet (C)
 *         SortedSet - (I)
 *            TreeSet - (C)
 *    Map - (I)
 *      HashMap - (C)
 *      LinkedHashMap - (C)
 *      SortedMap - (I)
 *          TreeMap - (C)
 *
 * Shortcuts -
 *
 *  List - Allows duplicates
 *  Set - No duplicates
 *  Hash - Uses hashing ( insertion order not preserved)
 *  Linked - Insertion order preserved.
 *  Tree - Sorted
 */
// Iterator
public class CollectionsEx5 {

    private static void display(Collection<Integer> col) {
        // For each value in the collection.
//        for (Integer value : col) {
//            System.out.println( value );
//        }

        Iterator<Integer> iterator = col.iterator();
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            System.out.println( value );
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(10);

        System.out.println("values in the list are...");
        display(list);

        HashSet<Integer> set = new HashSet<>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(10); // not added.

        System.out.println("values in the set are...");
        display(set);
    }
}
