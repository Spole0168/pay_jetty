package com.ibs.portal.framework.server.metadata;

public class GridModelFormatOptions {
	private String srcformat;
	private String newformat;
	private String suffix;

	private Integer decimalPlaces;
	private String  thousandsSeparator;
	private String  decimalSeparator;
	
	public String getSrcformat() {
		return srcformat;
	}

	public void setSrcformat(String srcformat) {
		this.srcformat = srcformat;
	}

	public String getNewformat() {
		return newformat;
	}

	public void setNewformat(String newformat) {
		this.newformat = newformat;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Integer getDecimalPlaces() {
		return decimalPlaces;
	}

	public void setDecimalPlaces(Integer decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}

	public String getThousandsSeparator() {
		return thousandsSeparator;
	}

	public void setThousandsSeparator(String thousandsSeparator) {
		this.thousandsSeparator = thousandsSeparator;
	}

	public String getDecimalSeparator() {
		return decimalSeparator;
	}

	public void setDecimalSeparator(String decimalSeparator) {
		this.decimalSeparator = decimalSeparator;
	}
	
}
