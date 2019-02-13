package com.javalec.spring_mybatis.dao;

import java.util.ArrayList;

import com.javalec.spring_mybatis.dto.BDto;
import com.javalec.spring_mybatis.dto.ContentDto;
import com.javalec.spring_mybatis.dto.MDto;

public interface IDao {
	
	public ArrayList<BDto> listDao();
	public void writeBDao(BDto bdto);
	public BDto contentViewBDao(String strID);
	public void deleteDao(String bId);
	public void insertMDao(MDto mdto);
	
}
