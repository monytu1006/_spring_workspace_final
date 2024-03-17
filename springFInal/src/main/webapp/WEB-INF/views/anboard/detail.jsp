<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
	
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container-md">
	<br>
	<h2> 익명 게시판 상세 페이지</h2>
	<br>
	
	<sec:authentication property="principal.mvo.email" var="authEmail"/>
	<sec:authentication property="principal.mvo.nickName" var="authNick"/>
	
	<c:set value="${anbvo}" var="anbvo" />
			<div class="mb-3">
				<label for="anBno" class="form-label">글 번호</label>
				<input type="text" name="anBno" class="form-control" id="anBno" value="${anbvo.anBno}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="anTitle" class="form-label">제목</label>
				<input type="text" name="anTitle" class="form-control" id="anTitle" value="${anbvo.anTitle}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="anRegAt" class="form-label">등록일</label>
				<input type="text" name="anRegAt" class="form-control" id="anRegAt" value="${anbvo.anRegAt}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="anContent" class="form-label">글 내용</label>
				<input type="text" name="anContent" class="form-control" id="anContent" value="${anbvo.anContent}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="anReadCount" class="form-label">조회수</label>
				<input type="text" name="anReadCount" class="form-control" id="anReadCount" value="${anbvo.anReadCount}" readonly="readonly">
			</div>
	
	<hr>
	<hr>
	
	<a href="/anboard/list"><button type="button" class="btn btn-secondary">리스트로</button></a>
</div>


<jsp:include page="../layout/footer.jsp"></jsp:include>


