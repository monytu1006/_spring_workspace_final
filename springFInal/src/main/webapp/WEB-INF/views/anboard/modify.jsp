<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>
	
	

	<div class="container-md">
	<br>
	<h2>익명 게시판 수정 페이지</h2>
	<br>
	
	<c:set value="${anbvo}" var="anbvo" />
	<form action="/anboard/modify" method="post" enctype="multipart/form-data">
			<div class="mb-3">
				<label for="anBno" class="form-label">글번호</label>
				<input type="text" name="anBno" class="form-control" id="anBno" value="${anbvo.anBno}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="anTitle" class="form-label">글 제목</label>
				<input type="text" name="anTitle" class="form-control" id="anTitle" value="${anbvo.anTitle}">
			</div>
			<div class="mb-3">
				<label for="anContent" class="form-label">글 내용</label>
				<input type="text" name="anContent" class="form-control" id="anContent" value="${anbvo.anContent}">
			</div>
			<div class="mb-3">
				<label for="anRegAt" class="form-label">등록일</label>
				<input type="text" name="anRegAt" class="form-control" id="anRegAt" value="${anbvo.anRegAt}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="anReadCount" class="form-label">조회수</label>
				<input type="text" name="anReadCount" class="form-control" id="anReadCount" value="${anbvo.anReadCount}" readonly="readonly">
			</div>

	  
	  <button type="submit" class="btn btn-primary" id="regBtn">수정하기</button>
	 <a href="/anboard/list"><button type="button" class="btn btn-secondary">리스트로</button></a>
	</form>
	
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>