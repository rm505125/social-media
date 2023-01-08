package com.code.socialmedia.user;

import java.time.LocalDate;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class CustomUser {
	
	public CustomUser(Integer id, String name, LocalDate birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}
	public CustomUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Integer id;
	
	@Size(min=2, message="name should be greater than length two")
	private String name;
	
	@Past(message="Birth Date should be in the past")
	private LocalDate birthdate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	@Override
	public String toString() {
		return "CustomUser [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}
	
	
}
