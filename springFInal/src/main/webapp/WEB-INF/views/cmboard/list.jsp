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
	<h2>고객 문의 게시판</h2>
	<br>
	
		<!-- 검색라인 -->
	<div>
		<form action="/cmboard/list" method="get">
		<div class="input-group mb-3">
			<select name="type" class="form-select" id="inputGroupSelect02">
				<option ${ph.pgvo.type==null ? 'selected' : ''}>Choose...</option>
					<option value="g" ${ph.pgvo.type eq 'g' ? 'selected' : ''} >카테고리</option>
					<option value="t" ${ph.pgvo.type eq 't' ? 'selected' : ''} >제목</option>
					<option value="w" ${ph.pgvo.type eq 'w' ? 'selected' : ''}>작성자</option>
					<option value="c" ${ph.pgvo.type eq 'c' ? 'selected' : ''}>글 내용</option>
					<option value="tc" ${ph.pgvo.type eq 'tc' ? 'selected' : ''}>제목&글 내용</option>
					<option value="tw" ${ph.pgvo.type eq 'tw' ? 'selected' : ''}>제목&작성자</option>
					<option value="wc" ${ph.pgvo.type eq 'wc' ? 'selected' : ''}>작성자&글 내용</option>
					<option value="gtwc" ${ph.pgvo.type eq 'gtwc' ? 'selected' : ''}>All</option>
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
		      <th scope="col">카테고리</th>
		      <th scope="col">제목</th>
		      <th scope="col">작성자</th>
		      <th scope="col">작성일</th>
		      <th scope="col">조회수</th>
		      <th scope="col">댓글수</th>
		      <th scope="col">첨부파일수</th>
		    </tr>
		  </thead>
		  
		  <tbody>
			<c:forEach items="${list}" var="cmbvo">
			    <tr>
			        <th scope="row">${cmbvo.cmBno}</th>
			        <th scope="row">${cmbvo.cmCategory}</th>
			        <td><a href="/cmboard/detail?cmbno=${cmbvo.cmBno }">${cmbvo.cmTitle}</a></td>
			        <td>${cmbvo.cmWriter}</td>
			        <td>${cmbvo.cmRegAt}</td>
			        <td>${cmbvo.cmReadCount}</td>
			        <td>${cmbvo.cmCmtQty}</td>
			        <td>${cmbvo.cmHasFile}</td>
			        
			    </tr>
			</c:forEach>	
			
		  </tbody>
		  
		</table>
		<a href="/cmboard/register" ><button type="submit" class="btn btn-primary" id="regBtn">등록하기</button></a>
		
		
<!-- 페이지 네이션 -->

 <nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
 
  <!-- 이전 -->
   <c:if test="${ph.prev }">
    <li class="page-item">
      <a class="page-link" href="/cmboard/list?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}
      &type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
  </c:if>
  
	<!-- 페이지 번호 -->    
 	<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
    <li class="page-item">
    	<a class="page-link" href="/cmboard/list?pageNo=${i}&qty=${ph.pgvo.qty}
    	&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a>
    </li>
	
	</c:forEach>

	<!-- 다음 -->
 	<c:if test="${ph.next }">
	    <li class="page-item">
	      <a class="page-link" href="/cmboard/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}
	      &type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	</c:if>

  	<li class="page-item"><a class="page-link" href="/cmboard/list">전체보기</a>
  </ul>
</nav>

</div>






