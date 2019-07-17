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
import com.thrift.generate2.weatherService.LocationException;
import com.thrift.generate2.weatherService.ReportException;
import com.thrift.generate2.weatherService.SystemWarning;
import com.thrift.generate2.weatherService.UnknownUserException;
import com.thrift.generate2.weatherService.Weather;
import com.thrift.generate2.weatherService.Weather.Client;
import com.thrift.generate2.weatherService.WeatherReport;
import com.thrift.generate2.weatherService.WeatherWarning;

import de.hda.fbi.vs.weatherstation.WeatherReportErrorWithServer;

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
	private SystemWarning systemWarning;
	private static final Map<WeatherServer, Long> sessionTokenMapping = new HashMap<WeatherServer, Long>();
	private Location locationWeather;
	private List<WeatherReport> listSendErrorWeatherReport = new ArrayList<WeatherReport>();
	private List<WeatherReportErrorWithServer> listError = new ArrayList<WeatherReportErrorWithServer>();
	private boolean resendError = false;

	public void setReSendError(boolean resendError) {
		this.resendError = resendError;
		if (resendError == true) {
			reSendErrorWeatherReport();
		}
	}

	public boolean getResendError() {
		return this.resendError;
	}

	public WeatherStationSubject() {
		this.weatherServers = new ArrayList<>();
	}

	public WeatherReport getCurrentWeatherReport() {
		return this.weatherReport;
	}

	public void setWeatherReport(WeatherReport weatherReport) {
		this.weatherReport = weatherReport;
	}

	public void setSystemWarning(SystemWarning systemWarning) {
		this.systemWarning = systemWarning;
	}

	public ArrayList<Long> getListSessionToken() {
		ArrayList<Long> sessionTokenList = new ArrayList<Long>();
		for (int i = 0; i < weatherServers.size(); i++) {
			WeatherServer weatherServer = weatherServers.get(i);
			long sessionToken = sessionTokenMapping.containsKey(weatherServer) ? sessionTokenMapping.get(weatherServer)
					: -1;
			sessionTokenList.add(sessionToken);
		}
		return sessionTokenList;
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
	 * @throws LocationException
	 * 
	 * @throws ReportException
	 * @throws TException
	 */
	public boolean notifyWeatherServer() throws LocationException, TException {
		boolean done = false;
		for (int i = 0; i < weatherServers.size(); i++) {
			WeatherServer weatherServer = weatherServers.get(i);
			try {
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
				relogin(weatherServer, this.locationWeather);
			} catch (TException e) {
				transport.close();
			}
		}
		if (done == false) {
			listSendErrorWeatherReport.add(getCurrentWeatherReport());
			System.out.println("[-] Add error weather report");
		}
		return done;
	}

	/**
	 * @throws TException
	 * @throws LocationException
	 * 
	 */
	public void notifyWeatherServer1() throws LocationException, TException {
		for (int i = 0; i < weatherServers.size(); i++) {
			WeatherServer weatherServer = weatherServers.get(i);
			boolean done = false;
			try {
				transport = new TSocket(weatherServer.getIp(), weatherServer.getPort());
				transport.open();
				TProtocol protocol = new TBinaryProtocol(transport);
				station = new Client(protocol);

				long sessionToken = sessionTokenMapping.containsKey(weatherServer)
						? sessionTokenMapping.get(weatherServer)
						: -1;
				done = station.sendWeatherReport(weatherReport, sessionToken);
				transport.close();
				if (done == false) {
					listError.add(new WeatherReportErrorWithServer(weatherReport, weatherServer));
				}
			} catch (UnknownUserException e) {
				// TODO: handle exception
				System.out.println("[-] Please Login to Server_" + i);
				transport.close();
				relogin(weatherServer, this.locationWeather);
			} catch (TException e) {
				transport.close();
			}
		}
	}

	/**
	 * re send weather report Error when one in all server active
	 * 
	 * @return
	 */
	public void reSendErrorWeatherReport() {
		// System.out.println("[+] Sending list weather report error!!!");
		System.out.println("[+] Befor send list weather report: " + listSendErrorWeatherReport.size());
		if (listSendErrorWeatherReport.size() != 0) {
			for (WeatherReport weatherRepo : listSendErrorWeatherReport) {
				System.out.println("[+] sending ... " + weatherRepo.dateTime);
				////
				/*
				 * boolean done = false; for (int i = 0; i < weatherServers.size(); i++) {
				 * WeatherServer weatherServer = weatherServers.get(i); try { transport = new
				 * TSocket(weatherServer.getIp(), weatherServer.getPort()); transport.open();
				 * TProtocol protocol = new TBinaryProtocol(transport); station = new
				 * Client(protocol);
				 * 
				 * long sessionToken = sessionTokenMapping.containsKey(weatherServer) ?
				 * sessionTokenMapping.get(weatherServer) : -1;
				 * System.out.println("[+] sending ... " + j + " " +
				 * listSendErrorWeatherReport.get(j).getDateTime()); done =
				 * station.sendWeatherReport(listSendErrorWeatherReport.get(j), sessionToken);
				 * transport.close(); if (done == true) { listSendErrorWeatherReport.remove(j);
				 * break; } } catch (UnknownUserException e) { // TODO: handle exception
				 * System.out.println("[-] Please Login to Server_" + i); transport.close(); }
				 * catch (TException e) { transport.close(); } }
				 */
			}
		}
		// System.out.println("[+] After send list weather report: " +
		// listSendErrorWeatherReport.size());
	}

	/**
	 * 
	 * @param location
	 */
	public void login(Location location) {
		this.locationWeather = location;
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

	public void sendWarning() throws LocationException, TException {
		for (int i = 0; i < weatherServers.size(); i++) {
			boolean done = false;
			System.out.println("[+] Send Warning to server: " + i);
			WeatherServer weatherServer = weatherServers.get(i);
			try {
				transport = new TSocket(weatherServer.getIp(), weatherServer.getPort());
				transport.open();
				TProtocol protocol = new TBinaryProtocol(transport);
				station = new Client(protocol);
				long sessionToken = sessionTokenMapping.containsKey(weatherServer)
						? sessionTokenMapping.get(weatherServer)
						: -1;
				done = station.sendWarning(this.systemWarning, sessionToken);
				transport.close();
				if (done == true) {
					break;
				}
			} catch (UnknownUserException e) {
				System.out.println("[-] Please Login to Server_" + i);
				transport.close();
				relogin(weatherServer, this.locationWeather);
			} catch (TException t) {
				t.getStackTrace();
				transport.close();
			}
		}
	}

	/**
	 * receiver fore cast weather report throws Unknow exception when userId not
	 * valid or not login
	 * 
	 * @param userId
	 * @param time
	 * @return
	 */
	public WeatherReport receiveForecastFor(long userId, String time) {
		for (int i = 0; i < weatherServers.size(); i++) {
			System.out.println("[+] Send forecast to server: " + i);
			WeatherReport weatherReport = new WeatherReport();
			try {
				WeatherServer weatherServer = weatherServers.get(i);
				transport = new TSocket(weatherServer.getIp(), weatherServer.getPort());
				transport.open();
				TProtocol protocol = new TBinaryProtocol(transport);
				station = new Client(protocol);
				weatherReport = station.receiveForecastFor(userId, time);
				transport.close();
				if (weatherReport != null) {
					return weatherReport;
				}
			} catch (UnknownUserException e) {
				System.out.println("[-] (receve) User Id: " + userId + " not valid");
				System.out.println("[-] (receve) Please Login oder validate user id to Server_" + i);
				transport.close();
			} catch (TException t) {
				t.getStackTrace();
				transport.close();
			}
		}
		return null;
	}

	public WeatherWarning checkWeatherWarnings(long userId) {
		for (int i = 0; i < weatherServers.size(); i++) {
			System.out.println("[+] Send check weather warning to server: " + i);
			try {
				WeatherWarning weatherWarning;
				WeatherServer weatherServer = weatherServers.get(i);
				transport = new TSocket(weatherServer.getIp(), weatherServer.getPort());
				transport.open();
				TProtocol protocol = new TBinaryProtocol(transport);
				station = new Client(protocol);
				long sessionToken = sessionTokenMapping.containsKey(weatherServer)
						? sessionTokenMapping.get(weatherServer)
						: -1;
				weatherWarning = station.checkWeatherWarnings(userId);
				transport.close();
				if (weatherWarning != null) {
					return weatherWarning;
				}
			} catch (UnknownUserException e) {
				System.out.println("[-] (Weather warning) User Id: " + userId + " not valid");
				System.out.println("[-] (Weather warning) Please Login oder validate user id to Server_" + i);
				transport.close();
			} catch (TException t) {
				t.getStackTrace();
				transport.close();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param weatherServer
	 * @param location
	 * @throws TException
	 * @throws LocationException
	 */
	public void relogin(WeatherServer weatherServer, Location location) throws LocationException, TException {
		System.out.println("[+] Relogin to Server");
		long sessionToken;
		try {
			transport = new TSocket(weatherServer.getIp(), weatherServer.getPort());
			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);
			station = new Client(protocol);
			// xoa weather server trong map, logout va xoa weather server + sessionToken
			System.out.println("[+] Old session Token: " + sessionTokenMapping.get(weatherServer));
			if (sessionTokenMapping.get(weatherServer) != null) {
				// System.out.println("[+] Logout and Remove 0");
				// station.logout(sessionTokenMapping.get(weatherServer));
				sessionTokenMapping.remove(weatherServer);
				System.out.println("[+] Remove weather server");
			}
			// tao login moi
			sessionToken = station.login(location);
			sessionTokenMapping.put(weatherServer, sessionToken);
			System.out.println("[+] New session token:" + sessionToken);
			transport.close();
			resenError(weatherServer);
		} catch (UnknownUserException u) {
			// TODO: handle exception
			// long sessionToken = station.login(location);
			// sessionTokenMapping.put(weatherServer, sessionToken);
			// System.out.println("[+] New session token:" + sessionToken);
			// transport.close();
		} catch (TException t) {
			t.getStackTrace();
			transport.close();
		}

	}

	/**
	 * 
	 */
	public void resenError(WeatherServer weatherServer) {
		System.out.println("[+] Befor send list weather report: " + listError.size());
		if (listError.size() != 0) {
			for(WeatherReportErrorWithServer error : listError) {
				try {
					transport = new TSocket(weatherServer.getIp(), weatherServer.getPort());
					transport.open();
					TProtocol protocol = new TBinaryProtocol(transport);
					station = new Client(protocol);

					long sessionToken = sessionTokenMapping.containsKey(weatherServer)
							? sessionTokenMapping.get(weatherServer)
							: -1;
					 station.sendWeatherReport(weatherReport, sessionToken);
					transport.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

}
