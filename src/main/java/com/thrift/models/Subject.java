package com.thrift.models;

/**
 * Subject interface. Observer desgin pattern.
 * @author uchiha
 */
public interface Subject {

    /**
     * Register new weather server
     * @param weatherServer
     */
    public void registerWeatherServer(WeatherServer weatherServer);

    /**
     * Remove weather server
     * @param weatherServer
     */
    public void removeWeatherServer(WeatherServer weatherServer);

}
