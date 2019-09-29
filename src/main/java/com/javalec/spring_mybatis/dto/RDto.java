package com.javalec.spring_mybatis.dto;

import java.sql.Timestamp;

public class RDto {
	public int rId;
	public int cId;
	public String mId;
	public String mName;
	public int rRcommend;
	public String rContent;
	public Timestamp rDate;
	
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public int getrRcommend() {
		return rRcommend;
	}
	public void setrRcommend(int rRcommend) {
		this.rRcommend = rRcommend;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public Timestamp getrDate() {
		return rDate;
	}
	public void setrDate(Timestamp rDate) {
		this.rDate = rDate;
	}
}
