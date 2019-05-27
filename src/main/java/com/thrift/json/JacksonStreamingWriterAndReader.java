package com.thrift.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.thrift.generate2.weatherService.WeatherReport;

public class JacksonStreamingWriterAndReader {
	 // Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    public static final String PATH_FILE_CSV = "result.csv";
    // CSV file header
    private static final String FILE_HEADER = "Report,locationID,name,latitude,longtitude,description,temperature,humidity,windStrength,rainfall,atmosphericpressure,windDirection,dateTime";
	public static void  WriterCsvFile(String fileName, WeatherReport weatherReport) {
		FileWriter fileWriter = null;
		try {
			File file = new File(fileName);
			fileWriter = new FileWriter(fileName);
			
			if(!file.exists()) {
				fileWriter.append(weatherReport.getReport().toString());
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getLocation().getLocationID()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(weatherReport.getLocation().getName());
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getLocation().getLatitude()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getLocation().getLongitude()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(weatherReport.getLocation().getDescription());
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getTemperature()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getHumidity()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getWindStrength()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getRainfall()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getAtmosphericpressure()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getWindDirection()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(weatherReport.getDateTime());
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(NEW_LINE_SEPARATOR);
	            System.out.println("CSV file was put successfully !!!");
			} else {
			
				// Write the CSV file header
	            fileWriter.append(FILE_HEADER);
	            // Add a new line separator after the header
	            fileWriter.append(NEW_LINE_SEPARATOR);
	            fileWriter.append(weatherReport.getReport().toString());
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getLocation().getLocationID()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(weatherReport.getLocation().getName());
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getLocation().getLatitude()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getLocation().getLongitude()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(weatherReport.getLocation().getDescription());
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getTemperature()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getHumidity()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getWindStrength()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getRainfall()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getAtmosphericpressure()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(String.valueOf(weatherReport.getWindDirection()));
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(weatherReport.getDateTime());
	            fileWriter.append(COMMA_DELIMITER);
	            fileWriter.append(NEW_LINE_SEPARATOR);
	            System.out.println("CSV file was created successfully !!!");
			}
		} catch (Exception e) {
			 System.out.println("Error in CsvFileWriter !!!");
	         e.printStackTrace();
		} finally {
			 try {
	                fileWriter.flush();
	                fileWriter.close();
	            } catch (IOException e) {
	                System.out.println("Error while flushing/closing fileWriter !!!");
	                e.printStackTrace();
	            }
		}
	}

}
