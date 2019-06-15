package de.hda.fbi.vs.weatherstation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HttpServer extends Thread {

  private static final int PORTNR = 9090;
  private boolean running = true;
  private ServerSocket serverSocket;
  ArrayList<String> header = new ArrayList<>();

  public HttpServer() throws IOException {
    serverSocket = new ServerSocket(PORTNR);
    System.out.println("[+] Started the TCP socket server at port " + PORTNR);
  }

  @Override
  public void run() {
    while (running) {
      Socket connectionSocket = null;
      try {
        connectionSocket = serverSocket.accept();

        BufferedReader in = new BufferedReader(
            new InputStreamReader(connectionSocket.getInputStream()));
        BufferedWriter out = new BufferedWriter(
            new OutputStreamWriter(connectionSocket.getOutputStream()));

        if (checkHeader(in)) {
          printStart(out);
          handleInput(out);
          printEnd(out);
        }
        out.close();
        in.close();
        connectionSocket.close();

      } catch (IOException e) {
        e.printStackTrace();

      } finally {
        closeConnectionSocket(connectionSocket);
        header.clear();
      }
    }
  }

  private boolean checkHeader(BufferedReader in) throws IOException {
    boolean checkedGET = false;
    boolean checkedHost = false;
    boolean checkedUser = false;
    boolean checkedAccept = false;
    boolean checkedLanguage = false;
    boolean checkedEncoding = false;
    boolean checkedLeereZeile = false;

    ArrayList<String> header = getHeader(in);

    if (header.size() == 0) {
      return false;
    } else {
      for (String line : header) {
        String[] splittedLine = line.split(" ");
        String stringToCheck = splittedLine[0];

        switch (stringToCheck) {
          case "GET":
            checkedGET = true;
            break;
          case "Host:":
            checkedHost = true;
            break;
          case "User-Agent:":
            checkedUser = true;
            break;
          case "Accept:":
            checkedAccept = true;
            break;
          case "Accept-Language:":
            checkedLanguage = true;
            break;
          case "Accept-Encoding:":
            checkedEncoding = true;
            break;
          case "LeereZeile":
            checkedLeereZeile = true;
            break;
          default:
            break;
        }
      }
    }
    if (checkedGET && checkedHost && checkedUser && checkedAccept && checkedLanguage
        && checkedEncoding && checkedLeereZeile) {
      System.out.println("checked = true");
      return true;
    } else {
      System.out.println("checked = false");
      return false;
    }
  }

  private ArrayList getHeader(BufferedReader in) throws IOException {
    int counter = 0;
    for (String line; (line = in.readLine()) != null; ) {
      counter++;
      System.out.println(line);
      header.add(line);
      if (line.isEmpty()) {
        line = "LeereZeile " + counter;
        header.add(line);
        return header;
      }
    }
    return header;
  }

  private void printStart(BufferedWriter out) throws IOException {
    out.write("HTTP/1.1 200 OK");
    out.write("Content-Type: text/html\r\n");
    out.write("\r\n");
    out.write("<!DOCTYPE html>\r\n");
    out.write("<head>\r\n");
    out.write("<meta charset=\"UTF-8\" />\r\n");
    out.write("<title>Wetterstation</title>\r\n");
    out.write("</head>\r\n");
    out.write("<body>\r\n");
    out.write("<h1>Sensoren:</h1>\r\n");
    out.write("<div>\r\n");
    out.write("<a href=\"/Temperatur\">Temperatur</a>\r\n");
    out.write("<a href=\"/Luftfeuchtigkeit\">Luftfeuchtigkeit</a>\r\n");
    out.write("<a href=\"/Windgeschwindigkeit\">Windgeschwindigkeit</a>\r\n");
    out.write("<a href=\"/Regen\">Regen</a>\r\n");
    //out.write("<a href=\"/Alle\">Alle</a>\r\n");
    out.write("</div>\r\n");
    out.write("\r\n");
  }

  private void handleInput(BufferedWriter out) throws IOException {
    String request = header.get(0);
    String[] splittedRequest = request.split(" ");
    String get = splittedRequest[0];
    String sensorTyp = "";
    String parameter = "";

    if (splittedRequest[1].lastIndexOf("/") > 0) {
      sensorTyp = splittedRequest[1].substring(1, splittedRequest[1].lastIndexOf("/"));
      parameter = splittedRequest[1].substring(splittedRequest[1].lastIndexOf("/") + 1);
    } else {
      sensorTyp = splittedRequest[1].substring(1);
    }


    if (get.equals("GET") && !sensorTyp.equals("favicon.ico") && !sensorTyp.equals("") ){
      if(sensorTyp.equals("Temperatur") || sensorTyp.equals("Luftfeuchtigkeit") || sensorTyp.equals("Windgeschwindigkeit")
          || sensorTyp.equals("Regen") || sensorTyp.equals("Alle")) {

        System.out.println("SensorTyp: " + sensorTyp);
        System.out.println("Parameter: " + parameter);

        if (parameter.equals("Status")) {
          printSensorStatus(sensorTyp, out);
        } else {
          printSensor(sensorTyp, parameter, out);
        }
      }else{
        out.write("Diese Seite existiert nicht.");
      }
    }

  }

  private void printSensorStatus(String sensorTyp, BufferedWriter out) throws IOException {
    List<String> sensorDaten = getSensordaten(sensorTyp);
    String status = "";
    if(!sensorDaten.isEmpty()){
      status = sensorDaten.get(sensorDaten.size() - 1);
      out.write("<p>");
      out.write("Der montane Status des ");
      out.write(sensorTyp);
      out.write(" Sensors ist: ");
      out.write(status);
      out.write(".");
      out.write("</p>");
    }else{
      out.write("<p>");
      out.write("Keine Daten für den ");
      out.write(sensorTyp);
      out.write(" Sensor.");
      out.write("</p>");
    }
  }

  private void printSensor(String sensorTyp, String parameter, BufferedWriter out) throws IOException {
    List<String> sensorDaten = getSensordaten(sensorTyp);
    out.write("<section>\r\n");
    out.write("<h2>");
    out.write(sensorTyp);
    out.write("</h2>\r\n");
    out.write("\r\n");
    for (String daten : sensorDaten) {
      if (daten != null) {
        printDaten(out, daten, sensorTyp);
        out.write("</section>");
      }
    }
  }
  private List getSensordaten(String sensorTyp){
    switch (sensorTyp) {
      case "Temperatur":
        return WetterStation.temperaturList;
      case "Luftfeuchtigkeit":
        return WetterStation.luftfeuchtigkeitList;
      case "Windgeschwindigkeit":
        return WetterStation.windgeschwindigkeitList;
      case "Regen":
        return WetterStation.regenList;
      default :
        return WetterStation.alle;

    }
  }

  private void printDaten(BufferedWriter out, String daten, String sensorTyp) throws IOException {
    out.flush();
    out.write("\r\n");
    out.write("<p>");
    out.write(sensorTyp);
    out.write(": ");
    out.write(daten);
    out.write("</p>");
  }

  private void printEnd(BufferedWriter out) throws IOException {
    out.write("</body>");
    out.write("</html>");
  }

  private void closeConnectionSocket(Socket connectionSocket) {
    if (connectionSocket != null) {
      try {
        connectionSocket.close();
      } catch (IOException e) {
        // Do nothing.
      }
    }
  }
}