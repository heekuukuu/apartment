<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %> <!-- 공통 헤더 파일 포함 -->

<div class="container"> <!-- 메인 컨테이너 시작 -->
    <!-- 게시물 리스트를 보여주는 반복문 시작 -->
    <c:forEach var="boards" items="${boards.content}">
        <div class="card m-2"> <!-- 카드 스타일을 이용한 게시물 컨테이너 -->
            <div class="card-body"> <!-- 게시물 내용 컨테이너 -->
                <h4 class="card-title">${boards.title}</h4> <!-- 게시물 제목 -->
                <a href="/board/${boards.id}" class="btn btn-primary">Detail</a> <!-- 상세보기 버튼 -->
            </div>
        </div>
    </c:forEach>
    <!-- 게시물 리스트 반복문 끝 -->

    <!-- 페이지 네비게이션 바 -->
    <div class="pagination justify-con
    tent-center">
        <ul class="pagination">
            <c:choose> <!-- 페이지 네비게이션 조건문 시작 -->
                <c:when test="${boards.first}"> <!-- 첫 번째 페이지일 때 이전 버튼 비활성화 -->
                    <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
                </c:when>
                <c:otherwise> <!-- 첫 번째 페이지가 아닐 때 이전 버튼 활성화 -->
                    <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${boards.last}"> <!-- 마지막 페이지일 때 다음 버튼 비활성화 -->
                    <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
                </c:when>
                <c:otherwise> <!-- 마지막 페이지가 아닐 때 다음 버튼 활성화 -->
                    <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    <!-- 페이지 네비게이션 바 끝 -->
</div> <!-- 메인 컨테이너 끝 -->

<%@ include file="../layout/footer.jsp" %> <!-- 공통 푸터 파일 포함 -->