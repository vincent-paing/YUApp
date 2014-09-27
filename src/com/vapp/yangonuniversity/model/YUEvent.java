package com.vapp.yangonuniversity.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("YUEvent")
public class YUEvent extends ParseObject {
	
	public YUEvent() {
		
	}

	public String getName() {
		return getString("name");
	}

	public String getDate() {
		return getString("date");
	}

	public String getDescription() {
		return getString("description");
	}

	public void setName(String name) {
		put("name", name);
	}

	public void setDate(String date) {
		put("date", date);
	}

	public void setDescription(String description) {
		put("description", description);
	}

	
	
	
}
