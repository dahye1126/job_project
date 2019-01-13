<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<head>
  <title>Intermission</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
  </style>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar">a</span>
        <span class="icon-bar">b</span>
        <span class="icon-bar">c</span>                        
      </button>
      <a><img src="img/logo.png"></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="main">Home</a></li>
        <li class="active"><a href="#">기업정보</a></li>
        <li><a href="board?board_code=list&page=1">자유게시판</a></li>
        <li><a href="csboard?board_code=list&page=1">고객센터</a></li>
      </ul>
      <form class="navbar-form navbar-right" role="search">
        <div class="form-group input-group">
          <input type="text" class="form-control" placeholder="Search..">
          <span class="input-group-btn">
            <button class="btn btn-default" type="button">
              <span class="glyphicon glyphicon-search"></span>
            </button>
          </span>        
        </div>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> My Account</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container text-center">    
  <div class="row">
    <div class="col-sm-3 well">
      <div class="well">
        <p style="font-weight:bold; text-transform: uppercase"><a href="${requestScope.CORP.web_site}">${requestScope.CORP.corp_name}</a></p>
      </div>
      <div class="well">
        <p>소재지 | ${requestScope.CORP.location}</p>
        <p>산업군 | ${requestScope.CORP.area}</p>
        <p>설립년도 | ${requestScope.CORP.est_year}</p>
        <p>대표자 | ${requestScope.CORP.representative}</p>
        <!-- <p>
          <span class="label label-default">News</span>
          <span class="label label-primary">W3Schools</span>
          <span class="label label-success">Labels</span>
          <span class="label label-info">Football</span>
          <span class="label label-warning">Gaming</span>
          <span class="label label-danger">Friends</span>          
        </p> -->
      </div>
      <div class="alert alert-success fade in">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
        <p><strong>Ey!</strong></p>
        People are looking at your profile. Find out who.
      </div>
      <p><a href="#">Link</a></p>
      <p><a href="#">Link</a></p>
      <p><a href="#">Link</a></p>
    </div>
    <div class="col-sm-7">
    
      <div class="row">
        <div class="col-sm-12">
          <div class="panel panel-default text-left">
            <div class="panel-body">
              <p contenteditable="true">Status: Feeling Blue</p>
              <button type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-thumbs-up"></span> Like
              </button>     
            </div>
          </div>
        </div>
      </div>
      
      <div class="row">
        <div class="col-sm-3">
          <div class="well">
           <p>${requestScope.STATUS[0].year}</p>
           <!-- <img src="bird.jpg" class="img-circle" height="55" width="55" alt="Avatar"> -->
          </div>
        </div>
        <div class="col-sm-9">
          <div class="well">
            <p>
            	직원수 | <fmt:formatNumber pattern="${requestScope.STATUS[0].emp >= 1000 ? '0,000' : '0'}" value='${requestScope.STATUS[0].emp}'/>명<br>
            	매출액 | ￦<fmt:formatNumber pattern='0,000' value='${requestScope.STATUS[0].sales}'/>원<br>
            	평균임금 | ￦<fmt:formatNumber pattern='0,000' value='${requestScope.STATUS[0].avg_wage}'/>원<br>
            	근속년수 | ${requestScope.STATUS[0].continuous_year}년<br>            	
            </p>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-3">
          <div class="well">
           <p>${requestScope.STATUS[1].year}</p>
           <!-- <img src="bandmember.jpg" class="img-circle" height="55" width="55" alt="Avatar"> -->
          </div>
        </div>
        <div class="col-sm-9">
          <div class="well">
            <p>
            	직원수 | ${requestScope.STATUS[1].emp}명<br>
            	매출액 | ￦${requestScope.STATUS[1].sales}원<br>
            	평균임금 | ￦${requestScope.STATUS[1].avg_wage}원<br>
            	근속년수 | ${requestScope.STATUS[1].continuous_year}년<br>            	
            </p>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-3">
          <div class="well">
           <p>${requestScope.STATUS[2].year}</p>
           <!-- <img src="bandmember.jpg" class="img-circle" height="55" width="55" alt="Avatar"> -->
          </div>
        </div>
        <div class="col-sm-9">
          <div class="well">
            <p>
            	직원수 | ${requestScope.STATUS[2].emp}명<br>
            	매출액 | ￦${requestScope.STATUS[2].sales}원<br>
            	평균임금 | ￦${requestScope.STATUS[2].avg_wage}원<br>
            	근속년수 | ${requestScope.STATUS[2].continuous_year}년<br>            	
            </p>
          </div>
        </div>
      </div>
      <div class="row">
      <div class="well">
        <canvas id='c'></canvas>  
        </div>      
      </div>     
    </div>
    <div class="col-sm-2 well">
      <div class="thumbnail">
        <p>Upcoming Events:</p>
        <img src="paris.jpg" alt="Paris" width="400" height="300">
        <p><strong>Paris</strong></p>
        <p>Fri. 27 November 2015</p>
        <button class="btn btn-primary">Info</button>
      </div>      
      <div class="well">
        <p>ADS</p>
      </div>
      <div class="well">
        <p>ADS</p>
      </div>
    </div>
  </div>
</div>
<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>
<script src="js/chat.js" type="text/javascript"></script>
</body>
</html>
