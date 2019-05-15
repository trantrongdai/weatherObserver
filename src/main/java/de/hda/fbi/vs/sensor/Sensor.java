package de.hda.fbi.vs.sensor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;
import java.util.Random;


public abstract class Sensor {

  protected InetAddress adress = null;
  protected int portNr = 0;
  protected String typ = "";
  protected String unit = "";
  protected int max = 0;
  protected String data = "";
  protected boolean running = true;
  protected DatagramPacket packet = null;
  protected DatagramSocket socket = null;
  protected int sleepTime = 5;
  protected Random sensorRandom = new Random();

  public Sensor(int portNr, InetAddress adress) {
    this.adress = adress;
    this.portNr = portNr;
  }

  /*Andert die Daten die der Sensort sendert zufällig ein einem bestimten Zahlenbereich*/
  void changeRandom() {
    int randomNumber = sensorRandom.nextInt(max);
    data = Integer.toString(randomNumber);
  }

  /*Simuliert einen Sendenden Sensor*/
  public void runSensor() {
    try {
      //InetAddress adress = null;
      //adress = InetAddress.getByName("localhost");
      //adress = InetAddress.getLocalHost();
      byte[] raw = typ.concat(data).getBytes();
      packet = new DatagramPacket(raw, raw.length, adress, portNr);
      socket = new DatagramSocket();
      String message = "";

      while (this.running) {
        changeRandom();
        socket.send(packet);
        TimeUnit.SECONDS.sleep(sleepTime);
        message = typ.concat(" : ").concat(data).concat(unit);
        raw = message.getBytes();
        packet = new DatagramPacket(raw, raw.length, adress, portNr);
        System.out.println("Datenpacket gesendet: " + message);
      }

    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }
}
