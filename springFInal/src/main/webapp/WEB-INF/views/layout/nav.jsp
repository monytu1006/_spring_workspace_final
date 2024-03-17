<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/">Home</a>
        </li>
        
   <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.mvo.email" var="authEmail"/>
        <sec:authentication property="principal.mvo.nickName" var="authNick"/>
        <sec:authentication property="principal.mvo.authList" var="auths"/>
        
      	<c:choose>
      		<c:when test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get()}">
				<li class="nav-item">
				  <a class="nav-link" href="/nmboard/list">일반 게시판</a>	
				</li>
				<li class="nav-item">
		          <a class="nav-link" href="/anboard/list">익명 게시판</a>
		        </li>
		   		<li class="nav-item">
		          <a class="nav-link" href="/member/list">멤버 게시판</a>
		        </li>
      		</c:when>

			<c:when test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_USER')).get() }">
				<li class="nav-item">
				  <a class="nav-link" href="/nmboard/list">일반 게시판</a>	
				</li>
				<li class="nav-item">
		          <a class="nav-link" href="/anboard/list">익명 게시판</a>
		        </li>
		
		    </c:when>
			<c:when test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_BlackList')).get() }">
				<li class="nav-item">
		          <a class="nav-link" href="/anboard/list">익명 게시판</a>
		        </li>
		    </c:when>
      	</c:choose>
      	
		<li class="nav-item">
		    <a class="nav-link" href="/member/detail">회원정보 ${authNick }(${authEmail })</a>
		</li>
      	
        <li class="nav-item">
          <a class="nav-link" href="/member/logout">로그아웃</a>
        </li>
        
       	<li class="nav-item">
          <a class="nav-link" href="/cmboard/list">고객센터</a>
        </li>
        
		<form action="/member/logout" method="post" id="logoutForm">
			<input hidden="hidden" name="email" value="authEmail">
		</form>
        
    </sec:authorize>

	<sec:authorize access="isAnonymous()">
        <li class="nav-item">
          <a class="nav-link" href="/member/register">회원 가입</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/member/login">로그인</a>
	
	</sec:authorize>   


      </ul>
    </div>
  </div>
</nav>


</body>
</html>