package com.taobq.bigdata.utils.time_utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.MutableDateTime;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

	private static Logger logger = LoggerFactory.getLogger(TimeUtils.class);

	private final static DateTimeFormatter miFormater = DateTimeFormat.forPattern("yyyyMMddHHmm");

	private final static DateTimeFormatter dayFormater = DateTimeFormat.forPattern("yyMMdd");

	private final static DateTimeFormatter normalFormater = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	private final static DateTimeField minutes5Field = new DividedDateTimeField(
			CopticChronology.getInstance().minuteOfDay(), DateTimeFieldType.minuteOfDay(), 5);

	private final static DateTimeField minutes15Field = new DividedDateTimeField(
			CopticChronology.getInstance().minuteOfDay(), DateTimeFieldType.minuteOfDay(), 15);

	public static String formatToMm60(Date date) {
		MutableDateTime mdt = new MutableDateTime(date);
		mdt.hourOfDay().roundFloor();
		return mdt.toString(miFormater);
	}

	public static String formatToDd(Date date) {
		MutableDateTime mdt = new MutableDateTime(date);
		mdt.dayOfMonth().roundFloor();
		return mdt.toString(dayFormater);
	}

	public static String formatToDay(Date date) {
		MutableDateTime mdt = new MutableDateTime(date);
		mdt.dayOfMonth().roundFloor();
		return mdt.toString(miFormater);
	}

	public static String formatToMm60(long time) {
		MutableDateTime mdt = new MutableDateTime(time);
		mdt.hourOfDay().roundFloor();
		return mdt.toString(miFormater);
	}

	public static String formatToDd(long time) {
		MutableDateTime mdt = new MutableDateTime(time);
		mdt.dayOfMonth().roundFloor();
		return mdt.toString(dayFormater);
	}

	/**
	 * 时间输出格式->1603091503
	 */
	public static String formatDateToMm(Date date) {
		return miFormater.print(date.getTime());
	}

	/**
	 * 时间输出格式->1603091503
	 */
	public static String formatDateToMm(MutableDateTime mdt) {
		return mdt.toString(miFormater);
	}

	/**
	 * 时间输出格式->2016-03-09 15:08:48
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return normalFormater.print(date.getTime());
	}

	/**
	 * 时间输出格式->2016-03-09 15:08:48
	 * 
	 * @param time
	 * @return
	 */
	public static String formatDate(long time) {
		return normalFormater.print(time);
	}

	public static Date parseDateOrNow(String s) {
		if (StringUtils.isEmpty(s)) {
			return new Date();
		}
		return normalFormater.parseDateTime(s).toDate();
	}

	public static Date parseDate(String s) {
		return normalFormater.parseDateTime(s).toDate();
	}

	public static DateTime parseDateToDateTime(String s) {
		return normalFormater.parseDateTime(s);
	}

	public static Date parseDateMM(String s) {
		return miFormater.parseDateTime(s).toDate();
	}

	public static DateTime parseDateMMToDateTime(String s) {
		return miFormater.parseDateTime(s);
	}

	public static String formatDateToMm5(DateTime date) {
		MutableDateTime mdt = new MutableDateTime(date);
		mdt.setRounding(minutes5Field);
		return mdt.toString(miFormater);
	}

	public static String formatDateToMm15(Date date) {
		MutableDateTime mdt = new MutableDateTime(date);
		mdt.setRounding(minutes15Field);
		return mdt.toString(miFormater);
	}

	public static String formatDateToMm5(long date) {
		MutableDateTime mdt = new MutableDateTime(date);
		mdt.setRounding(minutes5Field);
		return mdt.toString(miFormater);
	}

	public static Date roundDateToMm5(Date date) {
		MutableDateTime mdt = new MutableDateTime(date);
		mdt.setRounding(minutes5Field);
		return mdt.toDate();
	}

	public static Date roundDateToMm15(Date date) {
		MutableDateTime mdt = new MutableDateTime(date);
		mdt.setRounding(minutes15Field);
		return mdt.toDate();
	}

	public static Date roundDateToMm(Date date) {
		MutableDateTime mdt = new MutableDateTime(date);
		mdt.minuteOfHour().roundFloor();
		return mdt.toDate();
	}

	public static Date roundDateToHour(Date date) {
		MutableDateTime mdt = new MutableDateTime(date);
		mdt.hourOfDay().roundFloor();
		return mdt.toDate();
	}

	public static Date roundDateToDay(Date date) {
		MutableDateTime mdt = new MutableDateTime(date);
		mdt.dayOfMonth().roundFloor();
		return mdt.toDate();
	}

	public static void trySleepMs(long ms) {
		try {
			TimeUnit.MILLISECONDS.sleep(ms);
		} catch (InterruptedException e) {
			logger.error("", e);
		}
	}

	/**
	 * 休息时间sec
	 * 
	 * @param sec
	 */
	public static void trySleepSec(long sec) {
		try {
			TimeUnit.SECONDS.sleep(sec);
		} catch (InterruptedException e) {
			logger.error("", e);
		}
	}

}
