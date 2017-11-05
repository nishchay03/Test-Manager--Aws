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

import com.TestManagerMT.data.TestCaseDAO;
import com.TestManagerMT.models.TestCase;

@Component
@Path("/testcases")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class TestCases {
	
	@Autowired
	TestCaseDAO testcaseRepository;
	
	@GET
	@Path("/{id}")
	public Response getTestCases(@PathParam("id") Integer id) throws JSONException {
		List<TestCase> testcases = testcaseRepository.get(id);
		return Response.ok(testcases.toString()).build();
	}
	
	
	@POST
	public Response createTestCase(TestCase testcase) throws JSONException {
		int result = testcaseRepository.insert(testcase);
		return Response.ok("{\"response\":" + result + "}").build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteTestCase(@PathParam("id") Integer id) {
		int result = testcaseRepository.delete(id);
		return Response.ok("{\"response\":" + result + "}").build();
	}
	
	@PUT
	public Response updateTestCase(TestCase testcase) {
		int result = testcaseRepository.update(testcase);
		return Response.ok("{\"response\":" + result + "}").build();
	}
}
