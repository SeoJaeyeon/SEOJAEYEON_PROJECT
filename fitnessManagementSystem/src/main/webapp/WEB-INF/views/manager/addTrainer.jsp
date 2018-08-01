<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>관리자페이지</title>
  <!-- Bootstrap core CSS-->
  <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="${pageContext.request.contextPath}/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Page level plugin CSS-->
    <script>
    
    	var message='${message}';
    	if(message!="")
    		alert(message);   	
    	//이미지 미리보기 
    	function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#ShowImage')
                    .attr('src', e.target.result)
                    .width(150)
                    .height(200);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }
    </script>

</head>
<body>
   <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">헬스일정예약관리시스템</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
         <li class="nav-item active" data-toggle="tooltip" data-placement="right" title="adminUser">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseMulti" data-parent="#exampleAccordion">
            <i class="fa fa-fw fa-sitemap"></i>
            <span class="nav-link-text">사용자관리</span>
          </a>
          <ul class="sidenav-second-level collapse" id="collapseMulti">
            <li>
              <a href="/manager/addUser">회원추가</a>
            </li>
            <li>
              <a href="#">회원관리</a>
            </li>
            <li>
              <a href="/manager/addTrainer">트레이너추가</a>
            </li>
              <li>
              <a href="#">트레이너관리</a>
            </li>
          </ul>
        </li>
          <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="/manager">
            <i class="fa fa-fw fa-table"></i>
            <span class="nav-link-text">스케줄</span>
          </a>
        </li>
         <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Example Pages">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#" data-parent="#exampleAccordion">
            <i class="fa fa-fw fa-file"></i>
            <span class="nav-link-text">게시판</span>
          </a>
        </li>
        <li class="nav justify-content-end">
          <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
            <i class="fa fa-fw fa-sign-out"></i>Logout</a>
        </li>
      </ul>
    </div>
    </div>
  </nav>
<form method="post">
   <div class="container" style="padding-top:5%">
 	<div class="row">
 	<div class="col-md-3"></div>
 		<div class="col-md-6">
		     <div class="card">
		  <h5 class="card-header">트레이너추가</h5>
  		<div class="card-body">
  		<!-- 사진 입력폼  -->
  		  <div class="form-group">
              <label for="InputId">아이디</label>
              <input type="text" class="form-control" id="id" name="id" placeholder="ID">
            </div>
            <!-- 사진 입력폼 -->
            <div class="form-group">
              <label for="InpuImage">사진</label>          
			  <input type='file' onchange="readURL(this);" /> 
		      <img id="ShowImage" src="#" placeholder="사진을 등록하세요" />
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
              <label for="InputName">성</label>
              <input type="text" class="form-control" id="gender" name="gender" placeholder="Gender">
            </div>
             <div class="form-group">
              <label for="phoneNumber">휴대폰 번호</label>
              <input type="text" class="form-control" id="phone_number" name="phone_number" placeholder="Phone_number">
            </div>
               <div class="form-group">
              <label for="closedDay">휴무일</label>
              <input type="text" class="form-control" id="closed_day" name="closed_day" placeholder="Closed Day">
            </div>
            <button type="submit" class="btn btn-block btn-primary text-light">서비스 이용자 등록</button>
                  <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</div>
 	</div>
 	</div>
 	<div class="col-md-3"></div>
 	</div>
 	</div>
 	</form>
 	
     <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->

    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">로그아웃 하시겠습니까?</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="/logout">Logout</a>
          </div>
        </div>
      </div>
    </div>
</body>
</html>