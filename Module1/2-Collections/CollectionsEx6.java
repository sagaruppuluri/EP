import java.util.HashMap;

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
 *    Map -
 *      Key, Value pairs.
 *      (k1, v1)
 *      (k2, v2)
 *      put(key, value)
 *      value = get(key)
 *
 * Shortcuts -
 *
 *  List - Allows duplicates
 *  Set - No duplicates
 *  Hash - Uses hashing ( insertion order not preserved)
 *  Linked - Insertion order preserved.
 *  Tree - Sorted
 */
public class CollectionsEx6 {
    public static void main(String[] args) {
        HashMap<String, Integer> fruitMap = new HashMap<>();

        // store -> put(key, value)
        fruitMap.put("apple", 20);
        fruitMap.put("mango", 30);
        fruitMap.put("banana", 20);

        // value = get(key)
        Integer applePrice = fruitMap.get("apple");
        System.out.println( applePrice );

        System.out.println("content of the fruitMap");
        for (String key : fruitMap.keySet()) {
            Integer price = fruitMap.get(key);
            System.out.println( key + " -- " + price );
        }

    }
}
