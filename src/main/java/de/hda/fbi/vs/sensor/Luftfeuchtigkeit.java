package de.hda.fbi.vs.sensor;

import java.net.InetAddress;

public class Luftfeuchtigkeit extends Sensor {

  public Luftfeuchtigkeit(int portNr, InetAddress adress) {
    super(portNr, adress);
    typ = "Luftfeuchtigkeit";
    unit = "%";
    max = 100;
  }
}
