package com.alansumiya.workshopmongo.dto;

import java.io.Serializable;

import com.alansumiya.workshopmongo.domain.User;

public class UserDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String id;
	
	public UserDTO() {
		
	}
	
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
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

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
