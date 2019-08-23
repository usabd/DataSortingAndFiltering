package com.marinerxvu.reports.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.marinerxvu.reports.App;
import com.marinerxvu.reports.util.Report;
import com.marinerxvu.reports.util.SystemUtil;

public class ReadCSVFile implements IReadFile {

	Logger logger = Logger.getLogger(ReadCSVFile.class.getName());

	/**
	 * read the file and return Array list of Report object
	 * 
	 * 
	 * public List Reports readFile(String pathToCsv) throws IOException
	 * 
	 *
	 * @param pathToCsv the absolute value of the csv file
	 * @return the array list of the Reports that contain in csv file
	 * @throws ParseException in case the pars is for csv not done propely.
	 * @throws IOException  in case the file is not read properly. 
	 * @since 1.8
	 */
	public List<Report> readFile(String pathToCsv) throws IOException, ParseException {

		List<Report> reports = new ArrayList<Report>();
		File csvFile = SystemUtil.readFile(pathToCsv + ".csv");
		// create BufferedReader and read data from csv
		BufferedReader csvReader = new BufferedReader(new FileReader(csvFile.getAbsolutePath()));
		String row = "";
		int i = 0;
		while ((row = csvReader.readLine()) != null) {
			List<String> data = Arrays.asList(row.split(","));

			if (i == 0) {
				for (String cell : data) {
					App.ORDER_OF_OUTPUT.add(cell);
					i++;
				}
			} else {
				Report object = parseReportObject(data);
				if (object.getServiceGuid() != null)
					reports.add(object);
			}
		}
		csvReader.close();

		return reports;

	}

	private Report parseReportObject(List<String> data) throws ParseException {
		Report report = new Report();
		// try and catch for casting long

		for (int i = 0; i < data.size(); i++) {
			switch (App.ORDER_OF_OUTPUT.get(i)) {
			case "client-address":
				report.setClientAddress(data.get(i));
				break;

			case "client-guid":
				report.setClientGuid(data.get(i));

				break;
			case "request-time":

				Date date = App.FORMATER.parse(data.get(i));
				report.setRequestTime(date);

				break;
			case "service-guid":
				report.setServiceGuid(data.get(i));

				break;
			case "retries-request":
				report.setRetriesRequest(Long.parseLong(data.get(i)));

				break;
			case "packets-requested":
				report.setPacketsRequested(Long.parseLong(data.get(i)));

				break;
			case "packets-serviced":
				report.setPacketsServiced(Long.parseLong(data.get(i)));

				break;
			case "max-hole-size":
				report.setMaxHoleSize(Long.parseLong(data.get(i)));

				break;
			}
		}
		return report;
	}
}
