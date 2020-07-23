package date_utils;

import org.joda.time.DateTime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeHelper {

	public static String replaceTimePlaceHolder(long timems, String tpl) {
		String regex = "#\\{time\\s*(([+-])\\s*(\\d+)\\s*(\\S+)){0,1}\\s*(\\s+(.+?)){0,1}\\s*\\}";
		tpl = replaceTimePlaceHolder(regex, timems, tpl);
//		System.out.println(tpl);

		DateTime time = new DateTime(timems);
//		System.out.println(time.minuteOfHour());
		time = time.minuteOfHour().roundFloorCopy();
//		System.out.println(time);
//		System.out.println( time.minuteOfHour().setCopy(1));
		time = time.minuteOfHour().setCopy(time.minuteOfHour().get() / 5 * 5); // 16:34  34/5 *5 -> 16:30
//		System.out.println(time);
		timems = time.getMillis();
		regex = "#\\{time5\\s*(([+-])\\s*(\\d+)\\s*(\\S+)){0,1}\\s*(\\s+(.+?)){0,1}\\s*\\}";
		tpl = replaceTimePlaceHolder(regex, timems, tpl);
		return tpl;
	}

	public static String replaceTimePlaceHolder(String regex, long timems, String tpl) {
		try {
			DateTime time = new DateTime(timems);
			Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
			Matcher matcher = pattern.matcher(tpl);
			while (matcher.find()) {
				String matched = matcher.group();
				System.out.println(matched);
				String operate = matcher.group(1);
				System.out.println(operate);
				String format = matcher.group(6);
				System.out.println(format);
				if (format == null) {
					format = "yyyy-MM-dd HH:mm:ss";
				}
				format = format.trim();

				DateTime dateTime = time;
				if (operate != null) {
					String operator = matcher.group(2);
					System.out.println("operator: "+operator);
					String mi = matcher.group(3);
					System.out.println("mi: "+mi);
					String unit = matcher.group(4);
					System.out.println("unit: "+unit);
					int amount = Integer.parseInt(mi);
					if ("+".equals(operator)) {
						switch (unit) {
						case "m":
							dateTime = dateTime.plusMinutes(amount);
							break;
						case "H":
							dateTime = dateTime.plusHours(amount);
							break;
						case "d":
							dateTime = dateTime.plusDays(amount);
							break;
						case "M":
							dateTime = dateTime.plusMonths(amount);
							break;
						case "y":
							dateTime = dateTime.plusYears(amount);
							break;
						default:
							dateTime = dateTime.plusMinutes(amount);
							break;
						}
					} else if ("-".equals(operator)) {
						switch (unit) {
						case "m":
							dateTime = dateTime.minusMinutes(amount);
							break;
						case "H":
							dateTime = dateTime.minusHours(amount);
							break;
						case "d":
							dateTime = dateTime.minusDays(amount);
							break;
						case "M":
							dateTime = dateTime.minusMonths(amount);
							break;
						case "y":
							dateTime = dateTime.minusYears(amount);
							break;
						default:
							dateTime = dateTime.minusMinutes(amount);
							break;
						}
					}
				}
				String timeStr = dateTime.toString(format);
				System.out.println(timeStr);
				tpl = tpl.replaceAll(Pattern.quote(matched), Matcher.quoteReplacement(timeStr));
				System.out.println(Matcher.quoteReplacement(timeStr));
				matcher = pattern.matcher(tpl);
			}
			return tpl;
		} catch (Exception e) {
			e.printStackTrace();
			return tpl;
		}
	}

}
