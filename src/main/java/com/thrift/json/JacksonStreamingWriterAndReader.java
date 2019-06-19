package com.thrift.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.thrift.generate2.weatherService.SystemWarning;
import com.thrift.generate2.weatherService.WeatherReport;

public class JacksonStreamingWriterAndReader {
	 // Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    public static final String PATH_FILE_CSV = "result.csv";
    public static final String PATH_FILE = "server_";
    public static final String PATH_FILE_WARNING = "warning_server_";
    public static final String CSV = ".csv";
    // CSV file header weather report
    private static final String FILE_HEADER = "Report,locationID,name,latitude,longtitude,description,temperature,humidity,windStrength,rainfall,atmosphericpressure,windDirection,dateTime";
	// 
    private static final String SYSTEM_WARNING_HEADER = "systemWarning,userId";
    /**
	 * Writer Weather Reports on file result.csv data
	 * @param fileName
	 * @param weatherReport
	 */
    public static void  WriterCsvFile(String fileName, WeatherReport weatherReport) {
		try {
			FileWriter fileWriter;
			File file = new File(System.getProperty("user.dir") + "\\" + fileName);
			
			if(file.getAbsoluteFile().exists()) {
				fileWriter = new FileWriter(fileName, true);
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
	           // System.out.println("CSV file was put successfully !!!");
			} else {
				fileWriter = new FileWriter(fileName, true);
				//System.out.println("NOT EXISTS !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
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
	            //System.out.println("CSV file was created successfully !!!");
			}
			 try {
	                fileWriter.flush();
	                fileWriter.close();
	            } catch (IOException e) {
	                System.out.println("Error while flushing/closing fileWriter !!!");
	                e.printStackTrace();
	                fileWriter.flush();
	                fileWriter.close();
	            }
		} catch (Exception e) {
			 System.out.println("Error in CsvFileWriter !!!");
	         e.printStackTrace();
		}
	}
	
	/**
	 * read result Csv data 
	 * @param fileName
	 */
	public static void  readerCsvFile(String fileName) {
		BufferedReader reader;
		try {
			//reader = Files.newBufferedReader(Paths.get(PATH_FILE_CSV));
			reader = Files.newBufferedReader(Paths.get(fileName));
			String line = reader.readLine();
			while(line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		}	
	}
	
	public static void writeSystemWarning(String fileName, SystemWarning systemWarning, long userId) {
		try {
			FileWriter fileWriter;
			File file = new File(System.getProperty("user.dir") + "\\" + fileName);
			boolean fileExits = file.getAbsoluteFile().exists();
			fileWriter = new FileWriter(fileName, true);
			if(!fileExits) {
				fileWriter.append(SYSTEM_WARNING_HEADER);
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
	         fileWriter.append(systemWarning.toString());
	         fileWriter.append(COMMA_DELIMITER);
	         fileWriter.append(Long.toString(userId));
	         fileWriter.append(NEW_LINE_SEPARATOR);
			 try {
	                fileWriter.flush();
	                fileWriter.close();
	            } catch (IOException e) {
	                System.out.println("Error while flushing/closing fileWriter !!!");
	                e.printStackTrace();
	                fileWriter.flush();
	                fileWriter.close();
	            }
		} catch (Exception e) {
			 System.out.println("Error in CsvFileWriter !!!");
	         e.printStackTrace();
		}
	}
}
