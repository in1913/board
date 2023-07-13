package com.inyoungserver;

import java.io.IOException;
import java.io.PrintWriter;

import board.MemberDDL;
import board.MembersDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateLevel extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MembersDTO dto = new MembersDTO();
		
		dto.setLevel(Integer.parseInt(req.getParameter("level")));
		int result = MemberDDL.updateLevel(dto, req.getParameter("userid"));
		PrintWriter out = res.getWriter();
		out.println(result);
		out.flush();
		out.close();
		
		
	}

}
