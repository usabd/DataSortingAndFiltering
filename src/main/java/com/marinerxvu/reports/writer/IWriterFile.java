package com.marinerxvu.reports.writer;

import java.io.IOException;
import java.util.List;

import com.marinerxvu.reports.util.Report;
/**
 * 
 * interface of Writing File.
 * 
 * @author Uasma Ismail
 *
 */
public interface IWriterFile {

	public void writeFile(String fileName, List<Report> reports)  throws IOException;

}
