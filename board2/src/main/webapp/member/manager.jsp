<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*, java.util.*"%>
<%!
	String user = null;
	int listNum = 8; // 한 페이지당 보여줄 목록 수
	int pageNum = 5; // 한 블럭당 보여줄 페이지 수
%>

<%
	String pg = request.getParameter("page");
	int mypg = 1;
	try{
		mypg = Integer.parseInt(pg.substring(0, 1));
		if(mypg < 1) {
			mypg = 1;			
		}
	}catch(Exception e){
		e.printStackTrace();
		mypg = 1;
	}
	System.out.println(mypg);
	int limitNum = (mypg - 1) * listNum;
	Vector <MembersDTO> v = MemberDDL.getSelectAll(limitNum, listNum);
	Vector <MembersDTO> vAll = MemberDDL.getSelectAll();
	int maxColumn = vAll.size();
	
%>
<div class="container admin">
	<div class="row admin-title">
		<div class="col-12">
			<span>회원 : <%=maxColumn %> 명</span>
			<div class="user-search">
				<img src="images/search-line.png" alt="search" />
				<input type="search" placeholder="회원검색"/>
			</div>
		</div>
	</div>
	<div class="row pt-2 pb-2">
		<div class="col-1">번호</div>
		<div class="col-2" style="margin-left: -30px;">아이디</div>
		<div class="col-1">이름</div>
		<div class="col-3">이메일</div>
		<div class="col-2">전화번호</div>
		<div class="col-1">회원 등급</div>
		<div class="col-1">IP 주소</div>
	</div>
<%
	
	int i = 0; 
	for(MembersDTO mb : v){
		user = mb.getUserid();
%>
	<div class="row mem-con pt-2 pb-2">
	<div class="col-1">
		<%=mb.getNum() %>
	</div>
	<div class="col-2" style="margin-left: -40px;">
		<div class="d-flex">
<%
		if(mb.getPhoto() == null){
%>				
				<div class="d-inline-block">
					<div class="admin-user-img" style=" margin-left: 10px; margin-top: 5px; background: #ccc;">
						<img class="admin-sns" src="images/user-fill.png" alt="user"/>
					</div>
				</div>
<%
		}else{
%>
				<div class="d-inline-block">
					<div class="admin-user-img" style=" margin-left: 10px; margin-top: 5px; background: #ccc;">
						<img class="admin-sns" src="/board/ShowPhoto?userid=<%=mb.getUserid() %>" alt="user"/>
					</div>
				</div>
<% 				
		}
%>
		
		
			<div style="margin-left: 10px;">
				<span><%=mb.getUserid() %></span><br />
				<span style="font-size: 14px; color: #ccc;">생성 날짜 : <%=mb.getWdate().substring(0, 11) %></span>
			</div>
		</div>
	</div>
	<div class="col-1">
		<%=mb.getUsername() %>
	</div>
	<div class="col-3">
		<%=mb.getUseremail() %>
	</div>
	<div class="col-2">
		<%=mb.getTel() %>
	</div>
	<div class="col-1 editlevel">
<%
	int level = mb.getLevel();
	String selected1 = "", selected2 = "", selected3 = "", selected4 = "", selected5 = "";
	String mem = ""; 
	if(level == 1){
		mem = "신규회원";
		selected3 = "selected";
	}else if(level == 0){
		mem = "휴면 회원";
		selected2 = "selected";
	}else if(level == 99){
		mem = "관리자";
		selected5 = "selected";
	}else if(level == 2){
		mem = "일반회원";
		selected4 = "selected";
	}else if(level == -1){
		mem = "계정정지";
		selected1 = "selected";
	}
%>		
		<%=mem %>
		</div>
		<div class="col-1">
		<%=mb.getUip() %>
		</div>
		<div class="col-1">
			<div class="row">
				<div class="col-6 pt-2">
					<a href="javascript:editMember(<%=i%>);" class="editpen">
						<img src="images/pencil-fill.png" alt="edit" />
					</a>
				</div>

			</div>
			
		</div>
	</div>

<div class="editpopup">
	<div class="editpopup-title">
		<span>회원수정</span><a href="javascript:void(0)" onclick="editMClose(<%=i%>);"><span>X</span></a>	
	</div>
	<hr />
	<div class="editpopup-content">
		<input type="hidden" value="<%=mb.getUserid()%>" id="userid" class="userid"/>
		<form action="">
			<label for="userlevel" class="mb-2">회원 등급</label>
			<select id="userlevel" class="mb-3 userlevel">
				<option value="-1" <%=selected1 %>>계정정지</option>
				<option value="0" <%=selected2 %>>휴면회원</option>
				<option value="1" <%=selected3 %>>신규회원</option>
				<option value="2" <%=selected4 %>>일반회원</option>
				<option value="99" <%=selected5 %>>관리자</option>
			</select>
			
			<a class="btn btn-primary" href="#" onclick="updateLevel(<%=i %>);">확인</a>	
		</form>
	</div>
	
</div>
<%
	i++;
	}
%>
</div>
<div class="admin-paging">
<%
	int totalPage = (int) Math.ceil( maxColumn / (double) listNum);
	int totalBlock = (int) Math.ceil(totalPage / (double) pageNum);
	int nowBlock = (int) Math.ceil(mypg / (double) pageNum);
	
	int sPageNum = (nowBlock - 1) * pageNum + 1;
	int ePageNum = nowBlock * pageNum;
	
	if(ePageNum > totalPage) {ePageNum = totalPage;}
	
%>
	<ul class="pagination">
<%	
	if(sPageNum <= 1){
	}else{
		int prev = sPageNum - 1;
%>
		<li class="page-item"><a  class="page-link" href="?fname=member/manager.jsp?page=<%=prev%>"><<</a></li>
<%
	}
	
	for(int j = sPageNum; j <= ePageNum; j++){
		String act = "";
		if(mypg == i) act = " active";
%>
		<li class="page-item <%=act%>"><a class="page-link" href="?fname=member/manager.jsp?page=<%=j%>"><%=j %></a></li>
<%
	}
	
	if(sPageNum >= totalPage){		
	}else{
		int next = ePageNum + 1;
%>	
		<li class="page-item"><a class="page-link" href="?fname=member/manager.jsp?page=<%=next%>">>></a></li>
<%
	}
%>
	</ul>
</div>
	