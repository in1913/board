<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container findmem mt-5">
<ul class="nav nav-tabs nav-pills" id="myTab" role="tablist">
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
				<input type="text" name="useremail" id="useremail" placeholder="이메일" class="form-control useremail"/>
			</div>
			<div class="text-center mt-5">
				<button type="button" class="btn btn-success"  onclick="findId();"> 찾기</button>
			</div>
		</form>
	</div>
	<div class="tab-pane fade show" id="pwd" role="tabpanel" aria-labellelby="pwd-tab">
		<form action="/board/findIdPw" class="findIdform mt-5">
			<div class="mb-3 px-5">
				<input type="text" name="userid" id="userid" placeholder="아이디" class="form-control"/>
			</div>
			<div class="mb-2 px-5">
				<input type="text" name="useremail" id="useremail" placeholder="이메일" class="form-control useremail"/>
			</div>
			<div class="text-center mt-5">
				<button type="button" class="btn btn-success" onclick="findPw();"> 찾기</button>
			</div>
		</form>
	</div>
</div>
	

</div>
	
<div class="get-email">
<p class="find-message"></p>
<h6 class="bg-secondary"><a href="javascript:getEmailClose()">확인</a></h6>
</div>