package de.hda.fbi.vs.sensor;

import java.net.InetAddress;

public class Regen extends Sensor {

  public Regen(int portNr, InetAddress adress) {
    super(portNr, adress);
    typ = "Regen";
    unit = "ml/min";
    max = 1000;
  }
}
