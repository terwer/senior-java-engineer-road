package com.terwergreen.array;

/**
 * @name: Swap2
 * @author: terwer
 * @date: 2022-10-18 20:43
 **/
public class Swap2 {
    public static void main(String[] args) {
        char[] ch = {'A', 'C'};
        swap(ch, ch[1]);

        System.out.println(ch);
    }

    public static void swap(char[] ch, char c) {
        ch[0] = 'B';
        c = 'D';
    }
}
