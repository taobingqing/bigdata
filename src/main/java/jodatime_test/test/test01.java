package jodatime_test.test;

import jodatime_test.DateUtils;
import jodatime_test.TimeKey;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;

public class test01 {

    @Test
    public void dateUtilsTest() {
        long l = System.currentTimeMillis();
        String format = DateUtils.formatToMm5(l);
        System.out.println(format);
    }


    @Test
    public void timekeyTest(){
        DateTime now = DateTime.now();
        Date date = now.toDate();
        TimeKey timeKey = TimeKey.fromTime(date); // 5分钟粒度
        String key = timeKey.getKey();
        System.out.println(key);

    }

}
