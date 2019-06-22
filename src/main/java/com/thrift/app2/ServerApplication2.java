package com.thrift.app2;

import java.net.UnknownHostException;
import org.apache.thrift.transport.TTransportException;
import com.thrift.service2.WeatherServiceServer;

public class ServerApplication2 {
	public static void main(String[] args) throws TTransportException, UnknownHostException {
		WeatherServiceServer server = new WeatherServiceServer();
		server.start(9902);
	}
}
