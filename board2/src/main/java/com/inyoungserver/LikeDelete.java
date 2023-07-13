package com.inyoungserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import board.BoardDDL;
import board.CommentDDL;
import board.LikeDDL;
import board.ReplyDDL;


public class LikeDelete extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("application/json; charset=utf-8");
		StringBuffer jb = new StringBuffer();
		String line = null;
		
		try {
			BufferedReader br = req.getReader();
			while((line = br.readLine()) != null)
				jb.append(line);
			
			JsonObject jsonObj = (JsonObject) JsonParser.parseString(jb.toString());
			
			System.out.println(jsonObj);
			
			int bbsnum = jsonObj.get("bbsnum").getAsInt();
			int textnum = jsonObj.get("textnum").getAsInt();
			int cmtnum = jsonObj.get("cmtnum").getAsInt();
			int replynum = jsonObj.get("replynum").getAsInt();
			String user = jsonObj.get("userid").getAsString();
			
			System.out.println("bbsnum : " + bbsnum);
			System.out.println("textnum : " + textnum);
			System.out.println("cmtnum : " + cmtnum);
			System.out.println("replynum : " + replynum);
			// System.out.println("replynum : " + replynum);
			
			br.close();
			
			boolean isSuccess = LikeDDL.delete(bbsnum, textnum, cmtnum, replynum, user);
		
			PrintWriter out = res.getWriter();
			
			int doLike = 0;
			int doCmtLike = 0;
			int doReLike = 0;
		
			// 게시판별 게시글 좋아요
			if(cmtnum == -1 && replynum == -1) {
				doLike = LikeDDL.selectText(bbsnum, textnum);
				boolean insertSuccess = BoardDDL.updateLike(textnum, doLike);
				if(isSuccess && insertSuccess) {
					out.println("{\"result\" : "+ doLike +"}");
					System.out.println("좋아요 삭제 성공");
				}else {
					out.println("{\"result\" : \"-1\"}");
					System.out.println("좋아요 삭제 실패");
				}
			}
			
			// 댓글 좋아요
			if(cmtnum != -1 && replynum == -1) {
				doCmtLike = LikeDDL.selectCmt(bbsnum, textnum, cmtnum);
				boolean insertSuccess = CommentDDL.updateCmtLike(bbsnum, textnum, cmtnum, doCmtLike);
				if(isSuccess && insertSuccess) {
					out.println("{\"result\" : " + doCmtLike + "}");
					System.out.println("좋아요 삭제 성공");
				}else {
					out.println("{\"result\" : \"-1\"}");
					System.out.println("좋아요 삭제 실패");
				}
			}
			
			// 대댓글 좋아요
			if(cmtnum != -1 && replynum != -1) {
				doReLike = LikeDDL.selectReply(bbsnum, textnum, cmtnum, replynum);
				boolean insertSuccess = ReplyDDL.updateReplyLike(bbsnum, textnum, cmtnum, replynum, doReLike);
				if(isSuccess && insertSuccess) {
					out.println("{\"result\" : " + doReLike + "}");
					System.out.println("좋아요 삭제 성공");
				}else {
					out.println("{\"result\" : \"-1\"}");
					System.out.println("좋아요 삭제 실패");
				}
			}

			out.flush();
			out.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
