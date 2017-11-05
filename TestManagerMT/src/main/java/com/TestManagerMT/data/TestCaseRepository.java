package com.TestManagerMT.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;
import com.TestManagerMT.models.TestCase;

public class TestCaseRepository implements TestCaseDAO {
	
	private DataSource dataSource;
	
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int insert(TestCase testcase) {
		String sql= "INSERT INTO `testmanagerDB`.`TestCases`"
				+"(`name`,`description`,`owner`,`createdAt`,`projectId`) "
				+"VALUES(?,?,?,?,?);";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, testcase.name);
			ps.setString(2, testcase.description);
			ps.setString(3, testcase.owner);
			ps.setLong(4, testcase.createdAt);
			ps.setInt(5, testcase.projectId);
			
			
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
		String sql = "DELETE FROM testmanagerDB.TestCases WHERE id = ?";
		
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
	public int update(TestCase testcase) {
		if(testcase == null) return -1;
		
		TestCase existingTestCase = getById(testcase.id);
		TestCase mergedTestCase = mergeTestCase(existingTestCase, testcase);
		
		String sql = "UPDATE `testmanagerDB`.`TestCases` SET `name` = ?,"
				+ "`description` = ?,`owner` = ?,`createdAt` = ? WHERE `id` = ?;";
				
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mergedTestCase.name);
			ps.setString(2, mergedTestCase.description);
			ps.setString(3, mergedTestCase.owner);
			ps.setLong(4, mergedTestCase.createdAt);
			ps.setInt(5, mergedTestCase.id);
			
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
	public List<TestCase> getAll() {
		String sql = "SELECT * FROM testmanagerDB.TestCases";
		List<TestCase> testcases = new ArrayList<>();
		
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			/* Iterate over the rows. */
			while (rs.next()) {				
				TestCase testcase = new TestCase(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getLong(5),
					rs.getInt(6)
				);
				
				testcases.add(testcase);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
		
		return testcases;
	}
	
	@Override
	public List<TestCase> get(int projectId) {
		String sql = "SELECT * FROM testmanagerDB.TestCases WHERE projectId = ?";
		List<TestCase> testcases = new ArrayList<>();
		
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, projectId);
			ResultSet rs = ps.executeQuery();
			
			/* Iterate over the rows. */
			while (rs.next()) {				
				TestCase testcase = new TestCase(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getLong(5),
					rs.getInt(6)
				);
				
				testcases.add(testcase);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
		
		return testcases;
	}

	@Override
	public TestCase getById(int id) {
		String sql = "SELECT * FROM testmanagerDB.TestCases WHERE id = ?";
		TestCase testcase = null;
		
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			/* Iterate over the rows. */
			if (rs.next()) {				
				testcase = new TestCase(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getLong(5),
						rs.getInt(6)
				);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
		
		return testcase;
	}
	
	private TestCase mergeTestCase(TestCase existingTestCase, TestCase changedTestCase) {
		if(changedTestCase.name != null && !changedTestCase.name.isEmpty()) {
			existingTestCase.name = changedTestCase.name;
		}
		
		if(changedTestCase.description != null && !changedTestCase.description.isEmpty()) {
			existingTestCase.description = changedTestCase.description;
		}
		
		if(changedTestCase.owner != null && !changedTestCase.owner.isEmpty()) {
			existingTestCase.owner = changedTestCase.owner;
		}
		
		if(changedTestCase.createdAt != null && changedTestCase.createdAt != existingTestCase.createdAt) {
			existingTestCase.createdAt = changedTestCase.createdAt;
		}
		
		return existingTestCase;
	}
}