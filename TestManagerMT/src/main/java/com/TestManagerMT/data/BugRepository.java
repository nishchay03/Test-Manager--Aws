package com.TestManagerMT.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.TestManagerMT.models.Bug;

public class BugRepository implements BugDAO {
	
	private DataSource dataSource;
	
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public int insert(Bug bug) {
		String sql = "INSERT INTO `testmanagerDB`.`Bugs` "
				+ "(`priority`,`type`,`createdAt`,`reporter`,`assignedTo`,`name`,`description`, `bugProjectId`) "
				+ "VALUES (?,?,?,?,?,?,?,?);";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bug.priority);
			ps.setString(2, bug.type);
			ps.setLong(3, bug.createdAt);
			ps.setString(4, bug.reporter);
			ps.setString(5, bug.assignedTo);
			ps.setString(6, bug.name);
			ps.setString(7, bug.description);
			ps.setInt(8, bug.bugProjectId);
			
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
		String sql = "DELETE FROM testmanagerDB.Bugs WHERE id = ?";
		
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int rs = ps.executeUpdate();
			
			ps.close();
			
			return rs;
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public int update(Bug bug) {
		if(bug == null) return -1;
		
		Bug existingBug = getById(bug.id);
		Bug mergedBug = mergeBug(existingBug, bug);
		
		String sql = "UPDATE `testmanagerDB`.`Bugs` SET `priority` = ?,"
				+ "`type` = ?,`createdAt` = ?,`reporter` = ?,`assignedTo` = ?,"
				+ "`name` = ?,`description` = ? WHERE `id` = ?;";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mergedBug.priority);
			ps.setString(2, mergedBug.type);
			ps.setLong(3, mergedBug.createdAt);
			ps.setString(4, mergedBug.reporter);
			ps.setString(5, mergedBug.assignedTo);
			ps.setString(6, mergedBug.name);
			ps.setString(7, mergedBug.description);
			ps.setInt(8, mergedBug.id);
			
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
	public List<Bug> getAll() {
		String sql = "SELECT * FROM testmanagerDB.Bugs";
		List<Bug> bugs = new ArrayList<>();
		
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			/* Iterate over the rows. */
			while (rs.next()) {				
				Bug bug = new Bug(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getLong(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getInt(9)
				);
				
				bugs.add(bug);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
		
		return bugs;
	}
	
	@Override
	public List<Bug> get(int projectId) {
		String sql = "SELECT * FROM testmanagerDB.Bugs WHERE bugProjectId = ?";
		List<Bug> bugs = new ArrayList<>();
		
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, projectId);
			ResultSet rs = ps.executeQuery();
			
			/* Iterate over the rows. */
			while (rs.next()) {				
				Bug bug = new Bug(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getLong(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getInt(9)
				);
				
				bugs.add(bug);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
		
		return bugs;
	}

	@Override
	public Bug getById(int id) {
		String sql = "SELECT * FROM testmanagerDB.Bugs WHERE id = ?";
		Bug bug = null;
		
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			/* Iterate over the rows. */
			if (rs.next()) {				
				bug = new Bug(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getLong(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getInt(9)
				);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
		
		return bug;
	}

	private Bug mergeBug(Bug existingBug, Bug changedBug) {
		if(changedBug.priority != null && !changedBug.priority.isEmpty()) {
			existingBug.priority = changedBug.priority;
		}
		
		if(changedBug.priority != null && !changedBug.priority.isEmpty()) {
			existingBug.type = changedBug.type;
		}
		
		if(existingBug.createdAt != null &&  existingBug.createdAt!= changedBug.createdAt) {
			existingBug.createdAt = changedBug.createdAt;
		}
		
		if(changedBug.reporter != null && !changedBug.reporter.isEmpty()) {
			existingBug.reporter = changedBug.reporter;
		}
		
		if(changedBug.assignedTo != null && !changedBug.assignedTo.isEmpty()) {
			existingBug.assignedTo = changedBug.assignedTo;
		}
		
		if(changedBug.name != null && !changedBug.name.isEmpty()) {
			existingBug.name = changedBug.name;
		}
		
		if(changedBug.description != null && !changedBug.description.isEmpty()) {
			existingBug.description = changedBug.description;
		}
		
		return existingBug;
	}
}