package com.marinerxvu.reports.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.marinerxvu.reports.App;
import com.marinerxvu.reports.util.Report;

/**
 * 
 * class that used to write the new Merged CSV file.
 * 
 * @author Usama Ismail
 *
 */
public class WriteCSVFile implements IWriterFile {

	/**
	 * @param fileName : is absolute csv file name
	 * @param reports  : list of all the mapped reports.
	 * @throws IOException io exception in case the file not created.
	 */
	public void writeFile(String fileName, List<Report> reports) throws IOException {
		FileWriter csvWriter = new FileWriter(fileName + ".csv");
		csvWriter.append(appendHeaderForCsvFile());
		for (Report rowData : reports) {
			StringBuilder builder = new StringBuilder();
			for (String columnOrder : App.ORDER_OF_OUTPUT) {

				switch (columnOrder) {
				case "client-address":
					builder.append(rowData.getClientAddress() + ",");
					break;
				case "client-guid":
					builder.append(rowData.getClientGuid() + ",");
					break;
				case "request-time":
					builder.append(App.FORMATER.format(rowData.getRequestTime()) + ",");
					break;
				case "service-guid":
					String sg = rowData.getServiceGuid();
					if (App.SERVICE_GRID_COUNT.containsKey(sg))
						App.SERVICE_GRID_COUNT.put(sg, App.SERVICE_GRID_COUNT.get(sg) + 1);
					else
						App.SERVICE_GRID_COUNT.put(sg, 1);
					builder.append(sg + ",");

					break;
				case "retries-request":
					builder.append(rowData.getRetriesRequest() + ",");

					break;
				case "packets-requested":
					builder.append(rowData.getPacketsRequested() + ",");

					break;
				case "packets-serviced":
					builder.append(rowData.getPacketsServiced() + ",");

					break;
				case "max-hole-size":
					builder.append(rowData.getMaxHoleSize() + ",");
					break;
				}

			}
			csvWriter.append(builder.toString().substring(0, builder.length() - 1) + "\n");
		}

		csvWriter.flush();
		csvWriter.close();
	}

	/**
	 * in this method , the header of the CSV file will be created.
	 * 
	 * 
	 * @return String , the header of the csv file with separator
	 */
	public String appendHeaderForCsvFile() {
		return String.join(",", App.ORDER_OF_OUTPUT) + "\n";
	}
}
