<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bord Project</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>

<body>
    <!-- 네비게이션 바 -->
    <nav class="navbar navbar-expand-md bg-dark navbar-dark">
        <a class="navbar-brand" href="/"> Home</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
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
                            <a class="nav-link" href="/auth/updateForm">Info</a>
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

    <!-- 메인 콘텐츠 -->
    <div class="container">
        <!-- 게시물 리스트를 보여주는 반복문 시작 -->
        <c:forEach var="board" items="${boards.content}">
            <div class="card m-2">
                <div class="card-body">
                    <h4 class="card-title">${board.title}</h4>
                    <a href="/board/${board.id}" class="btn btn-primary">Detail</a>
                </div>
            </div>
        </c:forEach>
        <!-- 게시물 리스트 반복문 끝 -->

        <!-- 페이지 네비게이션 바 -->
        <div class="pagination justify-content-center">
            <ul class="pagination">
                <c:choose>
                    <c:when test="${boards.first}">
                        <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
                    </c:otherwise>
                </c:choose>

                <!-- 페이지 번호 표시 -->
                <c:forEach var="pageNum" begin="0" end="${boards.totalPages - 1}">
                    <li class="page-item ${boards.number == pageNum ? 'active' : ''}">
                        <a class="page-link" href="?page=${pageNum}">${pageNum + 1}</a>
                    </li>
                </c:forEach>

                <c:choose>
                    <c:when test="${boards.last}">
                        <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
        <!-- 페이지 네비게이션 바 끝 -->
    </div>

    <!-- 하단 콘텐츠 -->
    <div class="jumbotron text-center" style="margin-bottom:0">
        <p>apartment community site</p>
        <p>Create By heekuu : 오류사항이있다면 하단의 이메일로 부탁드립니다.</p>
        <p>&#9992; Email: ja04261@naver.com</p>
    </div>
</body>
</html>
