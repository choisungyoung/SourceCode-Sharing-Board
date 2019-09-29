package com.javalec.spring_mybatis.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javalec.spring_mybatis.dao.CDao;
import com.javalec.spring_mybatis.dao.RDao;
import com.javalec.spring_mybatis.dto.CDto;
import com.javalec.spring_mybatis.dto.RDto;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/insertReply")
	public String insertReply(HttpServletRequest request, HttpSession session) {
				//dao.writeDao(request.getParameter("mWriter"), request.getParameter("mContent"));
		System.out.println("insertReply");
		RDto rdto = new RDto();

		System.out.println();
		rdto.setmId(session.getAttribute("id").toString());
		rdto.setcId(Integer.parseInt(request.getParameter("cId")));
		rdto.setrContent(request.getParameter("rContent").toString());
		
		RDao dao = sqlSession.getMapper(RDao.class);
        dao.insertRDao(rdto);
		return "redirect:/code/view_code?cId="+request.getParameter("cId").toString();
	}
	
	@RequestMapping(value = "/listReply", method = RequestMethod.GET,  produces = "application/json")
	@ResponseBody
	public ArrayList<RDto> listReply(HttpServletRequest request, HttpSession session,  Model model) {
				//dao.writeDao(request.getParameter("mWriter"), request.getParameter("mContent"));
		System.out.println("listReply");
		
		
		int cid = Integer.parseInt(request.getParameter("cId"));
		RDao dao = sqlSession.getMapper(RDao.class);
		ArrayList<RDto> list = dao.listRDao(cid);

		//mav.setViewName("view_code?cId="+request.getParameter("cId").toString());
		
		
		
		for(int i = 0 ; i < list.size() ; i++) {
			System.out.println(list.get(i).getmName());
			System.out.println(list.get(i).getrContent());
		}
		

		model.addAttribute("list", list);
		
		//return "redirect:/code/view_code?cId="+request.getParameter("cId").toString();
		return list;
	}
	
	@RequestMapping("/deleteReply")
	public String deleteCode(HttpServletRequest request, Model model) {
		System.out.println("deleteReply");
		RDao dao = sqlSession.getMapper(RDao.class);
		int rid = Integer.parseInt(request.getParameter("rId"));
        dao.deleteReply(rid);
        
        return "redirect:/member/";
	}
}
