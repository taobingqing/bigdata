package date_utils;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test01 {
    @Test
    public void test1() throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");//设置日期格式
        String date = df.format(new Date());
        System.out.println(date);

        String unsimpleDateFormat = "yyyyMMddHHmm";

        Date date1 = new Date();// new Date()为获取当前系统时间
        System.out.println(date1);//Tue Jun 18 16:03:00 CST 2019

        long time = DateUtils.parseDate(date, unsimpleDateFormat).getTime();
        Date date2 = DateUtils.parseDate(date, unsimpleDateFormat);

        System.out.println(date2);//Tue Jun 18 16:03:00 CST 2019
        System.out.println(time);//1560844980000
    }

    @Test
    public void test2() throws InterruptedException {
        long lastAuthTime = System.currentTimeMillis();
        System.out.println(lastAuthTime);
        // Thread.currentThread().sleep(2*1000);

        int effectiveHours = 23;
        //日期 加 小时 getTime() 返回毫秒
        long time = DateUtils.addHours(new Date(lastAuthTime), effectiveHours).getTime();
        //毫秒转化为 日期格式
        System.out.println(new Date(lastAuthTime));

        System.out.println(time);
        //82800000毫秒 = 23 h
        System.out.println(time - lastAuthTime);

    }

    @Test
    public void test03() {
        /**
         * 这种格式转换会报错
         */
        String s = "2019-10-25 102941";
        Date date = new Date(s);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HHmmss");
        String format = df.format(date);
        System.out.println(format);
    }

    @Test
    public void test04() {
        int hours = 24;
        DateTime dateTime = DateTime.now().minusHours(hours);
        //获取小时
        System.out.println(dateTime.hourOfDay().get());
        Date latestTime = dateTime.toDate();
        System.out.println("latesttime :"+latestTime);
        //返回 时间戳毫秒数
        long time = latestTime.getTime();
        System.out.println(time);

        Date date = new DateTime(time).plusHours(1).toDate();
        System.out.println("date : "+date);
        System.out.println(date.after(latestTime));
    }

    /**
     * 测试 现网 DateUtils
     */
    @Test
    public void test05(){
        //formatToMm1 取当前时间 没有四舍五入
        String s = date_utils.DateUtils.formatToMm1(System.currentTimeMillis());
        System.out.println(s);
        //formatToMm60  取当前时间向上取小时整
        String s1 = date_utils.DateUtils.formatToMm60(System.currentTimeMillis());
        System.out.println(s1);

        MutableDateTime mutableDateTime = new MutableDateTime(System.currentTimeMillis());
        mutableDateTime.hourOfDay();
        String s2 = mutableDateTime.toString("yyMMddHHmm");
        System.out.println(s2);
    }

    @Test
    public void  test06(){
    }

}
