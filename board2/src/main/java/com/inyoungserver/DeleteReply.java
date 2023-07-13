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

import board.ReplyDDL;


public class DeleteReply extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
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
			
			JsonObject jsonObj = (JsonObject) JsonParser.parseString(jb.toString());
			System.out.println(jsonObj);
			
			
			int replynum = jsonObj.get("replynum").getAsInt();
			int textnum = jsonObj.get("textnum").getAsInt();
			int cmtnum = jsonObj.get("cmtnum").getAsInt();
			int bbsnum = jsonObj.get("bbsnum").getAsInt();
			
			br.close();
			
			boolean isSuccess = ReplyDDL.DeleteReply(replynum, cmtnum, textnum ,bbsnum);
			
			JSONObject jsonObj2 = new JSONObject();
			jsonObj2.put("result" , "대댓 삭제 성공");
			
			JSONObject jsonObj3 = new JSONObject();
			jsonObj3.put("result" , "대댓 삭제 실패");
			
			PrintWriter out = res.getWriter();
			if(isSuccess) {
				System.out.println("대댓 삭제 성공");
				out.println(jsonObj2);
				out.flush();
				out.close();
				
			}else {
				System.out.println("대댓 삭제 실패");
				out.println(jsonObj3);
				out.flush();
				out.close();
			}
		
		}catch(Exception e) {}
	}

}
