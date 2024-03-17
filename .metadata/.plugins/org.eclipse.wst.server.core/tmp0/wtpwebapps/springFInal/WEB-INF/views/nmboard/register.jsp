<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
	
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<!--  -->
	<div class="container-md">
	<br>
	<h2>일반게시판 등록 페이지</h2>
	<br>
	
	<sec:authentication property="principal.mvo.email" var="authEmail"/>
	<sec:authentication property="principal.mvo.nickName" var="authNick"/>
	
	<form action="/nmboard/register" method="post" enctype="multipart/form-data">
		<div class="mb-3">
			<label for="nmTitle" class="form-label">제목</label> 
			<input type="text" class="form-control" name="nmTitle" id="nmTitle" placeholder="제목을 입력하시오">
		</div>
		<div class="mb-3">
			<label for="nmWriter" class="form-label">작성자</label>
			<input type="text" class="form-control" name="nmWriter" id="nmWriter" value="${authNick }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="nmContent" class="form-label">글 내용</label>
			<textarea class="form-control" name="nmContent" id="nmContent" rows="3" placeholder="내용을 입력하시오"></textarea>
		</div>

		<!-- file 입력 라인 추가 -->
		<div class="mb-3">
			<input type="file" class="form-control" name="files" id="files" multiple="multiple" style="display: none"> <br>
			<!-- 파일 버튼 트리거 사용하기 위해서 주는 버튼 -->
			<button type="button" class="btn btn-outline-primary" id="trigger">파일 업로드</button>		
		</div>		
		
		<!-- 첨부파일 쵸기될 영역 -->
		<!-- 파일 목록 표시라인 -->
		<div class="mb-3" id="fileZone">
		
		</div>

		<button type="submit" class="btn btn-dark" id="regBtn">글 등록</button>


	</form>
	</div>

<script src="/resources/js/nmBoardRegister.js"></script>

<jsp:include page="../layout/footer.jsp"></jsp:include>






