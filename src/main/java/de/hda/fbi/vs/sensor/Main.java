package de.hda.fbi.vs.sensor;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args1) {
    	
    	// Sensor Send Data to Station  with Port - Sensor Type - IP of Station
        String[] args = {"9090", "1", "127.0.0.1"};
        //String[] args = {"9090", "2", "172.16.204.152"};
        //String[] args = {"9090", "3", "172.16.204.152"};
        //String[] args = {"9090", "4", "172.16.204.152"};
        if (1 < args.length && args.length < 4) {
            System.out.println("[+] Sensor started, sending to port " + args[0]);
            int portNr = Integer.parseInt(args[0]);
            int sensorNr = Integer.parseInt(args[1]);
            InetAddress address = null;

            try {
                if (args.length == 3) {
                    address = InetAddress.getByName(args[2]);
                } else {
                    address = InetAddress.getLocalHost();
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            if (0 < sensorNr && sensorNr < 5) {
                Sensor sensor = sensorErstellen(portNr, sensorNr, address);
                sensor.runSensor();

            } else {
                System.out.println("Falsche Sensor Eingabe!");
            }
        } else {
            System.out.println("Please pass 2 or 3 arguments! First argument: port number, second argument: sender number, third argument (optinal): IP address");
        }
    }

    /* Creates a sensor, depending on the 2nd attribute */
    public static Sensor sensorErstellen(int portNr, int sensorNr, InetAddress address) {
        switch (sensorNr) {
        case 1:
            Temperatur temperatur = new Temperatur(portNr, address);
            return temperatur;
        case 2:
            Luftfeuchtigkeit luftfeuchtigkeit = new Luftfeuchtigkeit(portNr, address);
            return luftfeuchtigkeit;
        case 3:
            Windgeschwindigkeit windgeschwindigkeit = new Windgeschwindigkeit(portNr, address);
            return windgeschwindigkeit;
        default:
            Regen regen = new Regen(portNr, address);
            return regen;
        }
    }

}
