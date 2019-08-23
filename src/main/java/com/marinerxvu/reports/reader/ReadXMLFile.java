package com.marinerxvu.reports.reader;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.marinerxvu.reports.App;
import com.marinerxvu.reports.util.Report;
import com.marinerxvu.reports.util.SystemUtil;

public class ReadXMLFile implements IReadFile {

	Logger logger = Logger.getLogger(ReadXMLFile.class.getName());

	public List<Report> readFile(String fileName)
			throws ParserConfigurationException, SAXException, IOException, ParseException {
		List<Report> reports = new ArrayList<Report>();

		File file = SystemUtil.readFile(fileName + ".xml");

		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

		Document doc = dBuilder.parse(file.getAbsolutePath());
		NodeList nList = doc.getElementsByTagName("report");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Report object = parseReportObject(node);
				if (object.getServiceGuid() != null)
					reports.add(object);
			}
		}

		return reports;
	}

	private Report parseReportObject(Node node) throws ParseException {

		Report report = new Report();
		Element eElement = (Element) node;

		report.setClientAddress(eElement.getElementsByTagName("client-address").item(0).getTextContent());
		report.setClientGuid(eElement.getElementsByTagName("client-guid").item(0).getTextContent());
		String dateStr = eElement.getElementsByTagName("request-time").item(0).getTextContent();
		Date date = App.FORMATER.parse(dateStr);
		report.setRequestTime(date);
		report.setServiceGuid(eElement.getElementsByTagName("service-guid").item(0).getTextContent());
		report.setRetriesRequest(
				Long.parseLong(eElement.getElementsByTagName("retries-request").item(0).getTextContent()));
		report.setPacketsRequested(
				Long.parseLong(eElement.getElementsByTagName("packets-requested").item(0).getTextContent()));
		report.setPacketsServiced(
				Long.parseLong(eElement.getElementsByTagName("packets-serviced").item(0).getTextContent()));
		report.setMaxHoleSize(Long.parseLong(eElement.getElementsByTagName("max-hole-size").item(0).getTextContent()));

		return report;
	}

}
