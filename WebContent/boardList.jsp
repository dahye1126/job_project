<%@page import="model.BoardBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>게시판 목록조회</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </head>
<body>
<div class="body-container">
  <h2>게시판 목록</h2><br>
  <div align="right">
    <button type="button" class="btn btn-primary" onclick="location.href='board.html'">글쓰기</button>
   </div>
    <div class="row">
      <div class="col-sm-1" style="background-color:lavender; font-weight: bold;">글번호</div>
      <div class="col-sm-5" style="background-color:lavenderblush; font-weight: bold;">글제목</div>
      <div class="col-sm-2" style="background-color:lavender; font-weight: bold;">작성자</div>
      <div class="col-sm-2" style="background-color:lavenderblush; font-weight: bold;">작성시간</div>
    </div>
    
    <c:forEach items="${ALLBOARD}" var="ab">
    <div class="row">
      <div class="col-sm-1" style="background-color:lavender;">${ab.no}</div>
      <div class="col-sm-5" style="background-color:lavenderblush;"><a href="board?num=${ab.no}&board_code=detail">${ab.title}</a></div>
      <div class="col-sm-2" style="background-color:lavender;">${ab.id}</div>
      <div class="col-sm-2" style="background-color:lavenderblush;">${ab.time}</div>
    </div>
    </c:forEach>
</div>

<br><br>
    
	<input name="board_code" type="hidden" value="list">

<div class="container">
	<ul class="pagination">
        <c:if test="${spage != 1}">
           <li><a href='board?page=1&board_code=list'>[처음]</a></li>
        </c:if>
        <c:if test="${spage >=6}">
           <li><a href='board?page=${startpage-1}&board_code=list'>[이전]</a></li>
        </c:if>
        
        <c:forEach var="pagenum" begin="${startpage}" end="${endpage}">
            <c:if test="${pagenum == spage}">
                <li class="active"><a href='#'>${pagenum}&nbsp;</a></li>
            </c:if>
            <c:if test="${pagenum != spage}">
               <li><a href='board?page=${pagenum}&board_code=list'>${pagenum}&nbsp;</a></li>
            </c:if>
        </c:forEach>
        
        <c:if test="${endpage != maxpage}">
           <li><a href='board?page=${endpage+1}&board_code=list'>[다음]</a></li>
           <li><a href='board?page=${maxpage}&board_code=list'>[끝]</a></li>
        </c:if>
	</ul>
</div>

<br>
    <div id="searchForm" align = "center">
        <form>
            <select name="opt">
                <option value="0">제목</option>
                <option value="1">내용</option>
                <option value="2">제목+내용</option>
                <option value="3">글쓴이</option>
            </select>
            <input type="text" size="20" name="condition"/>&nbsp;
            <input name="board_code" type="hidden" value="list">
            <input type="submit" value="검색"/>
        </form>    
    </div>

</body>
</html>
