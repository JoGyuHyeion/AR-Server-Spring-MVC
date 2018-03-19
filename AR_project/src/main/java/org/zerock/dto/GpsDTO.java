package org.zerock.dto;

public class GpsDTO {

	private String title;
	private double latitude;
	private double longitude;
	private String picture;
	private String karmarker;
	private String pinmarker;
	private String xml;
	private String dat;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getKarmarker() {
		return karmarker;
	}
	public void setKarmarker(String karmarker) {
		this.karmarker = karmarker;
	}
	public String getPinmarker() {
		return pinmarker;
	}
	public void setPinmarker(String pinmarker) {
		this.pinmarker = pinmarker;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public String getDat() {
		return dat;
	}
	public void setDat(String dat) {
		this.dat = dat;
	}
	@Override
	public String toString() {
		return "GpsDTO [title=" + title + ", latitude=" + latitude + ", longitude=" + longitude + ", picture=" + picture
				+ ", karmarker=" + karmarker + ", pinmarker=" + pinmarker + ", xml=" + xml + ", dat=" + dat + "]";
	}

}
