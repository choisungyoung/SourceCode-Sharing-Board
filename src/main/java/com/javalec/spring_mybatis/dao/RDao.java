package com.javalec.spring_mybatis.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.mapping.ResultMap;

import com.javalec.spring_mybatis.dto.BDto;
import com.javalec.spring_mybatis.dto.CDto;
import com.javalec.spring_mybatis.dto.ContentDto;
import com.javalec.spring_mybatis.dto.MDto;
import com.javalec.spring_mybatis.dto.RDto;

public interface RDao {

	public void insertRDao(RDto rdto);
	public ArrayList<RDto> listRDao(int cid);
	public void deleteReply(int rid);
	
	
	public void getReplyCnt(int cid);
	
}
