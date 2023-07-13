<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*, java.util.*"%>
<%

	Vector <BoardDTO> bnd = BoardDDL.getSelectLimit(1, 0, 10);
	Vector <BoardDTO> bfd = BoardDDL.getSelectLimit(2, 0, 10);
	Vector <BoardDTO> bpd = BoardDDL.selectOrderByCountLimit(0, 10);
	Vector <BoardDTO> bcd = BoardDDL.getSelectLimit(4, 0, 10);

%>
<div class="container bg-secondary p-3 mb-5">
	<div class="row">
		<div class="col-6 p-2">
			<h5 class="text-white d-inline-block py-2 px-3" style="background: #444444;"><a href="index.jsp?fname=notice/noticelist" class="text-white">공지사항</a></h5>
			
			<div class="mainContainer px-3" style="background: #444444;">
<%

	for(BoardDTO bd: bnd){
		int commentCnt = CommentDDL.CommentCount(bd.getNum(), 1);
%>			
				
				<p class="d-flex justify-content-between"><span class="text-white"><%=bd.getTitle() %><span style="color: orange;"> (<%=commentCnt %>)</span></span> <span style="color: #fff;"><img src="images/heart-fill-red.png" alt="like" style="width: 17px; object-position:0px -2px; color: #dc3545"/> <%=bd.getLikes() %></span></p>
<%
	}
%>				
	
			</div>
		</div>
		<div class="col-6 p-2">
			<h5 class="text-white d-inline-block py-2 px-3" style="background: #444444;"><a href="index.jsp?fname=freeboard/boardlist" class="text-white">인기글</a></h5>
			<div class="mainContainer px-3" style="background: #444444;">
<%
	for(BoardDTO bd: bpd){
%>			
				<p class="d-flex justify-content-between"><span class="text-white"><%=bd.getTitle() %><span style="color: orange;"> (99)</span></span> <span style="color: #fff;"><img src="images/heart-fill-red.png" alt="like" style="width: 17px; object-position:0px -2px; color: #dc3545"/> <%=bd.getLikes() %></span></p>
<%
	}
%>			
	
			</div>
		</div>
		<div class="col-6 p-2">
			<h5 class="text-white d-inline-block py-2 px-3" style="background: #444444;"><a href="index.jsp?fname=freeboard/boardlist" class="text-white">자유게시판</a></h5>
			<div class="mainContainer px-3" style="background: #444444;">
<%
	for(BoardDTO bd: bfd){
		int commentCnt = CommentDDL.CommentCount(bd.getNum(), 2);
%>			
				<p class="d-flex justify-content-between"><span class="text-white"><%=bd.getTitle() %><span style="color: orange;"> (<%=commentCnt %>)</span></span> <span style="color: #fff;"><img src="images/heart-fill-red.png" alt="like" style="width: 17px; object-position:0px -2px; color: #dc3545"/> <%=bd.getLikes() %></span></p>
<%
	}
%>			
			</div>
		</div>
		<div class="col-6 p-2">
			<h5 class="text-white d-inline-block py-2 px-3" style="background: #444444;"><a href="index.jsp?fname=notice/noticelist" class="text-white">컬럼게시판</a></h5>
			<div class="mainContainer px-3" style="background: #444444;">
<%
	for(BoardDTO bd: bcd){
		int commentCnt = CommentDDL.CommentCount(bd.getNum(), 4);
%>			
				<p class="d-flex justify-content-between"><span class="text-white"><%=bd.getTitle() %><span style="color: orange;"> (<%=commentCnt %>)</span></span> <span style="color: #fff;"><img src="images/heart-fill-red.png" alt="like" style="width: 17px; object-position:0px -2px; color: #dc3545"/> <%=bd.getLikes() %></span></p>
<%
	}
%>		
			</div>
		</div>	
	</div>
</div>