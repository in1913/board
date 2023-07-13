<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, board.*, java.time.format.DateTimeFormatter, java.time.LocalDateTime"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<%
	String user = "";
	if(session.getAttribute("user") == null){
		
	}else{
		user = (String) session.getAttribute("user"); 
	}
	int level = 100;
	if(session.getAttribute("level") == null){
		
	}else{
		level = (int) session.getAttribute("level") ;
	}
	
	String getNum = request.getParameter("bbsnum");
	int bbsnum = Integer.parseInt(getNum.split("\\.")[0]);
	
	String[] nums = request.getParameter("num").split("\\.");
	int num = Integer.parseInt(nums[0]);

	DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	String today = now.format(df).substring(0, 10);
	
	boolean checkTextLike = LikeDDL.checkTextLike(bbsnum, num, user);
	
	Vector <LikeDTO> lkd = LikeDDL.checkCmtLike(bbsnum, num);	
	
	Vector <BoardDTO> data = BoardDDL.getSelectByNum(num);
%>
<div class="container p-3 bg-secondary mytxt-container mb-5">
<input type="hidden" name="num" id="num" value="<%=num %>" />
<%
	for(BoardDTO dd: data){
%>
	<div class="text-title d-flex justify-content-between">
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
		<span>
<%
		if(level == 99 && (!user.equals(dd.getUserid()))){
%>
				<a href="javascript:deleteText();" class="btn btn-dark text-white">삭제</a>			
<%
		}
		if(level == 99 && user.equals(dd.getUserid())){
%>
				<a href="javascript:deleteText();" class="btn btn-dark text-white">삭제</a>			
				<a href="index.jsp?fname=list/modifiedEdit.jsp?bbsnum=<%=bbsnum %>&num=<%=dd.getNum() %>"  class="text-white btn btn-info">수정</a>
<%
		}
		if(level != 99 && user.equals(dd.getUserid())){
%>
				<a href="javascript:deleteText();" class="btn btn-dark text-white">삭제</a>
				<a href="index.jsp?fname=list/modifiedEdit.jsp?bbsnum=<%=bbsnum %>&num=<%=dd.getNum() %>"  class="text-white btn btn-info">수정</a>
<%
		}
%>	
		 </span>
	</div>
<%
	
%>
	<p class="py-2 px-3 text-white" style="background: rgba(44,44,44,0.3); font-size: 18px; font-weight: 500;"><%=dd.getTitle() %></p>
	<div class="mytxt-who text-white py-2 px-3" style="background: rgba(44,44,44,.5); border-top: 3px solid orange">
		<div class="mytxt-user d-flex justify-content-between" >
			<span><strong><%=dd.getUserid() %></strong> 님</span><span><img src="images/eye-line-dark.png" style="width: 15px;" alt="count" /> <%=dd.getCount() %></span>
		</div>
	</div>
	<div class="mytxt-content px-3 py-2" style="background: #444444;">
		<div class="mytxt-info px-1 pb-1 d-flex justify-content-between" style="color: #ccc; font-size: 12px; border-bottom: 1px solid rgba(255, 255, 255, .2);">
			<span><%=dd.getWdate() %></span><span><img src="images/map-pin-2-fill.png" alt="ip" style="width: 15px; vertical-align: top;"/><%=dd.getUip() %></span>
		</div>
		
		<div class="mytxt-real-content text-white px-3 py-3">
		<%--게시글 좋아요 갯수 표시되는 곳 --%>
		<div class="showLikeNum">	
<%
		if(dd.getLikes() > 0){
%>
			<div class="d-inline-block text-center text-white mb-3" style="width: 40px; background: #dc3545; border-radius: 4px; font-size: 12px;"><img src="images/heart-fill.png" style="width: 13px; object-position: 0px -2px;" alt="like" />		 
				<span class="doLike"><%=dd.getLikes() %></span>
			</div>	
<%
		}else{}
%>		
		</div>
		
		<%-- 게시글 좋아요 갯수 표시되는 곳 끝 --%>
			<%=dd.getContent() %>
		</div>
		<div class="text-funcs d-flex justify-content-center">
		<div class="doLikeBox" style="inline-block">

<%
		// 좋아요 이미 눌렀음
		if(checkTextLike == true){
%>		
			<a href="javascript:doLike();" class="d-inline-block text-center text-white p-2 " style="margin-right: 5px;"><img src="images/heart-fill-red.png" style="width: 24px; object-position: 0 -2px;" alt="like" />
				<input type="hidden" value="1" id="doLike" />
			</a>
<%
		// 좋아요 이미 누르지 않았음
		}else{
%>			
			<a href="javascript:doLike();" class="d-inline-block text-center text-white p-2 " style="margin-right: 5px;"><img src="images/heart-line.png" style="width: 24px; object-position: 0 -2px;" alt="like" />
				<input type="hidden" value="0" id="doLike" />
			</a>
<%
		}
%>
		</div>
			<a href="javascript:doShare();" class="d-inline-block text-center text-white py-2 px-3" style="background: #787878; border-radius: 4px;"><img src="images/share-fill.png" style="width: 20px; object-position: 0px -2px" alt="share" /></a>
		</div>
	</div>
<%
	}

	Vector <CommentDTO> cmd = CommentDDL.select(num, bbsnum);
	
	if(cmd.size() == 0){
		
	}else{
		int i = 0;
		for(CommentDTO cc: cmd){
			
			
%>
	<div class="mytxt-comment py-2 px-3 text-white" style="background: #444444;">	
		<input type="hidden" name="cmtnum" id="cmtnum" class="cmtnum" value="<%=cc.getNum() %>"/>
		<input type="hidden" class="origin-cmt" name="origin-cmt" id="origin-cmt" value="<%=cc.getComment() %>" />
		<div class="comment-user py-2 px-3 d-flex justify-content-between" style="font-size: 14px; background: rgba(255,255,255, .4)">
			<span><strong><%=cc.getUserid() %></strong> 님</span> 
			<span>
				<a href="javascript:showIP(<%=i%>);" class="ip-click text-center" style="background: #ccc; width: 50px; display: inline-block; border-radius: 4px; margin-right: 3px;">IP</a>
				<a href="javascript:hideIP(<%=i%>);" class="ip-show" style="background: #ccc; width: 100px; border-radius: 4px; text-align: center; margin-right: 3px;"><%=cc.getUip() %></a>
<%
			String dbDate = cc.getWdate().substring(0, 10);
			if(dbDate.equals(today)){				
%>			
			<span style="margin-right: 3px;"><%=cc.getWdate().substring(10, 16) %></span>
<%
			}else{
%>			
			<span style="margin-right: 3px;"><%=cc.getWdate().substring(0, 10) %></span>

<%
			}
%>
				<a href="javascript:doReply(<%=i%>);" class="d-inline-block text-center text-white" style="width: 50px; background: #555; border-radius: 4px; margin-right: 3px;">대댓</a>
				
				<%-- 사용자 댓글 좋아요 여부 보여주는 곳 --%>
				
<%
			int likeCmtCnt = 0;
			for(LikeDTO ld: lkd){
				if(ld.getNum() == cc.getNum()){
					if(user.equals(ld.getUserid())){
						likeCmtCnt = 1;
					}else{									
					}
				}
			} 			
			if(likeCmtCnt == 0){
%>	
				<span class="doCmtLikeBox">
					<a href="javascript:doCmtLike(<%=i %>);" class="d-inline-block text-center text-white " style="margin: 0;"><img src="images/heart-line.png" style="width: 17px; object-fit: cover; object-position: 0px -1px;" alt="like" />
						<input type="hidden" value="0" id="doCmtLike<%=i %>" />
					</a>			
				</span>
<%			
			}else{
%>
				<span class="doCmtLikeBox">
					<a href="javascript:doCmtLike(<%=i %>);" class="d-inline-block text-center text-white " style="margin: 0;"><img src="images/heart-fill-red.png" style="width: 17px; object-fit: cover; object-position: 0px -1px;" alt="like" />
						<input type="hidden" value="1" id="doCmtLike<%=i %>" />
					</a>	
				</span>		
	

				<%-- 끝 --%>
<%
			}
			if(level == 99 && (!user.equals(cc.getUserid()))){
%>
				<a href="javascript:deleteCmt(<%=i%>);"><img src="images/delete-bin-line.png" alt="edit" style="width: 15px; object-position: 0px -2px;" /></a>
<%
			}
			if(level == 99 && user.equals(cc.getUserid())){
%>
				<a href="javascript:modifiedCmt(<%=i%>);"><img src="images/pencil-fill-white.png" alt="edit" style="width: 15px; margin-left: 3px; object-position: 0px -2px;" /></a>
				<a href="javascript:deleteCmt(<%=i%>);"><img src="images/delete-bin-line.png" alt="edit" style="width: 15px; object-position: 0px -2px;" /></a>
<%				
			}
			if(level != 99 && user.equals(cc.getUserid())){

%>
				<a href="javascript:modifiedCmt(<%=i%>);"><img src="images/pencil-fill-white.png" alt="edit" style="width: 15px; margin-left: 3px; object-position: 0px -2px;" /></a>
				<a href="javascript:deleteCmt(<%=i%>);"><img src="images/delete-bin-line.png" alt="edit" style="width: 15px; margin-elft: 5px; object-position: 0px -2px;" /></a>
<%			
			}
%>
			</span>
		</div>
		<%-- 사용자 댓글 좋아요 개수 보여주는 곳 --%>
		<div class="showCmtLikeNum">		
<%
			if(cc.getLikes() > 0){
%>
			<div class="d-inline-block text-center text-white mx-2 mt-2" style="width: 40px; background: #dc3545; border-radius: 4px; font-size: 12px;"><img src="images/heart-fill.png" style="width: 13px; object-position: 0px -2px;" alt="like" />
				<span class="doCmtLike"><%=cc.getLikes() %></span>
			</div>
<%				
			}else{}
%>			
			
		</div>
		<%-- 끝 --%>
		
		<div class="comment-content px-3 py-2" style="font-weight: 200; font-size: 14px;">
		
			<div class="comment-origin">
				<%=cc.getComment() %>
			</div>
			<div class="comment-modibox">
				<textarea class="p-2 text-white modi-comment" name="modi-comment" id="modi-comment" cols="30" rows="10" style="width: 100%; max-height: 100px; background: #333; outline: none"><%=cc.getComment() %></textarea>
				<div class="d-flex justify-content-end">
					<a href="javascript:submitModiCmt(<%=i%>);" class="btn-sm btn-info text-white" style="margin-right: 5px;">수정</a>
					<a href="javascript:closeModiCmt(<%=i%>);" class="btn-sm btn-dark text-white">취소</a>
				</div>
			</div>
			
			
			
			<!-- replybox -->
<%
			Vector <ReplyDTO> rpd = ReplyDDL.select(cc.getNum(), num, bbsnum);
			int j = 0;
			for(ReplyDTO rr: rpd){
%>
			<div class="replybox mt-3" style="margin-left: 40px;">
				<input type="hidden" class="replynum<%=i%>" id="replynum" name="replynum" value="<%=rr.getNum() %>" />
				<div class=" reply-user py-1 px-3 d-flex justify-content-between" style="font-size: 13px; background: #eeb006;">
					<span><strong><%=rr.getUserid() %></strong> 님</span>
					<span>
						<a href="javascript:showReplyIP(<%=i %>,<%=j %>);" class="ip-reply-click<%=i%> text-center" id="ip-reply-click" style="background: #ccc; width: 50px; display: inline-block; border-radius: 4px; margin-right: 3px;">IP</a>
						<a href="javascript:hideReplyIP(<%=i %>,<%=j %>);" class="ip-reply-show<%=i%> text-center" id="ip-reply-show" style="background: #ccc; width: 80px; border-radius: 4px; text-align: center; margin-right: 3px;"><%=rr.getUip() %></a>
<%
				if(dbDate.equals(today)){				
%>						
						<span style="margin-right: 3px;"><%=rr.getWdate().substring(10, 16) %></span>
<%
				}else{
%>
						<span style="margin-right: 3px;"><%=rr.getWdate().substring(0, 10) %></span>
<%
				}



			Vector <LikeDTO> reLkd = LikeDDL.checkReplyLike(bbsnum, num, cc.getNum());
			
			int likeReplyCnt = 0;
			for(LikeDTO rl: reLkd){
				if(rl.getNum() == rr.getNum()){
					if(user.equals(rl.getUserid())){
						likeReplyCnt = 1;
					}
				}
			}
			if(likeReplyCnt == 0){
%>

	<%-- 사용자 대댓 좋아요 여부 보여주는 곳 --%>
					<span class="doReLikeBox<%=i%>">
						<a href="javascript:doReLike(<%=i %>, <%=j %>);" class="d-inline-block text-center text-white " style="margin: 0;"><img src="images/heart-line.png" style="width: 17px; object-fit: cover; object-position: 0px -1px;" alt="like" />
							<input type="hidden" value="0" class="checkDoReLike<%=i %>" />
						</a>			
					</span>
<%
			}else{
%>						
					<span class="doReLikeBox<%=i%>">
						<a href="javascript:doReLike(<%=i %>, <%=j %>);" class="d-inline-block text-center text-white " style="margin: 0;"><img src="images/heart-fill-red.png" style="width: 17px; object-fit: cover; object-position: 0px -1px;" alt="like" />
							<input type="hidden" value="1" class="checkDoReLike<%=i %>" />
						</a>			
					</span>		
	<%-- 끝 --%>
<%
			}
			if(level == 99 && (!user.equals(rr.getUserid()))){
%>
				<a href="javascript:deleteReply(<%=i%>, <%=j %>);"><img src="images/delete-bin-line.png" alt="edit" style="width: 15px; object-position: 0px -2px;" /></a>
<%
			}
			if(level == 99 && user.equals(rr.getUserid())){
%>
				<a href="javascript:modifiedReply(<%=i%>, <%=j %>);"><img src="images/pencil-fill-white.png" alt="edit" style="width: 15px; margin-left: 3px; object-position: 0px -2px;" /></a>
				<a href="javascript:deleteReply(<%=i%>, <%=j %>);"><img src="images/delete-bin-line.png" alt="edit" style="width: 15px; object-position: 0px -2px;" /></a>
<%				
			}
			if(level != 99 && user.equals(rr.getUserid())){

%>
				<a href="javascript:modifiedReply(<%=i%>, <%=j %>);"><img src="images/pencil-fill-white.png" alt="edit" style="width: 15px; margin-left: 3px; object-position: 0px -2px;" /></a>
				<a href="javascript:deleteReply(<%=i%>, <%=j %>);"><img src="images/delete-bin-line.png" alt="edit" style="width: 15px; margin-elft: 5px; object-position: 0px -2px;" /></a>
<%			
			}
%>						
					</span>
				</div>
				
				
				
				<div class="reply-content px-3 py-2" style="font-size: 13px; background: rgba(238, 176, 6, 0.1)">
				
	<%-- 사용자 대댓 좋아요 수 보여주는 곳 --%>	
				<div class="showReLikeNum<%=i%>">		
<%
							if(rr.getLikes() > 0){
%>
					<div class="d-inline-block text-center text-white ml-1 mb-2" style="width: 40px; background: #dc3545; border-radius: 4px; font-size: 12px;"><img src="images/heart-fill.png" style="width: 13px; object-position: 0px -2px;" alt="like" />
						<span class="doReLike<%=i%>"><%=rr.getLikes() %></span>
					</div>
<%				
							}else{}
%>							
				</div>
	<%-- 끝 --%>				
				
				
					<div class="reply-origin<%=i%>">
						<%=rr.getReply() %>
					</div>
					<div class="reply-modibox<%=i %>" id="reply-modibox">
						<textarea class="p-2 text-white modi-reply<%=i %>" name="modi-reply" id="modi-reply" cols="30" rows="10" style="width: 100%; max-height: 100px; background: #333; outline: none"><%=rr.getReply() %></textarea>
						<div class="d-flex justify-content-end">
							<a href="javascript:submitModiReply(<%=i%>, <%=j %>);" class="btn-sm btn-info text-white" style="margin-right: 5px;">수정</a>
							<a href="javascript:closeModiReply(<%=i%>, <%=j %>);" class="btn-sm btn-dark text-white">취소</a>
						</div>	
					</div>
					
					
					
					
					
				</div>
			</div>
<%
				j++;
			}
%>			
			<!-- write reply textarea -->
			<div class="write-reply mt-3 p-3" style="margin-left: 40px; background: #223344">
				<div class="write-reply-d-flex d-flex">
					<textarea spellCheck="false" class="py-2 px-3 text-white reply" name="reply" id="reply" cols="30" rows="10" style="width: 80%; max-height: 100px; background: #333; outline: none;">[ @<%=cc.getUserid() %> ] 님 </textarea>
					<a href="javascript:makeReply(<%=i%>);" class="text-white d-flex justify-content-center align-items-center" style="width: 20%; background: #444455">대댓쓰기</a>
				</div>
			</div>
		</div>		
	</div>
<%
	
			i++;
		}
	}
%>
	<div class="make-comment mt-3 p-3 d-flex" style="background: #223344">
		<input type="hidden" name="num" id="num" value="<%=num%>"/>
		<input type="hidden" name="user" id="user" value="<%=user%>"/>
		<input type="hidden" name="bbsnum" id="bbsnum" value="<%=bbsnum%>"/>
		<textarea class="p-2 text-white" name="comment" id="comment" cols="30" rows="10" style="width: 80%; max-height: 100px; background: #333; outline: none" placeholder="댓글은 로그인이 필요한 서비스입니다."></textarea>
		<a href="javascript:makeComment();" class="text-white d-flex justify-content-center align-items-center" style="width: 20%; background: #444455">댓글쓰기</a>
	</div>
</div>

<div class="text-clipboard" id="text-clipboard">
	<div class="d-flex justify-content-center align-items-center flex-column">
		<div class="input-group">
			<div class="input-group-prepand">
				<span class="input-group-text" id="addon01">URL</span>
			</div>
			<input type="text" id="text-clipboard-uri" class="form-control" readonly aria-describedby="addon01" onclick="URIcopy();"/>
		</div>
		<p id="noticeCopy">누르면 주소가 복사됩니다.</p> 
	</div>

<script src="../js/mytext.js"></script>

</div>