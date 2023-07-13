package com.inyoungserver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.MemberDDL;
import board.MembersDTO;


public class ShowMem extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String user =  (String) session.getAttribute("user");
		String fileName = MemberDDL.selectPhoto(user);
		
		if(fileName != null) {
			File file = new File(fileName);
			
			if(file.getName().endsWith(".svg")) {
				System.out.println("svg 파일입니다.");
				res.setContentType("image/svg+xml");
				byte [] buffer = new byte[1024];
				ServletOutputStream so = res.getOutputStream();
				
				String dir = req.getSession().getServletContext().getRealPath("./upload/users");

				try {
					String img = dir + "/" + fileName;
					
					BufferedInputStream in = new BufferedInputStream(new FileInputStream(img));
					int i = 0;
					while((i = in.read(buffer, 0, 1024)) != -1) {
						so.write(buffer, 0, i);
					}
					so.close();
					in.close();
					
				}catch(Exception e) {
					e.printStackTrace();
				}

			}else {
				res.setContentType("image/jpeg");
				byte [] buffer = new byte[1024];
				ServletOutputStream so = res.getOutputStream();
				
				String dir = req.getSession().getServletContext().getRealPath("./upload/users");

				try {
					String img = dir + "/" + fileName;
					
					BufferedInputStream in = new BufferedInputStream(new FileInputStream(img));
					int i = 0;
					while((i = in.read(buffer, 0, 1024)) != -1) {
						so.write(buffer, 0, i);
					}
					so.close();
					in.close();
					
				}catch(Exception e) {
					e.printStackTrace();
				}

			}
			
		}
		
		
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

}
