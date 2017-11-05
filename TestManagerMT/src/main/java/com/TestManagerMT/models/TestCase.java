package com.TestManagerMT.models;

import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONException;
import org.json.JSONObject;

@XmlRootElement
public class TestCase {
	public int id;
	public String name;
	public String description;
	public String owner;
	public Long createdAt;
	public int projectId;
	
	public TestCase() { }
	
	public TestCase(int id, String name, String description, String owner, long createdAt, int projectId) {
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.description = description;
		this.createdAt = createdAt;
		this.projectId = projectId;
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
            		.put("createdAt", createdAt)
            		.put("projectId", projectId)
            		.toString();
        } catch (JSONException e) {
            return null;
        }
	}
}