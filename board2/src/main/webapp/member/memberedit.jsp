<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, board.*, java.util.*"%>
<%! 
	String user = null;
%>
<%
	user = (String) session.getAttribute("user");
	
	String dir = request.getSession().getServletContext().getRealPath("./upload/users");

	Vector <MembersDTO> v = MemberDDL.getSelect(user);
	for(MembersDTO mb : v){
%>
<div class="container mb-5">
<h1 class="mt-5 registerform-h1 text-center bg-secondary">Board</h1>
	<form name="registerform" class="registerform" action="/board/MemberEdit" method="post" enctype="multipart/form-data">
		<input type="hidden" value="<%=user%>">
		<div class="mb-3 mt-3">
		 	<div class="row">
		 		<div class="col-md-12 text-center">
			 		<div class="register-user-img">
<%
		if(mb.getPhoto() == null){
%>			 		
						<img src="images/user-fill.png" alt="user" id="user-img">
<%
		}else{
%>
		 				<img src="/board/ShowMem" alt="user" id="user-img">
<%
		}
%>		 				
		 				<a href="javascript:selectImg();" class="user-photo-click text-center pt-2">
		 					<span class="text-white">편집</span>
		 					<input type="file" name="photo" id="photo" class="photo-upload" onchange="showFile()" />
		 				</a>
			 		</div>	
		 		</div>	
		 	</div>
		 </div>
		 <div class="mb-3 mt-0">
		 	<div class="row">
		 		<div class="col-md-12">
			 		<div class="input-group">
			 			<div class="input-group-prepand">
			 				<span class="input-group-text">아이디</span>
			 			</div>
			 			
			 			<input type="text" value="<%=mb.getUserid() %>" class="form-control" readonly id="userid" placeholder="아이디" name="userid" onfocusout="registerCheck(1);">
				 		
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
			 				<span class="input-group-text" id="basic-addon1">비밀번호</span>
			 			</div>
			 			<input type="password" value="" aria-describedby="basic-addon1" class="form-control input-pass" id="userpass" placeholder="비밀번호" name="userpass" onfocusout="registerCheck(2);">
			 			<i class="fa fa-eye-slash fa-lg passwordsee"></i>
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
			 				<span class="input-group-text">비밀번호 확인</span>
			 			</div>
		 				<input type="password" value="" class="form-control input-pass" id="reuserpass" placeholder="비밀번호 확인" name="reuserpass" onfocusout="registerCheck(3);">
		 				<i class="fa fa-eye-slash fa-lg passwordsee"></i>
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
			 				<span class="input-group-text">이름</span>
			 			</div>
			 			
			 			<input type="text" value="<%=mb.getUsername() %>" class="form-control" id="username" placeholder="이름" name="username">
				 				
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
			 				<span class="input-group-text">이메일</span>
			 			</div>
			 			
			 			<input type="text" value="<%=mb.getUseremail() %>" class="form-control" id="useremail" placeholder="이메일" name="useremail" onfocusout="registerCheck(4);">
				 		
   							
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
			 				<span class="input-group-text">주소</span>
			 			</div>
			 			
			 			<input type="number" value="<%=mb.getPostcode() %>" readonly class="form-control" id="postcode" placeholder="우편번호" name="postcode">
			 			<div class="input-group-append">
    						<button class="btn btn-primary" type="button" onclick="sDaumPostcode();">검색</button>
  						</div>
			 		</div>
			 	</div>	
		 	</div>
		 	<p class="warning-text"></p>
		 </div>
		 <div class="mb-3 mt-3">
		 	<div class="row">
		 		<div class="input-group">
			 	<div class="emptysp"></div>
			 		<input type="text" value="<%=mb.getAddr() %>" name="addr" readonly class="form-control" id="addr" placeholder="주소">
			 	</div>
		 	</div>
		 </div>
		 <div class="mb-3 mt-3">
		 	<div class="row">
		 		<div class="input-group">
			 	<div class="emptysp"></div>
			 		<input type="text" value="<%=mb.getDetailaddr() %>" name="detailaddr" class="form-control" id="detailaddr" placeholder="상세주소">
			 	</div>
		 	</div>
		 	<p class="warning-text"></p>
		 </div>
		 <input type="hidden" name="useridok" id="useridok">
		 <div class="mb-3 mt-3">
		 	<div class="row">
		 		<div class="col-md-12">
			 		<div class="input-group">
			 			<div class="input-group-prepand">
			 				<label class="input-group-text" for="tel1">전화번호</label>
			 			</div>
		 				<select name="tel1" class="tel-select" id="tel1">	 						 				
<%
	String[] telArr = {"010", "011", "016", "017", "018", "019"};
	String[] myTelArr = mb.getTel().split("-");
	int i = 0;
	for(String tel : telArr){
		if(myTelArr[0].equals(tel)){
			out.println("<option value=\"" + tel + "\" selected>" + tel + "</option>");
		}else{
			out.println("<option value=\"" + tel + "\">" + tel + "</option>");
		}
		i++;
	}
%>
		 				</select>
			 			<input type="number" value="<%=myTelArr[1] %>" class="form-control" id="tel2"  name="tel2">
			 			<input type="number" value="<%=myTelArr[2] %>" class="form-control" id="tel3"  name="tel3">
				 				
			 		</div>
			 	</div>	
			 	
		 	</div>
		 	<p class="warning-text"></p>
		 </div>
		 
		 <input type="hidden" name="tel" id="tel">
		 <div class="form-btn py-4 text-center">
		 
		 <button type="button" onclick="register2();" class="btn btn-primary ml-2">회원수정</button>
		 </div>
	</form>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</div>
<%}%>