package de.hda.fbi.vs.sensor;

import java.net.InetAddress;

public class Temperatur extends Sensor {

  public Temperatur(int portNr, InetAddress adress) {
    super(portNr, adress);
    typ = "Temperatur";
    unit = "°C";
    max = 40;
  }
}
