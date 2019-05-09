package com.thrift.models;

/**
 * WeatherServer model
 * @author uchiha
 */
public class WeatherServer {

    /** Ip of weather server */
    private String ip;

    /** Port of weather server */
    private int port;

    public WeatherServer(String ip, int port) {
        super();
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
