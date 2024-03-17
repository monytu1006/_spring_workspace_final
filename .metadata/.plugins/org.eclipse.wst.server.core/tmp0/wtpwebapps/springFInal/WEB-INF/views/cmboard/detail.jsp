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
	<h2> 고객 게시판 상세 페이지</h2>
	<br>
	
	<sec:authentication property="principal.mvo.email" var="authEmail"/>
	<sec:authentication property="principal.mvo.nickName" var="authNick"/>
	
	<c:set value="${bdto.cmbvo}" var="cmbvo" />
	<!-- <table class="table table-hover">
		<tbody> -->
			<div class="mb-3">
				<label for="cmBno" class="form-label">글 번호</label>
				<input type="text" name="cmBno" class="form-control" id="cmBno" value="${cmbvo.cmBno}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="cmCategory" class="form-label">카테고리 분류</label>
				<input type="text" name="cmCategory" class="form-control" id="cmCategory" value="${cmbvo.cmCategory}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="cmTitle" class="form-label">글 제목</label>
				<input type="text" name="cmTitle" class="form-control" id="cmTitle" value="${cmbvo.cmTitle}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="cmWriter" class="form-label">글 작성자</label>
				<input type="text" name="cmWriter" class="form-control" id="cmWriter" value="${cmbvo.cmWriter}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="cmRegAt" class="form-label">글 등록일</label>
				<input type="text" name="cmRegAt" class="form-control" id="cmRegAt" value="${cmbvo.cmRegAt}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="cmContent" class="form-label">글 내용</label>
				<input type="text" name="cmContent" class="form-control" id="cmContent" value="${cmbvo.cmContent}" readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="cmReadCount" class="form-label">조회수</label>
				<input type="text" name="cmReadCount" class="form-control" id="cmReadCount" value="${cmbvo.cmReadCount}" readonly="readonly">
			</div>
<!-- 		</tbody>
	</table> -->
	
	<hr>
	<hr>
	
	   <!-- 파일 표시 라인 -->

	   
	      <!-- file line -->	
   <c:set value="${bdto.flist}" var="flist"></c:set>
   <div class="col-12">
	   <label for="f" class="form-label">File 보여주기 공란</label>
	   <ul class="list-group list-group-flush">
	   	<c:forEach items="${flist}" var="cmfvo">
	   		<li class="list-group-item">
	   			<c:choose>
		   			<c:when test="${cmfvo.cmFileType==1}">
		   				<div>
		   					<img alt="" src="/upload/${cmfvo.cmSaveDir}/${cmfvo.cmUuid}_th_${cmfvo.cmFileName}">
		   				</div>
		   			</c:when>
		   			<c:otherwise>
		   				<div>
		   					<!-- 일반 파일 표시할 아이콘 -->
		   					<!-- 이미지 파일이 아닌 다른 종료의 파일 다운받기 -->
		   					<a href="/upload/${cmfvo.cmSaveDir}/${cmfvo.cmUuid}_${cmfvo.cmFileName}" download="${cmfvo.cmFileName}">
			   					<!-- <i class="bi bi-journal-arrow-down"></i>
			   					<i class="bi bi-archive-fill"></i> -->
		   					</a>
		   				</div>
		   			</c:otherwise>
	   			</c:choose>
	   			<div class="ms-2 me-auto">
	   				<div class="fw-bold">${cmfvo.cmFileName}</div>
	   				<span class="badge rounded-pill text-bg-secondary">${cmfvo.cmFileSize}Byte</span>
	   			</div>
	   		</li>
	   	</c:forEach>
	   	</ul>
	</div>
	
	<c:choose>
		<c:when test="${cmbvo.cmWriter == authNick }">
			<a href="/cmboard/modify?cmbno=${cmbvo.cmBno}"><button type="button" class="btn btn-primary">수정하기</button></a>
			<a href="/cmboard/remove?cmbno=${cmbvo.cmBno}"><button type="button" class="btn btn-success">삭제하기</button></a>
		</c:when>
	</c:choose>
	<a href="/cmboard/list"><button type="button" class="btn btn-secondary">리스트로</button></a>
</div>

	<!-- 댓글 등록 라인 -->
	<hr>
	<div class="input-group">
		<span class="input-group-text" id="cmtWriter" value="${authNick}">${authNick} </span>
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
	let cmBnoVal = "${cmbvo.cmBno}";
	console.log(cmBnoVal);
</script>

<script src="/resources/js/cmBoardComment.js"></script>

<script type="text/javascript">
	spreadCommentList(cmBnoVal);
</script>

<script>
    var authNick = "${authNick}";
</script>

<jsp:include page="../layout/footer.jsp"></jsp:include>


