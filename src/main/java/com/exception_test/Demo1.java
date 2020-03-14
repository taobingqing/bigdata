package com.exception_test;

import org.apache.log4j.Logger;

public class Demo1 {
    private static Logger logger = Logger.getLogger(Demo1.class);

    public static void main(String[] args) {

        try {
            for (int i = 0; i < 10; i++) {
                try{
                    System.out.println(Math.abs(10 / i));
                }catch (Exception e ){
                    logger.warn("error...",e);
                }

            }

            System.out.println("for 循环之后");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("==========");


    }
}
