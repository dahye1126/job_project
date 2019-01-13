<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<title>게시글 수정</title>
</head>
<body>
<div class="container">
  <h2>글 수정하기</h2><br>
  <p><b>다른 사람의 인격을 침해하거나 명예를 훼손하게 하는 글, 불쾌감을 주는 욕설 또는 비방하는 글, 유언비어나 허위사실을 유포하는 글, 도배성 글의 경우 글이 삭제되거나 이용제재를 받을 수 있습니다.</b></p>            
  <form action="board" method="post">
    <div class="form-group">
      <label for="title">글 제목:</label>
      <input type="text" class="form-control" id="mod_title" name="mod_title" value="${BOARD.title}">
    </div>
    <div class="form-group">
      <label for="content">글 내용:</label>
      <textarea class="form-control" rows="10" id="mod_content" name="mod_content">${BOARD.content}</textarea>
    </div>
    <input name="board_code" type="hidden" value="modify2">
    <input name="no" type="hidden" value="${BOARD.no}">
    <button type="submit" class="btn btn-primary">게시</button>
    <button type="button" class="btn btn-primary" onclick="location.href='board?board_code=list&page=1'">목록</button>
  </form>
</div>
</body>
</html>