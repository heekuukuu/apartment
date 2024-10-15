<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <h2>Social Login</h2>
    <p>Choose a social account to sign in:</p>
    <a href="<c:url value='/oauth2/authorization/google' />" class="btn btn-danger btn-block">Login with Google</a>
    <a href="<c:url value='/oauth2/authorization/kakao' />" class="btn btn-warning btn-block">Login with Kakao</a>
    <a href="<c:url value='/oauth2/authorization/naver' />" class="btn btn-success btn-block">Login with Naver</a>
</div>

<%@ include file="../layout/footer.jsp" %>