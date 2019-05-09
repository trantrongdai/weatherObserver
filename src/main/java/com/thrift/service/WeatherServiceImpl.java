package com.thrift.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;

import com.thrift.generated.WeatherData;
import com.thrift.generated.WeatherService;

public class WeatherServiceImpl implements WeatherService.Iface {

    /**
     * Data base in WeatherServer to store list of weather data.
     */
    public static List<WeatherData> weatherDataList = new ArrayList<>();

    /**
     * Method Save weather data in WeatherServer
     */
    @Override
    public void save(WeatherData weatherData) throws com.thrift.generated.InvalidOperationException, TException {
        System.out.println("size of list data before = " + weatherDataList.size());
        weatherDataList.add(weatherData);
        System.out.println("size of list data after = " + weatherDataList.size());
        System.out.println(weatherData.toString());
    }

    /**
     * Method ping() in WeatherServer. 
     */
    @Override
    public boolean ping() throws com.thrift.generated.InvalidOperationException, TException {
        System.out.println("Client has ping...!");
        return true;
    }

}
