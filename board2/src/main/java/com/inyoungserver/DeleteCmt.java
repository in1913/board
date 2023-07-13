package com.inyoungserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import board.CommentDDL;


public class DeleteCmt extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
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
			System.out.println("line : " +  line);
			
			JsonObject jsonObj = (JsonObject) JsonParser.parseString(jb.toString());
			
			
			int textnum = jsonObj.get("textnum").getAsInt();
			int num = jsonObj.get("cmtnum").getAsInt();
			int bbsnum = jsonObj.get("bbsnum").getAsInt();
			
			br.close();
			
			JSONObject jsonObj2 = new JSONObject();
			jsonObj2.put("result" , "댓글 삭제 성공");
			
			JSONObject jsonObj3 = new JSONObject();
			jsonObj3.put("result" , "댓글 삭제 실패");
			
			PrintWriter out = res.getWriter();
			
			
			boolean isSuccess = CommentDDL.DeleteCmtFree(bbsnum, num ,textnum);
			if(isSuccess) {
				System.out.println("댓글 삭제 성공");
				out.println(jsonObj2);
			}else {
				System.out.println("댓글 삭제 실패");
				out.println(jsonObj3);
			}	
			
			
			
			
			out.flush();
			out.close();
		
		}catch(Exception e) {}
	}

}
