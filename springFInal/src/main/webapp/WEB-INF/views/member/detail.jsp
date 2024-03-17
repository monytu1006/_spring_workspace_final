<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

	
	<jsp:include page="../layout/header.jsp"></jsp:include>
	<jsp:include page="../layout/nav.jsp"></jsp:include>
	
<div class="container-md">
<h2>회원정보 상세 페이지</h2><br>

	<sec:authentication property="principal.mvo.nickName" var="authNick" />
	<h2 class="mb-3"> ${authNick}님의 회원정보</h2>
	<sec:authentication property="principal.mvo.email" var="authEmail" />
	<sec:authentication property="principal.mvo.regAt" var="authRegAt" />
	<sec:authentication property="principal.mvo.authList" var="authList" />


		<div class="mb-3">
			<label for="email" class="form-label">이메일</label>
			<input type="email" name="email" class="form-control" id="email" value="${authEmail}" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="nickName" class="form-label">닉네임</label>
			<input type="text" name="nickName" class="form-control" id="nickName" value="${authNick }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="regAt" class="form-label">가입일</label>
			<input type="text" name="regAt" class="form-control" id="regAt" value="${authRegAt }" readonly="readonly">
		</div>
		<c:set var="authListNumber" value="${authList}" />
		
		<!-- 해당 멤버의 권한을 출력 (2개일 수도 있음) -->
		<div class="mb-3">
		    <label for="authList" class="form-label">회원 단계</label>
		    <c:forEach items="${authList}" var="auth">
		        <input type="text" class="form-control" value="${auth.auth}" readonly="readonly">
		    </c:forEach>
		</div>
		
		<br>
		<hr>
		<br>
		
		<a href="/member/modify"><button type="button" class="btn btn-dark" id="modBtn">회원정보 수정</button></a>
		<a id="deleteBtn"><button type="button" class="btn btn-danger del">탈퇴</button></a>
		
	
</div>

<script type="text/javascript">
	document.addEventListener('click', (e)=>{
		if(e.target.classList.contains('del')){
			if(confirm('회원탈퇴하시겠습니까?')){
				document.getElementById('deleteBtn').setAttribute('href', '/member/delete');
			}
		}
	})
</script>


	<jsp:include page="../layout/footer.jsp"></jsp:include>
	
	
	
	