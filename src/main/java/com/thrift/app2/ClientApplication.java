package com.thrift.app2;
import java.net.UnknownHostException;
import org.apache.thrift.TException;
import com.thrift.Modes2.WeatherServer;
import com.thrift.Modes2.WeatherStationSubject;
import com.thrift.generate2.weatherService.Location;
import com.thrift.generate2.weatherService.Report;
import com.thrift.generate2.weatherService.SystemWarning;
import com.thrift.generate2.weatherService.WeatherReport;
import com.thrift.generate2.weatherService.WeatherWarning;

public class ClientApplication {
	public static void main(String[] args) throws UnknownHostException, TException {
		WeatherStationSubject weatherStation = new WeatherStationSubject();
		Location location = new Location((byte) 20, "Darmstadt", 49.863, 8.64);

		// send weather report

		/*
		 * weatherStation.registerWeatherServer(new WeatherServer("127.0.0.1", 9901));
		 * weatherStation.registerWeatherServer(new WeatherServer("127.0.0.1", 9902));
		 * weatherStation.registerWeatherServer(new WeatherServer("127.0.0.1", 9903));
		 * 
		 * // Login after register server weatherStation.login(location);
		 * 
		 * WeatherReport weatherReport = new WeatherReport(); DateFormat dateFormat =
		 * new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); Date date = new Date(); Report
		 * report = Report.SNOW; weatherReport.setReport(report);
		 * weatherReport.setLocation(location); weatherReport.setTemperature(20.0);
		 * weatherReport.setHumidity((byte)35); weatherReport.setWindStrength((byte)25);
		 * weatherReport.setRainfall(45.0);
		 * weatherReport.setAtmosphericpressure((short)2);
		 * weatherReport.setWindDirection((short)90);
		 * weatherReport.setWindDirection((byte)60);
		 * weatherReport.setDateTime(dateFormat.format(date));
		 * weatherStation.setWeatherReport(weatherReport);
		 * weatherStation.notifyWeatherServer();
		 */
		/////////////////////
		// send warning
		/*
		 * weatherStation.registerWeatherServer(new WeatherServer("127.0.0.1", 9901));
		 * weatherStation.registerWeatherServer(new WeatherServer("127.0.0.1", 9902));
		 * weatherStation.registerWeatherServer(new WeatherServer("127.0.0.1", 9903));
		 * weatherStation.login(location);
		 * weatherStation.setSystemWarning(SystemWarning.BATTERY_LOW);
		 * weatherStation.sendWarning(); weatherStation.logout();
		 */

		////////////////////////////////
		/*
		weatherStation.registerWeatherServer(new WeatherServer("127.0.0.1", 9901));
		weatherStation.login(location);
		 WeatherReport forecastWeatherReport =
		weatherStation.receiveForecastFor(weatherStation.getListSessionToken().get(0), "2019-04-18");
		WeatherReport forecastWeatherReport1 = weatherStation
				.receiveForecastFor(123456, "2019-04-18");
		System.out.println("[+] Station receive forecast weather report: " + forecastWeatherReport);
		System.out.println("[+] Station receive forecast weather report: " + forecastWeatherReport1);
		*/
		
		////////////////////////////////////////
		
		weatherStation.registerWeatherServer(new WeatherServer("127.0.0.1", 9901));
		weatherStation.registerWeatherServer(new WeatherServer("127.0.0.1", 9902));
		weatherStation.login(location);
		WeatherWarning weatherWarning = weatherStation.checkWeatherWarnings(weatherStation.getListSessionToken().get(0));
		WeatherWarning weatherWarning1 = weatherStation.checkWeatherWarnings(12345);
		System.out.println("[+] Station receive weather warning : " + weatherWarning);
		System.out.println("[+] Station receive weather warning : " + weatherWarning1);
	}

}
