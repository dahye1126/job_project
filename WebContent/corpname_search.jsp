<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function searchcorpname() {
		document.getElementsByName("corp_search")[0].action = "corp_name.do";
		document.getElementsByName("corp_search")[0].submit();
	}
	function selectcorpname() {
		document.getElementsByName("corp_select")[0].action = "corp_name.do";
		document.getElementsByName("corp_select")[0].submit();
	}


</script>
</head>
<body>

	<form action="corp_name.do" method="get" name="corp_search">
		<input type="text" name="corp_name" placeholder="기업명 입력">
		<input type="hidden" name="code" value="corpname_code">
		<input type="button" value="재검색" onclick="javascript:searchcorpname()">				
	</form>


		
		
		



	<form action=corp_name.do name="corp_select">
	

	 	<c:forEach items="${CORPNAME}" var="list">
			${list.corp_code} 
			${list.corp_name}
	 		<input type="hidden" name="s_corpcode" value="${list.corp_code}">
			<input type="hidden" name="s_corpname" value="${list.corp_name}">
			<a href="corp_name.do?s_corpcode=${list.corp_code }&s_corpname=${list.corp_name }&code=aaa">버튼</a><br>
		</c:forEach>
		
	</form>
	
	<form action="corp_name.do" method="get" >
		<input type="text" name="s_corpname">
		<input type="hidden" name="code" value="aaa">
		<input type="submit" value="선택">
	</form>
		



</body>
</html>