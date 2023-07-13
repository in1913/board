package com.inyoungserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import board.ReplyDDL;
import board.ReplyDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReplySubmit extends HttpServlet {
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
			String userid = jsonObj.get("userid").getAsString();
			String reply = jsonObj.get("reply").getAsString();
			
			System.out.println("cmtnum : " + cmtnum);
			System.out.println("textnum : " + textnum);
			System.out.println("bbsnum : " + bbsnum);
			System.out.println("userid : " + userid);
			System.out.println("reply : " + reply);
						
			br.close();
			
			ReplyDTO dto = new ReplyDTO();
			dto.setCmtnum(cmtnum);
			dto.setTextnum(textnum);
			dto.setBbsnum(bbsnum);
			dto.setUserid(userid);
			dto.setReply(reply);
			dto.setUip();
			boolean isSuccess = ReplyDDL.insert(dto);
			PrintWriter out = res.getWriter();
			if(isSuccess) {
				System.out.println("대댓 등록 성공");
				out.println("{\"result\" : \"대댓 등록 성공\"}");
				out.flush();
				out.close();
			}else {
				System.out.println("대댓 등록 실패");
				out.println("{\"result\" : \"대댓 등록 실패\"}");
				out.flush();
				out.close();
			}
			
						
			
		}catch(Exception e) {}
	
	}

}
