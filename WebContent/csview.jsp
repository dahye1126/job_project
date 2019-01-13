<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>글 내용보기</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>

<form action="csboard" name="board">
	<div class="row">
      <div class="col-sm-1" style="background-color:lavender;"><b>글번호 : ${csb.no}</b></div>
      <div class="col-sm-5" style="background-color:lavenderblush;"><b>제목 : ${csb.title}</b></div>
      <div class="col-sm-2" style="background-color:lavender;"><b>작성자 : ${csb.id}</b></div>
      <div class="col-sm-2" style="background-color:lavenderblush;"><b>작성시간 : ${csb.time}</b></div>
    </div>
    <div>내용 : <br>${csb.content }</div>
	<button type="button" class="btn btn-primary">수정</button>
	<button type="button" class="btn btn-primary">삭제</button>
</form>
</body>
</html>