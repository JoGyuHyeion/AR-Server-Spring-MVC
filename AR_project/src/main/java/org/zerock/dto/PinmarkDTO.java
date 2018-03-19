package org.zerock.dto;

public class PinmarkDTO {
	
	private String pName;
	private String pLat;
	private String pLon;
	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpLat() {
		return pLat;
	}
	public void setpLat(String pLat) {
		this.pLat = pLat;
	}
	public String getpLon() {
		return pLon;
	}
	public void setpLon(String pLon) {
		this.pLon = pLon;
	}
	
	@Override
	public String toString() {
		return "PinmarkDTO [pName=" + pName + ", pLat=" + pLat + ", pLon=" + pLon + "]";
	}
	
}
