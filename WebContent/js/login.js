function loginCheck() {
	var id = document.getElementById("loginId").value;
	var pwd = document.getElementById("loginPwd").value;
	if (id == "") {
		document.getElementById("checkId").innerHTML = "<font color='red'>아이디를 입력하세요</font>";
	}
	if (pwd == "") {
		document.getElementById("checkPwd").innerHTML = "<font color='red'>비밀번호를 입력하세요</font>";
	}
	if (id != "" && pwd != "") {
		document.getElementById("loginfrm").submit();
	}
}
function intilogin() {
	document.getElementById("checkId").innerHTML = "";
	document.getElementById("checkPwd").innerHTML = "";
};
