package org.example;

import java.util.HashSet;
import java.util.LinkedList;

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
