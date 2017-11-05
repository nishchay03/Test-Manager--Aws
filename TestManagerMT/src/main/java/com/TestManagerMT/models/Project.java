package com.TestManagerMT.models;

import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONException;
import org.json.JSONObject;

@XmlRootElement
public class Project {
	public int id;
	public String name;
	public String owner;
	public String description;
	/* Price related properties */ 
	public Integer rate;
	/* Time related properties */
	public Long startDate;
	public Long endDate;
	public Integer hours;
	
	public Project() { 
	}
	
	public Project(int id, String name, String description, String owner, Integer rate, Long startDate, Long endDate, Integer hours) {
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.description = description;
		this.rate = rate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hours = hours;
	}
	
	@Override
    public String toString() {
        try {
            // takes advantage of toString() implementation to format {"a":"b"}
            return new JSONObject()
            		.put("id", id)
            		.put("name", name)
            		.put("owner", owner)
            		.put("description", description)
            		.put("rate", rate)
            		.put("startDate", startDate)
            		.put("endDate", endDate)
            		.put("hours", hours)
            		.toString();
        } catch (JSONException e) {
            return null;
        }
	}
}
