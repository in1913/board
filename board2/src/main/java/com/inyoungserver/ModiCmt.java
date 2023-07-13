package com.inyoungserver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import board.CommentDDL;
import board.CommentDTO;


public class ModiCmt extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("application/json; charset=utf-8");
		StringBuffer jb = new StringBuffer();
		String line = null;
		

		try {
			BufferedReader br = req.getReader();
			while((line = br.readLine()) != null) 
				jb.append(line);
			
			System.out.println(jb);
			
			JsonObject jsonObj = (JsonObject) JsonParser.parseString(jb.toString());
			
			int cmtnum = jsonObj.get("cmtnum").getAsInt();
			int textnum = jsonObj.get("textnum").getAsInt();
			int bbsnum = jsonObj.get("bbsnum").getAsInt();
			String comment = jsonObj.get("comment").getAsString();
						
			br.close();
			
			boolean isSuccess = CommentDDL.ModiCmt(cmtnum, textnum, comment, bbsnum);
			PrintWriter out = res.getWriter();
			if(isSuccess) {
				System.out.println("댓글 수정 성공");
				out.println("{\"result\" : \"댓글 수정 성공\"}");
				out.flush();
				out.close();
			}else {
				System.out.println("댓글 수정 실패");
				out.println("{\"result\" : \"댓글 수정 실패\"}");
				out.flush();
				out.close();
			}
			
						
			
		}catch(Exception e) {}
	}

}
