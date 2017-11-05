package com.TestManagerMT.data;

import java.util.List;

import com.TestManagerMT.models.TestCase;


public interface TestCaseDAO {
	public int insert(TestCase testcase);

	public int delete(int id);

	public int update(TestCase testcase);
	
	public List<TestCase> getAll();

	public List<TestCase> get(int projectId);
	
	public TestCase getById(int id);
}