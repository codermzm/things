package com.things.web.entity;

import org.apache.commons.lang3.StringUtils;

import com.things.frame.core.base.entity.Identifiable;

public class UserEntity implements Identifiable {

	private static final long serialVersionUID = -704481332206652907L;

	private String id = StringUtils.EMPTY;
	
	private String userName = StringUtils.EMPTY;
	
	private String passWord = StringUtils.EMPTY;
	
	private String birthday  = StringUtils.EMPTY;
	
	private String sex       = StringUtils.EMPTY;
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
