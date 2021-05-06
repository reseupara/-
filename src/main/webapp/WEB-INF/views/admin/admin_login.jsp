<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.80.0">
    <title>Floating labels example · Bootstrap v4.6</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/floating-labels/">

    

    <!-- Bootstrap core CSS -->
<%@include file="/WEB-INF/views/common/config.jsp" %>
<link rel="stylesheet" href="/resources/signin.css">



    <!-- Favicons -->
<meta name="theme-color" content="#563d7c">


    <style>
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
    
	<script>
		var mag = "${msg}";
		if(msg == "Fail"){
			alert("아이디와 비밀번호를 확인해주세요.");
		}else if(msg == "logout"){
			alert("로그아웃이 되었습니다.");
		}
			
	</script>

    

  </head>
  <body>
    
<form class="form-signin" action="/admin/admin_check" method="post">

  <div class="form-label-group">
    <input type="text" id="admin_id" name="admin_id" class="form-control" placeholder="Email address" required autofocus>
    <label for="inputId">Id</label>
  </div>

  <div class="form-label-group">
    <input type="password" id="admin_pw" name="admin_pw" class="form-control" placeholder="Password" required>
    <label for="inputPassword">Password</label>
  </div>

  <div class="checkbox mb-3">
    <label>
      <input type="checkbox" value="remember-me"> Remember me
    </label>
  </div>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  <p class="mt-5 mb-3 text-muted text-center">&copy; 2017-2021</p>
</form>

</body>
</html>