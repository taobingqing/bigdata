package String_test;

public class Test01 {
    public static void main(String[] args) {


        String s = String.valueOf(1);
        System.out.println(s.getClass());

        String fs;
        /**
         *  双引号 % 引用
         */
        fs = String.format("浮点型变量的值为 " +
                "%f, 整型变量的值为 " +
                " %d, 字符串变量的值为 " +
                " %s", 1.2, 2, "zifuchuan");
        System.out.println(fs);

    }
}
