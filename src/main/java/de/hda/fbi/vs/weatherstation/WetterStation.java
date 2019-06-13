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

import com.thrift.generate2.weatherService.Location;
import com.thrift.generate2.weatherService.Report;
import com.thrift.generate2.weatherService.Weather;
import com.thrift.generate2.weatherService.WeatherReport;
import com.thrift.generated.InvalidOperationException;
import com.thrift.generated.WeatherData;
import com.thrift.models.WeatherServer;
import com.thrift.models.WeatherStationSubject;

public class WetterStation {

    private static int portNr = 9090;

    /** weather server port */
    private static final int PORT_NUMBER_OF_WEATHER_SERVER = 9901;
    /** List of weather server ip */
    private static final String[] WEATHER_SERVER_IPS = { "192.168.48.1" };
    /** sensor name */
    private static final String TEMPERATUR = "Temperatur";
    private static final String LUFT_FEUCHTIG_KEIT = "Luftfeuchtigkeit"; 
    private static final String WIND_GESCHWINDIG_KEIT = "Windgeschwindigkeit"; 
    private static final String REGEN = "Regen";
    
   
    /** Weather station subject to notify weather data to all weather server */
    // for Demo
    //private WeatherStationSubject weatherStationSubject = new WeatherStationSubject();
    //WeatherStationSubject Hier
    private com.thrift.Modes2.WeatherStationSubject weatherStationSubject = new com.thrift.Modes2.WeatherStationSubject();
    /** Weather data instance */
    private WeatherData weatherData = new WeatherData();
    /** Weather Report instance */
    private WeatherReport weatherReport = new WeatherReport();
    /** Localhost address */
    private InetAddress localhost;
    /** Location of Station */
    private Location location = new Location((byte)20, "Darmstadt", 49.863, 8.64);

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
            System.out.println("Wetterstation hat gestartet..." + "Port : " + portNr + " IP : " + InetAddress.getLocalHost().getHostAddress());
            // Station register server with Port and IP of Server
            //weatherStationSubject.registerWeatherServer(new com.thrift.Modes2.WeatherServer(WEATHER_SERVER_IPS[0], PORT_NUMBER_OF_WEATHER_SERVER));
            //System.out.println(weatherStationSubject.login(location));
            weatherStationSubject.registerWeatherServer(new com.thrift.Modes2.WeatherServer("192.168.8.109", 9901));
            weatherStationSubject.registerWeatherServer(new com.thrift.Modes2.WeatherServer("192.168.8.106", 9901));
            
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
            notifyAllWeatherServe(data);
        }
    }
    private String getSensorValue(String sensorData) {
    	 String parts[] = sensorData.split(" ", 4);
    	 return parts[2];
    }
    
    private Report reportDependSensor(double sensorData) {
    	if (sensorData >= 0 && sensorData < 5) {
    		return Report.SNOW;
    	} else if( sensorData >= 5 && sensorData < 15) {
    		return Report.RAINY;
    	} else if(sensorData >= 15 && sensorData < 25) {
    		return Report.CLOUDY;
    	} else {
    		return Report.SUNNY;
		}
    }
    // Code Notify hier
    private void notifyAllWeatherServe(String data) throws InvalidOperationException, TException, UnknownHostException {
    	 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         Date date = new Date();
         localhost = InetAddress.getLocalHost();
         System.out.println("Weather Station IP Address : " + (localhost.getHostAddress()).trim());
         Report report = Report.SNOW;
         weatherReport.setLocation(location);
         ////// Set Weather Report Data
         String sensorData = null;
         if (data.contains(" ")) {
        	 if (data.contains(TEMPERATUR)) {
        		 sensorData = data.replace(TEMPERATUR, "");
        		 weatherReport.setTemperature(Double.parseDouble(getSensorValue(sensorData)));
        		 report = reportDependSensor(Double.parseDouble(getSensorValue(sensorData)));
             }else if (data.contains(LUFT_FEUCHTIG_KEIT)) {
            	 sensorData = data.replace(LUFT_FEUCHTIG_KEIT, "");
            	 weatherReport.setHumidity((byte)Integer.parseInt(getSensorValue(sensorData)));
            	 report = reportDependSensor(Double.parseDouble(getSensorValue(sensorData)));
             } else if (data.contains(WIND_GESCHWINDIG_KEIT)) {
            	 sensorData = data.replace(WIND_GESCHWINDIG_KEIT, "");
            	 weatherReport.setWindStrength((byte)Integer.parseInt(getSensorValue(sensorData)));
            	 report = reportDependSensor(Double.parseDouble(getSensorValue(sensorData)));
             }else {
            	 sensorData = data.replace(REGEN, "");
            	 weatherReport.setRainfall(Double.parseDouble(getSensorValue(sensorData)));
            	 report = reportDependSensor(Double.parseDouble(getSensorValue(sensorData)));
             }
         }
                  
         /*
         weatherReport.setTemperature(20.0);
         weatherReport.setHumidity((byte)35);
         weatherReport.setWindStrength((byte)25);
         weatherReport.setRainfall(45.0);
         */
         weatherReport.setReport(report);
         weatherReport.setAtmosphericpressure((short)2);
         weatherReport.setWindDirection((byte)60);
         weatherReport.setDateTime(dateFormat.format(date));
         weatherStationSubject.setWeatherReport(weatherReport);
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

    // Demo
    /*
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
     */
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
