package com.inyoungserver;

import java.io.IOException;

import board.MemberDDL;
import board.MembersDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberEdit extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberDDL ddl = new MemberDDL();
		MembersDTO dto = new MembersDTO();
		
		HttpSession session = req.getSession();
		String userid = (String) session.getAttribute("user");
		
		String dir = req.getSession().getServletContext().getRealPath("./upload/users");
		System.out.println(dir);
		
		String url = "index.jsp";
		
		int max = 10 * 1024 * 1024;
		try {
			MultipartRequest m = new MultipartRequest(req, dir, max, "UTF-8",
					new DefaultFileRenamePolicy());
			
			dto.setUserpass(m.getParameter("userpass"));
			dto.setUsername(m.getParameter("username"));
			dto.setUseremail(m.getParameter("useremail"));
			dto.setPostcode(Integer.parseInt(m.getParameter("postcode")));
			dto.setAddr(m.getParameter("addr"));
			dto.setDetailaddr(m.getParameter("detailaddr"));
			dto.setTel(m.getParameter("tel"));
			dto.setWdate();
			dto.setUip();
			dto.setPhoto(m.getFilesystemName("photo"));
			ddl.update(dto, userid);
			req.setAttribute("edit", "수정되었습니다.");
			req.getRequestDispatcher(url).forward(req, res);
			
		}catch(Exception e) {}
		doGet(req, res);
	}

}
