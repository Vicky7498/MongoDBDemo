package com.infy.collection;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.infy.dto.CustomerType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Document
public class Customer {
	@Transient
	public static final String SEQUENCE_NAME = "customers_sequence";
	@Id
	private Long id;
	@Field("name")
	private String name;
	private String email;
	private LocalDate dob;
	@Enumerated(value = EnumType.STRING)
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String emailId) {
		this.email = emailId;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
}
