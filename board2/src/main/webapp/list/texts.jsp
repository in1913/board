<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*, java.util.*, java.time.format.DateTimeFormatter, java.time.LocalDateTime"%>
<div class="container">
<%
	String user = (String) session.getAttribute("user");

	String getNum = request.getParameter("bbsnum");
	int bbsnum = Integer.parseInt(getNum.split("\\.")[0]);
	
	Vector <BoardDTO> data = BoardDDL.getSelect(bbsnum);

	Collections.reverse(data);
	
	DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	String today = now.format(df).substring(0, 10);
	int allCol = data.size();
	if(allCol == 0){
%>
	<div class="jumbotron">
	<br /><br /><br />
		<h1 class="display-4">글이 없습니다. 첫글을 써주세요.</h1>
		<br /><br /><br />
		<p class="lead">
			<input type="hidden" name="user" id="user" value="<%=user %>" />
			<input type="hidden" name="bbsnum" id="bbsnum" value="<%=bbsnum%>" />
	    	<a class="btn btn-primary btn-lg text-white" href="javascript:userCheck();" role="button">글쓰기</a>
	  	</p>
	</div>
<%
	}else{
%>
	<div class="myContainer">
<%
	if(bbsnum == 1){
%>	
	<h5 class="text-white d-inline-block py-2 px-3" style="background: #444444;">공지사항</h5>
<%
	}else if(bbsnum == 2){
%>
	<h5 class="text-white d-inline-block py-2 px-3" style="background: #444444;">자유게시판</h5>
<%
	}else if(bbsnum == 3){
%>
	<h5 class="text-white d-inline-block py-2 px-3" style="background: #444444;">인기글</h5>
<%
	}else if(bbsnum == 4){
%>
	<h5 class="text-white d-inline-block py-2 px-3" style="background: #444444;">컬럼게시판</h5>
<%
	}
%>
	<table class="text-center freeboardtable" style="width: 100%;">
		<tr>
			<th style="width: 50px; border-left: 1px solid #ccc;">번호</th>
			<th class="text-start" style=" padding-left: 10px;">글제목</th>
			<th style="width: 100px;">작성자</th>
			<th style="width: 70px;">조회수</th>
			<th style="width: 100px;">날짜</th>
		</tr>
<% 
		int i = 0;
		for(BoardDTO dd : data){
			int commentCnt = CommentDDL.CommentCount(dd.getNum(), bbsnum);
%>
		<tr>
			<td><%=allCol - i%></td>
			
			<td class="text-start free-content" style="padding-left: 10px;" onclick="viewClick(<%=i%>);">
				<a class="real-free-content d-block w-100 h-100" href="index.jsp?fname=list/text.jsp?bbsnum=<%=bbsnum %>&num=<%=dd.getNum() %>">
					<%=dd.getTitle() %> <span style="color: red">(<%=commentCnt %>)</span>
				</a>
			</td>
			<td><%=dd.getUserid() %></td>
			<td><%=dd.getCount() %>
				<input type="hidden" name="num" class="num" value="<%=dd.getNum()%>"/>
				<input type="hidden" name="bbsnum" class="bbsnum" id="bbsnum" value="<%=bbsnum%>"/>
			</td>
			
<%
			String dbDate = dd.getWdate().substring(0,10);
			if(dbDate.equals(today)){
%>
			<td><%=dd.getWdate().substring(10, 16) %></td>
<%
			}else{
%>
			<td><%=dd.getWdate().substring(0, 10) %></td>
<%
			}
%>
		</tr>				
<%
			i++;
		}
%>
	</table>
	</div>
	<input type="hidden" name="bbsnum" id="bbsnum" value="<%=bbsnum%>" />
	<input type="hidden" name="user" id="user" value="<%=user %>" />
	<div class="text-end mt-3"><a href="javascript:userCheck();" class="btn btn-primary text-white">글쓰기</a></div>
</div>

<%
	}
%>