package com.inyoungserver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BbsColDDL;
import board.BbsColDTO;
import board.BbsDDL;
import board.BbsDTO;
import board.BbsNoticeDDL;
import board.BbsNoticeDTO;
import board.BoardDDL;
import board.BoardDTO;


public class ModifyText extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int bbsnum = Integer.parseInt(req.getParameter("bbsnum"));
		int num = Integer.parseInt(req.getParameter("num")); 
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		System.out.println(num);
		System.out.println(bbsnum);
		System.out.println(title);
		System.out.println(content);
		
		BoardDTO dto = new BoardDTO();
		dto.setNum(num);
		dto.setTitle(title);
		dto.setContent(content);
		boolean isSuccess = BoardDDL.ModifiedText(dto);
		if(isSuccess) {
			System.out.println("게시판 업데이트 성공");
			res.sendRedirect("index.jsp?fname=list/text.jsp?bbsnum=" + bbsnum + "&num=" + num);
		}else {
			System.out.println("게시판 업데이트 실패");
		}
		
		doGet(req, res);
	}

}
