package com.inyoungserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import board.MemberDDL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MemComfirm extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("application/json; charset=utf-8");
		
		doPost(req, res);
		
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		StringBuffer jb = new StringBuffer();
		String line = null;
		
		MemberDDL ddl = new MemberDDL();
		
		try {
			BufferedReader br = req.getReader();
			while((line = br.readLine()) != null) 
				jb.append(line);
			
			System.out.println(jb);
			
			JsonObject jsonObj = (JsonObject) JsonParser.parseString(jb.toString());
			
			String userid = jsonObj.get("userid").getAsString();
						
			br.close();
			
			boolean result = ddl.useridCheck(userid);
			PrintWriter out = res.getWriter();
			
			out.println(result);
			out.flush();
			out.close();
			
						
			
		}catch(Exception e) {}
		
		
		
	}

}
