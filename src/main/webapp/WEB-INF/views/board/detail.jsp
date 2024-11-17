<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-5">
    <!-- Back Button -->
    <button class="btn btn-outline-secondary" onclick="history.back()">Back</button>

    <!-- Post Information -->
    <div class="card mt-3">
        <div class="card-header">
            <div class="d-flex justify-content-between">
                <span>Post ID = <b>${boards.id}</b></span>
                <span>Posted by: <b>${boards.user.username}</b></span>
            </div>
        </div>
        <div class="card-body">
            <!-- Category above the title -->
            <span class="badge badge-info mb-3">${boards.category}</span> <!-- Category displayed above the title -->
            <h3 class="card-title">${boards.title}</h3>
            <p class="card-text">${boards.content}</p>

            <!-- Modify & Delete Buttons (only visible if user is the author) -->
            <c:if test="${boards.user.id == principal.user.id}">
                <a href="/board/${boards.id}/updateForm" class="btn btn-warning btn-sm ml-2">Modify</a>
                <button onclick="index.deleteById(${boards.id})" class="btn btn-danger btn-sm ml-2">Delete</button>
            </c:if>

            <!-- Admin Delete Button (Visible only to Admin) -->
            <c:if test="${principal.user.role == 'ADMIN'}">
                <button onclick="index.deleteById(${boards.id})" class="btn btn-danger btn-sm ml-2">Delete Post (Admin)</button>
            </c:if>
        </div>
    </div>

    <!-- Comment Form -->
    <div class="card mt-3">
        <div class="card-body">
            <form>
                <input type="hidden" id="userId" value="${principal.user.id}">
                <input type="hidden" id="boardId" value="${boards.id}">
                <div class="form-group">
                    <textarea id="comment-content" class="form-control" rows="2" placeholder="Write your comment here..."></textarea>
                </div>
                <div class="text-right">
                    <button type="button" id="btn-comment-save" class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Comment List -->
    <div class="card mt-3">
        <div class="card-header">Replies</div>
        <ul id="comment-box" class="list-group list-group-flush">
            <c:forEach var="comment" items="${boards.comments}">
                <li id="comment-${comment.id}" class="list-group-item d-flex justify-content-between align-items-center">
                    <div>
                        <p>${comment.content}</p>
                        <div class="font-italic">Writer: ${comment.user.username}</div>
                    </div>
                    <!-- Remove Button for Comment Author Only -->
                    <c:if test="${principal.user.username eq comment.user.username}">
                        <button onClick="index.deleteComment(${boards.id}, ${comment.id})" class="badge badge-danger">Remove</button>
                    </c:if>

                    <!-- Admin Remove Button (Visible only to Admin) -->
                    <c:if test="${principal.user.role == 'ADMIN'}">
                        <button onClick="index.deleteComment(${boards.id}, ${comment.id})" class="badge badge-danger">Remove Comment (Admin)</button>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<script src="/js/board.js"></script>