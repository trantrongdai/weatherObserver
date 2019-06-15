package com.thrift.Modes2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.thrift.generate2.weatherService.Location;
import com.thrift.generate2.weatherService.ReportException;
import com.thrift.generate2.weatherService.UnknownUserException;
import com.thrift.generate2.weatherService.Weather;
import com.thrift.generate2.weatherService.Weather.Client;
import com.thrift.generate2.weatherService.WeatherReport;

/**
 * save weather data. and notify for all server
 * 
 * @author manht
 *
 */
public class WeatherStationSubject implements Subject {
	private static Weather.Client station;
	private static TTransport transport;
	private List<WeatherServer> weatherServers;
	private WeatherReport weatherReport;
	private static final Map<WeatherServer, Long> sessionTokenMapping = new HashMap();
	// private Location location;

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
	 * 
	 * @throws ReportException
	 * @throws TException
	 */
	public void notifyWeatherServer(){
		boolean done = false;
		for (int i = 0; i < weatherServers.size(); i++) {
			try {
				WeatherServer weatherServer = weatherServers.get(i);
				transport = new TSocket(weatherServer.getIp(), weatherServer.getPort());
				transport.open();
				TProtocol protocol = new TBinaryProtocol(transport);
				station = new Client(protocol);

				long sessionToken = sessionTokenMapping.containsKey(weatherServer)
						? sessionTokenMapping.get(weatherServer)
						: -1;
				done = station.sendWeatherReport(weatherReport, sessionToken);
				transport.close();
				if (done == true) {
					break;
				}

			} catch (UnknownUserException e) {
				// TODO: handle exception
				System.out.println("[-] Please Login to Server_" + i);
				transport.close();
			} catch (TException e) {
				transport.close();
			}
		}
	}

	public void login(Location location) {
		for (int i = 0; i < weatherServers.size(); i++) {
			try {
				WeatherServer weatherServer = weatherServers.get(i);
				transport = new TSocket(weatherServer.getIp(), weatherServer.getPort());
				transport.open();
				TProtocol protocol = new TBinaryProtocol(transport);
				station = new Client(protocol);
				long sessionToken = station.login(location);
				sessionTokenMapping.put(weatherServer, sessionToken);
				transport.close();
			} catch (TException t) {
				t.getStackTrace();
			}
		}
	}

	public void logout() {
		for (int i = 0; i < weatherServers.size(); i++) {
			try {
				WeatherServer weatherServer = weatherServers.get(i);
				transport = new TSocket(weatherServer.getIp(), weatherServer.getPort());
				transport.open();
				TProtocol protocol = new TBinaryProtocol(transport);
				station = new Client(protocol);
				station.logout(sessionTokenMapping.get(weatherServer));
				sessionTokenMapping.remove(weatherServer);
				transport.close();
			} catch (TException t) {
				t.getStackTrace();
			}
		}
	}
}
