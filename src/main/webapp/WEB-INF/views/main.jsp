<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-ua-compatible" content="IE=edge">
<title>Insert title here</title>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<link rel="stylesheet" href="resources/css/bootstrap.css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="resources/css/WebCss.css">
</head>
<body>
<div class="row">
<div class="col-md-2"></div>
<div class="col-md-8" >

<header class="bg-info">
	
	<c:choose>
			<c:when test="${sessionScope.token eq null}">
    			<div style="float: right;">
    				<button type="button" class="btn btn-warning btn-sm" onclick="location.href='https://kauth.kakao.com/oauth/authorize?client_id=cc7c1662ee40630ff7a3d8664d994f1d&redirect_uri=http://localhost:8081/spring_mybatis3/oauth&response_type=code'">
        				카카오 로그인</button>
    			</div>
			</c:when>
			<c:otherwise>
			<form class="form-inline" style="text-align: center;">
				<div style="text-align: right ;">
					<div style="float: right;">
						<button type="button" class="btn btn-warning btn-sm" onclick="location.href='logout'">
        				로그아웃</button>
					</div>
					<div style="float: right;">
						<p style="margin: 0px">${sessionScope.name} </p>
					</div>
					<div style="float: right;">
						<img src="${sessionScope.profile} }" />
					</div>
				</div>
        	</form>
			</c:otherwise>

		</c:choose>
	

	
	
	<div style="text-align: right;">
       
    </div>
	
	<div class="row">
    	<div class="col-md-3"></div>
    	<div class="col-md-6">
    	<div class="page-header">
  			<h1 class = "menu_top" align="center">dokky</h1>
		</div>	
    	
    	
    	<form class="form-inline" style="text-align: right;">
    			<input type="search" class="form-control" placeholder="검색어를 입력하세요" style="width: 83%;" >
 				<button class="btn btn-info btn-sm" style="width: 15%;" >검색</button>
		</form>
    	</div>
   		<div class="col-md-3"></div>
    </div> 
    	
	</br></br>
	
     <nav >
       
      </nav>
    
</header>

	<div class="row">
   		<div class="col-md-9">
   			 <section class="span3">
    	<table class="table table-hover">
			<tr>
				<td>번호</td>
				<td>이름</td>
				<td>제목</td>
				<td>날짜</td>
				<td>히트</td>
			</tr>
			<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.bId}</td>
				<td>${dto.bName}</td>
				<td>
				<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
				<a href="content_view?bId=${dto.bId}">${dto.bTitle}</a></td>
				<td>${dto.bDate}</td>
				<td>${dto.bHit}</td>
			</tr>
			</c:forEach>
			</table>
			<p><a href="writeForm">글작성</a></p>
        	<article>article</article>
    	</section>
   		</div>
    	<div class="col-md-3">
    		<p>${sessionScope.id}</p>
    		<p>${sessionScope.token}</p>
    		
    		<p>${sessionScope.id eq null}</p>
    		<p>${sessionScope.token eq null}</p>
    		<p>${sessionScope.token eq null}</p>
    	</div>
    </div> 


   
<aside class="sidebar col-md-3">aside</aside>
<footer>footer</footer>


<div class="col-md-2">
</div></div></div>
</body>
</html>

