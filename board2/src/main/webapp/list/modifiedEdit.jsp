<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, board.*"%>
<%

	String getNum = request.getParameter("bbsnum");
	int bbsnum = Integer.parseInt(getNum.split("\\.")[0]);

	String[] nums = request.getParameter("num").split("\\.");
	int num = Integer.parseInt(nums[0]);
	Vector <BoardDTO> data = BoardDDL.getSelectByNum(num);
	for(BoardDTO dd: data){
%>
<div class="container textedit mb-5">
	<form action="/board/ModifyText" method="post" name="modiform">
	<%-- 게시판 이름 --%>
	<input type="hidden" name="bbsnum" id="bbsnum" value="<%=bbsnum %>" />
	<input type="hidden" name="num" value="<%=num %>" />
	<div class="row">
		<div class="col-12 edit-left p-3 bg-secondary" >
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
			<input spellcheck="false" value="<%=dd.getTitle() %>" class="editboardtitle w-100 p-2" type="text" name="title" id="title" placeholder="제목을 입력하세요."/>
			<div class="typingbox mt-3"  style="background: #fff">
				<textarea name="content" class="mt-3" id="summernote" cols="30" rows="10"><%=dd.getContent() %></textarea>
			</div>
			<div class="edit-buttonbox w-100 mt-3 d-flex justify-content-end">
				<a  href="javascript:history.back();" class="btn btn-dark text-white" style="margin-right: 5px;">취소</a>
				<a href="javascript:modifiedText();" class="btn btn-info text-white">저장</a>
			</div>	
		</div>
		
	</div>
</form>
</div>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/summernote.min.js"></script>
<script src="css/lang/summernote-ko-KR.js"></script>


<%
	}
%>    