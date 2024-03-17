<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container-md">
	<br>
	<h2>익명 게시판 목록</h2>
	<br>
	
		<!-- 검색라인 -->
	<div>
		<form action="/anboard/list" method="get">
		<div class="input-group mb-3">
			<select name="type" class="form-select" id="inputGroupSelect02">
				<option ${ph.pgvo.type==null ? 'selected' : ''}>Choose...</option>
					<option value="t" ${ph.pgvo.type eq 't' ? 'selected' : ''} >제목</option>
					<option value="c" ${ph.pgvo.type eq 'c' ? 'selected' : ''}>글 내용</option>
					<option value="tc" ${ph.pgvo.type eq 'tc' ? 'selected' : ''}>제목&글 내용</option>
			</select>
			
			<input type="text" class="form-control" name="keyword" value="${ph.pgvo.keyword }" placeholder="Search...">
			<input type="hidden" name="pageNo" value="1">
			<input type="hidden" name="qty" value="${ph.pgvo.qty}">

			<button type="submit" class="btn btn-primary position-relative">찾기
			    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
				    ${ph.totalCount}
			    <span class="visually-hidden"></span>
			  </span>
			</button>
		</div>
		</form>
	</div>

	<!-- 본문  -->
		<table class="table table-hover">
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">제목</th>
		      <th scope="col">내용</th>
		      <th scope="col">작성일</th>
		      <th scope="col">조회수</th>
		    </tr>
		  </thead>
		  
		  <tbody>
			<c:forEach items="${list}" var="anbvo">
			    <tr>
			        <th scope="row">${anbvo.anBno}</th>
			        <td><a href="/anboard/detail?anbno=${anbvo.anBno }">${anbvo.anTitle}</a></td>
			        <td>${anbvo.anContent}</td>
			        <td>${anbvo.anRegAt}</td>
			        <td>${anbvo.anReadCount}</td>
			        
			    </tr>
			</c:forEach>	
			
		  </tbody>
		  
		</table>
		<a href="/anboard/register" ><button type="submit" class="btn btn-primary" id="regBtn">등록하기</button></a>
		
		
<!-- 페이지 네이션 -->

 <nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
 
  <!-- 이전 -->
   <c:if test="${ph.prev }">
    <li class="page-item">
      <a class="page-link" href="/anboard/list?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}
      &type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
  </c:if>
  
	<!-- 페이지 번호 -->    
 	<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
    <li class="page-item">
    	<a class="page-link" href="/anboard/list?pageNo=${i}&qty=${ph.pgvo.qty}
    	&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a>
    </li>
	
	</c:forEach>

	<!-- 다음 -->
 	<c:if test="${ph.next }">
	    <li class="page-item">
	      <a class="page-link" href="/anboard/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}
	      &type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	</c:if>

  	<li class="page-item"><a class="page-link" href="/anboard/list">전체보기</a>
  </ul>
</nav>

</div>






