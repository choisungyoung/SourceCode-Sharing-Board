<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-ua-compatible" content="IE=edge">
<title>dokky-소스코드공유사이트</title>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<link rel="stylesheet" href="../resources/css/bootstrap.css">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/bootstrap-theme.css">
<link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="../resources/css/WebCss.css">
</head>
<body>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8" id="middle-side">
			<header class="bg-info"> <jsp:include page="header.jsp"></jsp:include>
			</header>
			<div>
				<form action="insertCode" method="post">
					</br>
					<h2>소스코드 업로드</h2>
					</br>
					<div class="col-sm-1">
						<label for="exampleInputName2">제목</label>
					</div>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="exampleInputName2"
							name="cTitle" placeholder="제목을 입력하세요">
					</div>
					<div class="col-sm-1">
						<label for="exampleInputName2">언어</label>
					</div>
					<div class="col-sm-3">
						<select class="form-control" name="cLang">

							<c:forEach items="${ldtos}" var="ldto">
								<option>${ldto.lName }</option>
							</c:forEach>
						</select>
					</div>


					</br> </br> <label for="exampleInputName2">설명</label>
					<textarea class="form-control" name="cContent" rows="3"></textarea>
					</br> <label for="exampleInputName2">코드</label>
					<textarea class="form-control" name="cCode" rows="10"></textarea>
					</br>
					<button class="btn btn-info btn-sm" style="width: 18%;"
						type="submit">업로드</button>
				</form>
				</br> </br>
				<footer>
				<div style="text-align: center; background: #7f7f7f; color: white">
					</br>
					<h5>개발자 : 최성영</h5>
					<h5>Email : tjddud117@naver.com</h5>
					</br>
				</div>
				</footer>
			</div>


			<div class="col-md-2"></div>
		</div>
	</div>
</body>
</html>

