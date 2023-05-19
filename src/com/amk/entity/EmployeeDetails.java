package com.amk.entity;

public class EmployeeDetails {
	private String employeeId;
	private String firstName;
	private String lastName;
	private String department;
	private String generatedEmailId;
	private String generatedPassword;
	private String alternateEmailId;
	static public String companySuffix = "amk.com";

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGeneratedEmailId() {
		return generatedEmailId;
	}

	public void setGeneratedEmailId(String generatedEmailId) {
		this.generatedEmailId = generatedEmailId;
	}

	public String getGeneratedPassword() {
		return generatedPassword;
	}

	public void setGeneratedPassword(String generatedPassword) {
		this.generatedPassword = generatedPassword;
	}

	public String getAlternateEmailId() {
		return alternateEmailId;
	}

	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}

	public String getCompanySuffix() {
		return companySuffix;
	}

	@Override
	public String toString() {
		return "EmployeeDetails [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", department=" + department + ", generatedEmailId=" + generatedEmailId + ", generatedPassword="
				+ generatedPassword + ", alternateEmailId=" + alternateEmailId + "]";
	}

	

}
