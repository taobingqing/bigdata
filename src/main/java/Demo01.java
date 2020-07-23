import com.enumtest.EnumDemo;
import org.junit.Test;
import scala.math.Ordering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Demo01 {

    //可以看到相同类型的属性  可以连写
    public static final int a = 1, b = 2;

    @Test
    public void SOH() {
        String string1 = "HOW";
        String string2 = "DO";
        String string3 = "YOU";
        String string4 = "DO";
        StringBuffer clickBuffer = new StringBuffer();
        clickBuffer.append(string1).append("\001").append(string2).append("\002").append(string3).append("\003").append(string4);
        System.out.println(clickBuffer);
        String replace = clickBuffer.toString().replace("\001", ",");
        System.out.println(replace);
    }

    @Test
    public void urlTest() {


    }

    /**
     * split  "/"
     */
    @Test
    public void split01() {
        String[] split = "/0/0/1/0/9/".split("/");
        System.out.println(Arrays.asList(split));

    }

    @Test
    public void propertiesPutTest() {
        Properties p = new Properties();
        p.setProperty("aa", "tao");
        p.setProperty("bb", "bing");
        p.setProperty("cc", "qing");
        System.out.println(p.toString());
        p.setProperty("aa", "bing");
        System.out.println(p.toString());
        System.out.println(p.getProperty("aa"));
        System.out.println(UUID.randomUUID().toString());
    }

    @Test
    public void doubleTest() {
        Double a = 0d;
        a = Double.valueOf("2000");
        System.out.println(a);
        double b = 2000.0;
        System.out.println(b >= a == true);

        String[] arr = new String[32];
        arr[2] = "";
        System.out.println(arr[2]);
        System.out.println("".length());

        HashSet<String> objects = new HashSet<>();
        System.out.println(objects.size());
        System.out.println(objects == null);
        for (String object : objects) {
            System.out.println(object.split(","));
        }
    }

    @Test
    public void huoTest() {

        int a = 2;
        if ((1 == 1) || (++a == 1)) {
            System.out.println("true");
        }
        System.out.println(a);
    }

    @Test
    public void nullTest() throws UnsupportedEncodingException {

        String s = "a|a|Null";
        String s1 = s.split("\\|")[2];
        System.out.println(s1);
        System.out.println(s1.toLowerCase().equals("null"));
        System.out.println(s1.toLowerCase());
        System.out.println("".getBytes("utf-8"));
    }

    @Test
    public void splitRegex() {
//        final Pattern linePattern = Pattern.compile("(\n)|(\r\n)");
        final Pattern linePattern = Pattern.compile("\r");

        String s = "aaaa\n bbbb  \n  cc  ";

        String[] split = linePattern.split(s.trim());
        for (String s1 : split) {
            System.out.println(s1.trim());
        }
        System.out.println(split.length);
    }

    /**
     * 测试  list 装载复杂对象 比较大小
     */
    @Test
    public void compartList() {


        List<Students> students = new ArrayList<Students>();
        students.add(new Students(23, 100));
        students.add(new Students(30, 98));
        students.add(new Students(27, 98));
        students.add(new Students(29, 99));
        students.add(new Students(29, 98));
        students.add(new Students(22, 89));
        Collections.sort(students, new Comparator<Students>() {

            @Override
            public int compare(Students o1, Students o2) {
                int i = o1.getScore() - o2.getScore();
//                if (i == 0) {
//                    return o1.getAge() - o2.getAge();
//                }
                return i;
            }
        });
        for (Students stu : students) {
            System.out.println("score:" + stu.getScore() + ":age" + stu.getAge());
        }
    }

    @Test
    public void testStream() {

        ArrayList<String> objects = new ArrayList<>();
        objects.add("a");
        objects.add("abc");
        objects.add("abcd");
        objects.add("b");
        objects.add("c");
        objects.add("f");
        objects.add("g");

        int count = (int) objects.stream().filter(x -> x.contains("f")).count();
        System.out.println(count);

        System.out.println(EnumDemo.class.getSimpleName());
    }

    /**
     * stream 用法
     */
    @Test
    public void testStream02() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 5);
        stream.forEach(x -> {
            Students students = new Students(x, x);
            System.out.println(students.toString());
        });
    }

    /**
     * 测试  concat 和  stringbuilder
     */
    @Test
    public void testConcatAndStringbuilder() {
        int i = 0;
        long start = System.currentTimeMillis();
        do {
            String a = "start";
            String ss = a.concat("|").concat(String.valueOf(i));
            i++;
        } while (i <= 1000000);
        System.out.println("concat 耗时： " + (System.currentTimeMillis() - start));

        i = 0;
        long start2 = System.currentTimeMillis();
        String b = "start";
        do {
            StringBuilder sb = new StringBuilder();
            String ss = sb.append(b).append("|").append(String.valueOf(i)).toString();
            i++;
        } while (i <= 1000000);

        System.out.println("concat 耗时： " + (System.currentTimeMillis() - start2));
    }

    /**
     * new String[0]就是起一个模板的作用，指定了返回数组的类型，
     * 0是为了节省空间，因为它只是为了说明返回的类型
     */
    @Test
    public void testListToarr() {
        LinkedList<Object> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Integer[] arr = list.toArray(new Integer[0]);
        System.out.println(new Integer[3].length);
        System.out.println(arr.length);
    }

    @Test
    public void testSystemcopyarr() {
        String[] str = new String[32];
        String[] source = {"a", "b", "c", "d"};
        System.arraycopy(source, 0, str, 0, source.length);
        //[a, b, c, d, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null]
        System.out.println(Arrays.toString(str));
    }

    /**
     * 测试  && and  ||
     */
    @Test
    public void testYuandHuo() {
        boolean a = false;
        boolean b = true;
        boolean c = true;

        System.out.println(a && b || c);
    }

    /**
     * @test  测试 hashmap 和 linkedhashmap  插入顺序
     * hashmap  遍历时无序
     * linkedhasmp  遍历时按插入顺序
     */
    @Test
    public void testLinkHashMap(){
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("1", "a");
        map.put("2", "a");
        map.put("3", "a");
        map.put("4", "a");
        map.put("5", "a");
        map.put("6", "a");
        map.put("7", "a");
        map.put("8", "a");
        map.put("9", "a");
        map.put("10", "a");
        map.put("11", "a");
        map.put("12", "a");
        map.put("13", "a");
        map.put("14", "a");
        map.put("15", "a");
        map.put("16", "a");
        map.put("17", "a");
        map.put("18", "a");
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }

        System.out.println(map.get("a") == null);
        System.out.println("===========================");
        Map<Object, Object> hashMap = new HashMap<>();
        hashMap.put("1", "a");
        hashMap.put("2", "a");
        hashMap.put("3", "a");
        hashMap.put("4", "a");
        hashMap.put("5", "a");
        hashMap.put("6", "a");
        hashMap.put("7", "a");
        hashMap.put("8", "a");
        hashMap.put("9", "a");
        hashMap.put("10", "a");
        hashMap.put("11", "a");
        hashMap.put("12", "a");
        hashMap.put("13", "a");
        hashMap.put("14", "a");
        hashMap.put("15", "a");
        hashMap.put("16", "a");
        hashMap.put("17", "a");
        hashMap.put("18", "a");

        for (Map.Entry<Object, Object> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }

    }



    class Students {

        private int age;
        private int score;

        @Override
        public String toString() {
            return "Students{" +
                    "age=" + age +
                    ", score=" + score +
                    '}';
        }

        public Students(int age, int score) {
            super();
            this.age = age;
            this.score = score;
        }

        public Students() {

        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }

}
