
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
      <button class="btn btn-primary" onclick ="history.back()">Back</button>
      <c:if test="{boards.user.id == principal.user.id}">
          <a href="/board/${boards.id}updateForm" class= "btn btn-warning">Modify</a>
          <button onclick="index.deleteById(${boards.id})" class="btn btn-danger">Delete</button>
          </c:if>

          <br><br>
          <div>
             Post ID = <span id="id"><i>${boards.id} </i></span>
             Post Writer = <span><i>${boards.user.username} </i></span>
             </div>
             <br>

<div class="form-group">
     <h3>${boards.title}</h3>
     </div>
<div class="form-group">
   <span class="label label-info">${boards.category}</span>
  </div>
  <hr>
  <div class="form-group">
    <div>${boards.content}</div>
    </div>
    <div class="card">
       <form>
        <input type="hidden" id ="userId" value="${principal.user.id}">
         <input type="hidden" id="boardId" value="${boards.id}">
         <div class="form-group">
           <textarea id="comment-content" class="form-control" rows="1"></textarea>
           </div>
           <div class="card-footer">
            <button type="button" id="btn-comment-save" class="btn btn-primary">Save</button>
             </div>
             </form>
             </div>
             <br>
             <div class="card">
              <div class="card-header">Reply List</div>
              <ul id="comment-box" class="list-group">
              <c:forEach var ="comment" items="${boards.comments}">
              <li id="comment-${comment.id}" class="list-group-item d flex justify-content-between">
              <div>${comment.content}</div>
              <div class="font-italic">Writer : ${comment.user.username} </div><br>
                <c:if test="${principal.user.username eq comment.user.username}">
                <button onClick="index.deleteComment(${boards.id}, ${comment.id})" class="badge">Remove</button>
              </c:if>
              </li>
              </c:forEach>
              </ul>
              </div>
              <hr>
           </div>
           <script src="/js/board.js"></script>
           <%@ include file="../layout/footer.jsp" %>