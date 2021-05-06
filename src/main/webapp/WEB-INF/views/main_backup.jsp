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
<script>
	var message = '${status}';
	
	if(message == 'loginSuccess'){
		alert('로그인이 되었습니다.');
	}else if(message == 'loginIDFail'){
		alert('아이디가 틀립니다.');
	}else if(message == 'loginPWFail'){
		alert('비밀번호가 틀립니다.');
	}else if(message == 'logout'){
		alert('로그아웃 되었습니다.');
	}else if(message == 'modifySuccess'){
		alert('회원정보수정 완료.');
	}else if(message == 'regdelete'){
		alert('회원정보삭제 완료.');
	}
</script>


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

  </head>
  <body>
    
<!-- nav.jsp -->
<%@include file="/WEB-INF/views/common/nav.jsp" %>

<main role="main" class="container">
	
	<div class="row">
		<div class="col-2">
		
		</div>
		<div class="col-10">
		
		</div>
	</div>

  <div class="starter-template">
    <h1>Bootstrap starter template</h1>
    <p class="lead">Use this document as a way to quickly start any new project.<br> All you get is this text and a mostly barebones HTML document.</p>
  </div>

</main><!-- /.container -->


  </body>
</html>
