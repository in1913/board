package com.inyoungserver;

import java.io.IOException;

import javax.mail.Session;

import board.OauthService;
import board.SnsLogout;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class KakaoLogout extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String token = (String) session.getAttribute("token");
		SnsLogout.getKakaoJson(token);
	}

}
