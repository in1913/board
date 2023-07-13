<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String user = (String) session.getAttribute("user"); 
	String snsUser = (String) session.getAttribute("snsUser");
	int level = 100;
	if(session.getAttribute("level") == null){
		
	}else{
		level = (int) session.getAttribute("level") ;
	}
%>
<!DOCTYPE html>   
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BBS</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" crossorigin="anonymous">
<link rel="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="stylesheet" href="css/summernote.min.css" />
</head>
<body>

<header class="bg-secondary">
	<div class="container">
		<div class="row py-2">
			<div class="col-md-5"></div>
			<div class="col-md-2 text-center">
				<h1><a href="index.jsp" class="brand">Board</a></h1>			
			</div>
			<div class="col-md-5 text-end">
				<nav class="top-nav text-end">
					<c:choose>
						<c:when test="${sessionScope.level == 0 || empty sessionScope.level}">
							<a href="index.jsp?fname=member/login">로그인</a>
							<a href="index.jsp?fname=member/register">회원가입</a>
						</c:when>
						<c:otherwise>
							<ul class="mem-welcome">
								<li class="welcome-sm">
									<a class="d-flex align-items-center justify-content-end"  href="javascript:showMemInfo();">
									<span class="header-user-img d-flex align-items-center">
<%
	if(MemberDDL.getSelect(user).get(0).getPhoto() == null){
%>			 		
								
									<img src="images/user-fill.png" alt="user">
								
<%
	}else{
%>
	
					 				<img src="/board/ShowMem" alt="user" id="userImg-header">
	
<%
	}
%>		 				
									</span>&nbsp;<%=user %> 님 환영합니다.</a>
										<ul class="show-info  bg-secondary">
											<li class="show-info-li bg-secondary">
												<div class="show-info-toggle">
													<a class="mem-welcome-a" href="index.jsp?fname=member/memberedit">회원정보수정</a>
													<hr />
													<a class="mem-welcome-a" href="/board/logout">로그아웃</a>
<%
	if(level == 99){
%>												
													<hr />
													<a class="mem-welcome-a" href="index.jsp?fname=member/manager.jsp?page=1">회원관리</a>				
<%
	}
%>												
												</div>
											</li>
										</ul>
								</li>
							</ul>
						</c:otherwise>
					</c:choose>
				</nav>			
			</div>
		</div>
		<div class="row">
		<div class="col-md-4">
			<nav class="navi pb-2 text-center">			
				<a href="index.jsp?fname=list/texts.jsp?bbsnum=1">공지사항</a>
				<a href="index.jsp?fname=list/texts.jsp?bbsnum=2">자유게시판</a>
			</nav>
		</div>
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<nav class="navi pb-2 text-center">
				<a href="index.jsp?fname=list/texts.jsp?bbsnum=3">인기글</a>
				<a href="index.jsp?fname=list/texts.jsp?bbsnum=4">컬럼게시판</a>
			</nav>
		</div>
		</div>
	</div>	
</header>
