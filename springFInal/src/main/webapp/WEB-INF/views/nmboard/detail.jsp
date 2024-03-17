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
	<h2> 일반 게시판 상세 페이지</h2>
	<br>
	
	<sec:authentication property="principal.mvo.email" var="authEmail"/>
	<sec:authentication property="principal.mvo.nickName" var="authNick"/>
	
	<c:set value="${bdto.nmbvo}" var="nmbvo" />
			<div class="mb-3">
				<label for="nmBno" class="form-label">글 번호</label>
				<input type="text" name="nmBno" class="form-control" id="nmBno" value="${nmbvo.nmBno}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="nmTitle" class="form-label">글 제목</label>
				<input type="text" name="nmTitle" class="form-control" id="nmTitle" value="${nmbvo.nmTitle}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="nmWriter" class="form-label">작성자</label>
				<input type="text" name="nmWriter" class="form-control" id="nmWriter" value="${nmbvo.nmWriter}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="nmRegAt" class="form-label">등록일</label>
				<input type="text" name="nmRegAt" class="form-control" id="nmRegAt" value="${nmbvo.nmRegAt}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="nmContent" class="form-label">본문</label>
				<input type="text" name="nmContent" class="form-control" id="nmContent" value="${nmbvo.nmContent}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="nmReadCount" class="form-label">조회수</label>
				<input type="text" name="nmReadCount" class="form-control" id="nmReadCount" value="${nmbvo.nmReadCount}" readonly="readonly">
			</div>
	<hr>
	<hr>
	      <!-- file line -->	
   <c:set value="${bdto.flist}" var="flist"></c:set>
   <div class="col-12">
	   <label for="f" class="form-label">File 보여주기 공란</label>
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
		   					<a href="/upload/${nmfvo.nmSaveDir}/${nmfvo.nmUuid}_${nmfvo.nmFileName}" download="${nmfvo.nmFileName}">
		   					</a>
		   				</div>
		   			</c:otherwise>
	   			</c:choose>
	   			<div class="ms-2 me-auto">
	   				<div class="fw-bold">${nmfvo.nmFileName}</div>
	   				<span class="badge rounded-pill text-bg-secondary">${nmfvo.nmFileSize}Byte</span>
	   			</div>
	   		</li>
	   	</c:forEach>
	   	</ul>
	</div>
	<c:choose>
		<c:when test="${nmbvo.nmWriter == authNick }">
			<a href="/nmboard/modify?nmbno=${nmbvo.nmBno}"><button type="button" class="btn btn-primary">수정하기</button></a>
			<a href="/nmboard/remove?nmbno=${nmbvo.nmBno}"><button type="button" class="btn btn-success">삭제하기</button></a>
		</c:when>
	</c:choose>
	<a href="/nmboard/list"><button type="button" class="btn btn-secondary">리스트로</button></a>
</div>

	<!-- 댓글 등록 라인 -->
	<hr>
	<div class="input-group">
		<span class="input-group-text" id="cmtWriter" value="${authNick }">${authNick } </span>
		<input type="text" id="cmtText" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" placeholder="댓글을 입력하세요">
		<button class="btn btn-outline-secondary" type="button" id="cmtPostBtn">댓글 등록</button>
	</div>
	<br>
	
   <!-- 댓글표시라인 -->
	<ul class="list-group list-group-flush" id="cmtListArea">
	  <li class="list-group-item">
	     <div class="mb-3">
	        <div class="fw-bold">Writer</div>
	        Content
	     </div>
	     <span class="badge rounded-pill text-bg-warning">modAt</span>
	  </li>
	</ul>
	
	   <!-- 댓글 더보기 버튼 -->
   <div>
      <button type="button" id="moreBtn" data-page="1" class="btn btn-outline-dark" style="visibility:hidden">More+</button>
   </div>

   <br>
   
	<!-- 모달창 라인 -->
	<div class="modal" id="myModal" tabindex="-1">
  		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h5 class="modal-title">Writer</h5>
        			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      			</div>
      			<div class="modal-body">
        			<div class="input-group mb-3">
        				<input type="text" class="form-control" id="cmtTextMod">
        				<button type="button" class="btn btn-primary" id="cmtModBtn">Post</button>
        			</div>
      			</div>
      			<div class="modal-footer">
        			<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      			</div>
    		</div>
  		</div>
	</div>
	
	
	
	
	
	
	

<script type="text/javascript">
	/* let nmBnoVal = `<c:out value="${bvo.nmBno}"/>`; */
	let nmBnoVal = "${nmbvo.nmBno}";
	console.log(nmBnoVal);
</script>

<script src="/resources/js/nmBoardComment.js"></script>

<script type="text/javascript">
	spreadCommentList(nmBnoVal);
</script>

<script>
    var authNick = "${authNick}";
</script>


<jsp:include page="../layout/footer.jsp"></jsp:include>


