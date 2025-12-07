import java.util.HashSet;
import java.util.LinkedList;

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
public class CollectionsEx1 {
    public static void main(String[] args) {

        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(10);
        list1.add(20);
        list1.add(10);

        System.out.println( list1 );

        HashSet<Integer> set1 = new HashSet<>();
        set1.add(10);
        set1.add(20);
        set1.add(10);

        System.out.println( set1 );
    }
}
