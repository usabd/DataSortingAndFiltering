package com.marinerxvu.reports.util;

import java.util.Date;

/**
 * @author Usama Ismail
 * 
 *         Report Util class contain the main parameters of Report details
 *
 */
public class Report implements Comparable<Report> {

	@Override
	public String toString() {
		return "Report [clientAddress=" + clientAddress + ", clientGuid=" + clientGuid + ", requestTime=" + requestTime
				+ ", serviceGuid=" + serviceGuid + ", retriesRequest=" + retriesRequest + ", packetsRequested="
				+ packetsRequested + ", packetsServiced=" + packetsServiced + ", maxHoleSize=" + maxHoleSize + "]";
	}

	private String clientAddress;
	private String clientGuid;
	private Date requestTime;
	private String serviceGuid;
	private Long retriesRequest;
	private Long packetsRequested;
	private Long packetsServiced;
	private Long maxHoleSize;

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientGuid() {
		return clientGuid;
	}

	public void setClientGuid(String clientGuid) {
		this.clientGuid = clientGuid;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getServiceGuid() {
		return serviceGuid;
	}

	public void setServiceGuid(String serviceGuid) {
		this.serviceGuid = serviceGuid;
	}

	public Long getRetriesRequest() {
		return retriesRequest;
	}

	public void setRetriesRequest(Long retriesRequest) {
		this.retriesRequest = retriesRequest;
	}

	public Long getPacketsRequested() {
		return packetsRequested;
	}

	public void setPacketsRequested(Long packetsRequested) {
		this.packetsRequested = packetsRequested;
	}

	public Long getPacketsServiced() {
		return packetsServiced;
	}

	public void setPacketsServiced(Long packetsServiced) {
		this.packetsServiced = packetsServiced;
	}

	public Long getMaxHoleSize() {
		return maxHoleSize;
	}

	public void setMaxHoleSize(Long maxHoleSize) {
		this.maxHoleSize = maxHoleSize;
	}

	@Override
	public int compareTo(Report obj) {
		return getRequestTime().compareTo(obj.getRequestTime());
	}

}
