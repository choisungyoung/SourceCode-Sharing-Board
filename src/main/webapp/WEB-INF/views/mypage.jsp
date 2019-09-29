<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta http-equiv="X-ua-compatible" content="IE=edge">
<title>dokky-소스코드공유사이트</title>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<link rel="stylesheet" href="../resources/css/bootstrap.css">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/bootstrap-theme.css">
<link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="../resources/css/WebCss.css">
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function deleteCodeOnclick(cId){
	if (confirm("정말 삭제하시겠습니까??") == true){    //확인
		var stdCid = String(cId);
		$.ajax({
			type : "get",
			url : "${pageContext.request.contextPath}/code/deleteCode?cId="+stdCid,
			success : function() {
				alert("삭제되었습니다.");
				window.location.reload();
			}
		});
		
	}else{   //취소
	    return;
	}
}

function deleteReplyOnclick(rId){
	if (confirm("정말 삭제하시겠습니까??") == true){    //확인
		var stdRid = String(rId);
		$.ajax({
			type : "get",
			url : "${pageContext.request.contextPath}/reply/deleteReply?rId="+stdRid,
			success : function() {
				alert("삭제되었습니다.");
				window.location.reload();
			}
		});
		
	}else{   //취소
	    return;
	}
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
			<!-- ---------------------header---------------------------- -->


			<div class="page-header">
				<h3>마이페이지</h3>
			</div>
			<h4>
				<strong>나의 정보</strong>
			</h4>
			<h5>아이디 : ${mdto.mId}</h5>
			<h5>이메일 : ${mdto.mEmail}</h5>
			<h5>닉네임 : ${mdto.mName}</h5>
			<h5>가입일 : ${mdto.mDate}</h5>
			<h5>보유코인 : ${mdto.mCoin}</h5>
			</br>


			<h4>
				<strong>내가 업로드한 코드</strong>
			</h4>

			<!-- 나의 코드 리스트 -->
			<table class="table">
				<thead>
					<tr>
						<th>코드id</th>
						<th>제목</th>
						<th>작성일시</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${cdtos}" var="cdto">
					<tr>
						<td>${cdto.cId }</td>
						<td id="titleHover" style="cursor: pointer; width:60%"  onclick="location.href='../code/view_code?cId=${cdto.cId}'">${cdto.cTitle}</td>
						<td>${cdto.cDate}</td>
						<td><p id="deleteBtn" style="cursor: pointer" onclick="deleteCodeOnclick(${cdto.cId})">삭제</p></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

			<h4>
				<strong>내가 작성한 댓글</strong>
			</h4>

			<!-- 나의 댓글 리스트 -->
			
			<table class="table" >
				<thead>
					<tr>
						<th>댓글id</th>
						<th>코드id</th>
						<th>댓글내용</th>
						<th>작성일시</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${rdtos}" var="rdto">
					<tr >
						<td>${rdto.rId }</td>
						<td>${rdto.cId }</td>
						<td id="titleHover" style="cursor: pointer;width:55%" onclick="location.href='../code/view_code?cId=${rdto.cId}'">${rdto.rContent }</td>
						<td>${rdto.rDate }</td>
						<td><p id="deleteBtn" style="cursor:pointer" onclick="deleteReplyOnclick(${rdto.rId })">삭제</p></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			
			<h4>
				<strong>내가 구매한 코드</strong>
			</h4>
			<table class="table" >
				<thead>
					<tr>
						<th>구매번호</th>
						<th>코드번호</th>
						<th>코드제목</th>
						<th>구매일시</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pdtos}" var="pdto">
					<tr >
						<td>${pdto.pId }</td>
						<td>${pdto.cId }</td>
						<td id="titleHover" style="cursor: pointer;width:55%" onclick="location.href='../code/view_code?cId=${pdto.cId}'">${pdto.cTitle }</td>
						<td>${pdto.pDate }</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

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
</body>
</html>