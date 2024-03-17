<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
	
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container-md">
	<h2>회원 로그인 페이지</h2><br>
	<form action="/member/login" method="post">
		<div class="mb-3">
	      <label for="email" class="form-label">아이디(email과 동일)</label>
	      <input type="email" name="email" class="form-control" id="email" placeholder="Email..">
	   </div>
	   <div class="mb-3">
	      <label for="pwd" class="form-label">비밀번호</label>
	      <input type="password" name="pwd" class="form-control" id="pwd" placeholder="PassWord..">
	   </div>
   		<button type="submit" class="btn btn-primary">로그인</button>
	</form>
	
 	<c:if test="${not empty param.errMsg }">
		<div class="mb-3 text-danger">
			<c:choose>
				<c:when test="${param.errMsg eq 'Bad credentials' }">
					<c:set value="이메일 & 비밀번호가 일치하지 않습니다!" var="errText"></c:set>
				</c:when>
				<c:otherwise>
					<c:set value="관리자에게 문의해주세요" var="errText"></c:set>
				</c:otherwise>
			</c:choose>
			${errText}
		</div>
	</c:if> 
</div>




















<jsp:include page="../layout/footer.jsp"></jsp:include>