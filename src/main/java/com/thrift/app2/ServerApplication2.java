package com.thrift.app2;

import org.apache.thrift.transport.TTransportException;

import com.thrift.service2.WeatherServiceServer;

public class ServerApplication2 {
	public static void main(String[] args) throws TTransportException{
		 WeatherServiceServer server = new WeatherServiceServer();
	       server.start(9901);
	}
}
