<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Apartment</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <!-- 네비게이션 바 -->
    <nav class="navbar navbar-expand-md bg-dark navbar-dark">
        <a class="navbar-brand" href="/">Home</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/apartments/apartmentSearch">Apartment Search</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/apartments/myApartment">My Apartment</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container mt-4">
        <h2>My Apartment</h2>
        <div class="card mt-3">
            <div class="card-header bg-primary text-white">
                <h5 class="mb-0">Apartment Details</h5>
            </div>
            <div class="card-body">
                <!-- 아파트 정보 출력 -->
                <c:if test="${apartment != null}">
                    <p><strong>Name:</strong> ${apartment.name}</p>
                    <p><strong>Address:</strong> ${apartment.address}</p>
                    <p><strong>Residents:</strong> ${apartment.users.size()} users</p>
                </c:if>

                <!-- 아파트 정보가 없을 때 -->
                <c:if test="${apartment == null}">
                    <p>You have not selected any apartment yet.</p>
                    <a href="/apartments/apartmentSearch" class="btn btn-primary">Search for Apartments</a>
                </c:if>
            </div>
        </div>
    </div>

    <!-- 하단 콘텐츠 -->
    <div class="jumbotron text-center mt-5 mb-0">
        <p>Apartment community site</p>
        <p>Create By heekuu : 오류사항이 있다면 하단의 이메일로 부탁드립니다.</p>
        <p>&#9992; Email: ja04261@naver.com</p>
    </div>
</body>
</html>