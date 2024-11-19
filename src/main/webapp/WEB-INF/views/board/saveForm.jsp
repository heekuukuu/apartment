
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>  <!-- 공통 헤더 파일 포함 -->

<div class="container"> <!-- 메인 컨테이너 시작 -->
   <form>
         <div class="form-group">
            <label for="title">Title</label> <!-- 제목 입력 라벨 -->
            <input type="text" class="form-control" id="title" name="title" placeholder="Enter title"> <!-- 제목 입력 -->
        </div>

        <!-- 카테고리 선택 필드 -->
        <div class="form-group">
            <label class="mr-sm-2" for="category">Category</label> <!-- 카테고리 선택 라벨 -->
            <select class="custom-select mr-sm-2" id="category" name="category"> <!-- 카테고리 선택 박스 -->
                <option selected>Choose Category</option> <!-- 기본 선택 옵션 -->
                <option value="Tech">Tech</option> <!-- Tech 카테고리 -->
                <option value="communication">커뮤니케이션</option> <!-- communication 카테고리 -->
                <option value="notice">공지사항</option> <!-- notice 카테고리 -->
            </select>
        </div>

        <!-- 내용 입력 필드 -->
        <div class="form-group">
            <textarea class="form-control summernote" rows="5" id="content" name="content"></textarea> <!-- 게시물 내용 입력 -->
        </div>
         </form>
         <button id="btn-save" class="btn btn-primary">Save</button> <!-- 게시물 저장 버튼 -->





</div> <!-- 메인 컨테이너 끝 -->

<!-- Summernote 에디터 초기화 스크립트 -->
<script>

    $('.summernote').summernote({ // Summernote 초기화
        height: 300,  // 에디터 높이 설정
        minHeight: null,  // 최소 높이 설정
        maxHeight: null,  // 최대 높이 설정
        focus: true,  // 에디터 포커스 자동 설정
        lang: "ko-KR",  // 에디터 언어 설정
        placeholder: '최대 2048자까지 쓸 수 있습니다.'  // placeholder 설정
    });

  });
</script>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %> <!-- 공통 푸터 파일 포함 -->