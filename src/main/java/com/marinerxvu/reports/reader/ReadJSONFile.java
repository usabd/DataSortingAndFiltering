package com.marinerxvu.reports.reader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.marinerxvu.reports.util.Report;
import com.marinerxvu.reports.util.SystemUtil;

public class ReadJSONFile implements IReadFile {

	Logger logger = Logger.getLogger(ReadJSONFile.class.getName());

	public List<Report> readFile(String fileName) throws IOException, ParseException {
		// JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		List<Report> reports = new ArrayList<Report>();
		// Read JSON file
		File file = SystemUtil.readFile(fileName + ".json");
		FileReader json = new FileReader(file.getAbsoluteFile());

		Object obj = jsonParser.parse(json);

		JSONArray reportList = (JSONArray) obj;
		System.out.println(reportList);

		// Iterate over reports array
		reportList.forEach(emp -> reports.add(parseReportObject((JSONObject) emp)));

		return reports;
	}

	private Report parseReportObject(JSONObject reportObj) throws ClassCastException {

		Report report = new Report();

		report.setServiceGuid(SystemUtil.validateString(reportObj.get("service-guid")));
		report.setClientAddress(SystemUtil.validateString(reportObj.get("client-address")));
		report.setClientGuid(SystemUtil.validateString(reportObj.get("client-guid")));

		report.setRequestTime(new Date(SystemUtil.validateLong(reportObj.get("request-time"))));
		report.setRetriesRequest(SystemUtil.validateLong(reportObj.get("retries-request")));
		report.setMaxHoleSize(SystemUtil.validateLong(reportObj.get("max-hole-size")));
		report.setPacketsServiced(SystemUtil.validateLong(reportObj.get("packets-serviced")));
		report.setPacketsRequested(SystemUtil.validateLong(reportObj.get("packets-requested")));
		return report;

	}
}
