<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*, java.util.*"%>
<%! 
	String snsUser = null;
%>
<%
	snsUser = (String) session.getAttribute("snsUser");
	Vector <MembersDTO> v = MemberDDL.getSelectSnsUser(snsUser);
	for(MembersDTO mb : v){
		String userid = mb.getUserid();
		if(userid == null){
			userid = "";
		}
		String userpass = mb.getUserpass();
		if(userpass == null){
			userpass = "";
		}
		String username = mb.getUsername();
		if(username == null){
			username = "";
		}
		String useremail = mb.getUseremail();
		if(useremail == null){
			useremail = "";
		}
		int postcode = mb.getPostcode();
		String addr = mb.getAddr();
		if(addr == null){
			addr = "";
		}
		String detailAddr = mb.getDetailaddr();
		if(detailAddr == null){
			detailAddr = "";
		}
		String tels = mb.getTel();
		if(tels == null){
			tels = "";
		}
		
		
%>
<div class="container mb-5">
<h1 class="mt-5 registerform-h1 text-center bg-secondary">Board</h1>
	<form name="registerform" class="registerform" action="/board/SnsMoreInfo" method="post" enctype="multipart/form-data">
		<input type="hidden" value="<%=snsUser%>"/>
		<div class="mb-3 mt-3">
		 	<div class="row">
		 		<div class="col-md-12 text-center">
			 		<div class="register-user-img">
		 				<img src="images/user-fill.png" alt="user" id="user-img">
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
			 			<input type="text" value="<%=userid %>" class="form-control" id="userid" placeholder="아이디" name="userid" onfocusout="registerCheck(1);">
			 			<input type="hidden" id="duplicheck" value="notcheck">
				 		<div class="input-group-append">
    						<button class="btn btn-primary id-dupli" type="button" onclick="idCheck();">중복확인</button>
    						
  						</div>
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
			 			<input type="text" value="<%=username %>" class="form-control" id="username" placeholder="이름" name="username">
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
						<input type="text" value="<%=useremail%>" class="form-control" id="useremail" placeholder="이메일" name="useremail" onfocusout="registerCheck(4);">   							
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
<%
	String postcodeStr = "";
	if(postcode == -1){
%>
						<input type="number" value="<%=postcodeStr %>" readonly class="form-control" id="postcode" placeholder="우편번호" name="postcode">
<%		
	}else{
%>			 			
			 			<input type="number" value="<%=postcode %>" readonly class="form-control" id="postcode" placeholder="우편번호" name="postcode">
<%
	}
%>			 			
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
			 		<input type="text" name="addr" value="<%=addr %>" readonly class="form-control" id="addr" placeholder="주소">
			 	</div>
		 	</div>
		 </div>
		 <div class="mb-3 mt-3">
		 	<div class="row">
		 		<div class="input-group">
			 	<div class="emptysp"></div>
			 		<input type="text" name="detailaddr" value="<%=detailAddr %>" class="form-control" id="detailaddr" placeholder="상세주소">
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

	if(tels == ""){
%>
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
						</select>
				 			<input type="number" class="form-control" id="tel2"  name="tel2">
				 			<input type="number" class="form-control" id="tel3"  name="tel3">
<%
	}else{
		String[] myTelArr = tels.split("-");
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
<%
	}
%>			 			
			 		</div>
			 	</div>	
			 	
		 	</div>
		 	<p class="warning-text"></p>
		 </div>
		 
		 <input type="hidden" name="tel" id="tel">
		 <div class="form-btn py-4 text-center">
		 
		 <button type="button" onclick="register3();" class="btn btn-primary ml-2">회원수정</button>
		 </div>
	</form>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</div>
<%}%>