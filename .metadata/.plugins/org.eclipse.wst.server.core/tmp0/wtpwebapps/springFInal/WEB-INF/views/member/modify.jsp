<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>


<div class="container-md">
<h2>회원정보 수정 페이지</h2><br>
	<sec:authentication property="principal.mvo.email" var="authEmail" />
	<sec:authentication property="principal.mvo.nickName" var="authNick" />

	<form action="/member/modify" method="post" id="form">
		<div class="mb-3">
			<label for="email" class="form-label">이메일</label>
			<input type="email" name="email" class="form-control" id="email" value="${authEmail }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="pwd" class="form-label">비밀번호</label>
			 <input type="password" name="pwd" class="form-control" id="pwd" placeholder="원하는 비밀번호를 입력하세요">
		</div>
		<div class="mb-3">
			<label for="pwdCheck" class="form-label">비밀번호확인</label>
			<input type="password" name="pwdCheck" class="form-control" id="pwdCheck" placeholder="원하는 비밀번호를 다시 입력하세요">
			<span id="confirmText"></span>
		</div>
		<div class="mb-3">
			<label for="nickName" class="form-label">닉네임</label>
			 <input type="text" name="nickName" class="form-control" id="nickName" placeholder="원하는 닉네임을 입력하세요">
		</div>
		
		<!-- 해당 멤버의 권한을 출력(2개일수도 있음) -->
		
		<button type="button" class="btn btn-dark" id="modBtn">회원정보 수정</button>
		<a href="/board/index"><button type="button" class="btn btn-danger">취소</button></a>
		
		
	</form>
		<a href="/member/delete"><button type="button" class="btn btn-danger">삭제하기</button></a>
	
</div>

<!-- 비밀번호 재확인 -->
<script type="text/javascript">
document.addEventListener("input", (e)=>{
    const pwd = document.getElementById('pwd').value;
    const pwdCheck = document.getElementById("pwdCheck").value;

    if(e.target.id == "pwdCheck" || e.target.id == "pwd"){
        const span = document.getElementById("confirmText");
        if(pwd != pwdCheck){
            span.setAttribute("style", "color : red");
            span.innerText = '비밀번호가 일치하지 않습니다';
        } else{
            span.setAttribute("style", "color : blue");
            span.innerText = '비밀번호가 일치합니다.';
        }
    }

})

document.addEventListener('click', (e)=>{
    const pwd = document.getElementById('pwd').value;
    const pwdCheck = document.getElementById("pwdCheck").value;
    
    if(e.target.id == 'modBtn'){
        if(pwd != pwdCheck) {
            alert("비밀번호가 일치하지 않습니다.");
            document.getElementById('pwd').focus();
            return false;
        } else{
            document.getElementById('form').submit();
        }
    }
})
</script>

<jsp:include page="../layout/footer.jsp"></jsp:include>







