<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>사용자 프로필</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="card">
        <div class="card-header bg-primary text-white">
            <h2 class="text-center">프로필 정보</h2>
        </div>
        <div class="card-body">
            <div>
                <strong>Email:</strong> ${email}
            </div>
            <c:choose>
                <c:when test="${not empty apartmentName}">
                    <div>
                        <strong>Apartment:</strong> ${apartmentName}
                    </div>
                    <div>
                        <strong>Address:</strong> ${apartmentAddress}
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="row mb-3">
                        <div class="col-md-4"><strong>Apartment:</strong></div>
                        <div class="col-md-8">아파트 정보 없음</div>
                    </div>
                </c:otherwise>
            </c:choose>

            <!-- 삭제 버튼 추가 -->
            <form action="/deleteUser" method="post">
                <input type="hidden" name="email" value="${email}">
                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-danger">삭제</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>