package com.TestManagerMT.models;

import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONException;
import org.json.JSONObject;

@XmlRootElement
public class Bug {
	public int id;
	public String priority;
	public String type;
	public Long createdAt;
	public String reporter;
	public String assignedTo;
	public String name;
	public String description;
	public int bugProjectId;
	
	public Bug() { }
	
	public Bug(int id, String priority, String type, long createdAt, String reporter, String assignedTo, String name, String description, int bugProjectId) {
		this.id = id;
		this.priority = priority;
		this.type = type;
		this.createdAt = createdAt;
		this.reporter = reporter;
		this.assignedTo = assignedTo;
		this.name = name;
		this.description = description;
		this.bugProjectId = bugProjectId;
	}
	
	@Override
    public String toString() {
        try {
            // takes advantage of toString() implementation to format {"a":"b"}
            return new JSONObject()
            		.put("id", id)
            		.put("priority", priority)
            		.put("type", type)
            		.put("createdAt", createdAt)
            		.put("reporter", reporter)
            		.put("assignedTo", assignedTo)
            		.put("name", name)
            		.put("description", description)
            		.put("bugProjectId", bugProjectId)
            		.toString();
        } catch (JSONException e) {
            return null;
        }
	}
	
	
}
