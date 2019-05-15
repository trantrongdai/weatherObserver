package com.thrift.app;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.thrift.TException;

import com.thrift.generated.InvalidOperationException;
import com.thrift.generated.WeatherData;
import com.thrift.models.WeatherServer;
import com.thrift.models.WeatherStationSubject;

/**
 * Client application test.
 * @author uchiha
 */
public class ClientApplication {

    public static void main(String[] args) throws UnknownHostException, InvalidOperationException, TException {
        WeatherStationSubject weatherStation = new WeatherStationSubject();

        InetAddress localhost = InetAddress.getLocalHost();
        weatherStation.registerWeatherServer(new WeatherServer(localhost.getHostAddress().trim(), 9901));

        // client.registerWeatherServer(new
        // WeatherServer(localhost.getHostAddress().trim(), 9901)); // register
        // more server.

        WeatherData weatherData = new WeatherData();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println("System IP Address : " + (localhost.getHostAddress()).trim());
        weatherData.setStationIp(localhost.getHostAddress());
        weatherData.setTime(dateFormat.format(date));
        weatherData.setTemperature("30*C");
        weatherData.setHumidity("70");
        weatherData.setWindSpeed("100km/h");
        weatherData.setRain(false);

        weatherStation.setWeatherData(weatherData);
        weatherStation.notifyWeatherServer();
    }
}
