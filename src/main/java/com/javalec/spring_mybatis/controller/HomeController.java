package com.javalec.spring_mybatis.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.javalec.spring_mybatis.controller.KakaoRestApi;
import com.javalec.spring_mybatis.dao.ContentDao;
import com.javalec.spring_mybatis.dao.IDao;
import com.javalec.spring_mybatis.dto.BDto;
import com.javalec.spring_mybatis.dto.MDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
/*	ContentDao dao;*/
	
	@Autowired
	private SqlSession sqlSession;
	
/*	@Autowired
	public void setDao(ContentDao dao) {
		this.dao = dao;
	}*/
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/main")
	public String main(Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		ArrayList<BDto> dtos = dao.listDao();
		System.out.println(dtos.get(0).getbTitle());
		model.addAttribute("list", dao.listDao());
		return "/main";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
//		ArrayList<ContentDto> dtos = dao.listDao();
		IDao dao = sqlSession.getMapper(IDao.class);
//		ArrayList<ContentDto> dtos = dao.listDao();
		model.addAttribute("list", dao.listDao());
		
		return "/list";
	}
	
	@RequestMapping(value = "/oauth", produces = "application/json", method = { RequestMethod.GET, RequestMethod.POST })
    public String kakaoLogin(@RequestParam("code") String code, Model model, HttpSession session) {
        System.out.println("로그인 할때 임시 코드값");
        //카카오 홈페이지에서 받은 결과 코드
        System.out.println(code);
        System.out.println("로그인 후 결과값");
        
        //카카오 rest api 객체 선언
        KakaoRestApi kr = new KakaoRestApi();
        //결과값을 node에 담아줌
        JsonNode node = kr.getAccessToken(code);
        //결과값 출력
        System.out.println(node);
        //노드 안에 있는 access_token값을 꺼내 문자열로 변환
        JsonNode token = node.get("access_token");
        JsonNode userInfo = kr.getKakaoUserInfo(token);
        
        JsonNode properties = userInfo.path("properties");
        
        JsonNode kakao_account = userInfo.path("kakao_account");
        String id = userInfo.path("id").asText();
        System.out.println(token);
        
        
        //세션에 담아준다.

        
        
        // Get id
        String name = null;
        String email = null;
        String profile = null;
 
        // 유저정보 카카오에서 가져오기 Get properties
 
        name = properties.path("nickname").asText();
        email = kakao_account.path("email").asText();
        profile = properties.path("profile_image").asText();
        
        System.out.println("id : " + id);
        System.out.println("name : " + name);
        System.out.println("email : " + email);
        System.out.println("profile : " + profile);
        
        MDto mdto = new MDto();
        mdto.setmId(id);
        mdto.setmEmail(email);
        mdto.setmCoin(500);
        
        
        IDao dao = sqlSession.getMapper(IDao.class);
        dao.insertMDao(mdto);

        session.setAttribute("token", token);
        session.setAttribute("id", id);
        session.setAttribute("name", name);
        session.setAttribute("email", email);
        session.setAttribute("profile", profile);
        
        return "redirect:main";
    }
	
	@RequestMapping(value = "/logout", produces = "application/json")
    public String Logout(HttpSession session) {
        //kakao restapi 객체 선언
		KakaoRestApi kr = new KakaoRestApi();
        //노드에 로그아웃한 결과값음 담아줌 매개변수는 세션에 잇는 token을 가져와 문자열로 변환
        JsonNode node = kr.Logout(session.getAttribute("id").toString());
        //결과 값 출력
        System.out.println("로그인 후 반환되는 아이디 : " + session.getAttribute("id").toString());
        System.out.println("로그인 후 반환되는 token : " + session.getAttribute("token").toString());
        session.invalidate();
        return "redirect:/main";
    }  
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		
		return "/writeForm";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
				//dao.writeDao(request.getParameter("mWriter"), request.getParameter("mContent"));
		
		BDto bdto = new BDto();
		
		bdto.setbName(request.getParameter("bName").toString());
		bdto.setbTitle(request.getParameter("bTitle").toString());
		bdto.setbContent(request.getParameter("bContent").toString());
		
		IDao dao = sqlSession.getMapper(IDao.class);
        dao.writeBDao(bdto);
		return "redirect:main";
	}
	
	@RequestMapping("/content_view")
	public String view(HttpServletRequest request, Model model) {
		
		String bId = request.getParameter("bId");
		
		
		IDao dao = sqlSession.getMapper(IDao.class);
		BDto dto = dao.contentViewBDao(bId);
		
		model.addAttribute("content_view", dto);
		return "/content_view";
	}
	
	@RequestMapping("/view")
	public String view() {
		
		return "/view";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.deleteDao(request.getParameter("mId"));
		return "redirect:list";
	}
	
}
