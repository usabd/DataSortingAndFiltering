package com.marinerxvu.reports.reader;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.marinerxvu.reports.util.Report;

public interface IReadFile {

	List<Report> readFile(String pathToCsv)
			throws IOException, ParserConfigurationException, SAXException, ParseException, org.json.simple.parser.ParseException;

}
