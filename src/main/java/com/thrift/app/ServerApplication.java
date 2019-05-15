package com.thrift.app;

import org.apache.thrift.transport.TTransportException;

import com.thrift.service.WeatherServiceServer;

/**
 * Server application to test. Run in other PC with weather station.
 * @author uchiha
 */
public class ServerApplication {

    public static void main(String[] args) throws TTransportException {
        WeatherServiceServer server = new WeatherServiceServer();
        server.start(9901);

    }
}
