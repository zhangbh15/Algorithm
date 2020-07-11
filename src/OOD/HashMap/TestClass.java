package OOD.HashMap;

import java.util.HashMap;
import java.util.Map;

public class TestClass {
    public static void main(String[] args) {
        Map<Student, Integer> map = new HashMap<>();
        Student std1 = new Student("abcd123");
        Student std2 = new Student("abcd123");
        map.put(std1, 1);
        map.put(std2, 2);
        System.out.println(map.size());
        for (Student std : map.keySet()) {
            System.out.println(std.getId());
            System.out.println(map.get(std));
        }
    }
}
