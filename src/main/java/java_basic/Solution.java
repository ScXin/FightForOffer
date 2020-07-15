package java_basic;

import java.util.*;

/**
 * @author ScXin
 * @date 9/1/2019 5:11 PM
 */

class Student implements Comparable<Student> {
    String name;
    String address;

    public Student(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }


    public static void main(String[] args) {
//        Queue<Integer> queue = new LinkedList<>();
//        queue.offer(1);
//        queue.offer(2);
//        queue.offer(3);
//        queue.offer(4);
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
        Student st1 = new Student("zhangsan", "chengdu");
        Student st2 = new Student("wanghu", "chongqing");
        Student st3 = new Student("jiangyi", "hangzhou");
        Student st4 = new Student("shangxin", "hefie");
        List<Student> list = new ArrayList<Student>();
        list.add(st1);
        list.add(st2);
        list.add(st3);
        list.add(st4);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());
        }
    }
}
