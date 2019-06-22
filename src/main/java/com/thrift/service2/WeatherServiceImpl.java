package com.thrift.service2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.thrift.TException;
import com.thrift.generate2.weatherService.DateException;
import com.thrift.generate2.weatherService.Location;
import com.thrift.generate2.weatherService.LocationException;
import com.thrift.generate2.weatherService.Report;
import com.thrift.generate2.weatherService.ReportException;
import com.thrift.generate2.weatherService.SystemWarning;
import com.thrift.generate2.weatherService.UnknownUserException;
import com.thrift.generate2.weatherService.WeatherReport;
import com.thrift.generate2.weatherService.WeatherWarning;
import com.thrift.json.JacksonStreamingWriterAndReader;

public abstract class WeatherServiceImpl implements com.thrift.generate2.weatherService.Weather.Iface {

	public static final List<WeatherReport> weatherReportList = new ArrayList<WeatherReport>();
	private static final Map<Location, Long> tokenMapping = new HashMap<Location, Long>();

	public static int serverWarningNumber = 0;

	public static void setServerWarningNumber(int serverWarningNumber) {
		WeatherServiceImpl.serverWarningNumber = serverWarningNumber;
	}

	@Override
	public boolean sendWarning(SystemWarning systemWarning, long userId) throws UnknownUserException, TException {
		if (userId == -1 || !tokenMapping.containsValue(userId)) {
			throw new UnknownUserException(userId, "Invalid sessionToken");
		}
		if ((systemWarning == SystemWarning.BATTERY_LOW) || systemWarning == SystemWarning.EXTERNAL_FAILURE
				|| systemWarning == SystemWarning.INTERNAL_FAILURE || systemWarning == SystemWarning.NETWORK_UNSTABLE
				|| systemWarning == SystemWarning.SHUTDOWN) {
			System.out.println("[+] Received corretly " + systemWarning);
			try {
				JacksonStreamingWriterAndReader.writeSystemWarning(JacksonStreamingWriterAndReader.PATH_FILE_WARNING
						.concat(Integer.toString(WeatherServiceImpl.serverWarningNumber))
						.concat(JacksonStreamingWriterAndReader.CSV), systemWarning, userId);
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		System.out.println("[+] Received not corretly" + systemWarning);
		return false;
	}

	@Override
	public WeatherReport receiveForecastFor(long userId, String time)
			throws UnknownUserException, DateException, TException {
		// TODO Auto-generated method stub
		if (!tokenMapping.containsValue(userId)) {
			throw new UnknownUserException(userId, "Invalid sessionToken");
		}
		try {
			// SimpleDateFormat dateFormat = new
			// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			// Date date = dateFormat.parse(time);
			WeatherReport weatherReport = new WeatherReport();
			Report report = Report.SUNNY;
			weatherReport.setReport(report);
			Location location = new Location();
			for (Location loca : tokenMapping.keySet()) {
				if (tokenMapping.get(loca).equals(userId)) {
					location = loca;
					break;
				}
			}
			if(location == null) {
				throw new UnknownUserException(userId, "Invalidate user id");
			}
			weatherReport.setLocation(location);
			weatherReport.setTemperature(20.0);
			weatherReport.setHumidity((byte) 35);
			weatherReport.setWindStrength((byte) 25);
			weatherReport.setRainfall(45.0);
			weatherReport.setAtmosphericpressure((short) 2);
			weatherReport.setWindDirection((short) 90);
			weatherReport.setWindDirection((byte) 60);
			weatherReport.setDateTime(time);
			return weatherReport;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	@Override
	public boolean logout(long sessionToken) throws UnknownUserException, TException {
		if (!tokenMapping.containsValue(sessionToken)) {
			throw new UnknownUserException(sessionToken, "Invalid sessionToken");
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
		if (!tokenMapping.containsValue(userId)) {
			throw new UnknownUserException(userId, "Invalid sessionToken");
		}
		try {
			Random ran = new Random();
			int x = ran.nextInt(6);
			return WeatherWarning.findByValue(x);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			return null;
		}
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
