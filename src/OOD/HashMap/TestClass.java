package OOD.HashMap;

import java.util.HashMap;
import java.util.Map;

public class TestClass {
    public static void main(String[] args) {
        MyMap<Student, String> map = new MyMap<>(2);

        System.out.println("Insert [std1, haha]=====================");
        Student std1 = new Student("abcd123");
        map.put(std1, "haha");
        System.out.println(map.size());
        System.out.println(map.get(std1));


        System.out.println("Update [std1, xixi]=====================");
        Student std2 = new Student("abcd123");
        map.put(std2, "xixi");
        System.out.println(map.size());
        System.out.println(map.get(std1));
        System.out.println(map.get(std2));


        System.out.println("Insert [std3, wuwu], [std4, shuapaoche], rehashing===========");
        Student std3 = new Student("666");
        map.put(std3, "wuwu");

        Student std4 = new Student("laotie");
        map.put(std4, "shuapaoche");
        System.out.println(map.size());
        System.out.println(map.get(std3));
        System.out.println(map.get(std4));


        System.out.println("Get & remove non-existing element============");
        Student std5 = new Student("Xiaotouming");
        System.out.println(map.get(std5));
        System.out.println(map.remove(std5));


        System.out.println("Insert [null, WoShiNull]====================");
        map.put(null, "WoShiNull");
        System.out.println(map.size());
        System.out.println(map.get(null));


        System.out.println("Update [null, XinDeNull]====================");
        map.put(null, "XinDeNull");
        System.out.println(map.size());
        System.out.println(map.get(null));


        System.out.println("Remove null=================================");
        map.remove(null);
        System.out.println(map.size());


        System.out.println("Inserting 100 students");
        for (int i = 0; i < 512; i++) {
            int id = i * 31;
            map.put(new Student(Integer.toString(id)), Integer.toString(i));
        }
        System.out.println(map.size());
        System.out.println(map.get(std1));
    }
}
