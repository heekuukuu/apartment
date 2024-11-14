<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>내 아파트 정보</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* 커스텀 스타일 추가 */
        .card-header {
            background-color: #007bff;
            color: white;
        }
        .card-body {
            background-color: #f8f9fa;
        }
        .btn-custom {
            background-color: #007bff;
            color: white;
            border-radius: 5px;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }
        .section-title {
            color: #007bff;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <div class="card">
        <!-- 카드 헤더 -->
        <div class="card-header text-center">
            <h2>내 아파트 정보</h2>
        </div>
        <!-- 카드 본문 -->
        <div class="card-body">
            <div class="row mb-3">
                <div class="col-md-4">
                    <span class="section-title">아파트 이름:</span>
                </div>
                <div class="col-md-8">
                    <p>${apartment.name}</p>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-4">
                    <span class="section-title">아파트 주소:</span>
                </div>
                <div class="col-md-8">
                    <p>${apartment.address}</p>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-md-4">
                    <span class="section-title">이메일:</span>
                </div>
                <div class="col-md-8">
                    <p>${apartment.email}</p>
                </div>
            </div>

            <!-- 아파트 수정 버튼 -->
            <div class="text-center mt-4">
                <a href="/apartments/apartmentSearch" class="btn btn-custom">아파트 수정</a>
            </div>
             <!-- 홈 버튼 추가 -->
             <div class="text-center mt-3">
             <a href="/" class="btn btn-custom">홈</a>
             </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS, Popper.js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>