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

import com.TestManagerMT.data.BugDAO;
import com.TestManagerMT.models.Bug;

@Component
@Path("/bugs")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class Bugs {
	@Autowired
	BugDAO bugRepository;
	
	@GET
	@Path("/{id}")
	public Response get(@PathParam("id") Integer id) throws JSONException {
		List<Bug> bugs = bugRepository.get(id);
		return Response.ok(bugs.toString()).build();
	}
	
	@POST
	public Response create(Bug bug) throws JSONException {
		int result = bugRepository.insert(bug);
		return Response.ok("{\"response\":" + result + "}").build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Integer id) throws JSONException {
		int result = bugRepository.delete(id);
		return Response.ok("{\"response\":" + result + "}").build();
	}
	
	@PUT
	public Response update(Bug bug) throws JSONException {
		int result = bugRepository.update(bug);
		return Response.ok("{\"response\":" + result + "}").build();
	}
	
}