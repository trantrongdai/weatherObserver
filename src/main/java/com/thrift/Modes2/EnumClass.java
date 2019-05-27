package com.thrift.Modes2;

import java.util.HashMap;
import java.util.Map;

public class EnumClass {
	/**
	 * This Enum defines different weather warnings the system can return when a warning is requested
	 */
	public enum WeatherWarning {
		NONE(0),
		BLIZZARD(1),
		FLOOD(2),
		HURRICANE(3),
		STORM(4),
		TORNADO(5),
		UV(6);
		// APOCALYPSE = 666
		private int value;
	    private static Map<Object, Object> map = new HashMap<>();
	    private WeatherWarning(int value) {
	        this.value = value;
	    }
	    static {
	        for (WeatherWarning weatherWarning : WeatherWarning.values()) {
	            map.put(weatherWarning.value, weatherWarning);
	        }
	    }
	    public static WeatherWarning valueOf(int weatherWarning) {
	        return (WeatherWarning)map.get(weatherWarning);
	    }
	    public int getValue() {
	        return value;
	    }
	}
	public enum SystemWarning{
		SHUTDOWN(1),			// Panic: About to shut down without logout
		BATTERY_LOW(2),		// Reducing QoS to save battery
		NETWORK_UNSTABLE(3),	// Jitter too large, ping too long, etc.
		INTERNAL_FAILURE(4),	// Report that internal tests failed
		EXTERNAL_FAILURE(5); // Report that received data failed tests
		
		private int value;
		private static Map<Object, Object> map = new HashMap<>();
		private SystemWarning(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
		static {
	        for (SystemWarning systemWarning : SystemWarning.values()) {
	            map.put(systemWarning.value, systemWarning);
	        }
	    }
	    public static SystemWarning valueOf(int systemWarning) {
	        return (SystemWarning)map.get(systemWarning);
	    }
	}
	
	public enum Report{
		SUNNY(1),
		CLOUDY(2),
		RAINY(3),
		SNOW(4);
		
		private int value;
		private static Map<Object, Object> map = new HashMap<>();
		private Report(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
		static {
	        for (Report report : Report.values()) {
	            map.put(report.value, report);
	        }
	    }
	    public static Report valueOf(int report) {
	        return (Report)map.get(report);
	    }
	}
	
}
