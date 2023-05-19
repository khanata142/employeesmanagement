package com.amk.service;

import java.util.Optional;
import java.util.Random;
import com.amk.dao.Dao;
import com.amk.entity.EmployeeDetails;

public class EmployeeService {

	private EmployeeDetails employeeDetails;
	private Dao dao = new Dao();

	// SETTING DEPARTMENT FOR EMPLOYEEDETAILS OBJECT
	public void setDepartment(int choice, EmployeeDetails employeeDetails) {
		switch (choice) {
		case 1:
			employeeDetails.setDepartment("Sales");
			break;
		case 2:
			employeeDetails.setDepartment("IT");
			break;
		case 3:
			employeeDetails.setDepartment("Accounts");
			break;
		case 4:
			employeeDetails.setDepartment("HR");
			break;
		default:
			employeeDetails.setDepartment("none");

		}
		this.employeeDetails = employeeDetails;

	}

	// GENERATING AND SETTING EMAIL FOR EMPLOYEEDETAILS OBJECT
	public String generateEmail() {
		String emailId = this.employeeDetails.getFirstName() + "." + this.employeeDetails.getLastName() + "."
				+ this.employeeDetails.getDepartment() + "." + EmployeeDetails.companySuffix;
		emailId.replace(' ','.');//Replacing all spaces with dots.
		this.employeeDetails.setGeneratedEmailId(emailId);
		return emailId;
	}

	// GENERATING AND SETTING PASSWORD FOR EMPLOYEEDETAILS OBJECT
	public String generatePassword() {
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = "!@#$";
		String numbers = "1234567890";
		String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
		Random random = new Random();
		char[] password = new char[8];
		password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
		password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
		password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
		password[3] = numbers.charAt(random.nextInt(numbers.length()));
		for (int i = 4; i < 8; i++) {
			password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
		}
		String generatedPassword = new String(password);
		this.employeeDetails.setGeneratedPassword(generatedPassword);
		return generatedPassword;

	}

	// GENERATING AND SETTING EMPLOYEEID FOR EMPLOYEEDETAILS OBJECT
	public String generateEmployeeId() {
		String employeeId = this.employeeDetails.getFirstName().substring(0, 3) + "000"
				+ this.employeeDetails.getLastName().substring(0, 3);
		this.employeeDetails.setEmployeeId(employeeId.toUpperCase());
		return employeeId;
	}

	// ADDING EMPLOYEEDETAILS TO DATABASE
	public EmployeeDetails addEmployeeDetails() {
		// int rowsAffected = 0;
		EmployeeDetails employeeStored = dao.addEmployee(this.employeeDetails);
		return employeeStored;

	}

	// CHANGING EMPLOYEE PASSWORD
	public int changePassword(String employeeId, String newPassword) {
		int rowsAffected = 0;
		rowsAffected = dao.changePassword(employeeId, newPassword);
		return rowsAffected;
	}

	// FETCHING EMPLOYEE
	public Optional<EmployeeDetails> getEmployee(String employeeId) {
		Optional<EmployeeDetails> optional= dao.getEmployee(employeeId);
		return optional;
	}
	//DELETING EMPLOYEE
	public int deleteEmployee(String employeeId)
	{
		int rowsAffected=dao.deleteEmployee(employeeId);
		return rowsAffected;
	}
}
