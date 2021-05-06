<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.80.0">
    <title>포트폴리오 참고용 Bootstrap v4.6</title>

<%@include file="/WEB-INF/views/common/config.jsp" %>

<script src="/js/member/login.js"></script>
   
<meta name="theme-color" content="#563d7c">

    <style>
    .right {
    float:right;
    }
    
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

  </head>
  <body>
    
<!-- nav.jsp -->
<%@include file="/WEB-INF/views/common/nav.jsp" %>

<div role="main" class="container">

    <!-- Content Wrapper. Contains page content -->
		<div class="wrapper">
			<%-- Main content  로그인 UI --%>
			<section class="content container-fluid">
				<div class="container" style="width: 450px; height:620px; background-color: white; margin-top:30px;">
					<form id="loginForm" class="form-signin" action="/member/loginPost" method="post" style="padding:50px 30px;">
						<h2 class="form-signin-heading">로그인</h2>
						<br><br>
						<label for="inputId" class="sr-only">Id</label> 
						<input type="text" id="mbme_id" name="mbme_id" class="form-control" style="margin-bottom: 15px"
							placeholder="Id" required autofocus> 
						<label for="inputPassword" class="sr-only">Password</label> 
						<input type="password" id="mbme_pw" name="mbme_pw" class="form-control"
							placeholder="Password" required>
						<br><br>
						<div class="checkbox">
							<label> <input type="checkbox" name="useCookie" />
								자동로그인
							</label>
							<a href="/member/find_pwd" class="right" >비밀번호 찾기</a>
							<a class="right" >/</a>
							<a href="/member/find_id" class="right" >아이디(이메일)</a>
						</div>
						<button id="btn_login" class="btn btn-lg btn-primary btn-block" 
								type="submit"  >
							로그인
						</button>
					</form>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
  </div>



      
  </body>
</html>