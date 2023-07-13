<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.net.URLEncoder, java.security.SecureRandom, java.math.BigInteger"%>
<%!
	String message = null;
%>

<div class="container mb-3">
<h1 class="mt-5 text-center loginform-h1 bg-secondary">Board</h1>
	<form name="loginform" class="loginform" action="/board/LoginsOk" method="post">
		 <div class="mb-3 mt-0">
		 	<div class="row">
		 		<div class="col-md-12">
			 		<div class="input-group">
			 			<div class="input-group-prepand">
			 				<span class="input-group-text">아이디</span>
			 			</div>
			 			
			 			<input type="text" class="form-control" id="userid" placeholder="아이디" name="userid">
			 		</div>
			 	</div>	
		 	</div>
		 	<p class="warning-text"></p>
		 </div>
		 
		 <div class="mb-3 mt-3">
		 	<div class="row">
		 		<div class="col-md-12">
			 		<div class="input-group">
			 			<div class="input-group-prepand">
			 				<span class="input-group-text">비밀번호</span>
			 			</div>
			 			<input type="password" class="form-control input-pass" id="userpass" placeholder="비밀번호" name="userpass">
			 			<i class="fa fa-eye-slash fa-lg passwordsee" id="passwordsee"></i>
			 		</div>
			 	</div>	
		 	</div>
		 	<p class="warning-text"></p>
		 </div>
		 <div class="mb-3 mt-3">
		 	<div class="row">
		 		<div class="offset-md-8 col-md-4 text-end">
		 			<label class="login-btn"><input type="checkbox" name="huid" value="ok" onclick="isChecked();"> 아이디 저장</label>
		 			<p class="warning-public"></p>
		 		</div>
		 	</div>
		 </div>
		 <div class="mb-3 mt-3">
		 	<div class="row justify-content-center align-items-center">
		 		<div class="col-md-12">
		 			<button type="button" class="btn btn-primary login-btn" onclick="loginSubmit();">로그인</button>
	 			</div>
		 	</div>
		 </div>
		 
		 <div class="mb-3 mt-3">
		 	<div class="row">
		 		<div class="offset-md-5 col-md-7 text-end">
		 			<a href="index.jsp?fname=member/findMember" class="me-3 login-btn">아이디/패스워드 찾기</a>
		 			<a href="index.jsp?fname=member/register" class="login-btn">회원가입</a>
		 		</div>
		 	</div>
		 </div>
	</form>
</div>
<div class="wh600 mb-5">
 	<div class="row">
 		<div class="col-sm-4 mb-3">
<%
    String clientId = "vAA18rocBL8pJBmrDGXD";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://localhost:8080/board/NaverOauth", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=" + clientId;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&state=" + state;
    session.setAttribute("state", state);
 %>
	 		
	 			<a href="<%=apiURL%>" class="btn btn-block naver-btn">
	 				<img alt="naverlogin" src="images/naverloginbtn.png">
 				</a>
	 		
 		</div>
	 	<div class="col-sm-4 mb-3">
	 		
	 			<a href="javascript:loginWithKakao()" class="btn btn-block kakao-btn">
	 				<img alt="kakaologin" src="images/kakaologinbtn.png">
	 			</a>
	 		
	 	</div>
	 	 
	 	<div class="col-sm-4 mb-3">
    	<div id="buttonDiv"></div>
	 	
	 			<!-- <a href="javascript:loginWithGoogle()" class="btn btn-block google-btn">
	 				<img alt="googlelogin" src="images/googleloginbtn.png">
	 			</a>  -->
	 		
	 	</div>
 	</div>
 </div>
 
 <div class="login-fail">
 	<p>
 		입력정보와 일치하는 회원정보가 없습니다.<br>정보를 다시 입력하시거나 <br> "아이디/패스워드 찾기" 서비스를 이용해주세요.
	</p>
 	<h6 class="bg-secondary"><a href="javascript:loginFailClose()">확인</a></h6>
 </div>
<%
	message = (String) request.getAttribute("message");
	if(message != null){
%>
	<script src="js/hidden.js"></script>
<%
	}
%>

    
  