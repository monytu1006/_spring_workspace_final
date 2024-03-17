<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container">
	<br>
	<h2>회원 리스트 게시판</h2>
	<br>
	
		<table class="table table-hover">
		  <thead>
		    <tr>
		      <th scope="col">번호</th>
		      <th scope="col">이메일</th>
		      <th scope="col">닉네임</th>
		      <th scope="col">가입일</th>
		      <th scope="col">최근 접속일</th>
		      <th scope="col">권한</th>
		    </tr>
		  </thead>
		  
		  <tbody>
			<c:forEach items="${list}" var="mvo" varStatus="i">
			    <tr>
			        <th scope="row">${i.count}</th>
			        <td>${mvo.email}</td>
			        <td>${mvo.nickName}</td>
			        <td>${mvo.regAt}</td>
			        <td>${mvo.lastLogin}</td>
			        <c:set value="${mvo.authList}" var="authList"/>
					<td>
						<c:choose>
							<c:when test="${authList.stream().anyMatch(AuthVO -> AuthVO.auth.contains('ROLE_ADMIN')).get() }">
								<p>관리자[ADMIN]</p>
							</c:when>
							<c:when test="${authList.stream().anyMatch(AuthVO -> AuthVO.auth.contains('ROLE_BlackList')).get() }">
								<p>블랙리스트[BlackList]</p>
								<a href="/member/cancelBlackList?email=${mvo.email}"><button type="button" class="btn btn-success canBlack" id="canBlackBtn">블랙리스트 취소</button></a>
							</c:when>
							<c:otherwise>
								<p>유저[USER]</p>
								<a href="/member/registerBlackList?email=${mvo.email}"><button type="button" class="btn btn-danger regBlack" id="modBlackBtn">블랙리스트 등록</button></a>
							</c:otherwise>
						</c:choose>
					</td>
    			</tr>
  		  </c:forEach>
 		 </tbody>
		</table>
</div>

<script type="text/javascript">
	document.addEventListener('click', (e)=>{
		if(e.target.classList.contains('regBlack')){
			if(confirm('해당 회원을 블랙리스트 등록하시겠습니까?')){
				document.getElementById('modBlackBtn').setAttribute('href', '/member/registerBlackList');
			}
		}
		if(e.target.classList.contains('canBlack')){
			if(confirm('해당 회원의 블랙리스트 해제하시겠습니까?')){
				document.getElementById('canBlackBtn').setAttribute('href', '/member/cancelBlackList');
			}
		}
	})
</script>

<jsp:include page="../layout/footer.jsp"></jsp:include>





