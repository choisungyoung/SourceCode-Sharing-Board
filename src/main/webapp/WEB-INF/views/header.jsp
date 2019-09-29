<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="width: 100%; height: 40px;">
		<c:choose>
			<c:when test="${sessionScope.token eq null}">
				<div style="float: right;">
					<button type="button" class="btn btn-warning btn-sm"
						onclick="location.href='https://kauth.kakao.com/oauth/authorize?client_id=cc7c1662ee40630ff7a3d8664d994f1d&redirect_uri=http://localhost:8081/spring_mybatis3/oauth&response_type=code'">
						카카오 로그인</button>
				</div>
			</c:when>
			<c:otherwise>
				<form class="form-inline" style="text-align: center;">
					<div style="text-align: right;">
						<div style="float: right;">
							<button type="button" class="btn btn-warning btn-sm"
								onclick="logout()">로그아웃</button>
						</div>
						<div style="float: right;">
							<h5>
								<strong> &nbsp;${sessionScope.name}&nbsp; &nbsp;</strong>
							</h5>
						</div>
						<div style="float: right;">
							<c:choose>
								<c:when test="${sessionScope.profile eq '' }">
									<img
										src="https://naqyr37xcg93tizq734pqsx1-wpengine.netdna-ssl.com/wp-content/plugins/all-in-one-seo-pack/images/default-user-image.png"
										class="img-circle" width="35px" height="35px" />
								</c:when>
								<c:otherwise>
								
									<img src="${sessionScope.profile}" class="img-circle"
										width="35px" height="35px" />
								</c:otherwise>
							</c:choose>
						</div>
						<div style="float: right;">
							<h5>&nbsp;&nbsp;|&nbsp;&nbsp;보유코인 : ${sessionScope.coin}&nbsp; &nbsp;</h5>
						</div>
						<div style="float: right; cursor: pointer"
							onclick="location.href='member/'">
							<h5 id="mypage">마이페이지</h5>
						</div>
					</div>
				</form>
			</c:otherwise>

		</c:choose>

	</div>


	<div style="text-align: right;"></div>

			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
						<h1 class="menu_top" align="center">Dokky</h1>
					</br>
					<form class="form-inline" style="text-align: right;">
						<input type="search" class="form-control" placeholder="검색어를 입력하세요"
							style="width: 83%;">
						<button class="btn btn-info btn-sm" style="width: 15%;">검색</button>
					</form>
				</div>
				<div class="col-md-3"></div>
			</div>

			</br>
			</br>
</body>
</html>