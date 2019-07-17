package de.hda.fbi.vs.weatherstation;

import com.thrift.Modes2.WeatherServer;
import com.thrift.generate2.weatherService.WeatherReport;

public class WeatherReportErrorWithServer {
	public WeatherReport weatherReport; 
	public WeatherServer weatherServer;
	public WeatherReportErrorWithServer(WeatherReport weatherReport, WeatherServer weatherServer) {
		this.weatherReport = weatherReport;
		this.weatherServer = weatherServer;
	}

}
