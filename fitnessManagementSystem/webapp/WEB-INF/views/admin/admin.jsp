<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>서비스 등록</title>

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
    
    <script>
    
    	var message='${message}';
    	if(message!="")
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
              <a class="nav-link" href="/logout">로그아웃</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <!-- Page Content -->
    <form style="margin:10px" method="post" action="/admin">
    <div class="container">
      <div class="row" style="padding-top:10%">
      <div class="col-md-6">
      <div class="card">
		  <h5 class="card-header">센터정보</h5>
  		<div class="card-body">
            <div class="form-group">
              <label for="InpuCenterId">센터아이디</label>
              <input type="text" class="form-control" id="center_id" name="center_id" placeholder="Center ID">
            </div>
            <div class="form-group">
              <label for="InputCenterName">센터이름</label>
              <input type="text" class="form-control" id="center_name"  name="center_name" placeholder="Center Name">
            </div>
            <div class="form-group">
              <label for="InputCenterPhoneNumber">센터연라처</label>
              <input type="text" class="form-control" id="center_phone_number" name="center_phone_number" placeholder="Center PhoneNumber">
            </div>
             <div class="form-group">
              <label for="CenterAddress">센터주소</label>
              <input type="text" class="form-control" id="address" name="address" placeholder="Center Address">
            </div>
		</div>
		</div>
		</div>
		<div class="col-md-6">
		     <div class="card">
		  <h5 class="card-header">관리자정보</h5>
  		<div class="card-body">
            <div class="form-group">
              <label for="InputId">아이디</label>
              <input type="text" class="form-control" id="id" name="id" placeholder="ID">
            </div>
            <div class="form-group">
              <label for="InputPassword">비밀번호</label>
              <input type="password" class="form-control" id="password"  name="password" placeholder="Password">
            </div>
            <div class="form-group">
              <label for="InputName">이름</label>
              <input type="text" class="form-control" id="name" name="name" placeholder="Name">
            </div>
             <div class="form-group">
              <label for="phoneNumber">휴대폰 번호</label>
              <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="PhoneNumber">
            </div>
            <button type="submit" class="btn btn-block btn-primary text-light">서비스 이용자 등록</button>
                  <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</div>
		</div>
		</div>
      
       
    </div>
    </div>
        </form>



    <!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>
</html>