<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BBS</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" crossorigin="anonymous">
<link rel="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<style>
footer{
	position: fixed;
	width: 100%;
	border-top: 1px solid #ddd;
	bottom: 0;
	left: 0;
	text-align: center;
	background: #ddd;
	padding-top: 5px;
	padding-bottom: 5px;
	font-size: 14px;
	color: #999;
}
a, a:link{
	color: #333;
	text-decoration: none;
}
</style>
</head>
<body>
<ul class="nav nav-tabs" id="myTab" role="tablist">
	<li class="nav-item" role="presentation">
		<button class="nav-link active" id="id-tab" data-bs-toggle="tab" data-bs-target="#id" type="button" role="tab"
		aria-controls="id" aria-selected="true">
		아이디 찾기
		</button>
	</li>
	<li class="nav-item" role="presentation">
		<button class="nav-link" id="pwd-tab" data-bs-toggle="tab" data-bs-target="#pwd" type="button" role="tab"
		aria-controls="pwd" aria-selected="true">
		비밀번호 찾기
		</button>
	</li>
</ul>
<div class="tab-content" id="myTabcontent">
	<div class="tab-pane fade show active" id="id" role="tabpanel" aria-labellelby="id-tab">
		<form action="/board/findIdPw" class="findIdform mt-5">
			<div class="mb-3 px-5">
				<input type="text" name="username" id="username" placeholder="이름" class="form-control"/>
			</div>
			<div class="mb-2 px-5">
				<input type="email" name="useremail" id="useremail" placeholder="이메일" class="form-control"/>
			</div>
			<div class="text-center mt-5">
				<button type="button" class="btn btn-success"> 찾기</button>
			</div>
			<input type="hidden" value="1"/>
		</form>
	</div>
	<div class="tab-pane fade show" id="pwd" role="tabpanel" aria-labellelby="pwd-tab">
		<form action="/board/findIdPw" class="findIdform mt-5">
			<div class="mb-3 px-5">
				<input type="text" name="username" id="username" placeholder="아이디" class="form-control"/>
			</div>
			<div class="mb-2 px-5">
				<input type="email" name="useremail" id="useremail" placeholder="이메일" class="form-control"/>
			</div>
			<div class="text-center mt-5">
				<button type="button" class="btn btn-success"> 찾기</button>
			</div>
			<input type="hidden" value="2"/>
		</form>
	</div>
	</div>

 
 
 
<footer>
	<a href="index.jsp">Copyright &copy; GomGomGold all right reserved.</a>
</footer>
<script src="../js/popper.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/jquery.min.js"></script>
<script src="../js/script.js"></script>
<script src="../js/cookie.js"></script> 
<script src="../js/form.js"></script>
</body>
</html>