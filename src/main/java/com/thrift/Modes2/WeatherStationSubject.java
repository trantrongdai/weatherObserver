package com.thrift.Modes2;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.thrift.generate2.weatherService.Location;
import com.thrift.generate2.weatherService.LocationException;
import com.thrift.generate2.weatherService.ReportException;
import com.thrift.generate2.weatherService.Weather;
import com.thrift.generate2.weatherService.Weather.Client;
import com.thrift.generate2.weatherService.WeatherReport;
import com.thrift.generated.WeatherService;
import com.thrift.json.JacksonStreamingWriterAndReader;

/**
 * save weather data. and notify for all server
 * @author manht
 *
 */
public class WeatherStationSubject implements Subject {
	private static Weather.Client station;
	private static TTransport transport;
	private List<WeatherServer> weatherServers;
	private WeatherReport weatherReport;
	//private Location location;
	
	public WeatherStationSubject() {
		this.weatherServers = new ArrayList<>();
	}
	
	public WeatherReport getCurrentWeatherReport() {
		return this.weatherReport;
	}
	public void setWeatherReport(WeatherReport weatherReport) {
		this.weatherReport = weatherReport;
	}
	@Override
	public void registerWeatherServer(WeatherServer weatherServer) {
		weatherServers.add(weatherServer);
	}
	@Override
	public void removeWeatherServer(WeatherServer weatherServer) {
		int index = weatherServers.indexOf(weatherServer);
		if (index >= 0) {
            weatherServers.remove(index);
        }
	}
	/**
	 * Send Weather Report for all Server
	 * @throws ReportException
	 * @throws TException
	 */
	public void notifyWeatherServer() throws ReportException, TException{
		for(int i = 0; i < weatherServers.size(); i++) {
			WeatherServer weatherServer = weatherServers.get(i);
			transport = new TSocket(weatherServer.getIp(), weatherServer.getPort());
			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);
			station = new Client(protocol);
			station.sendWeatherReport(weatherReport, 0);
			transport.close();
		}
	}
	public long login(Location location) throws LocationException, TException {
		return station.login(location);
	}
}
