<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
   <form>
   <div class="form-group">
       <label for="username">User Name</label>
       <input type="username" class="form-control" placeholder="Enter username" id="username"
       aria-describedby="resultUsername" autocomplete="username">
       <p id="resultUsername">
          Must be length is more than 4
        </p>
       </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" placeholder="Enter password" id="password"
        aria-describedby="resultPassword"  autocomplete="current-password">
        <p id="resultPassword">
         Must be 8-20 charset long.
         </p>
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" placeholder="Enter email" id="email" name="email"
            aria-describedby="resultEmail" autocomplete="email">
            <p id="resultEmail" >
            Must be (your email id)@(your email domain)
            </p>
        </div>


 </form>
   <button id="btn-save" class="btn btn-primary">Sign Up</button>

</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>