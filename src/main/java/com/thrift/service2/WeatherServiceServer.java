package com.thrift.service2;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import com.thrift.generate2.weatherService.Weather;
import com.thrift.json.JacksonStreamingWriterAndReader;
import com.thrift.service2.WeatherServiceImpl;

public class WeatherServiceServer {
	private TServer server;

	public void start(int port) throws TTransportException, UnknownHostException {
		TServerTransport serverTransport = new TServerSocket(port);
		server = new TSimpleServer(
				new TServer.Args(serverTransport).processor(new Weather.Processor<>(new WeatherServiceImpl() {
				})));
		// JacksonStreamingWriterAndReader.readerCsvFile(JacksonStreamingWriterAndReader.PATH_FILE_CSV);
		System.out.println("[+] Server started on port " + port);
		server.serve();
	}

	public void stop() {
		if (server != null && server.isServing()) {
			System.out.print("Stopping the server... ");
			server.stop();
			System.out.println("done.");
		}
	}

}
