package com.epam.eurekadiscoveryclient.model;

public class User {

	private long userId;
	
	private String userName;
	
	private long userPhone;
	
	private String userAddress;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(long userId, String userName, long userPhone, String userAddress) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userAddress = userAddress;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPhone=" + userPhone + ", userAddress="
				+ userAddress + "]";
	}
	
}
