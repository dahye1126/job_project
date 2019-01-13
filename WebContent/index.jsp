<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Intermission</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link
	href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template -->

<link href="css/one-page-wonder.min.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/login.js" type="text/javascript"></script>
<script>
	$(document)
			.ready(
					function() {
						if ("${requestScope.ID}") {
							$("#loginForm").modal('show');
							document.getElementById("loginId").value = "${requestScope.ID}";
							document.getElementById("checkPwd").innerHTML = "<font color='red'>로그인정보가 맞지 않습니다.<br>아이디와 비밀번호를 다시 한 번 확인해보십시오</font>";
						}
						if ("${requestScope.LIST}") {
							$("#findCorp").modal('show');
							document.getElementById("corp_list").innerHTML = 
								"<c:set var='corp_list' value='${requestScope.LIST}'/><c:forEach items='${corp_list}' var='corp'><p><a href='corp.do?corp_code=${corp.corp_code}'>${corp.corp_name}</a></p></c:forEach>";
						}
					});
</script>
</head>
<body>

	<!-- Navigation -->
	<nav
		class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top">
		<div class="container">
			<a class="navbar-brand" href="main">Intermission</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="NewFile.html">채용공고</a></li>
					<li class="nav-item"><a class="nav-link" href="corp.do">기업정보</a></li>
					<li class="nav-item"><a class="nav-link"
						href="board?board_code=list&page=1">자유게시판</a></li>
					<li class="nav-item"><a class="nav-link"
						href="csboard?board_code=list&page=1">고객센터</a></li>
						<li class="nav-item"><a class="nav-link"
						href="corpboard?board_code=list&page=1">JOB담</a></li>

					<c:choose>

						<c:when test="${empty sessionScope.MEMBERBEAN}">
							<li class="nav-item"><a class="nav-link"
								href="addmember.jsp">Sign Up </a></li>
							<li class="nav-item"><a class="nav-link" href="#loginForm"
								data-toggle="modal" data-target="#loginForm"
								onclick="javascript:intilogin()">LogIn</a></li>
						</c:when>

						<c:when test="${sessionScope.MEMBERBEAN.grade eq '0'}">
							<li class="nav-item"><a class="nav-link" href="#">관리자모드
							</a></li>
							<li class="nav-item"><a class="nav-link"
								href="member.do?member_code=logout">Logout</a></li>
						</c:when>

						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="evaluation.do">마이페이지
							</a></li>
							<li class="nav-item"><a class="nav-link"
								href="member.do?member_code=logout">LogOut</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>

	<!-- 로그인 -->
	<div class="modal fade" id="loginForm">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">로그인</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<form id="loginfrm" action="member.do" method="post">
					<div class="form-group">
						<div class="modal-body">
							<div class="container">
								<a href="./index.jsp"><img src="img/intermission.png"
									style="display: block; height: 150px; width: 450px" /></a>
							</div>
							<h5>
								<label>아이디</label>
							</h5>
							<input class="form-control" id="loginId" name="loginId"
								type="text" id="id" />
							<div id="checkId"></div>
							<br>
							<h5>
								<label>비밀번호</label>
							</h5>
							<input class="form-control" id="loginPwd" name="loginPwd"
								type="password" id="pwd" />
							<div id="checkPwd"></div>
						</div>
						<div class="modal-footer">
							<a data-toggle="modal" data-target="#MissingForm"><u>혹시
									아이디 또는 비밀번호를 잊어버리셨나요?</u></a> 
							<input type="hidden" name="member_code" value="login" />
							<button class="btn btn-primary" type="button"
								onclick="javascript:loginCheck();">로그인</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- 비밀번호 찾기 -->
	<form action="memberFindPass.do" method="post">
		<div class="modal fade" id="MissingForm">
			<div class="modal-dialog  modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">비밀번호 찾기</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<div class="modal-body">
						현재 아이디를 입력하시면, 가입 정보에 기입된 이메일로 안내 메일을 발송해 드립니다. <input
							class="form-control" type="text" name="memberID"
							placeholder="아이디를 입력해주세요.">
					</div>

					<div class="modal-footer">
						<p style="color: red;" id="passCheckMessage"></p>
						<button class="btn btn-info" type="submit">이메일 전송</button>

					</div>
				</div>
			</div>
		</div>
	</form>

	<!-- Modal -->
	<div class="modal fade" id="findCorp" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">검색 결과</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<p id="corp_list"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<header class="masthead text-center text-white main">
		<div class="masthead-content">
			<div class="container">
				<h1 class="masthead-heading mb-0">Hello Intermission!</h1>
				<h2 class="masthead-subheading mb-0">Will Rock Your Socks Off</h2>
				<form action="corp.do">
					<input type="text" name="corp_name" placeholder="기업명을 검색하세요"
						size='40'>
					<button type="submit"
						class="btn btn-primary btn-xl rounded-pill mt-5 btn-ver">검색하기</button>
				</form>
			</div>
		</div>
	</header>


	<!-- Footer -->
	<footer class="py-5 bg-black">
		<div class="container">
			<p class="m-0 text-center text-white small">Copyright &copy; Your
				Website 2018</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>