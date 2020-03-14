package jodatime_test;

import java.util.Date;

import static jodatime_test.TimeUtils.*;


public class TimeKey {

	public static final TimeKey EMPTY_TIMEKEY;

	private Date time;

	private String key;

	static {
		EMPTY_TIMEKEY = new TimeKey();
		EMPTY_TIMEKEY.setKey("");
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return this.key;
	}

	public static TimeKey fromTimeMM(String timeMM5) {
		TimeKey timeKey = new TimeKey();
		timeKey.setKey(timeMM5);
		timeKey.setTime(TimeUtils.parseDateMM(timeMM5));
		return timeKey;
	}

	public static TimeKey fromTime(Date time) {
		TimeKey timeKey = new TimeKey();
		timeKey.setKey(formatDateToMm5(time));
		timeKey.setTime(roundDateToMm5(time));
		return timeKey;
	}

	public static TimeKey fromTime15(Date time) {
		TimeKey timeKey = new TimeKey();
		timeKey.setKey(formatDateToMm15(time));
		timeKey.setTime(roundDateToMm15(time));
		return timeKey;
	}

	public static TimeKey fromTime1(Date time) {
		TimeKey timeKey = new TimeKey();
		timeKey.setKey(formatDateToMm(time));
		timeKey.setTime(roundDateToMm(time));
		return timeKey;
	}

	public static TimeKey fromTime60(Date time) {
		TimeKey timeKey = new TimeKey();
		timeKey.setKey(TimeUtils.formatToMm60(time));
		timeKey.setTime(TimeUtils.roundDateToHour(time));
		return timeKey;
	}

	public static TimeKey fromTimeDay(Date time) {
		TimeKey timeKey = new TimeKey();
		timeKey.setKey(TimeUtils.formatToDd(time));
		timeKey.setTime(TimeUtils.roundDateToDay(time));
		return timeKey;
	}

	public static TimeKey fromTimeDd(Date time) {
		TimeKey timeKey = new TimeKey();
		timeKey.setKey(TimeUtils.formatToDay(time));
		timeKey.setTime(TimeUtils.roundDateToDay(time));
		return timeKey;
	}

	public static TimeKey fromTime(Date time, int granularity) {
		switch (granularity) {
		case 1:
			return fromTime1(time);
		case 60:
			return fromTime60(time);
		default:
			return fromTime(time);
		}
	}

}
