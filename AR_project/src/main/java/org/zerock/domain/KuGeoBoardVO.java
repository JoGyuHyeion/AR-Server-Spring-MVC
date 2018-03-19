package org.zerock.domain;

public class KuGeoBoardVO {
	private int id_num;
	private String title;
	private double latitude;
	private double longitude;
	private String regdate;
	private String pinmarker;
	private String karmarker;

	public KuGeoBoardVO() {
	}
	
	public KuGeoBoardVO(int id_num, String title, double latitude, double longitude, String regdate, String pinmarker,
			String karmarker) {
		this.id_num = id_num;
		this.title = title;
		this.latitude = latitude;
		this.longitude = longitude;
		this.regdate = regdate;
		this.pinmarker = pinmarker;
		this.karmarker = karmarker;
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

	public String getPinmarker() {
		return pinmarker;
	}

	public void setPinmarker(String pinmarker) {
		this.pinmarker = pinmarker;
	}

	public String getKarmarker() {
		return karmarker;
	}

	public void setKarmarker(String karmarker) {
		this.karmarker = karmarker;
	}

	@Override
	public String toString() {
		return "KuGeoBoardVO [id_num=" + id_num + ", title=" + title + ", latitude=" + latitude + ", longitude="
				+ longitude + ", regdate=" + regdate + ", pinmarker=" + pinmarker + ", karmarker=" + karmarker + "]";
	}



}