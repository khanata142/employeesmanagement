package com.amk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import com.amk.connection.ConnectionFactory;
import com.amk.entity.EmployeeDetails;

public class Dao {

	ConnectionFactory connectionFactory = new ConnectionFactory();
	Connection connection;
	PreparedStatement ps;
	int rowsAffected = 0;

	// METHOD TO ADD EMPLOYEE
	public EmployeeDetails addEmployee(EmployeeDetails employeeDetails) {

		try {
			connection = connectionFactory.getConnection();
		} catch (Exception e) {
			System.out.println("Error connecting to the database...");
		}
		try {
			ps = connection.prepareStatement("insert into employeedetails values(?,?,?,?,?,?,?)");
			ps.setString(1, employeeDetails.getEmployeeId());
			ps.setString(2, employeeDetails.getFirstName());
			ps.setString(3, employeeDetails.getLastName());
			ps.setString(4, employeeDetails.getDepartment());
			ps.setString(5, employeeDetails.getGeneratedEmailId());
			ps.setString(6, employeeDetails.getAlternateEmailId());
			ps.setString(7, employeeDetails.getGeneratedPassword());
		} catch (Exception e) {
			System.out.println("Error creating statement....");
		}

		try {
			rowsAffected = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error updating database...");
		}
		if (rowsAffected > 0) {
			return employeeDetails;
		} else
			return null;

	}

//METHOD TO GET EMPLOYEE
	public Optional<EmployeeDetails> getEmployee(String employeeId) {
		EmployeeDetails employeeDetails = new EmployeeDetails();
		try {
			connection = connectionFactory.getConnection();

			ps = connection.prepareStatement("select * from employeedetails where employeeId=?");
			ps.setString(1, employeeId);
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					employeeDetails.setEmployeeId(rs.getString(1));
					employeeDetails.setFirstName(rs.getString(2));
					employeeDetails.setLastName(rs.getString(3));
					employeeDetails.setDepartment(rs.getString(4));
					employeeDetails.setGeneratedEmailId(rs.getString(5));
					employeeDetails.setAlternateEmailId(rs.getString(6));
					employeeDetails.setGeneratedPassword(rs.getString(7));
				}
			}

		} catch (Exception e) {
			System.out.println("Something went wrong while fetching employee details...");
			e.printStackTrace();

		}
		Optional<EmployeeDetails> optional = Optional.of(employeeDetails);
		return optional;
	}

	// METHOD TO CHANGE EMPLOYEE PASSWORD
	public int changePassword(String employeeId, String newPassword) {
		try {
			connection = connectionFactory.getConnection();
			ps = connection.prepareStatement("update employeeDetails set pwd=? where employeeId=?");
			ps.setString(1, newPassword);
			ps.setString(2, employeeId);
			rowsAffected = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error changing password...");

		}
		return rowsAffected;
	}

	// METHOD TO DELETE EMPLOYEE
	public int deleteEmployee(String employeeId) {
		try {
			connection = connectionFactory.getConnection();
			ps = connection.prepareStatement("delete from employeedetails where employeeId=?");
			ps.setString(1, employeeId);
			rowsAffected = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Something went wrong while deleting the employee...");
			e.printStackTrace();
		}
		return rowsAffected;

	}

}
