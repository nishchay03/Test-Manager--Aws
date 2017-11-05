package com.TestManagerMT.models;

import org.json.JSONException;
import org.json.JSONObject;

public class ProjectDetail {
	public Integer id;
	public Integer testcaseId;
	public Integer bugId;
	
	public String projectName;
	public String projectDescription;
	
	public String testcaseName;
	public String testcaseDescription;
	
	public String bugName;
	public String bugDescription;
	
	
	@Override
    public String toString() {
        try {
            // takes advantage of toString() implementation to format {"a":"b"}
            return new JSONObject()
            		.put("id", id)
            		.put("testcaseId", testcaseId)
            		.put("bugId", bugId)
            		.put("projectName", projectName)
            		.put("projectDescription", projectDescription)
            		.put("testcaseName", testcaseName)
            		.put("testcaseDescription", testcaseDescription)
            		.put("bugName", bugName)
            		.put("bugDescription", bugDescription)
            		.toString();
        } catch (JSONException e) {
            return null;
        }
	}
}
