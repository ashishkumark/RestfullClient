package com.ashish.webservices;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
@XmlAccessorType (XmlAccessType.FIELD)
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "user")
	private List<User> users;

	public Users() {
	}

	public Users(List<User> userList) {
		this.setUsers(userList);
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}