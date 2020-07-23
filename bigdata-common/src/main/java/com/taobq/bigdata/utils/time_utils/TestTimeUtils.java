package com.taobq.bigdata.utils.time_utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.MutableDateTime;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.Date;

public class TestTimeUtils {

    @Test
    public void test01() {
        System.out.println(TimeUtils.formatDateToMm5(DateTime.now()));
        MutableDateTime mdt = new MutableDateTime(new Date());
        MutableDateTime mdt2 = mdt.hourOfDay().roundFloor();

        DateTimeFormatter miFormater = DateTimeFormat.forPattern("yyyyMMddHHmm");
        concatSout("mdt", mdt2.toString(miFormater));

        DividedDateTimeField dividedDateTimeField = new DividedDateTimeField(
                CopticChronology.getInstance().minuteOfDay(), DateTimeFieldType.minuteOfDay(), 5);
        concatSout("dividedDateTimeField",dividedDateTimeField);
        concatSout("CopticChronology.getInstance().minuteOfDay()",CopticChronology.getInstance().minuteOfDay());
        System.out.println(mdt + "|" + mdt2);
    }


    /**
     * 打印输出的内容
     * @param content
     * @param object
     */
    public void concatSout(String content, Object object) {
        System.out.println("======   " + content + " :  " + object + "  =======");

    }
}
