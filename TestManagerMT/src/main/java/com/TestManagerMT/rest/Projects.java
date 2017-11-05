package com.TestManagerMT.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.TestManagerMT.data.ProjectDAO;
import com.TestManagerMT.models.Project;

@Component
@Path("/projects")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class Projects {
	
	@Autowired
	ProjectDAO projectRepository;
	
	
	@GET
	public Response get() throws JSONException {
 		List<Project> projects = projectRepository.get();
		return Response.ok(projects.toString()).build();
	}
	
	@POST
	public Response create(Project project) throws JSONException {
		int result = projectRepository.insert(project);
		return Response.ok("{\"response\":" + result + "}").build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Integer id) throws JSONException {
		int result = projectRepository.delete(id);
		return Response.ok("{\"response\":" + result + "}").build();
	}
	
	@PUT
	public Response update(Project project) throws JSONException {
		int result = projectRepository.update(project);
		return Response.ok("{\"response\":" + result + "}").build();
	}
}
