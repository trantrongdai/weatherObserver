package com.thrift.service2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;

import com.thrift.generate2.weatherService.DateException;
import com.thrift.generate2.weatherService.Location;
import com.thrift.generate2.weatherService.LocationException;
import com.thrift.generate2.weatherService.ReportException;
import com.thrift.generate2.weatherService.SystemWarning;
import com.thrift.generate2.weatherService.UnknownUserException;
import com.thrift.generate2.weatherService.WeatherReport;
import com.thrift.generate2.weatherService.WeatherWarning;
import com.thrift.json.JacksonStreamingWriterAndReader;
import java.net.*;

public abstract class WeatherServiceImpl implements com.thrift.generate2.weatherService.Weather.Iface {

	public static final List<WeatherReport> weatherReportList = new ArrayList<WeatherReport>();
	private static final Map<Location, Long> tokenMapping = new HashMap<Location, Long>();

	@Override
	public boolean sendWarning(SystemWarning systemWarning, long userId) throws UnknownUserException, TException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WeatherReport receiveForecastFor(long userId, String time)
			throws UnknownUserException, DateException, TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean logout(long sessionToken) throws UnknownUserException, TException {
		if (!tokenMapping.containsValue(sessionToken)) {
			throw new UnknownUserException();
		}
		for (Location location : tokenMapping.keySet()) {
			if (tokenMapping.get(location).equals(sessionToken)) {
				tokenMapping.remove(location);
				System.out.println("[+] Weather Station logged out. Token: " + sessionToken);
			} else {
				throw new UnknownUserException();
			}
		}
		return true;
	}

	@Override
	public long login(Location location) throws LocationException, TException {
		// TODO Auto-generated method stub
		if (location == null || tokenMapping.containsKey(location)) {
			throw new LocationException();
		}
		long sessionToken = System.currentTimeMillis();

		tokenMapping.put(location, sessionToken);
		System.out.println("[+] Weather Station logged in. Session Token: " + sessionToken);
		return sessionToken;
	}

	@Override
	public WeatherWarning checkWeatherWarnings(long userId) throws UnknownUserException, TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean sendWeatherReport(WeatherReport report, long sessionToken) throws UnknownUserException,
			ReportException, DateException, LocationException, org.apache.thrift.TException {
		if (sessionToken == -1 || !tokenMapping.containsKey(report.getLocation())
				|| !tokenMapping.get(report.getLocation()).equals(sessionToken)) {
			throw new UnknownUserException(sessionToken, "Invalid sessionToken");
		}
		try {
			weatherReportList.add(report);
			JacksonStreamingWriterAndReader.WriterCsvFile(JacksonStreamingWriterAndReader.PATH_FILE_CSV, report);
			// displayWeatherReportList(weatherReportList);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void displayWeatherReportList(List<WeatherReport> weatherReportList) {
		for (int i = 0; i < weatherReportList.size(); i++) {
			System.out.println(weatherReportList.get(i).toString());
		}
	}

}
