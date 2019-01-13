
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>게시판 상세조회</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%-- <%
	ArrayList<BoardBean> list = (ArrayList<BoardBean>) request.getAttribute("ALLBOARD");
%> --%>
<h2>글 상세조회</h2><br>
<form action="corpboard" method="post" name="board">
	<div class="row">
      <div class="col-sm-1" style="background-color:lavender;"><b>글번호 : ${BOARD.no}</b></div>
      <div class="col-sm-5" style="background-color:lavenderblush;"><b>제목 : ${BOARD.title}</b></div>
      <div class="col-sm-2" style="background-color:lavender;"><b>작성자 : ${BOARD.id}</b></div>
      <div class="col-sm-2" style="background-color:lavenderblush;"><b>작성시간 : ${BOARD.time}</b></div>
    </div>
    <div>내용 : <br>${BOARD.content }</div>
	<button type="button" class="btn btn-primary">수정</button>
	<button type="button" class="btn btn-primary">삭제</button>
	<%-- <%
		for(int i = 0; i < list.size(); i++) {
	%>
	입력한 아이디 : <%= list.get(i).getId() %><br>
	입력한 글 제목 : <%= list.get(i).getTitle() %><br>
	입력한 글 내용 : <%= list.get(i).getContent() %><br>
	추천수 : <%= list.get(i).getRecommend() %><br>
	비추천수 : <%= list.get(i).getNot_recommend() %><br>
	작성시간 : <%= list.get(i).getTime() %><br>
	글번호 : <%= list.get(i).getNo() %><br>
	<% 
		}
	%> --%>
	
	<%-- 입력한 회사 코드 : ${BOARD.corp_code}<br>
	입력한 아이디 : ${BOARD.id}<br>
	입력한 글 제목 : ${BOARD.title}<br>
	입력한 글 내용 : ${BOARD.content}<br>
	추천수 : ${BOARD.recommend}<br>
	비추천수 : ${BOARD.not_recommend}<br>
	작성시간 : ${BOARD.time}<br>
	글번호 : ${BOARD.no}<br>
	입력한 회사 명 : ${BOARD.corp_name}<br>
	
	<input name="h_corp_code" type="hidden" value="${BOARD.corp_code}">
	<input name="h_id" type="hidden" value="${BOARD.id}">
	<input name="h_title" type="hidden" value="${BOARD.title}">
	<input name="h_content" type="hidden" value="${BOARD.content}">
	<input name="h_recommend" type="hidden" value="${BOARD.recommend}">
	<input name="h_not_recommend" type="hidden" value="${BOARD.not_recommend}">
	<input name="h_time" type="hidden" value="${BOARD.time}">
	<input name="h_no" type="hidden" value="${BOARD.no}">
	<input name="h_corp_name" type="hidden" value="${BOARD.corp_name}">
	<input name="board_code" type="hidden" value="detail">
	<button type="button" class="btn btn-primary">수정</button>
	<button type="button" class="btn btn-primary">삭제</button>
	<button type="button" class="btn btn-primary" onclick="location.href='board?board_code=list&page=1'">목록</button> --%>
</form>
</body>
</html>