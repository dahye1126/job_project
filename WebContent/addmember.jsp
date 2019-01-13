<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>회원가입</title>

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i"
	rel="stylesheet">
<link href="css/one-page-wonder.min.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet">
<link href="css/join.css" rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/join.js" type="text/javascript" ></script>

<script type="text/javascript">
	function joinCheck() {
		var isjoin = true;
		if (document.getElementById("M_Name").value == "") {
			document.getElementById("notice_msg_name").innerHTML = "<font color='red'>이름을 입력하세요.</font>";
			isjoin = false;
		}
		if (document.getElementById("yy").value == ""
				|| document.getElementById("mm").value == "월"
				|| document.getElementById("dd").value == "") {
			document.getElementById("notice_msg_birth").innerHTML = "<font color='red'>생년월일을 입력하세요.</font>";
			isjoin = false;
		}
		if (document.getElementById("idcheck").value == "") {
			document.getElementById("notice_msg_id").innerHTML = "<font color='red'>아이디를 입력하세요.</font>";
			isjoin = false;
		}
		if (document.getElementById("M_Pwd").value == "") {
			document.getElementById("notice_msg_pwd").innerHTML = "<font color='red'>비밀번호를 입력하세요.</font>";
			isjoin = false;
		}
		if (document.getElementById("M_Email").value == "") {
			document.getElementById("notice_msg_email").innerHTML = "<font color='red'>이메일을 입력하세요.</font>";
			isjoin = false;
		}
		if (isjoin & isphone()) {
			document.getElementById("frm").submit();
		}
	}

	function isphone() {
		var phone = document.getElementById("M_Phone").value;
		var isphone = true;
		var msg = "";
		if (phone == "") {
			msg = "<font color='red'>전화번호를 입력하세요.</font>";
			isphone = false;
		} else if (phone.length != 13) {
			var regExp = /^(01[016789]{1})-?[0-9]{4}-?[0-9]{4}$/;
			if (!regExp.test(phone)) {
				msg = "<font color='red'>올바르지 않은 휴대폰 번호입니다.</font>";
				isphone = false;
			}
		}
		document.getElementById("notice_msg_phone").innerHTML = msg;
		return isphone;
	}
</script>
</head>
<body>
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
					<li class="nav-item"><a class="nav-link" href="main">뒤로가기</a></li>
					<!-- <li class="nav-item"><a class="nav-link" href="#">기업정보</a></li> -->
				</ul>
			</div>
		</div>
	</nav>
	<header class="masthead text-white join">
		<div id="wrap">

			<div id="header">
				<ul class="snb f_clear">
					<li class="person on"><a>회원 가입</a></li>
				</ul>
			</div>
			<div id="container" class="mbrRegist">
				<fieldset>
					<legend>개인회원 가입</legend>
					<form action="member.do" id="frm" method="post" name="frm">
						<div class="row_group mbr_info">
							<h4>회원가입하고 다양한 혜택을 누리세요!</h4>
							<p class="subTx">
								<strong>*</strong> 필수 입력 정보입니다.
							</p>

							<div class="row mbr_name">
								
								<div class="col_2">
									<input type="text" id="M_Name" name="M_Name"
										class="mbr_name devReadOnly dev-name" maxlength="12"
										placeholder="이름(실명) *">
								</div>
							</div>
							<div class="notice_msg" id="notice_msg_name"></div>

							<div class="row mbr_birthday">
								<div class="bir_yy col_1">
									<span class="ps_box"> <input type="text" id="yy"
										name="yy" class="int" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" maxlength="4" placeholder="년(4자)"
										aria-label="년(4자)">
									</span>
								</div>
								<div class="bir_mm col_1">
									<span class="ps_box"> <select id="mm" name="mm"
										class="sel" aria-label="월">
											<option>월</option>
											<option value="01">1</option>
											<option value="02">2</option>
											<option value="03">3</option>
											<option value="04">4</option>
											<option value="05">5</option>
											<option value="06">6</option>
											<option value="07">7</option>
											<option value="08">8</option>
											<option value="09">9</option>
											<option value="10">10</option>
											<option value="11">11</option>
											<option value="12">12</option>
									</select>
									</span>
								</div>
								<div class="bir_dd col_1">
									<span class="ps_box"> <input type="text" id="dd"
										name="dd" class="int" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" maxlength="2" placeholder="일"
										aria-label="일">
									</span>
								</div>
							</div>
							<div class="notice_msg" id="notice_msg_birth"></div>

							<div class="row mbr_id">
								<div class="col_2">
									<input type="text" id="idcheck" name="idcheck" maxlength="16"
										class="dev-id" value="" style="ime-mode: disabled;"
										placeholder="아이디 *">
								</div>
							</div>
							<div class="notice_msg" id="notice_msg_id"></div>

							<div class="row mbr_passwd">
								<div class="col_2">
									<input type="password" id="M_Pwd" name="M_Pwd"
										class="dev-password" maxlength="16"
										style="ime-mode: disabled;"
										placeholder="비밀번호(6~16자의 영문, 숫자, 특수기호) *">
								</div>
							</div>
							<div class="notice_msg" id="notice_msg_pwd"></div>

							<div class="row mbr_email">
								<div class="col_2">
									<input type="text" id="M_Email" name="M_Email"
										class="mbr_email_id dev-mail" size="8" maxlength="30"
										placeholder="이메일 *">
								</div>
							</div>
							<div class="notice_msg" id="notice_msg_email"></div>

							<div class="row mbr_phone">
								<div class="col_2">
									<input type="text" id="M_Phone" name="M_Phone" size="4"
										maxlength="11" onkeyup="this.value=this.value.replace(/[^0-9|-]/g,'')" class="dev-phone" placeholder="휴대폰번호 *">
								</div>
							</div>
							<div class="notice_msg" id="notice_msg_phone"></div>

						</div>
						<!-- 회원 가입 버튼 -->
						<div>
							<div>
								<input type="hidden" name="member_code" value="join" />
								<button type="button" class="mbrBtnRegist"
									onclick="javscript:joinCheck();">
									<span>가입하기</span>
								</button>
							</div>
						</div>
					</form>
				</fieldset>
			</div>

		</div>
	</header>
	<footer class="py-5 bg-black">
		<div class="container">
			<p class="m-0 text-center text-white small">Copyright &copy; Your
				Website 2018</p>
		</div>
	</footer>
</body>
</html>
