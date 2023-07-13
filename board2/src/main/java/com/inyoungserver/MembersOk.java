package com.inyoungserver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.MemberDDL;
import board.MembersDTO;


public class MembersOk extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MembersDTO dto = new MembersDTO();
		MemberDDL ddl = new MemberDDL();
		
		String dir = req.getSession().getServletContext().getRealPath("./upload/users");
		System.out.println(dir);
		
		
		int max = 10 * 1024 * 1024;
		try {
			MultipartRequest m = new MultipartRequest(req, dir, max, "UTF-8",
					new DefaultFileRenamePolicy());
			
			dto.setUserid(m.getParameter("userid"));
			dto.setUserpass(m.getParameter("userpass"));
			dto.setUsername(m.getParameter("username"));
			dto.setUseremail(m.getParameter("useremail"));
			dto.setPostcode(Integer.parseInt(m.getParameter("postcode")));
			dto.setAddr(m.getParameter("addr"));
			dto.setDetailaddr(m.getParameter("detailaddr"));
			dto.setTel(m.getParameter("tel"));
			dto.setUip();
			dto.setPhoto(m.getFilesystemName("photo"));
			boolean isSuccess = ddl.insert(dto);
			if(isSuccess) {
				System.out.println("인서트 성공");
				HttpSession session = req.getSession();
				session.setAttribute("user", m.getParameter("userid"));
				session.setAttribute("level", 1);
				req.setAttribute("signIn", "회원가입을 환영합니다. 로그인 되었습니다.");
				req.getRequestDispatcher("index.jsp").forward(req, res);
				//res.sendRedirect("index.jsp");
			}else {
				System.out.println("인서트 실패");
			}
		}catch(Exception e) {}
		doGet(req, res);
		
		
	}

}
