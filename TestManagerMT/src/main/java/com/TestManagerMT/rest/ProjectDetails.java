package com.TestManagerMT.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.TestManagerMT.data.BugDAO;
import com.TestManagerMT.data.ProjectDAO;
import com.TestManagerMT.data.TestCaseDAO;
import com.TestManagerMT.models.Bug;
import com.TestManagerMT.models.Project;
import com.TestManagerMT.models.ProjectDetail;
import com.TestManagerMT.models.TestCase;


@Component
@Path("/projectdetails")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class ProjectDetails {

	@Autowired
	ProjectDAO projectRepository;
	
	@Autowired
	TestCaseDAO testcaseRepository;
	
	@Autowired
	BugDAO bugRepository;
	
	@GET
	public Response getAll() throws JSONException {
		List<ProjectDetail> projectDetails = new ArrayList<>();
		
		List<Project> projects = projectRepository.get();
		
		for(Project project: projects) {
			List<TestCase> testcases = testcaseRepository.get(project.id);
			List<Bug> bugs = bugRepository.get(project.id);
			
			for(TestCase testcase: testcases) {
				ProjectDetail projectDetail = new ProjectDetail();
				projectDetail.id = project.id;
				projectDetail.projectName = project.name;
				projectDetail.projectDescription = project.description;
				
				projectDetail.testcaseId = testcase.id;
				projectDetail.testcaseName = testcase.name;
				projectDetail.testcaseDescription = testcase.description;
				
				projectDetail.bugName = "NA";
				projectDetail.bugDescription = "NA";
				
				projectDetails.add(projectDetail);
			}
			
			for(Bug bug : bugs) {
				ProjectDetail projectDetail = new ProjectDetail();
				projectDetail.id = project.id;
				projectDetail.projectName = project.name;
				projectDetail.projectDescription = project.description;
				
				projectDetail.bugId = bug.id;
				projectDetail.bugName = bug.name;
				projectDetail.bugDescription = bug.description;
				
				projectDetail.testcaseName = "NA";
				projectDetail.testcaseDescription = "NA";
				
				projectDetails.add(projectDetail);
			}
			
		}
		
		return Response.ok(projectDetails.toString()).build();
	}
	
}
