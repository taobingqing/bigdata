package com.taobq.web.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class slf4jDemo extends slf4jDemoFather{
//    public static final Logger logger = LoggerFactory.getLogger(slf4jDemo.class);

    public static void main(String[] args) {
        logger.info("info ===========");
        System.out.println("end ==============");
    }
}
