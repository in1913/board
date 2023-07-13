package com.inyoungserver;

import java.io.IOException;

import board.CreateSnsUser;
import board.MemberDDL;
import board.MembersDTO;
import board.OauthService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class KakaoOauth extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String code = req.getParameter("code");
		String token = OauthService.getKakaoAccessToken(code);
		String[] KakaoInfo = CreateSnsUser.getKakaoJson(token);
		String id = KakaoInfo[0];
		String kakaoEmail = KakaoInfo[1];
		String nickname = KakaoInfo[2];
		
		String snsUser = "kakao" + id;
		
		req.setAttribute("userEmail", kakaoEmail);
		
		// 이메일을 받아 바로 디비에 인서트?
		
		// 로그인과 회원가입 구분
		MembersDTO dto = new MembersDTO();
		MemberDDL ddl = new MemberDDL();
		HttpSession session = req.getSession();
		dto.setSnsuser(snsUser);
		dto.setUserid(nickname);
		dto.setUserpass(null);
		dto.setUsername(null);
		dto.setUseremail(kakaoEmail);
		dto.setPostcode(-1);
		dto.setAddr(null);
		dto.setDetailaddr(null);
		dto.setTel(null);
		dto.setUip();
		String isMem = ddl.snsUserCheck(snsUser);
		if(isMem != null) {
			// sns 사용자가 회원가입되어 있을 때
			session.setAttribute("snsUser", snsUser);
			session.setAttribute("user", isMem);
			session.setAttribute("level", 1);
			req.setAttribute("signIn", "환영합니다. 로그인 되었습니다.");
			req.getRequestDispatcher("index.jsp").forward(req, res);
		}else {
			// sns 사용자가 회원가입되어 있지 않을 때
			boolean isSuccess = ddl.insertSns(dto);
			if(isSuccess) {
				System.out.println("인서트 성공");
				
				session.setAttribute("snsUser", snsUser);
				session.setAttribute("user", isMem);
				res.sendRedirect("index.jsp?fname=member/snsRegister");
			}else {
				System.out.println("인서트 실패");
			}
		}
		
		
		
		
	}

}
