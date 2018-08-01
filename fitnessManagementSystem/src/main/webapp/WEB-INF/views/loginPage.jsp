
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="kr">



  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>로그인</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <style>
      body {
        padding-top: 54px;
      }
      @media (min-width: 992px) {
        body {
          padding-top: 56px;
        }
      }




    </style>
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script type="text/javascript">
    	var message='${message}';
    	if(message!="default")
    		alert(message);
    	
    </script>
    
    

  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">헬스일정 예약관리 시스템</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link" href="#">아이디 찾기</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">비밀번호 찾기</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">서비스 신청하기</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Page Content -->

    <div class="container">
      <div class="row" style="padding-top:20%">
        <div class="col-md-4 "></div>
          <div class="col-md-4" style="flex-align:center">
          <div class="card">
           <h5 class="card-header">로그인</h5>
           <div class="card-body">
          <form action='/loginProcess' method='POST' >

            <div class="form-group">
              <label for="InputId"> 아이디</label>
              <input type="text" class="form-control" id="username" name="username" value="${saved_id}" maxlength=20 >
            </div>
            <div class="form-group">
              <label for="InputPassword1">패스워드</label>
              <input type="password" class="form-control" id="password" name="password" maxlength=20>
            </div>
            <div class="checkbox">
              <label>
              <input type="checkbox" name="remember" ${chk} > 아이디 기억하기
              </label>
            </div>
            <button name="submit" type="submit" class="btn btn-block btn-primary text-light" onclick=login()>로그인</button>
            <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
          </form>
          </div>
         </div>
         </div>
       
    </div>
    </div>



    <!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


  </body>

</html>
