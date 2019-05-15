package de.hda.fbi.vs.sensor;

import java.net.InetAddress;

public class Windgeschwindigkeit extends Sensor {

  public Windgeschwindigkeit(int portNr, InetAddress adress) {
    super(portNr, adress);
    typ = "Windgeschwindigkeit";
    unit = "km/h";
    max = 80;
  }
}
