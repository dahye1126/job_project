<%@page import="model.BoardBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
<h2>글 상세조회</h2><br>
<form action="board" method="post" name="board">
<div class="container-fluid">
  <div class="row">
    <div class="col-sm-2" style="background-color:lavender; font-weight: bold;">글 번호</div>
    <div class="col-sm-4" style="background-color:lavenderblush;">${BOARD.no}</div>
    <div class="col-sm-2" style="background-color:lavender; font-weight: bold;">작성시간</div>
    <div class="col-sm-4" style="background-color:lavenderblush;">${BOARD.time}</div>
  </div>
  <div class="row">
    <div class="col-sm-2" style="background-color:lavender; font-weight: bold;">작성자</div>
    <div class="col-sm-4" style="background-color:lavenderblush;">${BOARD.id}</div>
    <div class="col-sm-2" style="background-color:lavender; font-weight: bold;">추천</div>
    <div class="col-sm-1" style="background-color:lavenderblush;">${BOARD.recommend}</div>
    <div class="col-sm-2" style="background-color:lavender; font-weight: bold;">비추천</div>
    <div class="col-sm-1" style="background-color:lavenderblush;">${BOARD.not_recommend}</div>
  </div>
  <div class="row">
    <div class="col-sm-2" style="background-color:lavender; font-weight: bold;">글 제목</div>
    <div class="col-sm-10" style="background-color:lavenderblush;">${BOARD.title}</div>
  </div>
  <div class="row">
    <div class="col-sm-12" style="background-color:lavender; font-weight: bold;">글 내용</div>
  </div>
  <div class="row">
    <div class="col-sm-12" style="background-color:lavenderblush;">${BOARD.content}</div>
  </div>
</div>	
	<input name="h_id" type="hidden" value="${BOARD.id}">
	<input name="h_title" type="hidden" value="${BOARD.title}">
	<input name="h_content" type="hidden" value="${BOARD.content}">
	<input name="h_recommend" type="hidden" value="${BOARD.recommend}">
	<input name="h_not_recommend" type="hidden" value="${BOARD.not_recommend}">
	<input name="h_time" type="hidden" value="${BOARD.time}">
	<input name="h_no" type="hidden" value="${BOARD.no}">
	<input name="board_code" type="hidden" value="detail">
	<br><br>
	<button type="button" class="btn btn-primary" onclick="location.href='board?num=${BOARD.no}&board_code=modify'">수정</button>
	<button type="button" class="btn btn-primary" onclick="location.href='board?num=${BOARD.no}&board_code=delete'">삭제</button>
	<button type="button" class="btn btn-primary" onclick="location.href='board?board_code=list&page=1'">목록</button> 
</form>
</body>
</html>
