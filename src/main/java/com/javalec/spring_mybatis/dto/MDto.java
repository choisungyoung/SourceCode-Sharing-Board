package com.javalec.spring_mybatis.dto;

import java.sql.Date;

public class MDto {
	private String mId;
	private String mEmail;
	private Date mDate;
	private int mCoin;
	
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public Date getmDate() {
		return mDate;
	}
	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}
	public int getmCoin() {
		return mCoin;
	}
	public void setmCoin(int mCoin) {
		this.mCoin = mCoin;
	}
	
	
}
