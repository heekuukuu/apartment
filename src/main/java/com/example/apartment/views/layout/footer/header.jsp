<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
  <sec:authentication property="principal" var="principal"/>

</sec:authorize>
<!DOCTYPE html>
<html lang ="en">
<head>
    <title>Bord Project</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">


</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.jss"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
 <body>

  <nav class="navbar" navbar-expand-md bg-dark navbar-dark>
       <a class="navbar-brand" href="/"> Home</a>
       <button  class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
          <span class="navbar-toggler-icon"></span>

  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
     <c:choose>
       <c:when test="${empty principal}">
          <ul class="navbar-nav">
            <li class="nav-item">
               <a class="nav-link" href="/auth/loginForm">Sign In</a>
               </li>
                <li class="nav-item">
                 <a class="nav-link" href="/auth/joinForm">Sign Up</a>
                 </li>
               </ul>
               </c:when>
               <c:otherwise>
                  <ul class="navbar-nav">
                   <li class="nav-item">
                     <a class="nav-link" href="/board/saveForm">Write</a>
                   </li>
                 <li class="nav-item">
                  <a class="nav-link" href="/user/updateForm">Info</a>
                   </li>
                   <li class="nav-item">
                   <a class="nav-link" href="/logout">Sign Out</a>
                   </li>
                </ul>

      </c:otherwise>
      </c:choose>
      </div>
      </nav>
      <br/>
      </body>

</html>