
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %> <!-- 공통 헤더 파일 포함 -->

<div class="container"> <!-- 메인 컨테이너 시작 -->
    <button class="btn btn-secondary" onclick="history.back()">Back</button> <!-- 이전 페이지로 돌아가는 버튼 -->
    <c:if test="${boards.user.id == principal.user.id}"> <!-- 현재 로그인한 사용자와 게시물 작성자가 동일할 때 수정, 삭제 버튼을 보여줌 -->
        <a href="/board/${boards.id}/updateForm" class="btn btn-warning">Modify</a> <!-- 수정 버튼 -->
        <button onclick="index.deleteById(${boards.id})" class="btn btn-danger">Delete</button> <!-- 삭제 버튼 -->
    </c:if>

    <br><br>
    <!-- 게시물 세부 정보 출력 -->
    <div>
        Post ID : <span id="id"><i>${boards.id}</i></span>
        Post Writer : <span><i>${boards.user.username} </i></span> <!-- 작성자 정보 -->
    </div>
   <br>
    <div class="form-group">
        <h3>${boards.title}</h3> <!-- 게시물 제목 -->
    </div>
    <div class="form-group">
        <span class="label label-info">${boards.category}</span> <!-- 게시물 카테고리 -->
    </div>
    <div class="form-group">
        <div>${boards.content}</div> <!-- 게시물 내용 -->
    </div>
</div> <!-- 메인 컨테이너 끝 -->

<script src="/board.js"></script> <!-- JavaScript 파일 포함 -->
<%@ include file="../layout/footer.jsp" %> <!-- 공통 푸터 파일 포함 -->