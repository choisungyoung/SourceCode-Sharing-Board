
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-ua-compatible" content="IE=edge">
<title>dokky-소스코드공유사이트</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="resources/css/WebCss.css">
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<script type="text/javascript">
window.onpageshow = function(event) {

    if ( event.persisted || (window.performance && window.performance.navigation.type == 2)) {
			window.location.reload();
			// Back Forward Cache로 브라우저가 로딩될 경우 혹은 브라우저 뒤로가기 했을 경우
    }

}
function uploadOnclick(){
	if(${sessionScope.token==null}){
		alert("로그인 후 이용가능 합니다.");
	}
	else {
		location.href="code/writeCode";
	}
}
function codeOnclick(cId){
	if(${sessionScope.token==null}){
		alert("로그인 후 이용가능 합니다.");
	}
	else {
		location.href="code/view_code?cId="+cId;
	}
}


function logout(){
	Kakao.init('39484ee026d2cdf8d4e15fd31cb31c5e'); //카카오에서 제공 myceo.co.kr 수정
	Kakao.Auth.logout(
	function(obj) {
		if(obj==true){
			alert("obj==true");
		}else{

			alert("obj==false");
		}

	 }
	);
	//window.location.reload();
	location.href="logout";
	//Kakao.Auth.logout();
	//alert("로그아웃 하였습니다.");
	//window.location.reload();
}
</script>
</head>
<body>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8" id="middle-side">
			
			
			<header class="bg-info"> 
			<jsp:include page="header.jsp"></jsp:include>
			</header>

			<div class="row">
				<div class="col-md-9">
					<section class="span3"> </br>
					<c:forEach items="${list}" var="cdto">
						<div class="panel panel-info stylish-panel " style="cursor: pointer"  onclick="codeOnclick(${cdto.cId})">
							<div class="panel-body gray-hover">
								<label>${cdto.cTitle}  <span class="label label-primary">${cdto.cLang}</span></label>
								<hr>
								<div class="row">
									<div class="col-md-6">작성자 : ${cdto.mdto.mName}</div>
									<div class="col-md-6">게시일 : ${cdto.cDate}</div>
								</div>
								</br>
								<div class="row">
									<div class="col-md-3">댓글 <span class="badge">${cdto.replyCnt}개</span></div>
									<div class="col-md-3">종아요 <span class="badge">${cdto.cGood}개</span></div>
									<div class="col-md-3">싫어요 <span class="badge">${cdto.cHate}개</span></div>
									<div class="col-md-3">조회수 <span class="badge">${cdto.cHit}회</span></div>
								</div>
								<hr>
								${cdto.cContent}

							</div>
						</div>
					</c:forEach> 
					
					<nav>
  						<ul class="pager">
   							<c:choose>
  							<c:when test="${page gt 1}">
  								<li><a href="?page=${page-1 }">Previous</a></li>
    							<li><a href="?page=${page+1 }">Next</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="?page=${page }">Previous</a></li>
    							<li><a href="?page=${page+1 }">Next</a></li>
							</c:otherwise>
							</c:choose>
  						</ul>
					</nav>
					
				</div>
				
				
				
				<div class="col-md-3">
				</br>
					<button class="btn btn-info btn-sm" style="width: 100%;"
						onclick="uploadOnclick()">소스코드 업로드</button>
					<hr>
					<h5 style="text-align: center">
						<strong>Language</strong>
					</h5>
					<ul>
						<c:forEach items="${ldtos}" var="ldto">
						<li id="langHover" style="cursor:pointer">${ldto.lName }</li>
						</c:forEach>
					</ul>
					
					
					</br></br>
					
					
				</div>
			</div>


			</br>
			<footer>
				<div style="text-align: center ; background:#7f7f7f ; color:white">
				</br>
				<h5>개발자 : 최성영</h5>
				<h5>Email : tjddud117@naver.com</h5>
				</br>
				</div>
			</footer>


			<div class="col-md-2"></div>
		</div>
	</div>
</body>
</html>

