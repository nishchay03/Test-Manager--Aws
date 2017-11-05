package com.TestManagerMT.data;

import java.util.List;

import com.TestManagerMT.models.Bug;

public interface BugDAO {
	public int insert(Bug bug);

	public int delete(int id);

	public int update(Bug bug);

	public List<Bug> getAll();
	
	public List<Bug> get(int projectId);
	
	public Bug getById(int id);
}