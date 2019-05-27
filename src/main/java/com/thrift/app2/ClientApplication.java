package com.thrift.app2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.thrift.TException;

import com.thrift.Modes2.WeatherServer;
import com.thrift.Modes2.WeatherStationSubject;
import com.thrift.generate2.weatherService.Location;
import com.thrift.generate2.weatherService.Report;
import com.thrift.generate2.weatherService.WeatherReport;

public class ClientApplication {
	public static void main(String[] args) throws UnknownHostException, TException {
		WeatherStationSubject weatherStation = new WeatherStationSubject();
		InetAddress localhost = InetAddress.getLocalHost();
		weatherStation.registerWeatherServer(new WeatherServer("192.168.48.1", 9901));
		
		WeatherReport weatherReport = new WeatherReport();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println("System IP Address : " + (localhost.getHostAddress()).trim());
        Location location = new Location((byte)20, "Darmstadt", 49.863, 8.64);
        Report report = Report.RAINY;
        weatherReport.setReport(report);
        weatherReport.setLocation(location);
        weatherReport.setTemperature(20.0);
        weatherReport.setHumidity((byte)75);
        weatherReport.setWindStrength((byte)75);
        weatherReport.setRainfall(45.0);
        weatherReport.setAtmosphericpressure((short)2);
        weatherReport.setWindDirection((short)90);
        weatherReport.setWindDirection((byte)60);
        weatherReport.setDateTime(dateFormat.format(date));
        
        weatherStation.setWeatherReport(weatherReport);
        weatherStation.notifyWeatherServer();
	}

}
