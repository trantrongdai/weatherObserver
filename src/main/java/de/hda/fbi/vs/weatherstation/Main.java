package de.hda.fbi.vs.weatherstation;

import java.io.IOException;

import org.apache.thrift.TException;

import com.thrift.generated.InvalidOperationException;

import mqtt.subscriber.CliProcessor;
import mqtt.subscriber.Subscriber;

public class Main {

  public static void main(String[] args) throws InvalidOperationException, TException {
    try {
      WetterStation wetterStation = new WetterStation();
      Thread httpServer = new HttpServer();
      httpServer.start();
      ///// Subscriber
      
      CliProcessor.getInstance().parseCliOptions(args);
      // Start the MQTT subscriber.
      Subscriber subscriber = new Subscriber();
      subscriber.run();
      
      ////////////
      //wetterStation.runWetterStation();
      wetterStation.runWetterStationMqtt();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
