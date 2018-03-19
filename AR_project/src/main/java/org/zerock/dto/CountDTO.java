package org.zerock.dto;

public class CountDTO {

	private int aquarium;
	private int draw;
	private int geo;
	private int sign;
	private int kuAquarium;
	private int kuGeo;
	private int kuSign;

	public int getAquarium() {
		return aquarium;
	}

	public void setAquarium(int aquarium) {
		this.aquarium = aquarium;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getGeo() {
		return geo;
	}

	public void setGeo(int geo) {
		this.geo = geo;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public int getKuAquarium() {
		return kuAquarium;
	}

	public void setKuAquarium(int kuAquarium) {
		this.kuAquarium = kuAquarium;
	}

	public int getKuGeo() {
		return kuGeo;
	}

	public void setKuGeo(int kuGeo) {
		this.kuGeo = kuGeo;
	}

	public int getKuSign() {
		return kuSign;
	}

	public void setKuSign(int kuSign) {
		this.kuSign = kuSign;
	}

	@Override
	public String toString() {
		return "CountDTO [aquarium=" + aquarium + ", draw=" + draw + ", geo=" + geo + ", sign=" + sign + ", kuAquarium="
				+ kuAquarium + ", kuGeo=" + kuGeo + ", kuSign=" + kuSign + "]";
	}

}
