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


public class SnsMoreInfo extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberDDL ddl = new MemberDDL();
		MembersDTO dto = new MembersDTO();
		
		
		HttpSession session = req.getSession();
		String snsUser = (String) session.getAttribute("snsUser");
		
		String dir = req.getSession().getServletContext().getRealPath("./upload/users");
		System.out.println(dir);
		
		
		int max = 10 * 1024 * 1024;
		try {
			MultipartRequest m = new MultipartRequest(req, dir, max, "UTF-8",
					new DefaultFileRenamePolicy());
		
			dto.setUserid(m.getParameter("userid"));
			dto.setUsername(m.getParameter("username"));
			dto.setUseremail(m.getParameter("useremail"));
			dto.setPostcode(Integer.parseInt(m.getParameter("postcode")));
			dto.setAddr(m.getParameter("addr"));
			dto.setDetailaddr(m.getParameter("detailaddr"));
			dto.setTel(m.getParameter("tel"));
			dto.setWdate();
			dto.setUip();
			dto.setPhoto(m.getFilesystemName("photo"));
			
			boolean isSuccess = ddl.updateForSnsUser(dto, snsUser);
			if(isSuccess) {
				System.out.println("sns사용자 정보 입력 성공");
				session.setAttribute("level", 1);
				session.setAttribute("user", m.getParameter("userid"));
				req.setAttribute("signIn", "회원가입을 축하합니다. 로그인 되었습니다.");
				req.getRequestDispatcher("index.jsp").forward(req, res);
			}else {
				System.out.println("sns사용자 정보 입력 실패");
			}
		}catch(Exception e) {}
		doGet(req, res);
		
	}

}
