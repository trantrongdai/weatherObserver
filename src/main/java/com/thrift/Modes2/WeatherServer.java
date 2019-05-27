package com.thrift.Modes2;

public class WeatherServer {
	private String ip;
	private int port;
	
	public WeatherServer(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}
	
	// set Methode
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setPort(int port) {
		this.port = port;
	}
	// get Methode
	public String getIp() {
		return this.ip;
	}
	public int getPort() {
		return this.port;
	}
}
