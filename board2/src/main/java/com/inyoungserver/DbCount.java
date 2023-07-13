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

import board.BbsColDDL;
import board.BbsDDL;
import board.BbsNoticeDDL;
import board.BoardDDL;


public class DbCount extends HttpServlet {

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
			
			int num = jsonObj.get("num").getAsInt();
			int bbsnum = jsonObj.get("bbsnum").getAsInt();
			System.out.println("num" + num);
			System.out.println("bbsnum" + bbsnum);
			br.close();
			
			PrintWriter out = res.getWriter();
			
			boolean isSuccess = BoardDDL.DbCount(num);
			
			if(isSuccess) {
				System.out.println("조회수 카운트 성공");
				out.println("{\"result\" : \"조회수 카운트 성공\"}");
				out.flush();
				out.close();
				
			}else {
				System.out.println("조회수 카운트 실패");
				out.println("{\"result\" : \"조회수 카운트 실패\"}");
				out.flush();
				out.close();
			}
			
			
			out.flush();
			out.close();
			
		}catch(Exception e) {}
		
		
		
		
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
		
		
	}

}
