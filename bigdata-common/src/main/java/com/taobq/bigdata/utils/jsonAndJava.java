package com.taobq.bigdata.utils;

import com.taobq.bigdata.data.TestEnum;

/**
 * json和java互转
 */
public class jsonAndJava {

    public static void main(String[] args) {

        TestEnum none = TestEnum.NONE;

        System.out.println(none.name().toLowerCase() .equals("none")  );
    }

}
