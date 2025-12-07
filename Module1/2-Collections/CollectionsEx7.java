
import java.util.Map;
import java.util.TreeMap;

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
// Display the word along with its frequency.
public class CollectionsEx7 {
    public static void main(String[] args) {
        String sentence = "this is sample sentence this is for map example";
        String [] words = sentence.split(" ");

        // Map<String, Integer> wordMap = new HashMap<String, Integer>();
        Map<String, Integer> wordMap = new TreeMap<String, Integer>();
        for (String word: words) {
            Integer value = wordMap.get(word);
            if (value == null) {
                value = 1;
            } else {
                value = value + 1;
            }
            wordMap.put(word, value);
        }

        System.out.println( wordMap );

    }
}
/*
  wordMap -
    this - 2
    is - 2
    sample - 1
    sentence - 1
    for - 1
    map - 1
    example - 1
 */