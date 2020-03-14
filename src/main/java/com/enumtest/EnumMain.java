package com.enumtest;

public class EnumMain {



    public static void main(String[] args) {


        for (EnumDemo e : EnumDemo.values()) {
            System.out.println(e.toString());
        }

        System.out.println("----------------我是分隔线------------------");

        EnumDemo test = EnumDemo.TUE;
        System.out.println(test);
        switch (test) {
            case MON:
                System.out.println("今天是星期一");
                break;
            case TUE:
                System.out.println("今天是星期二");
                break;
            // ... ...
            default:
                System.out.println(test);
                break;
        }


        test02(OperationType.insert );



    }

    public static void test02(EnumMain.OperationType batchtype){
        /**
         * 序号 从 0 开始
         */
        System.out.println(batchtype.ordinal());
        /**
         * 常量的内容
         */
        System.out.println(batchtype.name());

    }

   public enum OperationType
    {
        insert,

        update,

        delete,

        truncate;
    }



}
