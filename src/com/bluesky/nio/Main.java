package com.bluesky.nio;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    /**
     * 密码要求:
        1.长度超过8位
        2.包括大小写字母.数字.其它符号,以上四种至少三种
        3.不能有相同长度超2的子串重复
        说明:长度超过2的子串
     * @param args
     */
    public static void main(String[] args)  {
        AtomicReference<Thread> te = new AtomicReference<>();
        Collections.synchronizedList(new ArrayList<>());
        Scanner scanner = new Scanner(System.in);
        String ok = "OK";
        String ng = "NG";
        while(scanner.hasNextLine()){
            String password = scanner.nextLine();
            int number = 0;
            int az = 0;
            int AZ = 0;
            int el = 0;
            if(password.length() <= 8){
                System.out.println(ng);
                continue;
            }
            for (int i = 0;i < password.length();i++) {
                char c = password.charAt(i);
                if('0' <= c && c <= '9'){
                    number = 1;
                }else if('A' <= c && c <= 'Z'){
                    AZ = 1;
                }else if('a' <= c && c <= 'z'){
                    az = 1;
                }else {
                    el = 1;
                }
            }
            int count = number + az + AZ + el;
            if(count < 3){
                System.out.println(ng);
                continue;
            }
            boolean fg = true;
            List<String> list = new ArrayList<>();
            for (int i = 0;i < password.length() - 2;i++) {
                String s = String.valueOf(password.charAt(i)) + String.valueOf(password.charAt(i+1)) + String.valueOf(password.charAt(i+2));
                if(list.contains(s)){
                    System.out.println(ng);
                    fg = false;
                    break;
                }else {
                    list.add(s);
                }
            }
            if(fg){
                System.out.println(ok);
            }
        }
    }

    /**
     1、 记录最多8条错误记录，循环记录（或者说最后只输出最后出现的八条错误记录），对相同的错误记录（净文件名称和行号完全匹配）只记录一条，错误计数增加；
     2、 超过16个字符的文件名称，只记录文件的最后有效16个字符；
     3、 输入的文件可能带路径，记录文件名称不能带路径。
     */
    public static void main12(String[] args){
        Scanner scanner = new Scanner(System.in);
        Map<String,Integer> map = new LinkedHashMap<>();
        while(scanner.hasNextLine()){
            String str = scanner.nextLine();
            String[] split = str.split(" ");
            String name = split[0];
            name = name.substring(name.lastIndexOf("\\")+1);
            name = name.substring(Math.max(name.length()-16,0));
            String key = name + " " +split[1];
            int count = map.get(key) == null ? 1:map.get(key)+1;
            map.put(key,count);
        }
        int index = 0;
        for (Map.Entry<String,Integer> me:map.entrySet()) {
            if(map.size()-index <=8){
                System.out.println(me.getKey()+" "+me.getValue());
            }
            index++;
        }
    }
    
    /**  */
    public static void main10(String[] args){
        Scanner scanner = new Scanner(System.in);
        double data = Double.parseDouble(scanner.nextLine());
        double result = 0;
        for(int i = 0;i<data;i++){
            if(i*i*i == data){
                result = i;
            }
        }
        System.out.println(result);
    }

    /** 输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。 */
    public static void main9(String[] args){
        Scanner scanner = new Scanner(System.in);
        Map<String,Integer> map = new TreeMap();
        List<String> list =  new ArrayList<>();
        while (scanner.hasNextLine()) {
            int rows = Integer.parseInt(scanner.nextLine());
            for(int i = 0;i < rows;i++){
                String word = scanner.nextLine();
                Integer exist = map.put(word,1);
                if (exist != null) {
                    map.put(word,exist+1);
                }
            }
        }
        for (Map.Entry<String,Integer> me:map.entrySet()) {
            int row = me.getValue();
            for (int i = 0;i < row;i++) {
                list.add(me.getKey());
            }
        }

        for (String word:list) {
            System.out.println(word);
        }

//        Scanner input = new Scanner(System.in);
//        while (input.hasNextLine()){
//            int num = Integer.parseInt(input.nextLine());
//            ArrayList<String> list = new ArrayList<String>();
//            while(num > 0){
//                list.add(input.nextLine());
//                num--;
//            }
//            Collections.sort(list);
//            for (String s : list)
//                System.out.println(s);
//        }
    }

    /** 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
     所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符。 */
    public static void main8(String[] args){
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();
        System.out.println(reverse(data));
    }

    public static String reverse(String data){
        String[] split = data.split(" ");
        int index = split.length - 1;
        String str = "";
        for (int i = index;i >= 0 ;i--){
            String c = split[i];
            str += c + " ";
        }
        return str;
    }

    /** 输入一个整数，将这个整数以字符串的形式逆序输出
     程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001。 */
    public static void main7(String[] args){
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();
        int index = data.length()-1;
        String str = "";
        for (int i = index;i >= 0 ;i--){
            String c = String.valueOf(data.charAt(i)); // 当前字符
            str += c;
        }
        System.out.println(str);
    }

    /** 编写一个函数，计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)，
     * 换行表示结束符，不算在字符里。不在范围内的不作统计。 */
    public static void main6(String[] args){
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();
        int index = data.length()-1;
        int count = 0;
        String str = "";
        for (int i = index;i >= 0 ;i--){
            String c = String.valueOf(data.charAt(i)); // 当前字符
            if (!str.contains(c)) {
                str += c;
                count++;
            }
        }
        System.out.println(count);
    }

    /** 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数 */
    public static void main5(String[] args){
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();
        int index = data.length()-1;
        String str = String.valueOf(data.charAt(index));
        for (int i = index;i >= 0 ;i--){
            String c = String.valueOf(data.charAt(i)); // 当前字符
            if (!str.contains(c)) {
                str += c;
            }
        }
        System.out.println(str);
    }
    
    public static void main4(String[] args){
        Scanner scanner = new Scanner(System.in);
        double flo = scanner.nextDouble();
        double ceil = Math.floor(flo);
        double c = flo - ceil;
        if(c >= 0.5){
            System.out.println((int)Math.ceil(flo));
        }else {
            System.out.println((int)Math.floor(flo));
        }
    }

    public static void main3(String[] args){
        Scanner scanner = new Scanner(System.in);
        int sum = scanner.nextInt();
        List<Integer> dataList = new ArrayList<>();
        get(sum,dataList);

        for (int i = dataList.size() - 1;i >=0 ;i--) {
            System.out.print(dataList.get(i) + " ");
        }

    }

    private static void get(int sum,List dataList) {
        int min = sum;
        for(int i = 2;i<sum;i++){
            if(sum % i == 0){
                min = i;
                int m = sum/i;
                get(m,dataList);
                break;
            }
        }
        dataList.add(min);
    }

    public static void main2(String[] args){

        Scanner scanner = new Scanner(System.in);

        Map<Integer,Integer> map = new TreeMap<>();

        while (scanner.hasNextLine()) {
            int count = Integer.valueOf(scanner.nextLine());
            for (int j = 0 ; j<count ;j++) {
                String str = scanner.nextLine();
                String[] array = str.split(" ");
                Integer key = Integer.parseInt(array[0]);
                Integer value = Integer.parseInt(array[1]);
                Integer mapValue = map.get(key);
                int i = mapValue == null?0:mapValue;
                int total = i + value;
                map.put(key,total);
            }

            for (Map.Entry<Integer,Integer> data:map.entrySet()) {
                System.out.println(data.getKey() + " " + data.getValue());
            }
        }

    }
}