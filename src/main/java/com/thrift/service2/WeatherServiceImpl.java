package com.thrift.service2;

import java.util.ArrayList;
import java.util.List;

import com.thrift.generate2.weatherService.DateException;
import com.thrift.generate2.weatherService.LocationException;
import com.thrift.generate2.weatherService.ReportException;
import com.thrift.generate2.weatherService.UnknownUserException;
import com.thrift.generate2.weatherService.WeatherReport;

public abstract class WeatherServiceImpl implements com.thrift.generate2.weatherService.Weather.Iface  {
	
	public static List<WeatherReport> weatherReportList = new ArrayList<WeatherReport>();
	
	@Override
	public boolean sendWeatherReport(WeatherReport report, long sessionToken) throws UnknownUserException, ReportException, DateException, LocationException, org.apache.thrift.TException {
		System.out.println("size of list data before = " + weatherReportList.size());
		weatherReportList.add(report);
        System.out.println("size of list data after = " + weatherReportList.size());
        System.out.println(weatherReportList.toString());
		return false;
	}
	
}
