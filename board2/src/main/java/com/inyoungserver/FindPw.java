package com.inyoungserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import board.MemberDDL;
import board.OCIEmail;
import board.RandomPassword;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FindPw extends HttpServlet {
	
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
			
			String uid = jsonObj.get("userid").getAsString();
			String uemail = jsonObj.get("useremail").getAsString();
			
			String upass = MemberDDL.findMem("userid", "useremail", uid, uemail, "userpass");
			
			String newPass = RandomPassword.getRandomPassword(10);
			int updateRs = MemberDDL.updatePw(newPass, uid);
			
			br.close();
			PrintWriter out = res.getWriter();
			if(upass == "" || upass.isEmpty()) {
				out.print(false);
				System.out.println("일치하는 정보 없음");
			}else {
				out.print("{\"userpass\" : \"" + upass + "\"}");
				//MailSender.sendMail(uemail, "임시 비밀번호는[" + newPass + "]입니다. 보안을 위해 로그인 후 변경해주세요.");
				OCIEmail.sendOCIEmail(uemail, "임시 비밀번호는[" + newPass + "]입니다. \t 보안을 위해 로그인 후 변경해주세요.");
			}
			out.flush();   
			out.close();
		}catch(Exception e) {};
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
		doGet(req,res);
	}

}
