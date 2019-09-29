package com.javalec.spring_mybatis.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javalec.spring_mybatis.dao.MDao;
import com.javalec.spring_mybatis.dto.CDto;
import com.javalec.spring_mybatis.dto.MDto;
import com.javalec.spring_mybatis.dto.PDto;
import com.javalec.spring_mybatis.dto.RDto;

@Controller
@RequestMapping("/member/*")

public class MemberController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/")
	public String memberInfo(HttpSession session, Model model) {
		
		String mid = session.getAttribute("id").toString();
		MDao mdao = sqlSession.getMapper(MDao.class);
		
		MDto mdto = mdao.memberInfo(mid);
		ArrayList<CDto> cdtos = mdao.memberCodeList(mid);
		ArrayList<RDto> rdtos = mdao.memberReplyList(mid);
		ArrayList<PDto> pdtos = mdao.memberPurchaseList(mid);

		model.addAttribute("mdto", mdto);
		model.addAttribute("cdtos", cdtos);
		model.addAttribute("rdtos", rdtos);
		model.addAttribute("pdtos", pdtos);
		
		return "/mypage";
	}
}