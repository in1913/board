package com.inyoungserver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.BbsColDDL;
import board.BbsColDTO;
import board.BbsDDL;
import board.BbsDTO;
import board.BbsNoticeDDL;
import board.BbsNoticeDTO;
import board.BoardDDL;
import board.BoardDTO;

public class TextSubmit extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("user");
		int bbsnum = Integer.parseInt(req.getParameter("bbsnum"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		BoardDTO dto = new BoardDTO();
		dto.setBbsnum(bbsnum);
		dto.setUserid(user);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setUip();
		boolean isSuccess = BoardDDL.insert(dto);
		if(isSuccess) {
			System.out.println("게시판 인서트 성공");
			res.sendRedirect("index.jsp?fname=list/texts.jsp?bbsnum=" + bbsnum);
		}else {
			System.out.println("게시판 인서트 실패");
		}		
		doGet(req, res);
		
	}

}
