package com.thrift.service2;

import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import com.thrift.generate2.weatherService.DateException;
import com.thrift.generate2.weatherService.Location;
import com.thrift.generate2.weatherService.LocationException;
import com.thrift.generate2.weatherService.SystemWarning;
import com.thrift.generate2.weatherService.UnknownUserException;
import com.thrift.generate2.weatherService.Weather;
import com.thrift.generate2.weatherService.WeatherReport;
import com.thrift.generate2.weatherService.WeatherWarning;
import com.thrift.json.JacksonStreamingWriterAndReader;
import com.thrift.service2.WeatherServiceImpl;

public class WeatherServiceServer {
	private TServer server;
	public void start(int port) throws TTransportException{
		TServerTransport serverTransport = new TServerSocket(port);
		 server = new TSimpleServer(new TServer.Args(serverTransport)
		            .processor(new Weather.Processor<>(new WeatherServiceImpl() {
						
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
							return 0;
						}
						
						@Override
						public WeatherWarning checkWeatherWarnings(long userId) throws UnknownUserException, TException {
							// TODO Auto-generated method stub
							return null;
						}
					})));
		JacksonStreamingWriterAndReader.readerCsvFile(JacksonStreamingWriterAndReader.PATH_FILE_CSV);
		System.out.println("Startting Server...");
		server.serve();
		System.out.println("Done");
	}
	
	public void stop() {
		if(server != null && server.isServing()) {
			System.out.print("Stopping the server... ");
            server.stop();
            System.out.println("done.");
		}
	}

}
