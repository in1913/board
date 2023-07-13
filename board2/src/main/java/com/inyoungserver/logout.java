package com.inyoungserver;

import java.io.IOException;
import java.io.PrintWriter;

import board.SnsLogout;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class logout extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("level");
		session.removeAttribute("user");
		String snsUser = "";
		if((String) session.getAttribute("snsUser") == null) {
			
		}else {
			snsUser = (String) session.getAttribute("snsUser");
		}
		
		
		if(snsUser.contains("kakao")) {
			System.out.println("카카오 사용자 로그아웃: " + snsUser);
			session.removeAttribute("snsUser");
		}else {
			System.out.println("카카오 사용자 아님");
		}
		
		if(snsUser.contains("naver")) {
			System.out.println("네이버 사용자 로그아웃: " + snsUser);
			session.removeAttribute("snsUser");
		}else {
			System.out.println("네이버 사용자 아님");
		}
		
		if(snsUser.contains("google")) {
			System.out.println("구글 사용자 로그아웃: " + snsUser);
			session.removeAttribute("snsUser");
		}else {
			System.out.println("구글 사용자 아님");
		}
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("UTF-8");
		String script = "<script>alert('로그아웃되었습니다.');"
				+ "document.location.href='/board';"
				+ "</script>";
		out.println("<html><head></head><body>");
		out.println(script);
		out.println("</body></html>");
		
	}	

}
