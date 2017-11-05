package com.TestManagerMT.data;

import java.util.List;

import com.TestManagerMT.models.Project;

public interface ProjectDAO {
	public int insert(Project project);

	public int delete(int id);

	public int update(Project project);

	public List<Project> get();
	
	public Project getById(int id);
}