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
	<h2>일반 게시판 수정 페이지</h2>
	<br>
	
	<c:set value="${bdto.nmbvo}" var="nmbvo" />
	<form action="/nmboard/modify" method="post" enctype="multipart/form-data">
			<div class="mb-3">
				<label for="nmBno" class="form-label">글번호</label>
				<input type="text" name="nmBno" class="form-control" id="nmBno" value="${nmbvo.nmBno}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="nmTitle" class="form-label">글 제목</label>
				<input type="text" name="nmTitle" class="form-control" id="nmTitle" value="${nmbvo.nmTitle}">
			</div>
			<div class="mb-3">
				<label for="nmWriter" class="form-label">작성자</label>
				<input type="text" name="nmWriter" class="form-control" id="nmWriter" value="${nmbvo.nmWriter}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="nmContent" class="form-label">글 내용</label>
				<input type="text" name="nmContent" class="form-control" id="nmContent" value="${nmbvo.nmContent}">
			</div>
			<div class="mb-3">
				<label for="nmRegAt" class="form-label">등록일</label>
				<input type="text" name="nmRegAt" class="form-control" id="nmRegAt" value="${nmbvo.nmRegAt}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="nmReadCount" class="form-label">조회수</label>
				<input type="text" name="nmReadCount" class="form-control" id="nmReadCount" value="${nmbvo.nmReadCount}" readonly="readonly">
			</div>
			
			
	   
	      <!-- file line -->	
   <c:set value="${bdto.flist}" var="flist"></c:set>
   <div class="col-12">
	   <label for="f" class="form-label"></label>
	   <ul class="list-group list-group-flush">
	   	<c:forEach items="${flist}" var="nmfvo">
	   		<li class="list-group-item">
	   			<c:choose>
		   			<c:when test="${nmfvo.nmFileType==1}">
		   				<div>
		   					<img alt="" src="/upload/${nmfvo.nmSaveDir}/${nmfvo.nmUuid}_th_${nmfvo.nmFileName}">
		   				</div>
		   			</c:when>
                    <c:otherwise>
                       <div>
                          <!-- 일반 파일 표시할 아이콘 -->
                          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-journal-arrow-down" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M8 5a.5.5 0 0 1 .5.5v3.793l1.146-1.147a.5.5 0 0 1 .708.708l-2 2a.5.5 0 0 1-.708 0l-2-2a.5.5 0 1 1 .708-.708L7.5 9.293V5.5A.5.5 0 0 1 8 5z"/>
  <path d="M3 0h10a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2v-1h1v1a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H3a1 1 0 0 0-1 1v1H1V2a2 2 0 0 1 2-2z"/>
  <path d="M1 5v-.5a.5.5 0 0 1 1 0V5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0V8h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0v.5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1z"/>
</svg>
                       </div>
                    </c:otherwise>
	   			</c:choose>
	   			<div class="ms-2 me-auto">
	   				<div class="fw-bold">${nmfvo.nmFileName}</div>
	   				<span class="badge rounded-pill text-bg-secondary">${nmfvo.nmFileSize}Byte</span>
	   				<button type="button" data-uuid="${nmfvo.nmUuid }" class="btn btn-sm btn-outline-danger file-x">x</button>
	   			</div>
	   		</li>
	   	</c:forEach>
	   	</ul>
	</div>
	
	   <!-- 파일 등록 라인 -->
   <div class="mb-3">
      <input type="file" name="files" class="form-control" id="files" multiple style="display: none"> <br>
      <button type="button" class="btn btn-primary" id="trigger">파일 업로드</button>
   </div> 
   <!-- 파일 목록 표시라인 -->
    <div class="mb-3" id="fileZone"></div> 
    
	  
	  <button type="submit" class="btn btn-primary" id="regBtn">수정하기</button>
	  <%-- <a href="/board/remove?bno=${bvo.bno }"><button type="button" class="btn btn-danger">delete</button></a> --%>
	 <a href="/nmboard/list"><button type="button" class="btn btn-secondary">리스트로</button></a>
	</form>
	
</div>

<script src="/resources/js/nmBoardRegister.js"></script>
<script src="/resources/js/nmBoardModify.js"></script>

<jsp:include page="../layout/footer.jsp"></jsp:include>