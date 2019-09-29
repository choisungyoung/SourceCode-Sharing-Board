package com.javalec.spring_mybatis.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.mapping.ResultMap;

import com.javalec.spring_mybatis.dto.BDto;
import com.javalec.spring_mybatis.dto.CDto;
import com.javalec.spring_mybatis.dto.ContentDto;
import com.javalec.spring_mybatis.dto.MDto;
import com.javalec.spring_mybatis.dto.PDto;
import com.javalec.spring_mybatis.dto.RDto;

public interface MDao {

	public void insertMDao(MDto mdto);
	public MDto memberInfo(String mid);
	public ArrayList<CDto> memberCodeList(String mid);
	public ArrayList<RDto> memberReplyList(String mid);
	public ArrayList<PDto> memberPurchaseList(String mid);
	public void decreaseCoin(String mid);
}
