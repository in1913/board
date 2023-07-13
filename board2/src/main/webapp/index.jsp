<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! 
	String fname = null; 
	String signIn = null;
	String edit = null;
%>    
<%
	fname = request.getParameter("fname");
	if(fname == null || "null".equals(fname)){
		fname = "main.jsp";
	}else{
		fname += ".jsp";
	}
	signIn = (String) request.getAttribute("signIn");
	edit = (String) request.getAttribute("edit");
	// session.getAttribute();
	// System.out.println(session.getAttribute());
	
%>


<%@ include file="include/header.jsp" %>
<jsp:include page="<%=fname %>"/>
<div class="signIn">
	<p><%=signIn %></p>
	<h6 class="bg-secondary"><a href="javascript:loginClose()">확인</a></h6>
</div>
<div class="edit">
	<p><%=edit %></p>
	<h6 class="bg-secondary"><a href="javascript:editClose()">확인</a></h6>
</div>
<%
	if(signIn != null){
%>
<script>
	document.getElementsByClassName("signIn")[0].style.display = "block";
	
	function loginClose(){
		document.getElementsByClassName("signIn")[0].style.display = "none";
		location.href = "index.jsp";
	}	
</script>
<%
	}
%>
<%	
	if(edit != null){
%>
<script>
	document.getElementsByClassName("edit")[0].style.display = "block";
	
	function editClose(){
		document.getElementsByClassName("signIn")[0].style.display = "none";
		location.href = "index.jsp";
	}	
</script>
<%
	}
%>

<%@ include file="include/footer.jsp" %>
