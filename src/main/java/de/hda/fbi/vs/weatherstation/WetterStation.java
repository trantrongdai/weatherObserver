package de.hda.fbi.vs.weatherstation;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.thrift.TException;

import com.thrift.generated.InvalidOperationException;
import com.thrift.generated.WeatherData;
import com.thrift.models.WeatherServer;
import com.thrift.models.WeatherStationSubject;

public class WetterStation {

    private static int portNr = 9090;

    /** weather server port */
    private static final int PORT_NUMBER_OF_WEATHER_SERVER = 9901;
    /** List of weather server ip */
    private static final String[] WEATHER_SERVER_IPS = { "192.168.11.187" };
    /** Weather station subject to notify weather data to all weather server */
    private WeatherStationSubject weatherStationSubject = new WeatherStationSubject();
    /** Weather data instance */
    private WeatherData weatherData = new WeatherData();
    /** Localhost address */
    private InetAddress localhost;

    private ServerSocket serverSocket;
    private boolean running = true;

    public static List<String> temperaturList = new ArrayList<>();
    public static List<String> luftfeuchtigkeitList = new ArrayList<>();
    public static List<String> windgeschwindigkeitList = new ArrayList<>();
    public static List<String> regenList = new ArrayList<>();
    public static List<String> alle = new ArrayList<>();

    public void WetterStation() {
    }

    public void runWetterStation() throws InvalidOperationException, TException {
        try {
            DatagramSocket socket = new DatagramSocket(portNr);
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

            System.out.println("Wetterstation hat gestartet.");

            packetHandling(socket, packet);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void packetHandling(DatagramSocket socket, DatagramPacket packet) throws InvalidOperationException, TException, IOException {
        while (running) {
            System.out.println("vao day1");
            socket.receive(packet);
            System.out.println("vao day2");
            String data = new String(packet.getData(), 0, packet.getLength());
            System.out.printf("IP: %s | Port: %d | %s\n", packet.getAddress(), packet.getPort(), data);
            sensorToList(data);
            notifyAllWeatherServer();
        }
    }

    private void notifyAllWeatherServer() throws InvalidOperationException, TException, UnknownHostException {
        
        weatherStationSubject.registerWeatherServer(new WeatherServer(WEATHER_SERVER_IPS[0], PORT_NUMBER_OF_WEATHER_SERVER));

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        localhost = InetAddress.getLocalHost();
        System.out.println("Weather Station IP Address : " + (localhost.getHostAddress()).trim());
        weatherData.setStationIp(localhost.getHostAddress());
        weatherData.setTime(dateFormat.format(date));
        weatherData.setRain(false);
        weatherStationSubject.setWeatherData(weatherData);
        (new Thread() {
            @Override
            public void run() {
                try {
                    weatherStationSubject.notifyWeatherServer();
                } catch (InvalidOperationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (TException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sensorToList(String msg) {
        String msgToAdd = null;
        if (msg.contains(" ")) {
            if (msg.contains("Temperatur")) {
                msgToAdd = msg.replace("Temperatur", "");
                temperaturList.add(msgToAdd);
                weatherData.setTemperature(msgToAdd);
                alle.add(msgToAdd);
            } else if (msg.contains("Luftfeuchtigkeit")) {
                msgToAdd = msg.replace("Luftfeuchtigkeit", "");
                luftfeuchtigkeitList.add(msgToAdd);
                alle.add(msgToAdd);
            } else if (msg.contains("Windgeschwindigkeit")) {
                msgToAdd = msg.replace("Windgeschwindigkeit", "");
                windgeschwindigkeitList.add(msgToAdd);
                alle.add(msgToAdd);
            } else {
                msgToAdd = msg.replace("Regen", "");
                regenList.add(msgToAdd);
                alle.add(msgToAdd);
            }
        }

    }

    ;
}
