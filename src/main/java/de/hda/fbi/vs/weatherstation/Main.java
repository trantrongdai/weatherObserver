package de.hda.fbi.vs.weatherstation;

import java.io.IOException;

import org.apache.thrift.TException;

import com.thrift.generated.InvalidOperationException;

public class Main {

  public static void main(String[] args) throws InvalidOperationException, TException {
    try {
      WetterStation wetterStation = new WetterStation();
      Thread httpServer = new HttpServer();
      httpServer.start();
      wetterStation.runWetterStation();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
