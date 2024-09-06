<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
   <form>


       <input type="hidden" id="id" value="${principal.user.id}">
       <div class="form-group">
                <label for="username">User Name</label>
                <input type="username" value="${principal.user.username}" class="form-control"
                 placeholder="Enter username" id="username" readonly>
             </div>

               <c:if test="${principal.user.loginType eq 'GENERAL'}">
               <div class="form-group">
                   <div class="form-group">
                       <label for="password">Password</label>
                       <input type="password" class="form-control" placeholder="Enter password" id="password"
                       aria-describedby="resultPassword">
                       <p id="resultPassword">
                        Must be 8-20 charset long.
                        </p>
                       </div>

                 <div class="form-group">
                     <label for="email">Email</label>
                     <input type="email" value="${principal.user.email}" class="form-control" id="email"
                        aria-describedby="resultEmail">
                   <p id="resultEmail">
                         Must be (your email id)@(your email domain)
                   </p>
                   </div>
                  </c:if>

                </form>
                <button id="btn-update" class="btn btn-primary">Update</button>

              </div>

           <script src="/js/user.js"></script>
            <%@ include file="../layout/footer.jsp" %>