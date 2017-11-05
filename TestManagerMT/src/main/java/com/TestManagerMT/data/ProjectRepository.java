package com.TestManagerMT.data;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.TestManagerMT.models.Project;

public class ProjectRepository implements ProjectDAO {
	
	private DataSource dataSource;
	
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int insert(Project project) {
		String sql = "INSERT INTO testmanagerDB.Projects (`name`,`owner`,`description`,`rate`,`startDate`,`endDate`,`hours`)"
                     + " VALUES (?,?,?,?,?,?,?);";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, project.name);
			ps.setString(2, project.owner);
			ps.setString(3, project.description);
			ps.setInt(4, project.rate);
			ps.setLong(5, project.startDate);
			ps.setLong(6, project.endDate);
			ps.setInt(7, project.hours);
			
			int res = ps.executeUpdate();
			
			ps.close();
			
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM testmanagerDB.Projects WHERE id = ?";
		String testcaseSql = "DELETE FROM `testmanagerDB`.`TestCases` WHERE projectId = ?";
		String bugSql = "DELETE FROM `testmanagerDB`.`Bugs` WHERE bugProjectId = ?";
		
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps1 = conn.prepareStatement(testcaseSql);
			ps1.setInt(1, id);
			int rs = ps1.executeUpdate();
			
			PreparedStatement ps2 = conn.prepareStatement(bugSql);
			ps2.setInt(1, id);
			rs += ps2.executeUpdate();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs += ps.executeUpdate();
			
			ps.close();
			ps1.close();
			ps2.close();
			
			return rs;
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public int update(Project project) {
		if(project == null) return -1;
		
		Project existingProject = getById(project.id);
		Project mergedProject = mergeProject(existingProject, project);
		
		String sql = "UPDATE testmanagerDB.Projects SET `name` = ?, `owner` = ?, `description` = ?, `rate` = ?, `startDate` = ?, `endDate` = ?, `hours` = ?"
                + " WHERE id = ?";
		Connection conn = null;
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mergedProject.name);
			ps.setString(2, mergedProject.owner);
			ps.setString(3, mergedProject.description);
			ps.setInt(4, mergedProject.rate);
			ps.setLong(5, mergedProject.startDate);
			ps.setLong(6, mergedProject.endDate);
			ps.setInt(7, mergedProject.hours);
			ps.setInt(8, mergedProject.id);
			int res = ps.executeUpdate();
			
			ps.close();
			
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
}

	@Override
	public List<Project> get() {
		String sql = "SELECT * FROM testmanagerDB.Projects";
		List<Project> projects = new ArrayList<>();
		
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			/* Iterate over the rows. */
			while (rs.next()) {				
				Project project = new Project(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getInt(5),
					rs.getLong(6),
					rs.getLong(7),
					rs.getInt(8)
				);
				projects.add(project);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
		
		return projects;
	}

	public Project getById(int id) {
		String sql = "SELECT * FROM testmanagerDB.Projects WHERE id = ?";
		Project project = null;
		
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			/* Iterate over the rows. */
			if (rs.next()) {				
				project = new Project(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getLong(6),
						rs.getLong(7),
						rs.getInt(8)
				);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
		
		return project;
	}
	
	private Project mergeProject(Project existingProject, Project changedProject) {
		
		if(changedProject.name != null && !changedProject.name.isEmpty()) {
			existingProject.name = changedProject.name;
		}
		
		if(changedProject.owner != null && !changedProject.owner.isEmpty()) {
			existingProject.owner = changedProject.owner;
		}
		
		if(changedProject.description != null && !changedProject.description.isEmpty()) {
			existingProject.description = changedProject.description;
		}
		
		if(changedProject.rate != null && changedProject.rate != existingProject.rate) {
			existingProject.rate = changedProject.rate;
		}
		
		if(changedProject.hours != null && changedProject.hours != existingProject.hours) {
			existingProject.hours = changedProject.hours;
		}
			
		if(changedProject.startDate != null && changedProject.startDate != existingProject.startDate) {
			existingProject.startDate = changedProject.startDate;
		}
		
		if(changedProject.startDate != null && changedProject.endDate != existingProject.endDate) {
			existingProject.endDate = changedProject.endDate;
		}
		
		return existingProject;
	}

}
