package com.thrift.models;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.thrift.generated.InvalidOperationException;
import com.thrift.generated.WeatherData;
import com.thrift.generated.WeatherService;

/**
 * Weather station client. this can call method save weather data of Weather
 * server. WeatherClient is design follow observer pattern to notify all server
 * when has changed weather data.
 * @author uchiha
 */
public class WeatherStationSubject implements Subject {
    private static WeatherService.Client station;
    private static TTransport transport;
    private List<WeatherServer> weatherServers;
    private WeatherData weatherData;

    public WeatherStationSubject() {
        weatherServers = new ArrayList<>();
    }

    /**
     * Get current WeatherData
     * @return
     */
    public WeatherData getWeatherData() {
        return weatherData;
    }

    /**
     * Set latest WeatherData
     * @param weatherData
     */
    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    /**
     * Register weather server
     */
    @Override
    public void registerWeatherServer(WeatherServer weatherServer) {
        weatherServers.add(weatherServer);
    }

    /**
     * Remove weather server
     */
    @Override
    public void removeWeatherServer(WeatherServer weatherServer) {
        int index = weatherServers.indexOf(weatherServer);
        if (index >= 0) {
            weatherServers.remove(index);
        }
    }

    /**
     * Call save method of all weatherServer to save weather data
     * @throws InvalidOperationException
     * @throws TException
     */
    public void notifyWeatherServer() throws InvalidOperationException, TException {
        for (int i = 0; i < weatherServers.size(); i++) {
            WeatherServer weatherServer = weatherServers.get(i);
            transport = new TSocket(weatherServer.getIp(), weatherServer.getPort());
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            station = new WeatherService.Client(protocol);
            boolean result = station.ping();
            if (result) {
                station.save(weatherData);
            }
        }
    }

}
