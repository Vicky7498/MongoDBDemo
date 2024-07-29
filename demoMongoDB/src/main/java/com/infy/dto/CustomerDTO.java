package com.infy.dto;

import java.time.LocalDate;

public class CustomerDTO {
	private Long id;
	private String name;
	private String email;
	private LocalDate dob;
	private CustomerType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @deprecated Use {@link #getEmail()} instead
	 */
	public String getEmailId() {
		return getEmail();
	}

	public String getEmail() {
		return email;
	}

	/**
	 * @deprecated Use {@link #setEmail(String)} instead
	 */
	public void setEmailId(String emailId) {
		setEmail(emailId);
	}

	public void setEmail(String emailId) {
		this.email = emailId;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", type=" + type
				+ "]";
	}
}
