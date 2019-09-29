package com.javalec.spring_mybatis.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.mapping.ResultMap;

import com.javalec.spring_mybatis.dto.BDto;
import com.javalec.spring_mybatis.dto.CDto;
import com.javalec.spring_mybatis.dto.ContentDto;
import com.javalec.spring_mybatis.dto.MDto;
import com.javalec.spring_mybatis.dto.RDto;

public interface CDao {

	public void insertCDao(CDto cdto);
	public ArrayList<CDto> codeList(Map<String, Integer> map);
	public CDto viewCodeCDao(int cid);
	public void deleteCode(int cid);
	
	public void codeHitUp(int cid);
	public void goodCodeUp(int cid);
	public void hateCodeUp(int cid);

	
	
	public void getReplyCnt(int cid);
}
