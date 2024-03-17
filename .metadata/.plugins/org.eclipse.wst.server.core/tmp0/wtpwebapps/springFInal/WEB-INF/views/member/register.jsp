<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<div class="container-md">
	<br>
	<h2>회원가입 페이지</h2>
	<br>
	<form action="/member/register" method="post" id="form">
		<div class="mb-3">
			<label for="email" class="form-label">이메일</label>
			<br>
			<input type="email" name="email" class="form-control" id="email" placeholder="이메일을 입력하세요">
 			<button type="button" class="btn btn-light" id="validationId">중복확인</button>
		</div>
		
		<div class="mb-3">
			<label for="pwd" class="form-label">비밀번호</label>
			<input type="password" name="pwd" class="form-control" id="pwd" placeholder="비밀번호를 입력하세요">
		</div>
		<div class="mb-3">
			<label for="pwdConfirm" class="form-label">비밀번호확인</label>
			<input type="password" name="pwdConfirm" class="form-control" id="pwdConfirm" placeholder="비밀번호를 다시 입력하세요">
			<span id="confirmText"></span>
		</div>
		<div class="mb-3">
			<label for="nickName" class="form-label">닉네임</label>
			<input type="text" name="nickName" class="form-control" id="nickName" placeholder="닉네임을 입력하세요">
		</div>
		<button type="button" class="btn btn-primary" id="submitBtn">회원가입</button>
	</form>
</div>

<script src="../resources/js/memberRegister.js"></script>


<jsp:include page="../layout/footer.jsp"></jsp:include>