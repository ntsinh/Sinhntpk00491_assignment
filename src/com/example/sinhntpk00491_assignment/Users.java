package com.example.sinhntpk00491_assignment;

import android.app.Activity;

public class Users extends Activity{
	private int ID;
	private String Usename;
	private String Password;
	private String Role;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(int iD, String usename, String password, String role) {
		super();
		ID = iD;
		Usename = usename;
		Password = password;
		Role = role;
	}
	public Users(String usename, String password, String role) {
		super();
		Usename = usename;
		Password = password;
		Role = role;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUsename() {
		return Usename;
	}
	public void setUsename(String usename) {
		Usename = usename;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
}
