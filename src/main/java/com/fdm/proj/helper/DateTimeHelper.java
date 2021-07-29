package com.fdm.proj.helper;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface DateTimeHelper {
	
	public static final List<Long> times = Arrays.asList(
			TimeUnit.DAYS.toMillis(365),
			TimeUnit.DAYS.toMillis(7),
			TimeUnit.DAYS.toMillis(1),
			TimeUnit.HOURS.toMillis(1),
			TimeUnit.MINUTES.toMillis(1),
			TimeUnit.SECONDS.toMillis(1)
			);
			
	public static final List<String> timesString = Arrays.asList(
			"y", "w", "d", "h", "min", "s");
	
	
	public static String toTimeString(long duration) {
		
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < times.size(); i++) {
			Long current = times.get(i);
			long temp = duration/current;
			
			if(temp > 0) {
				result.append(temp).append(" ").append(timesString.get(i));
				break;
			}
		}
		if (result.toString().equals("")) {
			return "0 s";
		}
		return result.toString();
	}

	
	public static long timePassedInMillis(Instant currentTime, Instant timeCreated) {
		
		Duration duration = Duration.between(timeCreated, currentTime);
		return duration.toMillis();
	}
	
	public static String timePassed(Instant currentTime, Instant timeCreated) {
		
		Duration duration = Duration.between(timeCreated, currentTime);
		return toTimeString(duration.toMillis());
	}
}
