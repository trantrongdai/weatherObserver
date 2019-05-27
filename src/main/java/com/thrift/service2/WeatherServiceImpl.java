package com.thrift.service2;

import java.util.ArrayList;
import java.util.List;

import com.thrift.generate2.weatherService.DateException;
import com.thrift.generate2.weatherService.LocationException;
import com.thrift.generate2.weatherService.ReportException;
import com.thrift.generate2.weatherService.UnknownUserException;
import com.thrift.generate2.weatherService.WeatherReport;
import com.thrift.json.JacksonStreamingWriterAndReader;

public abstract class WeatherServiceImpl implements com.thrift.generate2.weatherService.Weather.Iface  {
	
	public static List<WeatherReport> weatherReportList = new ArrayList<WeatherReport>();
	
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
