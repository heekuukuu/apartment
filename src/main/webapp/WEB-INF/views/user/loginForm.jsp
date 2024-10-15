<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form action="/auth/loginProc" method="post">
    <div class="form-group">
       <label for="username">User Name</label>
       <input type="username" name="username" class="form-control" placeholder="Enter username"id="username">
    </div>

    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <c:if test="${not empty error}">

        <span>

         <p id="valid" class="alert alert-danger">
             ${exception}
              </p>
              </span>
             </c:if>
            <button id="btn-login" class="btn btn-primary">Sign In</button>
         </form>
         </div>
         <%@ include file="../layout/footer.jsp" %>