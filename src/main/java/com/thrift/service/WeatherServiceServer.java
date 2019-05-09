package com.thrift.service;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import com.thrift.generated.WeatherService;

public class WeatherServiceServer {

    private TServer server;

    public void start(int port) throws TTransportException {
        TServerTransport serverTransport = new TServerSocket(port);
        server = new TSimpleServer(new TServer.Args(serverTransport)
            .processor(new WeatherService.Processor<>(new WeatherServiceImpl())));

        System.out.print("Starting the server... ");

        server.serve();

        System.out.println("done.");
    }

    public void stop() {
        if (server != null && server.isServing()) {
            System.out.print("Stopping the server... ");

            server.stop();

            System.out.println("done.");
        }
    }

}
