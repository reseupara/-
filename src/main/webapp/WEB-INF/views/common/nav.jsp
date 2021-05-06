<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
 
  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
  
  
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
      </li>
      <!-- href="/member/*" 안의 링크는 MemberController쪽을 가리킨다.  -->
      <!-- 인증되기 전 표시 -->
      <c:if test="${sessionScope.loginStatus == null }">
      <li class="nav-item">
        <a class="nav-link" href="/member/login">Login</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/member/join">Register</a>
      </li>
      </c:if>
      <!-- 인증 후 표시  loginStatus는 MemberController.java 쪽에 있다.-->
      <!-- 관리자페이지로 접속하면 loginStatus과 같으면 안된다. 다른이름으로. -->
      <!-- /member/다음에 MemberController.java 쪽에 있는 주소. -->
      <c:if test="${sessionScope.loginStatus != null }">
       <li class="nav-item">
        <a class="nav-link" href="/member/logout">로그아웃</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/member/modifycheck">내정보 수정</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/cart/cart_list">장바구니</a>
      </li>
      </c:if>
      <li class="nav-item">
        <a class="nav-link" href="#">MyPage</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Order Info</a>
      </li>
       <!--
      <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
      </li>
       
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      -->
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
      <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>

