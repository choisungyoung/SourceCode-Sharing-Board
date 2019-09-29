package com.javalec.spring_mybatis.dto;

import java.sql.Timestamp;

public class CDto {
	int cId;
	String mId;
	String cTitle;
	String cCode;
	String cContent;
	Timestamp cDate;
	String cLang;
	int cGood;
	int cHate;
	int cCoin;
	int cHit;
	int replyCnt;
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	MDto mdto;
	public MDto getMdto() {
		return mdto;
	}
	public void setMdto(MDto mdto) {
		this.mdto = mdto;
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
	public String getcTitle() {
		return cTitle;
	}
	public void setcTitle(String cTitle) {
		this.cTitle = cTitle;
	}
	public String getcCode() {
		return cCode;
	}
	public void setcCode(String cCode) {
		this.cCode = cCode;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public Timestamp getcDate() {
		return cDate;
	}
	public void setcDate(Timestamp cDate) {
		this.cDate = cDate;
	}
	public String getcLang() {
		return cLang;
	}
	public void setcLang(String cLang) {
		this.cLang = cLang;
	}
	public int getcGood() {
		return cGood;
	}
	public void setcGood(int cGood) {
		this.cGood = cGood;
	}
	public int getcHate() {
		return cHate;
	}
	public void setcHate(int cHate) {
		this.cHate = cHate;
	}
	public int getcCoin() {
		return cCoin;
	}
	public void setcCoin(int cCoin) {
		this.cCoin = cCoin;
	}
	public int getcHit() {
		return cHit;
	}
	public void setcHit(int cHit) {
		this.cHit = cHit;
	}
}
