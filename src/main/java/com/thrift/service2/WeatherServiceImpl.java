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

public abstract class WeatherServiceImpl implements com.thrift.generate2.weatherService.Weather.Iface  {
	
	public static List<WeatherReport> weatherReportList = new ArrayList<WeatherReport>();
	private static final Map<Object, Long> locations = new IdentityHashMap<Object, Long>();
	
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
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public long login(Location location) throws LocationException, TException {
		// TODO Auto-generated method stub
		if(location == null) {
	           return 0;
		}	
	     /** Map from ObjectEntry to the objects corresponding ID*/
	     Map<Location, Long> locations = new HashMap<Location, Long>();
	     long nextSessionTockenId = System.currentTimeMillis();
	     /** get Session Tocken if locaton exit or create neu session Tocken when location not exit*/
	     Long sessionTocken = locations.get(location);
	     if(sessionTocken == null) {
	    	 locations.put(location, sessionTocken = nextSessionTockenId++);
	    	 return sessionTocken;
	     }
	     return 0;
	}
	
	@Override
	public WeatherWarning checkWeatherWarnings(long userId) throws UnknownUserException, TException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean sendWeatherReport(WeatherReport report, long sessionToken) throws UnknownUserException, ReportException, DateException, LocationException, org.apache.thrift.TException {
		System.out.println("size of list data before = " + weatherReportList.size());
		weatherReportList.add(report);
        System.out.println("size of list data after = " + weatherReportList.size());
        JacksonStreamingWriterAndReader.WriterCsvFile(JacksonStreamingWriterAndReader.PATH_FILE_CSV, report);
        displayWeatherReportList(weatherReportList);
		return true;
	}
	public void displayWeatherReportList(List<WeatherReport> weatherReportList) {
		for(int i = 0; i< weatherReportList.size(); i++) {
			System.out.println(weatherReportList.get(i).toString());
		}
	}
	
}
