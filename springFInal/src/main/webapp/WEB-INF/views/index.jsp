<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>




<jsp:include page="layout/header.jsp"></jsp:include>
<jsp:include page="layout/nav.jsp"></jsp:include>

<h1> Hello world! </h1>
<br>

<P>The time on the server is ${serverTime}. </P>
<br>

<h2>계정에 따라 게시판 이용에 제한이 발생합니다.</h2>
<br>
<p>게시판 관리자는 일반 게시판, 익명 게시판, 회원관리 게시판, 회원정보창 이용 가능</p>
<p>일반 이용자는 일반 게시판, 익명 게시판, 개인정보관리창 이용 가능</p>
<p>블랙리스트 이용자는 익명 게시판, 개인정보관리창만 이용 가능</p>
<p>비회원의 경우 회원가입부터 진행해 주세요</p>


<br>
<hr>
<br>
<jsp:include page="layout/footer.jsp"></jsp:include>