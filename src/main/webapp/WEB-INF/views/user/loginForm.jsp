<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <h2>Login</h2>
    <form action="/auth/loginProc" method="post">
        <div class="form-group">
            <label for="username">User Name</label>
            <input type="text" name="username" class="form-control" placeholder="Enter username" id="username" required>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password" required>
        </div>

        <c:if test="${not empty error}">
            <span>
                <p id="valid" class="alert alert-danger">${exception}</p>
            </span>
        </c:if>

        <button id="btn-login" class="btn btn-primary">Sign In</button>
    </form>

    <hr>

    <!-- 소셜 로그인 옵션 -->
    <p>Or sign in using a social account:</p>
    <div class="social-login">
        <a href="<c:url value='/oauth2/authorization/google' />" class="btn btn-danger">Login with Google</a>
        <a href="<c:url value='/oauth2/authorization/kakao' />" class="btn btn-warning">Login with Kakao</a>
        <a href="<c:url value='/oauth2/authorization/naver' />" class="btn btn-success">Login with Naver</a>
    </div>
</div>

<%@ include file="../layout/footer.jsp" %>