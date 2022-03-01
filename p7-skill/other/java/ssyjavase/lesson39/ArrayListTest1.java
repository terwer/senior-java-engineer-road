package lesson39;

import java.util.ArrayList;

/**
 * ArrayListTest1
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/25 23:18
 **/
@SuppressWarnings("unchecked")
public class ArrayListTest1 {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("hello");
        arrayList.add("world");
        arrayList.add("world");
        arrayList.add("welcome");

        String s1 = (String) arrayList.get(0);
        String s2 = (String) arrayList.get(1);
        String s3 = (String) arrayList.get(2);
        String s4 = (String) arrayList.get(3);

        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        System.out.println("s3 = " + s3);
        System.out.println("s4 = " + s4);

        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("arraylist[" + i + "] = " + arrayList.get(i));
        }

        // arrayList.clear();

        // System.out.println(arrayList.isEmpty());
        // System.out.println(arrayList.size());

        // arrayList.remove(0);
        // System.out.println();
        // for (int i = 0; i < arrayList.size(); i++) {
        //    System.out.println("arraylist[" + i + "] = " + arrayList.get(i));
        //}

        arrayList.remove("world");
        System.out.println();
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("arraylist[" + i + "] = " + arrayList.get(i));
        }
    }
}
