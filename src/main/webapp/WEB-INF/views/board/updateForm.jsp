<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %> <!-- 공통 헤더 파일 포함 -->

<div class="container"> <!-- 메인 컨테이너 시작 -->
    <form> <!-- 게시물 수정 폼 -->
        <!-- 게시물 ID (숨김) -->
        <input type="hidden" id="id" value="${boards.id}">

        <!-- 제목 입력 필드 -->
        <div class="form-group">
            <input value="${boards.title}" type="text" name="title" class="form-control" placeholder="Enter title" id="title"> <!-- 기존 제목을 표시 -->
        </div>

        <!-- 카테고리 선택 필드 -->
        <div class="form-group">
            <label class="mr-sm-2" for="selectCategory">Category</label> <!-- 카테고리 라벨 -->
            <select class="custom-select mr-sm-2" id="selectCategory"> <!-- 카테고리 선택 박스 -->
                <option selected>${boards.category}</option> <!-- 기존 카테고리를 기본 선택 -->
            </select>
        </div>

        <!-- 내용 입력 필드 -->
        <div class="form-group">
            <textarea class="form-control summernote" rows="5" id="content">${boards.content}</textarea> <!-- 게시물 내용 입력 -->
        </div>
    </form>

    <!-- 저장 버튼 -->
    <button id="btn-update" class="btn btn-primary">Save</button> <!-- 게시물 저장 버튼 -->
</div> <!-- 메인 컨테이너 끝 -->

<!-- Summernote 에디터 초기화 스크립트 -->
<script>
    $('.summernote').summernote({ // Summernote 초기화
        height: 300,  // 에디터 높이 설정
        minHeight: null,  // 최소 높이 설정
        maxHeight: null,  // 최대 높이 설정
        focus: true,  // 에디터 포커스 자동 설정
        lang: "ko-KR",  // 에디터 언어 설정
        placeholder: '${boards.content}'  // placeholder 설정
    });
</script>

<%@ include file="../layout/footer.jsp" %> <!-- 공통 푸터 파일 포함 -->