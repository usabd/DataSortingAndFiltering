package com.marinerxvu.reports;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import com.marinerxvu.reports.reader.ReadCSVFile;
import com.marinerxvu.reports.reader.ReadJSONFile;
import com.marinerxvu.reports.reader.ReadXMLFile;
import com.marinerxvu.reports.util.Report;
import com.marinerxvu.reports.writer.WriteCSVFile;

/**
 * Main Application Class .
 *
 */
public class App {

	/**
	 * logger for main class
	 */
	static Logger logger = Logger.getLogger(App.class.getName());

	/**
	 * sorted output columns for cvs file
	 */
	public static List<String> ORDER_OF_OUTPUT = new ArrayList<String>();
	/**
	 * date formater to arrange and parse data object file
	 */
	public static SimpleDateFormat FORMATER = new SimpleDateFormat("yyyy-M-dd HH:mm:ss zzz");
	/**
	 * HashMap to load and count Service Grid
	 */
	public static HashMap<String, Integer> SERVICE_GRID_COUNT = new HashMap<String, Integer>();

	/**
	 * main class
	 * 
	 * @param args passed aragument from console.
	 */
	public static void main(String[] args) {
		try {
			String fileName = "reports";

			ReadJSONFile rjf = new ReadJSONFile();
			List<Report> reports = rjf.readFile(fileName);
			logger.log(Level.INFO, "No Of Reports after loading JASON file are : " + reports.size());
			System.out.println();

			ReadXMLFile rxf = new ReadXMLFile();
			reports.addAll(rxf.readFile(fileName));
			logger.log(Level.INFO, "No Of Reports after loading XML file are : " + reports.size());
			System.out.println();

			ReadCSVFile rsf = new ReadCSVFile();
			reports.addAll(rsf.readFile(fileName));
			logger.log(Level.INFO, "No Of Reports after loading csv file are : " + reports.size());
			System.out.println();

			// removing reports which packet service = 0
			reports.removeIf(e -> e.getPacketsServiced().equals(0L));
			logger.log(Level.INFO, "No Of Reports after clearing reports from zero files : " + reports.size());
			System.out.println();

			// sorting the reports by request date.
			Collections.sort(reports);
			logger.log(Level.INFO, "sorting the reports by request date.");
			System.out.println();

			WriteCSVFile wsf = new WriteCSVFile();
			wsf.writeFile("outputFile", reports);

			logger.log(Level.INFO,"No Of Rows in CSV is " + reports.size());
			System.out.println("********************************************");
			System.out.println("Service Grid                         : Count");
			SERVICE_GRID_COUNT.forEach((k, v) -> System.out.println(k + " : " + v));
		} catch (IOException io) {
			logger.log(Level.WARNING, io.getMessage());
		} catch (ParseException e) {
			logger.log(Level.WARNING, e.getMessage());
		} catch (ClassCastException e) {
			logger.log(Level.WARNING, e.getMessage());
		} catch (ParserConfigurationException | SAXException | java.text.ParseException e1) {
			logger.log(Level.WARNING, e1.getMessage());
		}
	}
}
