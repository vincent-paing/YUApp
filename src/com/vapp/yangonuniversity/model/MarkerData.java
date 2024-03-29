package com.vapp.yangonuniversity.model;

import android.content.Context;

public class MarkerData {
	private String title;
	private double longitude;
	private double latitude;
	private String iconres;
	
	public MarkerData() {
		super();
	}

	public MarkerData(String title, double longitude, double latitude,
			String iconres) {
		super();
		this.title = title;
		this.longitude = longitude;
		this.latitude = latitude;
		this.iconres = iconres;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getIconres(Context context) {
		int id = context.getResources().getIdentifier(iconres, "drawable", "com.vapp.yangonuniversity");
		return id;
	}

	public void setIconres(String iconres) {
		this.iconres = iconres;
	}
	
	

}
