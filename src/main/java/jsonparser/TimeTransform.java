package jsonparser;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeTransform {
    private static String simpleDateFormat = "yyyy-MM-dd-HH:mm";
    private static String unsimpleDateFormat = "yyyyMMddHHmm";

    public static String getDate(long timestamp) {
        return DateFormatUtils.format(new Date(timestamp), simpleDateFormat);
    }

    public static long getTimestamp(String date) throws ParseException {
        return DateUtils.parseDate(date, unsimpleDateFormat).getTime();
    }

    public static Date getDate(String date) throws ParseException {
        return DateUtils.parseDate(date, unsimpleDateFormat);
    }

    /**
     * 用当前时间替换文本中的时间变量
     * @param template
     * @return
     */
    public static String replaceTimePlaceHolder(String template)
    {
       return replaceTimePlaceHolder( new DateTime(),template );
    }

    /**
     * 用 指定时间 替换文本中的时间变量
     * @param time
     * @param template
     * @return
     */
    public static String replaceTimePlaceHolder(DateTime time, String template) {
        try {

            String regex = "#\\{time\\s*(([+-])\\s*(\\d+)\\s*(\\S+)){0,1}\\s*(\\s+(.+?)){0,1}\\s*\\}";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(template);

            while (matcher.find()) {
                String matched = matcher.group();
                String operate = matcher.group(1);
                String format = matcher.group(6);
                if (format == null) {
                    format = "yyyy-MM-dd HH:mm:ss";
                }
                format = format.trim();


                if (operate != null) {
                    String operator = matcher.group(2);
                    String mi = matcher.group(3);
                    String unit = matcher.group(4);
                    int amount = Integer.parseInt(mi);
                    if ("+".equals(operator)) {
                        switch (unit) {
                            case "m":
                                time = time.plusMinutes(amount);
                                break;
                            case "H":
                                time = time.plusHours(amount);
                                break;
                            case "d":
                                time = time.plusDays(amount);
                                break;
                            case "M":
                                time = time.plusMonths(amount);
                                break;
                            case "y":
                                time = time.plusYears(amount);
                                break;
                            default:
                                time = time.plusMinutes(amount);
                        }
                    } else if ("-".equals(operator)) {
                        switch (unit) {
                            case "m":
                                time = time.minusMinutes(amount);
                                break;
                            case "H":
                                time = time.minusHours(amount);
                                break;
                            case "d":
                                time = time.minusDays(amount);
                                break;
                            case "M":
                                time = time.minusMonths(amount);
                                break;
                            case "y":
                                time = time.minusYears(amount);
                                break;
                            default:
                                time = time.minusMinutes(amount);
                        }
                    }
                }
                String timeStr = time.toString(format);
                template = template.replaceAll(Pattern.quote(matched), Matcher.quoteReplacement(timeStr));
                matcher = pattern.matcher(template);
            }
        } catch (Exception e) {
            return template;
        }
        return template;
    }


    public static void main(String[] args) {
           System.out.println(TimeTransform.replaceTimePlaceHolder( new DateTime(), " --- #{time +1d yyyyMMddhhmmss}  ----") );
    }

}
