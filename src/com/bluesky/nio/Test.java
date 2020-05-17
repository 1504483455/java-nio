package com.bluesky.nio;

import java.util.*;

public class Test{
    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();
//        String[] s = str.split(" "); //正则表达式实用性更强( str.split("\\s+"))
//        int length = s[s.length - 1].length();
//        System.out.println(length);
//        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();
//        String str2 = sc.nextLine();
//        char[] chars = str.toLowerCase().toCharArray();
//        int i = 0;
//        char c2 = str2.toLowerCase().toCharArray()[0];
//        for (char c:chars) {
//            if(c2 == c){
//                i++;
//            }
//        }
//        System.out.println(i);

        int[] array = new int[1001];
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        while (scanner.hasNextLine()){
            int data = scanner.nextInt();
            array[index] = data;
            index++;
            for (int i = 0;i<index;i++){
                for (int j = 0;j<index;j++){
                    if(array[i]<array[j]){
                        int swap = array[i];
                        array[i] = array[j];
                        array[j] = swap;
                    }
                }
            }
            for (int i = 0;i<index;i++) {

                if (i == 0 || array[i] != array[i - 1]) {
                    System.out.println(array[i]);
                }

            }
        }

    }
}