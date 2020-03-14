package com.enumtest;


import java.util.EnumSet;

public class EnumDemo1 {
    public static void main(String[] args) {

        EnumDemo test = EnumDemo.TUE;

        //compareTo(E o)
        switch (test.compareTo(EnumDemo.MON)) {
            case -1:
                System.out.println("TUE 在 MON 之前");
                break;
            case 1:
                System.out.println("TUE 在 MON 之后");
                break;
            default:
                System.out.println("TUE 与 MON 在同一位置");
                break;
        }

        //getDeclaringClass()
        System.out.println("getDeclaringClass(): " + test.getDeclaringClass().getName());

        //name() 和  toString()
        System.out.println(test);
        System.out.println("name(): " + test.name());
        System.out.println("toString(): " + test.toString());

        //ordinal()， 返回值是从 0 开始
        System.out.println("ordinal(): " + test.ordinal());

        System.out.println("-------分割线---------");

        EnumSet<EnumDemo> enumSet = EnumSet.allOf(EnumDemo.class);
        for (EnumDemo day : enumSet) {
            System.out.println(day);
        }

        System.out.println("-------分割线---------");


    }
}
