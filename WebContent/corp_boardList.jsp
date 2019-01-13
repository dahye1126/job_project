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
  <script type="text/javascript">
  document.getElementsByClassName("body-container").action = "corpboard";
  document.getElementsByClassName("body-container").submit();
  </script>
  </head>
<body>
<%-- <%
	ArrayList<CorpBoardBean> list = (ArrayList<CorpBoardBean>) request.getAttribute("ALLBOARD");
%> --%>
<div class="body-container">
  <h2>게시판 목록</h2><br>
    <div class="row">
      <div class="col-sm-1" style="background-color:lavender;"><b>글번호</b></div>
      <div class="col-sm-5" style="background-color:lavenderblush;"><b>글제목</b></div>
      <div class="col-sm-2" style="background-color:lavender;"><b>작성자</b></div>
      <div class="col-sm-2" style="background-color:lavenderblush;"><b>작성시간</b></div>
    </div>
<form action="corpboard">
    <c:forEach items="${list}" var="ab">
    <div class="row">
		<div class="col-sm-1" style="background-color:lavender;">${ab.no}</div>
		<div class="col-sm-5" style="background-color:lavenderblush;"><a href="corpboard?num=${ab.no}&board_code=detail">${ab.title}</a></div>
		<div class="col-sm-2" style="background-color:lavender;">${ab.id}</div>
		<div class="col-sm-2" style="background-color:lavenderblush;">${ab.time}</div>
    </div>
    </c:forEach>
    <input name="board_code" type="hidden" value="list">
</form>
</div> 

    <button type="button" class="btn btn-primary" onclick="location.href='board.html'">글쓰기</button>
    <input name="board_code" type="hidden" value="list">

<div class="container">
        <c:if test="${startpage != 1}">
            <a href='corpboard?page=${startpage-1}&board_code=list'>[ 이전 ]</a>
        </c:if>
        
        <c:forEach var="pagenum" begin="${startpage}" end="${endpage}">
            <c:if test="${pagenum == spage}">
                ${pagenum}&nbsp;
            </c:if>
            <c:if test="${pagenum != spage}">
                <a href='corpboard?page=${pagenum}&board_code=list'>${pagenum}&nbsp;</a>
            </c:if>
        </c:forEach>
        
        <c:if test="${endpage != maxpage }">
            <a href='corpboard?page=${endpage+1 }&board_code=list'>[다음]</a>
        </c:if>
</div>
 <br>
	<div id="searchForm" align = "center">
		<form>
			<select name="opt">
				<option value="0">회사명</option>
                <option value="1">제목</option>
                <option value="2">내용</option>
                <option value="3">제목+내용</option>
                <option value="4">글쓴이</option>
			</select>
			<input type="text" size="20" name="condition"/>&nbsp;
			<input name="board_code" type="hidden" value="list">
			<input type="submit" value="검색"/>
		</form>    
	</div>
</div>    
</body>
</html>