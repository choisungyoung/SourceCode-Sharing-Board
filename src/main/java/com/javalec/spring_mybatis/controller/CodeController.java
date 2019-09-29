package com.javalec.spring_mybatis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.spring_mybatis.dao.CDao;
import com.javalec.spring_mybatis.dao.LDao;
import com.javalec.spring_mybatis.dao.MDao;
import com.javalec.spring_mybatis.dao.PDao;
import com.javalec.spring_mybatis.dto.CDto;
import com.javalec.spring_mybatis.dto.LDto;
import com.javalec.spring_mybatis.dto.PDto;

@Controller
@RequestMapping("/code/*")
public class CodeController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/view_code")
	public String viewCode(HttpServletRequest request, HttpSession session, Model model) {
		
		int cId = Integer.parseInt(request.getParameter("cId"));
		CDao dao = sqlSession.getMapper(CDao.class);
		dao.codeHitUp(cId);					//조회수 업
		CDto dto = dao.viewCodeCDao(cId);	//코드정보
		
		PDao pdao = sqlSession.getMapper(PDao.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "cid",Integer.parseInt(request.getParameter("cId")));
		map.put( "mid", session.getAttribute("id").toString());
		
		PDto pdto = pdao.getPurchase(map);			//구매했는지 확인
		
		System.out.print("구매여부 : ");
		System.out.println(pdto);
		

		model.addAttribute("cdto", dto);
		model.addAttribute("pdto", pdto);
		return "/view_code";
	}
	
	@RequestMapping("/writeCode")
	public String writeCode(Model model) {
		LDao ldao = sqlSession.getMapper(LDao.class);
		ArrayList<LDto> ldtos = ldao.getLang();				//언어리스트
		
		model.addAttribute("ldtos", ldtos);
		return "/write_code"; 
	}
	
	@RequestMapping("/insertCode")
	public String insertCode(HttpServletRequest request, HttpSession session) {
				//dao.writeDao(request.getParameter("mWriter"), request.getParameter("mContent"));
		System.out.println("insertCode");
		CDto cdto = new CDto();
		cdto.setmId(session.getAttribute("id").toString());
		cdto.setcTitle(request.getParameter("cTitle").toString());
		cdto.setcLang(request.getParameter("cLang").toString());
		cdto.setcContent(request.getParameter("cContent").toString());
		cdto.setcCode(request.getParameter("cCode").toString());
		
		CDao dao = sqlSession.getMapper(CDao.class);
        dao.insertCDao(cdto);
        System.out.println("insertCode");
		return "redirect:/";
	}
	
	@RequestMapping("/goodCode")
	public String goodCode(HttpServletRequest request) {
		CDao dao = sqlSession.getMapper(CDao.class);
		int cid = Integer.parseInt(request.getParameter("cId"));
        dao.goodCodeUp(cid);
		return "/view_code"; 
	}
	
	@RequestMapping("/hateCode")
	public String hateCode(HttpServletRequest request) {
		CDao dao = sqlSession.getMapper(CDao.class);
		int cid = Integer.parseInt(request.getParameter("cId"));
        dao.hateCodeUp(cid);
		return "/view_code"; 
	}
	
	@RequestMapping("/deleteCode")
	public String deleteCode(HttpServletRequest request, Model model) {
		System.out.println("deleteCode");
		CDao dao = sqlSession.getMapper(CDao.class);
		int cid = Integer.parseInt(request.getParameter("cId"));
        dao.deleteCode(cid);
        
        return "redirect:/member/";
	}
	
	@RequestMapping(value = "/purchase", method = RequestMethod.GET)
	public String purchaseCode(HttpServletRequest request, HttpSession session) {
		PDao pdao = sqlSession.getMapper(PDao.class);
		
		Map<String, Object> map = new HashMap<String, Object>();
		int cid = Integer.parseInt(request.getParameter("cId"));
		String mid = session.getAttribute("id").toString();
		map.put( "cid",cid);
		map.put( "mid", mid);
		
		pdao.insertPDao(map);				//코인구매
		
		MDao mdao = sqlSession.getMapper(MDao.class);
		mdao.decreaseCoin(mid);				//구매자 코인 감소

        session.setAttribute("coin", Integer.parseInt(session.getAttribute("coin").toString())-100);
		
		/*
		 * 코인 감소시키기
		 * 
		 * */
		
		return "redirect:/code/view_code?cId="+request.getParameter("cId").toString();
	}
}
