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

public interface PDao {

	public void insertPDao(Map<String, Object> map);		//구매할 코드 id, 구매자 id
	public PDto getPurchase(Map<String, Object> map);
}
