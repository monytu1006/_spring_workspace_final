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
	<h2>익명게시판 등록 페이지</h2>
	<br>
	
	
	<form action="/anboard/register" method="post" enctype="multipart/form-data">
		<div class="mb-3">
			<label for="anTitle" class="form-label">제목</label> 
			<input type="text" class="form-control" name="anTitle" id="anTitle" placeholder="제목을 입력하시오">
		</div>
		<div class="mb-3">
			<label for="anContent" class="form-label">글 내용</label>
			<textarea class="form-control" name="anContent" id="anContent" rows="3" placeholder="내용을 입력하시오"></textarea>
		</div>

		<button type="submit" class="btn btn-dark" id="regBtn">글 등록</button>


	</form>
	</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>






