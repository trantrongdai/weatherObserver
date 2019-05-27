package com.thrift.Modes2;

import com.thrift.Modes2.WeatherServer;

public interface Subject {
	/**
	 * new Weather Server
	 * @param weatherServer
	 */
	 public void registerWeatherServer(WeatherServer weatherServer);
	 /**
	  * remove Weather Server
	  * @param weatherServer
	  */
	 public void removeWeatherServer(WeatherServer weatherServer);
}
