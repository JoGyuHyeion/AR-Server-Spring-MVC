package org.zerock.domain;

public class AquariumBoardVO {
	private int id_num;
	private String title;
	private double latitude;
	private double longitude;
	private String regdate;
	private String xml;
	private String dat;

	public AquariumBoardVO() {

	}

	public AquariumBoardVO(int id_num, String title, double latitude, double longitude, String regdate, String xml,
			String dat) {

		this.id_num = id_num;
		this.title = title;
		this.latitude = latitude;
		this.longitude = longitude;
		this.regdate = regdate;
		this.xml = xml;
		this.dat = dat;
	}

	public int getId_num() {
		return id_num;
	}

	public void setId_num(int id_num) {
		this.id_num = id_num;
	}

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

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
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
		return "AquariumBoardVO [id_num=" + id_num + ", title=" + title + ", latitude=" + latitude + ", longitude="
				+ longitude + ", regdate=" + regdate + ", xml=" + xml + ", dat=" + dat + "]";
	}

}