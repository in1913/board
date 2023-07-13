package com.inyoungserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import board.MemberDDL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindId extends HttpServlet {
	
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
			
			System.out.println(jb);
			
			JsonObject jsonObj = (JsonObject) JsonParser.parseString(jb.toString());
			System.out.println(jsonObj);
			
			
			String uname = jsonObj.get("username").getAsString();
			String uemail = jsonObj.get("useremail").getAsString();
			
			
			/*JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(jb.toString());
			
			String uname = element.getAsJsonObject().get("username").getAsString();
			String uemail = element.getAsJsonObject().get("useremail").getAsString();
			
			System.out.println(uname);
			System.out.println(uemail);
			*/
			
			br.close();
			
			String userid = MemberDDL.findMem("username", "useremail", uname, uemail, "userid");
			PrintWriter out = res.getWriter();
			if(userid == "" || userid.isEmpty()) {
				out.print(false);
				System.out.println("일치하는 정보 없음");
			}else {
				out.print("{\"userid\" : \"" + userid + "\"}");
			}
			out.flush();   
			out.close();
			

			
		}catch(Exception e) {}
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
