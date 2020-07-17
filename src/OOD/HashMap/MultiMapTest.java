package OOD.HashMap;

import java.sql.Time;
import java.util.List;

public class MultiMapTest {
    public static void main(String[] args) {
        MultiMap<Student, String> map = new MultiMap<>(10);
        System.out.println("size = " + map.size() + ", expected: 0");


        System.out.println("\nTest: put new keys");
        Student std1 = new Student("PangHu");
        map.put(std1, "ZhenPang");
        Student std2 = new Student("DaXiong");
        map.put(std2, "ZhenXiong");
        Student std3 = new Student("JingXiang");
        map.put(std3, "ZhenXiang");
        map.put(null, "WoShiNull");

        System.out.println("size = " + map.size() + ", expected: 4");
        System.out.println("get(std1): " + map.get(std1) + ", expected: [ZhenPang]");
        System.out.println("get(std2): " + map.get(std2) + ", expected: [ZhenXiong]");
        System.out.println("get(std3): " + map.get(std3) + ", expected: [ZhenXiang]");
        System.out.println("get(null): " + map.get(null) + ", expected: [WoShiNull]");


        System.out.println("\nTest: put existing keys");
        Student std4 = new Student("PangHu");
        map.put(std4, "ZhenHu");
        Student std5 = new Student("DaXiong");
        map.put(std5, "ZhenDa");
        Student std6 = new Student("JingXiang");
        map.put(std6, "ZhenJing");
        map.put(null, null);

        System.out.println("size = " + map.size() + ", expected: 4");
        System.out.println("get(std1): " + map.get(std1) + ", expected: [ZhenPang, ZhenHu]");
        System.out.println("get(std4): " + map.get(std4) + ", expected: [ZhenPang, ZhenHu]");
        System.out.println("get(std2): " + map.get(std2) + ", expected: [ZhenXiong, ZhenDa]");
        System.out.println("get(std5): " + map.get(std5) + ", expected: [ZhenXiong, ZhenDa]");
        System.out.println("get(std3): " + map.get(std3) + ", expected: [ZhenXiang, ZhenJing]");
        System.out.println("get(std6): " + map.get(std6) + ", expected: [ZhenXiang, ZhenJing]");
        System.out.println("get(null): " + map.get(null) + ", expected: [WoShiNull, null]");


        System.out.println("\nTest: get non-existing key");
        Student std7 = new Student("BuPeiYongYouXingMing");
        System.out.println("get(std7): " + map.get(std7) + ", expected: null");


        System.out.println("\nTest: remove existing key");
        System.out.println("remove(std1): " + map.remove(std1) + ", expected: true");
        System.out.println("size = " + map.size() + ", expected: 3");
        System.out.println("get(std4): " + map.get(std4) + ", expected: null");


        System.out.println("\nTest: remove non-existing key");
        System.out.println("remove(std7): " + map.remove(std7) + ", expected: false");
        System.out.println("size = " + map.size() + ", expected: 3");


        System.out.println("\nTest: rehashing");
        long start = System.nanoTime();
        map.get(std2);
        map.get(std3);
        map.get(null);
        long end = System.nanoTime();
        long timeBefore = end - start;

        for (int i = 0; i < 1024; i++) {
            map.put(new Student(Integer.toString(i)), Integer.toString(i * 31));
        }
        System.out.println("size = " + map.size() + ", expected: 1027");
        System.out.println("get(std2): " + map.get(std2) + ", expected: [ZhenXiong, ZhenDa]");

        start = System.nanoTime();
        map.get(std2);
        map.get(std3);
        map.get(null);
        end = System.nanoTime();
        long timeAfter = end - start;

        System.out.println("Time consuming (ns) before rehashing: " + timeBefore);
        System.out.println("Time consuming (ns) after rehashing: " + timeAfter);


        System.out.println("\nTest: asMap");
        MyMap<Student, List<String>> myMap = map.asMap();
        System.out.println("myMap.size() = " + myMap.size() + ", expected: 1027");
        System.out.println("myMap.get(std3): " + myMap.get(std3) + ", expected: [ZhenXiang, ZhenJing]");
    }
}
