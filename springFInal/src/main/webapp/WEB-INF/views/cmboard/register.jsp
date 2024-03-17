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


	<div class="container-md">
	<br>
	<h2>고객 게시판 글 등록 페이지</h2>
	<br>
	
	<sec:authentication property="principal.mvo.email" var="authEmail"/>
	<sec:authentication property="principal.mvo.nickName" var="authNick"/>
	
	<form action="/cmboard/register" method="post" enctype="multipart/form-data">
		<div class="mb-3">
			<label for="cmCategory" class="form-label">카테고리</label> 
			<select name="cmCategory" class="form-select" id="inputGroupSelect02">
			    <option value="계정 관리 문의">계정 관리 문의</option>
			    <option value="게시판 이용 문의">게시판 이용 문의</option>
			    <option value="기타 문의">기타 문의</option>
			</select>
		</div>
		<div class="mb-3">
			<label for="cmTitle" class="form-label">제목</label> 
			<input type="text" class="form-control" name="cmTitle" id="cmTitle" placeholder="제목을 입력하시오">
		</div>
		<div class="mb-3">
			<label for="cmWriter" class="form-label">작성자</label>
			<input type="text" class="form-control" name="cmWriter" id="cmWriter" value="${authNick }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="cmContent" class="form-label">글 내용</label>
			<textarea class="form-control" name="cmContent" id="cmContent" rows="3" placeholder="내용을 입력하시오"></textarea>
		</div>

		<!-- file 입력 라인 추가 -->
		<div class="mb-3">
			<input type="file" class="form-control" name="files" id="files" multiple="multiple" style="display: none"> <br>
			<button type="button" class="btn btn-outline-primary" id="trigger">참고 사진 업로드</button>		
		</div>		
		
		<!-- 첨부파일 초기될 영역 -->
		<!-- 파일 목록 표시라인 -->
		<div class="mb-3" id="fileZone">
		
		</div>

		<button type="submit" class="btn btn-dark" id="regBtn">글 등록</button>


	</form>
	</div>

<script src="/resources/js/cmBoardRegister.js"></script>

<jsp:include page="../layout/footer.jsp"></jsp:include>






