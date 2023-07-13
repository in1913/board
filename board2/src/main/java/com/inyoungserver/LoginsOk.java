package com.inyoungserver;

import java.io.IOException;

import board.MemberDDL;
import board.MembersDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginsOk extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MembersDTO dto = new MembersDTO();
		MemberDDL db = new MemberDDL();
		String url = null;
		
		dto.setUserid(req.getParameter("userid"));
		dto.setUserpass(req.getParameter("userpass"));
		
		int isSuccess = db.checkLogin(dto);
		if(isSuccess > 0) {
			// 세션 등록
			url = "index.jsp";
			
			// 로그인  후 uri 받아와서 새로고침 화면으로 !!
			HttpSession session = req.getSession();
			
			// user,level  세션 등록
			session.setAttribute("user", req.getParameter("userid"));
			session.setAttribute("level", isSuccess);
			System.out.println("로그인 성공");
			res.sendRedirect(url);
		}else {
			url = "?fname=member/login";
			req.setAttribute("message", "아이디 또는 비밀번호가 틀렸습니다.");
			req.getRequestDispatcher(url).forward(req, res);
			System.out.println("로그인 실패");
			
		}
		doGet(req, res);
	}

}
