package com.epam.model;

import org.springframework.stereotype.Component;

@Component
public class Admin extends User {

	public Admin(String userName, String password, String roleType) {
		super(userName, password, roleType);
	}
public  Admin(){

}
}
