<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<link rel="stylesheet" href="../resources/highlight/androidstudio.css">
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/highlight.js/8.7/styles/monokai_sublime.min.css">
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="//cdn.jsdelivr.net/highlight.js/8.7/highlight.min.js"></script>
<script>
	hljs.initHighlightingOnLoad();
</script>
<script type="text/javascript">


	$(document).ready(function() {
		if(${pdto == null && sessionScope.id != cdto.mId}){
			var purchaseBox = document.getElementById('purchaseBox');
			var codeDiv = document.getElementById('codeDiv');
			var code = document.getElementById('code');
			var val = $('#codeDiv').css('height').replace(/[^-\d\.]/g, '');
			if(val < 200){
				code.style.height = "200px";
				val = 200;
			}
			val = val*1/5
			purchaseBox.style.top = (val)+"px";
			val = val*3;
			purchaseBox.style.height = (val)+"px";
			
		}
		
		listReply();
	})
	
	
	function insertReply() {
		var rContent = $("#rContent").val();
		var cId = "${cdto.cId}";
		var param = "rContent=" + rContent + "&cId=" + cId;
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/reply/insertReply",
			data : param,
			success : function() {
				listReply();
			}
		});
	}

	function replyOnclick(){
		if(${sessionScope.token==null}){
			alert("로그인 후 등록가능 합니다.");
		}
		else if(${pdto == null && sessionScope.id != cdto.mId}){
			alert("구매 후 댓글 작성이 가능합니다.");
		}
		else {
			insertReply();
		}
		 $("#rContent").val('');
	}
	
	function listReply() {
		$.ajax({
			type : "get",
			contentType:"application/json",
			url : "${pageContext.request.contextPath}/reply/listReply?cId=${cdto.cId}",
			success : function(result) {
				var output = "";
				for(var i in result){
				output += "<h5><strong>"+result[i].mName+"</strong></h5>";
				output += "<h5>"+result[i].rContent+"</h5>";
				output += "<h5>"+changeDate(result[i].rDate)+"</h5>";
				output += "<hr>";
				}
				
				$("#listReply").html(output);
			}

		});
	}
	
	function changeDate(date){
		date = new Date(parseInt(date));
		year = date.getFullYear();
		month = date.getMonth();
		day = date.getDate();
		hour = date.getHours();
		minute = date.getMinutes();
		second = date.getSeconds();
		stdDate = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
		return stdDate;
	}
	
	function goodOnclick(){
		if(${sessionScope.id == cdto.mId}){
			alert("작성자는 평가할 수 없습니다.");
		}
		else if(${pdto == null}){
			alert("구매후 평가해주세요.");
		}
		else{
			$.ajax({
				type : "get",
				url : "${pageContext.request.contextPath}/code/goodCode?cId=${cdto.cId}",
				success : function() {
					var good = $("#goodBtn").text();
					good *= 1;
					$("#goodBtn").text(good+1);
				}
			});
		}
	}
	
	function hateOnclick(){
		
		if(${sessionScope.id == cdto.mId}){
			alert("작성자는 평가할 수 없습니다.");
		}
		else if(${pdto == null}){
			alert("구매후 평가해주세요.");
		}
		else{
			$.ajax({
				type : "get",
				url : "${pageContext.request.contextPath}/code/hateCode?cId=${cdto.cId}",
				success : function() {
					var hate = $("#hateBtn").text();
					hate *= 1;
					$("#hateBtn").text(hate+1);
				}
			});
		}
	}
	function purchaseOnclick(cid){
		if(${sessionScope.coin} < 100){
			alert("코인이 부족합니다.");
		}
		else{
			location.href="purchase?cId="+cid;
		}
	}
	
</script>
</head>
<body>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8" id="middle-side">

			<header class="bg-info"> 
			<!-- ---------------------header---------------------------- --> 
			<jsp:include page="header.jsp"></jsp:include>
			</header>

			<div class="page-header">
				<h3>${ cdto.cTitle }
					<small>${ cdto.cLang }</small>
				</h3>
			</div>
			
			<h4>
				<strong>설명</strong>
			</h4>
			<div class="panel panel-default">
				<div class="panel-body" style="height: 100px">${ cdto.cContent }
				</div>
			</div>

			<h4>
				<strong>코드</strong>
			</h4>


			<c:choose>
				<c:when test="${pdto eq null && cdto.mId ne sessionScope.id}">
					<div id="codeDiv" style="width: 100%; position: relative">
						<pre id="codeBlur"><code id="code" class="${ cdto.cLang }">${ cdto.cCode }</code></pre>

						<div class="panel panel-info stylish-panel " id="purchaseBox">
							<div id="purchaseCell">
								<h5>구매 후 열람가능합니다. (100coin)</h5>
								<button class="btn btn-info btn-sm" style="width: 15%;"
									onclick="purchaseOnclick(${cdto.cId})">구매하기</button>
							</div>
						</div>

					</div>
				</c:when>
				<c:otherwise>
					<pre><code class="${ cdto.cLang }">${ cdto.cCode }</code></pre>
					
				</c:otherwise>
			</c:choose>
			
			
			<!-- 코드 숨기기 -->
			
			
			
			<div id="relative"style="width:100%; text-align: center;">
			<span><strong id="goodBtn">${ cdto.cGood }</strong></span>
			<img src="../resources/img/좋아요1.jpg"  onclick="goodOnclick()"
										 class="img-circle" width="70px" height="70px" style="cursor: pointer"/>
										 
			<img src="../resources/img/싫어요1.jpg" onclick="hateOnclick()"
										 class="img-circle" width="70px" height="70px" style="cursor: pointer"/>
			<span><strong id="hateBtn">${ cdto.cHate }</strong></span>
			</div>
			
			<!-- 
				좋아요 싫어요 버튼
			
			 -->

			<hr> 
				<h4>
					<strong>댓글</strong>
				</h4>
			 <hr>
			 <div id="listReply">
			 
			 
			 </div>
			 
			<!-- 
				댓글 표시
			
			 -->
			 
			 
			<!-- <form action="insertReply" method="post"> -->
				<h4>
					<strong>댓글 작성</strong>
				</h4>
				<textarea class="form-control" name="rContent" id="rContent" rows="3" placeholder="댓글을 입력하세요"></textarea>
				
				<p align="right">
				<button class="btn btn-info btn-sm" id="replyBtn" style="width: 18%;" onclick="replyOnclick()">댓글 작성</button>
				</p>
				
			<!--</form>  -->
			
			</br> </br>
			
			<footer>
				<div style="text-align: center ; background:#7f7f7f ; color:white">
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

