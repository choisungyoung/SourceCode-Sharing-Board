package com.javalec.spring_mybatis.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.javalec.spring_mybatis.controller.KakaoRestApi;
import com.javalec.spring_mybatis.dao.CDao;
import com.javalec.spring_mybatis.dao.LDao;
import com.javalec.spring_mybatis.dao.MDao;
import com.javalec.spring_mybatis.dto.BDto;
import com.javalec.spring_mybatis.dto.CDto;
import com.javalec.spring_mybatis.dto.LDto;
import com.javalec.spring_mybatis.dto.MDto;
import com.javalec.spring_mybatis.dto.RDto;

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
	@RequestMapping(value = "/123", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/")
	public String main(HttpServletRequest request, Model model) {
		System.out.println("###########################################################################");
		//페이징
		int page;
		if(request.getParameter("page") == null) {
			page = 1;
		}
		else
			page = Integer.parseInt(request.getParameter("page"));

		System.out.println(page);
		System.out.println((page-1)*5 );
		System.out.println((page-1)*5 +5);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put( "startCnt",(page-1)*5  );
		map.put( "endCnt",  (page-1)*5+5 );

		CDao dao = sqlSession.getMapper(CDao.class);
		ArrayList<CDto> cdtos = dao.codeList(map);	//코드랑 회원데이터 가져옴
		LDao ldao = sqlSession.getMapper(LDao.class);
		ArrayList<LDto> ldtos = ldao.getLang();
		//cdtos.get(0).getMdto().getmId();
		for(int i = 0 ; i < cdtos.size(); i++) {

			System.out.println(cdtos.get(i).getcId());
			System.out.println(cdtos.get(i).getReplyCnt());
		}
		
		model.addAttribute("list", cdtos);
		model.addAttribute("page", page);
		model.addAttribute("ldtos", ldtos);
		
		//int page = (Integer)request.getAttribute("page");
		System.out.println(request.getParameter("page"));
		return "/main";
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

        System.out.println("userInfo");
        System.out.println(userInfo);
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
        profile = properties.path("thumbnail_image").asText();

        System.out.println(properties);
        MDto mdto = new MDto();
        mdto.setmId(id);
        mdto.setmEmail(email);
        mdto.setmCoin(500);
        mdto.setmName(name);
        mdto.setmProfile(profile);
        
        
        MDao dao = sqlSession.getMapper(MDao.class);
        dao.insertMDao(mdto);
        MDto mdto1 = dao.memberInfo(id);
        
        
        session.setAttribute("token", token);
        session.setAttribute("id", id);
        session.setAttribute("name", name);
        session.setAttribute("email", email);
        session.setAttribute("profile", profile);
        session.setAttribute("coin", mdto1.getmCoin());
        
        return "redirect:/";
    }
	
	@RequestMapping(value = "/logout", produces = "application/json")
    public String Logout(HttpSession session) {
        //kakao restapi 객체 선언
		KakaoRestApi kr = new KakaoRestApi();
        //노드에 로그아웃한 결과값음 담아줌 매개변수는 세션에 잇는 token을 가져와 문자열로 변환
        JsonNode node = kr.Logout(session.getAttribute("id").toString());
        //결과 값 출력3
        session.invalidate();
        return "redirect:/";
    }  
}
