$(document).ready(function(){
	$(".registerform .passwordsee").on("click", function(){
		$('.registerform input.input-pass').toggleClass('active');
        if($('.registerform input.input-pass').hasClass('active')){
            $(this).attr('class',"fa fa-eye fa-lg passwordsee")
            .prev('.registerform input.input-pass').attr('type',"text");
        }else{
            $(this).attr('class',"fa fa-eye-slash fa-lg passwordsee")
            .prev('.registerform input.input-pass').attr('type','password');
        }
	})
	
	
	$(".loginform .passwordsee").on("click", function(){
		$('.loginform input.input-pass').toggleClass('active');
        if($('.loginform input.input-pass').hasClass('active')){
            $(this).attr('class',"fa fa-eye fa-lg passwordsee")
            .prev('.loginform input.input-pass').attr('type',"text");
        }else{
            $(this).attr('class',"fa fa-eye-slash fa-lg passwordsee")
            .prev('.loginform input.input-pass').attr('type','password');
        }
	})
	
})
function showMemInfo(){
	const showInfo = document.getElementsByClassName("show-info")[0];
	showInfo.classList.toggle("active");
}


function findIdPw(){
	const url = "/board/member/findIdPw.jsp";
	const width = "500px";
	const height = "360px";
	let left = (document.body.offsetWidth / 2) - (width / 2);
	let top = (document.body.offsetHeight / 2) - (height / 2);
	left += window.screenLeft;
	document.findidform.submit();
	window.open(url, "popup", `width=${width}, height=${height}, left = ${left}, top = ${top}`);
	
}

function editMember(n){
	document.getElementsByClassName("editpopup")[n].style.display = "block";
	document.getElementsByClassName("shadow")[0].style.display = "block";
}
function editMClose(n){
	document.getElementsByClassName("editpopup")[n].style.display = "none";
	document.getElementsByClassName("shadow")[0].style.display = "none";
}
function updateLevel(n){
	const level = document.getElementsByClassName("userlevel")[n].value;
	const userid = document.getElementsByClassName("userid")[n].value;
	const list = ["계정정지", "휴면회원", "신규회원", "일반회원", "관리자"];
	const intlist = [-1, 0, 1, 2, 99];
	let levelRs = "";
	for(i = 0; i < list.length; i++){
		if(level == intlist[i]){
			levelRs = list[i];
		}
	}
	console.log(userid);
	$.ajax({
		url : "/board/UpdateLevel",
		type : "post",
		data : {level : level, userid: userid},
		dataType: "json",
		success: function(result){
			if(result == 0){
				alert("수정에 실패했습니다.");
				  
			}else{
				alert("수정되었습니다.");
				document.getElementsByClassName("editlevel")[n].innerHTML = levelRs; 
			}
		}
	})
	editMClose(n);
}

function uploadFile(){
	const upload = document.querySelector(".upload");
	upload.click();
}

function showFile(){
	const upload = document.querySelector(".upload");
	let filename = upload.files[0];
	const filebox = document.getElementsByClassName("filebox")[0];
	let fileLength = document.getElementsByClassName("fileshow").length;
	
	
	let str = "";
	str += `<div class="fileshow">`;
	str += `<div class="imgshow">`;
	str += `<img src=${URL.createObjectURL(filename)} alt="img">`;
	str += `</div>`;
	str += `<div class="nameshow">`;
	str += `${filename.name}`;
	str += `</div><a href="javascript:imgClose(${fileLength});" class="img-close">X</div>`;	
	filebox.insertAdjacentHTML('beforeend', str);
}

function imgClose(n){
	const fileshow = document.getElementsByClassName("fileshow")[n];
	fileshow.remove();
}


function boardSubmit(){
	const boardform = document.boardform;
	var text = boardform.content.value;
	const title = boardform.title.value;
	if(title == null || title == ""){
		alert("제목을 입력해주세요.")
	}else if(text == null || text == ""){
		alert("내용을 입력해주세요.")
	}else{
		boardform.submit();
	}
}

$(document).ready(function(){
	$("#summernote").summernote({
		theme: 'darkly',
		height: 400,
		lang: 'ko-KR',
		spellCheck: "false",
		toolbar: [
          ['style', ['style']],
          ['font', ['bold', 'underline', 'clear']],
          ['color', ['color']],
          ['para', ['ul', 'ol', 'paragraph']],
          ['table', ['table']],
          ['insert', ['link', 'picture', 'video']],
          ['view', ['fullscreen', 'codeview', 'help']]
        ]
	});
})

/*******05.22. 17:57:20 ********* */
function userCheck(){
	const user = document.getElementById("user").value;
	const bbsnum = document.getElementById("bbsnum").value;
	if(user == "null" || user == ""){
		alert("로그인을 해주세요.");
		location.href="index.jsp?fname=member/login";
	}else if(bbsnum == 1){
		location.href="index.jsp?fname=list/edit.jsp?bbsnum=1";
	}else if(bbsnum == 2){
		location.href="index.jsp?fname=list/edit.jsp?bbsnum=2";
	}else if(bbsnum == 3){
		location.href="index.jsp?fname=list/edit.jsp?bbsnum=3";
	}else if(bbsnum == 4){
		location.href="index.jsp?fname=list/edit.jsp?bbsnum=4";
	}
}

function userAdminCheck(){
	const user = document.getElementById("user").value;
	if(user != "admin"){
		alert("공지사항은 관리자만 글을 쓸 수 있습니다.");
	}else{
		location.href="index.jsp?fname=list/edit.jsp?bbsnum=1";
	}
}

function viewClick(n){
	let num = document.getElementsByClassName("num")[n].value;
	let bbsnum = document.getElementById("bbsnum").value;
	fetch("/board/DbCount", {
		headers: {"Content-Type" : "application/json"},
		method : "post",
		body : JSON.stringify({
			num : num, bbsnum : bbsnum
		})
	}).then((res) => res.json())
	.then(() => {
		
	})
}
/*********** 여기하고 있었다!!!!!!!!!!!!! *********** */
function makeComment(){
	const num = document.getElementById("num").value;
	const user = document.getElementById("user").value;
	const comment = document.getElementById("comment").value;
	const bbsnum = document.getElementById("bbsnum").value;
	if(user == "null" || user == ""){
		alert("로그인을 해주세요.");
		location.href="index.jsp?fname=member/login";
	}else{
		fetch("/board/CommentSubmit", {
		headers: {"Content-Type" : "application/json"},
		method : "post",
		body : JSON.stringify({
			num: num, user : user, comment : comment, bbsnum : bbsnum
			})
		}).then((res) => res.json())
		.then((result) => {
			console.log(result.result)
			location.reload();
		})
	}
	
}

function deleteText(){
	const num = document.getElementById("num").value;
	const bbsnum = document.getElementById("bbsnum").value;
	if(confirm("정말 글을 삭제하시겠습니까?")){
		fetch("/board/DeleteText", {
		headers: {"Content-Type" : "application/json"},
		method : "post",
		body : JSON.stringify({
			num: num, bbsnum : bbsnum
			})
		}).then((res) => res.json())
		.then((result) => {
			location.href="index.jsp?fname=list/texts.jsp?bbsnum=" + bbsnum;
		})	
	}else{
		
	}
	
}


function modifiedText(){
	const modiform = document.modiform;
	var text = modiform.content.value;
	const title = modiform.title.value;
	
	if(title == null || title == ""){
		alert("제목을 입력해주세요.")
	}else if(text == null || text == ""){
		alert("내용을 입력해주세요.")
	}else{
		modiform.submit();
	}
}


function deleteCmt(n){
	var cmtnum = document.getElementsByClassName("cmtnum")[n].value;
	var textnum = document.getElementById("num").value;
	var bbsnum = document.getElementById("bbsnum").value;
	
	if(confirm("정말 댓글을 삭제하시겠습니까?")){
		fetch("/board/DeleteCmt", {
		headers: {"Content-Type" : "application/json"},
		method : "post",
		body : JSON.stringify({
			textnum : textnum, cmtnum : cmtnum, bbsnum : bbsnum
			})
		}).then((res) => res.json())
		.then((result) => {
			location.reload();
		})		
	}else{
		
	}
	
}

function modifiedCmt(n){
	const modibox = document.getElementsByClassName("comment-modibox")[n];
	const cnt_origin = document.getElementsByClassName("comment-origin")[n];
	modibox.style.display = "block";
	cnt_origin.style.display = "none";
	
}

function closeModiCmt(n){
	const modibox = document.getElementsByClassName("comment-modibox")[n];
	const cnt_origin = document.getElementsByClassName("comment-origin")[n];
	modibox.style.display = "none";
	cnt_origin.style.display = "block";
}

function submitModiCmt(n){
	const bbsnum = document.getElementById("bbsnum").value;
	const modi_cmt = document.getElementsByClassName("modi-comment")[n].value;
	const cmtnum = document.getElementsByClassName("cmtnum")[n].value;
	const textnum = document.getElementById("num").value; 
	console.log("modi_cmt : " + modi_cmt);
	console.log("cmtnum : " + cmtnum);
	console.log("textnum : " + textnum);
	fetch("/board/ModiCmt", {
		headers : {"Content-Type" : "application/json"},
		method : "post",
		body : JSON.stringify({
			cmtnum : cmtnum, textnum : textnum, comment : modi_cmt, bbsnum : bbsnum
		})
	}).then((res) => res.json())
	.then((result) => {
		location.reload();
	})
}

function showIP(n){
	document.getElementsByClassName("ip-click")[n].style.display = "none";
	document.getElementsByClassName("ip-show")[n].style.display = "inline-block";
}

function hideIP(n){
	document.getElementsByClassName("ip-click")[n].style.display = "inline-block";
	document.getElementsByClassName("ip-show")[n].style.display = "none";
}

function doReply(n){
	const reply = document.getElementsByClassName("write-reply")[n];
	reply.style.display = "block";
}



function makeReply(n){
	const cmtnum = document.getElementsByClassName("cmtnum")[n].value;
	const textnum = document.getElementById("num").value;
	const bbsnum = document.getElementById("bbsnum").value;
	const userid = document.getElementById("user").value;
	const reply = document.getElementsByClassName("reply")[n].value;
	if(userid == "null" || userid == ""){
		alert("로그인을 해주세요.");
		location.href="index.jsp?fname=member/login";
	}else{
		fetch("/board/ReplySubmit", {
		headers: {"Content-Type" : "application/json"},
		method : "post",
		body : JSON.stringify({
			cmtnum: cmtnum, textnum: textnum, bbsnum: bbsnum,
			userid : userid, reply : reply
			})
		}).then((res) => res.json())
		.then((result) => {
			location.reload();
		})
	}
}



function showReplyIP(n, m){
	document.getElementsByClassName("ip-reply-click" + n)[m].style.display = "none";
	document.getElementsByClassName("ip-reply-show" + n)[m].style.display = "inline-block";
}

function hideReplyIP(n, m){
	document.getElementsByClassName("ip-reply-click" + n)[m].style.display = "inline-block";
	document.getElementsByClassName("ip-reply-show" + n)[m].style.display = "none";
}

function modifiedReply(n, m){
	const modibox = document.getElementsByClassName("reply-modibox" + n)[m];
	const reply_origin = document.getElementsByClassName("reply-origin" + n)[m];
	modibox.style.display = "block";
	reply_origin.style.display = "none";
}
function closeModiReply(n ,m){
	const modibox = document.getElementsByClassName("reply-modibox" + n)[m];
	const reply_origin = document.getElementsByClassName("reply-origin" + n)[m];
	modibox.style.display = "none";
	reply_origin.style.display = "block";
}
function submitModiReply(n, m){
	const modi_reply = document.getElementsByClassName("modi-reply" + n)[m].value;
	const replynum = document.getElementsByClassName("replynum" + n)[m].value;
	const cmtnum = document.getElementsByClassName("cmtnum")[n].value;
	const textnum = document.getElementById("num").value;
	const bbsnum = document.getElementById("bbsnum").value; 
	fetch("/board/ModiReply", {
		headers : {"Content-Type" : "application/json"},
		method : "post",
		body : JSON.stringify({
			 replynum : replynum, cmtnum : cmtnum, textnum : textnum, reply : modi_reply, bbsnum : bbsnum
		})
	}).then((res) => res.json())
	.then((result) => {
		location.reload();
	})	
}

function deleteReply(n, m){
	const replynum = document.getElementsByClassName("replynum" + n)[m].value;
	var cmtnum = document.getElementsByClassName("cmtnum")[n].value;
	var textnum = document.getElementById("num").value;
	const bbsnum = document.getElementById("bbsnum").value;
	/*
	console.log("replynum : " + replynum);
	console.log("cmtnum : " + cmtnum);
	console.log("textnum : " + textnum);
	*/
	if(confirm("정말 대댓을 삭제하시겠습니까?")){
		fetch("/board/DeleteReply", {
		headers: {"Content-Type" : "application/json"},
		method : "post",
		body : JSON.stringify({
			replynum : replynum, textnum : textnum, cmtnum : cmtnum, bbsnum : bbsnum
			})
		}).then((res) => res.json())
		.then((result) => {
			location.reload();
		})		
	}else{
		
	}
}

function doLike(){
	const bbsnum = document.getElementById("bbsnum").value;
	const textnum = document.getElementById("num").value;
	const userid = document.getElementById("user").value;
	// 좋아요 수 나타낼 곳
	var dolike = document.getElementsByClassName("doLike")[0];   
	
	// 좋아요 버튼 나타날 곳
	const doLikeBox = document.getElementsByClassName("doLikeBox")[0];
	 // 좋아요 했는지 안했는지 확인 
	var checkDoLike = document.getElementById("doLike").value;
	// 좋아요 수 나타낼 박스 - 좋아요가 0일 경우에 대비
	var likeNumBox = document.getElementsByClassName("showLikeNum")[0]; 
	if(userid == "null" || userid == ""){
		alert("로그인을 해주세요.");
	}else{
		if(checkDoLike == 1){
			fetch("/board/LikeDelete", {
			headers : {"Content-Type" : "application/json"},
			method : "post",
			body : JSON.stringify({
				bbsnum : bbsnum, textnum : textnum, cmtnum : -1, replynum : -1, userid : userid
				})
			}).then((res) => res.json())
			.then((result) => {
			console.log(result.result);
				if(result.result == 1){
					likeNumBox.innerHTML = `<div class="d-inline-block text-center text-white mb-3" style="width: 40px; background: #dc3545; border-radius: 4px; font-size: 12px;"><img src="images/heart-fill.png" style="width: 13px; object-position: 0px -2px;" alt="like" />		 
											<span class="doLike">${result.result}</span>
											</div>`;
					doLikeBox.innerHTML = `<a href="javascript:doLike();" class="d-inline-block text-center text-white p-2 " style="margin-right: 5px;"><img src="images/heart-line.png" style="width: 24px; object-position: 0 -2px;" alt="like" />
											<input type="hidden" value="0" id="doLike" />
											</a>`;
				}else if(result.result > 1){
					dolike.innerHTML = result.result;
					doLikeBox.innerHTML = `<a href="javascript:doLike();" class="d-inline-block text-center text-white p-2 " style="margin-right: 5px;"><img src="images/heart-line.png" style="width: 24px; object-position: 0 -2px;" alt="like" />
											<input type="hidden" value="0" id="doLike" />
											</a>`; 
				}else if(result.result == 0){
					likeNumBox.innerHTML = "";
					doLikeBox.innerHTML = `<a href="javascript:doLike();" class="d-inline-block text-center text-white p-2 " style="margin-right: 5px;"><img src="images/heart-line.png" style="width: 24px; object-position: 0 -2px;" alt="like" />
											<input type="hidden" value="0" id="doLike" />
											</a>`; 
				}
			})
		}else{
			fetch("/board/LikeCnt", {
			headers : {"Content-Type" : "application/json"},
			method : "post",
			body : JSON.stringify({
				bbsnum : bbsnum, textnum : textnum, cmtnum : -1, replynum : -1, userid : userid
				})
			}).then((res) => res.json())
			.then((result) => {
				if(result.result == 1){
					likeNumBox.innerHTML = `<div class="d-inline-block text-center text-white mb-3" style="width: 40px; background: #dc3545; border-radius: 4px; font-size: 12px;"><img src="images/heart-fill.png" style="width: 13px; object-position: 0px -2px;" alt="like" />		 
											<span class="doLike">${result.result}</span>
											</div>`;
					doLikeBox.innerHTML = `<a href="javascript:doLike();" class="d-inline-block text-center text-white p-2 " style="margin-right: 5px;"><img src="images/heart-fill-red.png" style="width: 24px; object-position: 0 -2px;" alt="like" />
											<input type="hidden" value="1" id="doLike" />
											</a>`;
				}else if(result.result > 1){
					dolike.innerHTML = result.result;
					doLikeBox.innerHTML = `<a href="javascript:doLike();" class="d-inline-block text-center text-white p-2 " style="margin-right: 5px;"><img src="images/heart-fill-red.png" style="width: 24px; object-position: 0 -2px;" alt="like" />
											<input type="hidden" value="1" id="doLike" />
											</a>`; 
				}else if(result.result == 0){
					likeNumBox.innerHTML = "";
					doLikeBox.innerHTML = `<a href="javascript:doLike();" class="d-inline-block text-center text-white p-2 " style="margin-right: 5px;"><img src="images/heart-fill-red.png" style="width: 24px; object-position: 0 -2px;" alt="like" />
											<input type="hidden" value="1" id="doLike" />
											</a>`; 
				}
			})	
		}
					
	}
}

function doCmtLike(n){
	const bbsnum = document.getElementById("bbsnum").value;
	const textnum = document.getElementById("num").value;
	const cmtnum = document.getElementsByClassName("cmtnum")[n].value;
	const userid = document.getElementById("user").value;
	
	// 좋아요 수 나타낼 곳
	var dolike = document.getElementsByClassName("doCmtLike")[n];    
	
	// 좋아요 버튼 나타날 곳
	const doLikeBox = document.getElementsByClassName("doCmtLikeBox")[n];
	 // 좋아요 했는지 안했는지 확인 
	var checkDoLike = document.getElementById("doCmtLike" + n).value;
	// 좋아요 수 나타낼 박스 - 좋아요가 0일 경우에 대비
	var likeNumBox = document.getElementsByClassName("showCmtLikeNum")[n];
	if(userid == "null" || userid == ""){
		alert("로그인을 해주세요.");
	}else{
		if(checkDoLike == 1){
			fetch("/board/LikeDelete", {
			headers : {"Content-Type" : "application/json"},
			method : "post",
			body : JSON.stringify({
				bbsnum : bbsnum, textnum : textnum, cmtnum : cmtnum, replynum : -1, userid : userid
				})
			}).then((res) => res.json())
			.then((result) => {
				if(result.result == 1){
					likeNumBox.innerHTML = `<div class="d-inline-block text-center text-white mx-2 mt-2" style="width: 40px; background: #dc3545; border-radius: 4px; font-size: 12px;"><img src="images/heart-fill.png" style="width: 13px; object-position: 0px -2px;" alt="like" />		 
											<span class="doCmtLike">${result.result}</span>
											</div>`;
					doLikeBox.innerHTML = `<a href="javascript:doCmtLike(${n});" class="d-inline-block text-center text-white" style="margin: 0;"><img src="images/heart-line.png" style="width: 17px; object-fit: cover; object-position: 0 -1px;" alt="like" />
											<input type="hidden" value="0" id="doCmtLike${n}" />
											</a>`;
				}else if(result.result > 1){
					dolike.innerHTML = result.result;
					doLikeBox.innerHTML = `<a href="javascript:doCmtLike(${n});" class="d-inline-block text-center text-white" style="margin: 0;"><img src="images/heart-line.png" style="width: 17px; object-fit: cover; object-position: 0 -1px;" alt="like" />
											<input type="hidden" value="0" id="doCmtLike${n}" />
											</a>`; 
				}else if(result.result == 0){
					likeNumBox.innerHTML = "";
					doLikeBox.innerHTML = `<a href="javascript:doCmtLike(${n});" class="d-inline-block text-center text-white" style="margin: 0;"><img src="images/heart-line.png" style="width: 17px; object-fit: cover; object-position: 0 -1px;" alt="like" />
											<input type="hidden" value="0" id="doCmtLike${n}" />
											</a>`; 
				}
			})
		}else{
			fetch("/board/LikeCnt", {
			headers : {"Content-Type" : "application/json"},
			method : "post",
			body : JSON.stringify({
				bbsnum : bbsnum, textnum : textnum, cmtnum : cmtnum, replynum : -1, userid : userid
				})
			}).then((res) => res.json())
			.then((result) => {
				if(result.result == 1){
					likeNumBox.innerHTML = `<div class="d-inline-block text-center text-white mx-2 mt-2" style="width: 40px; background: #dc3545; border-radius: 4px; font-size: 12px;"><img src="images/heart-fill.png" style="width: 13px; object-position: 0px -2px;" alt="like" />		 
											<span class="doCmtLike">${result.result}</span>
											</div>`;
					doLikeBox.innerHTML = `<a href="javascript:doCmtLike(${n});" class="d-inline-block text-center text-white" style="margin: 0;"><img src="images/heart-fill-red.png" style="width: 17px; object-fit: cover; object-position: 0 -1px;" alt="like" />
											<input type="hidden" value="1" id="doCmtLike${n}" />
											</a>`;
				}else if(result.result > 1){
					dolike.innerHTML = result.result;
					doLikeBox.innerHTML = `<a href="javascript:doCmtLike(${n});" class="d-inline-block text-center text-white" style="margin: 0;"><img src="images/heart-fill-red.png" style="width: 17px; object-fit: cover; object-position: 0 -1px;" alt="like" />
											<input type="hidden" value="1" id="doCmtLike${n}" />
											</a>`;
				}else if(result.result == 0){
					likeNumBox.innerHTML = "";
					doLikeBox.innerHTML = `<a href="javascript:doCmtLike(${n});" class="d-inline-block text-center text-white" style="margin: 0;"><img src="images/heart-fill-red.png" style="width: 17px; object-fit: cover; object-position: 0 -1px;" alt="like" />
											<input type="hidden" value="1" id="doCmtLike${n}" />
											</a>`; 
				}
			})	
		}
					
	}
}

function doReLike(n, m){
	const bbsnum = document.getElementById("bbsnum").value;
	const textnum = document.getElementById("num").value;
	const cmtnum = document.getElementsByClassName("cmtnum")[n].value;
	const replynum = document.getElementsByClassName("replynum" + n)[m].value;
	const userid = document.getElementById("user").value;
	
	// 좋아요 수 나타낼 곳
	var dolike = document.getElementsByClassName("doReLike" + n)[m];    
	
	// 좋아요 버튼 나타날 곳
	const doLikeBox = document.getElementsByClassName("doReLikeBox" + n)[m];
	 // 좋아요 했는지 안했는지 확인 
	var checkDoLike = document.getElementsByClassName("checkDoReLike" + n)[m].value;
	// 좋아요 수 나타낼 박스 - 좋아요가 0일 경우에 대비
	var likeNumBox = document.getElementsByClassName("showReLikeNum" + n)[m];
	 
	if(userid == "null" || userid == ""){
		alert("로그인을 해주세요.");
	}else{
		if(checkDoLike == 1){
			fetch("/board/LikeDelete", {
			headers : {"Content-Type" : "application/json"},
			method : "post",
			body : JSON.stringify({
				bbsnum : bbsnum, textnum : textnum, cmtnum : cmtnum, replynum : replynum, userid : userid
				})
			}).then((res) => res.json())
			.then((result) => {
				if(result.result == 1){
					likeNumBox.innerHTML = `<div class="d-inline-block text-center text-white ml-1 mb-2" style="width: 40px; background: #dc3545; border-radius: 4px; font-size: 12px;"><img src="images/heart-fill.png" style="width: 13px; object-position: 0px -2px;" alt="like" />		 
											<span class="doReLike${n}">${result.result}</span>
											</div>`;
					doLikeBox.innerHTML = `<a href="javascript:doReLike(${n},${m});" class="d-inline-block text-center text-white" style="margin: 0;"><img src="images/heart-line.png" style="width: 17px; object-fit: cover; object-position: 0 -1px;" alt="like" />
											<input type="hidden" value="0" class="checkDoReLike${n}" />
											</a>`;
				}else if(result.result > 1){
					dolike.innerHTML = result.result;
					doLikeBox.innerHTML = `<a href="javascript:doReLike(${n},${m});" class="d-inline-block text-center text-white" style="margin: 0;"><img src="images/heart-line.png" style="width: 17px; object-fit: cover; object-position: 0 -1px;" alt="like" />
											<input type="hidden" value="0" class="checkDoReLike${n}" />
											</a>`;
				}else if(result.result == 0){
					likeNumBox.innerHTML = "";
					doLikeBox.innerHTML = `<a href="javascript:doReLike(${n},${m});" class="d-inline-block text-center text-white" style="margin: 0;"><img src="images/heart-line.png" style="width: 17px; object-fit: cover; object-position: 0 -1px;" alt="like" />
											<input type="hidden" value="0" class="checkDoReLike${n}" />
											</a>`;
				}
			})
		}else{
			fetch("/board/LikeCnt", {
			headers : {"Content-Type" : "application/json"},
			method : "post",
			body : JSON.stringify({
				bbsnum : bbsnum, textnum : textnum, cmtnum : cmtnum, replynum : replynum, userid : userid
				})
			}).then((res) => res.json())
			.then((result) => {
				if(result.result == 1){
					likeNumBox.innerHTML = `<div class="d-inline-block text-center text-white ml-1 mb-2" style="width: 40px; background: #dc3545; border-radius: 4px; font-size: 12px;"><img src="images/heart-fill.png" style="width: 13px; object-position: 0px -2px;" alt="like" />		 
											<span class="doReLike${n}">${result.result}</span>
											</div>`;
					doLikeBox.innerHTML = `<a href="javascript:doReLike(${n},${m});" class="d-inline-block text-center text-white" style="margin: 0;"><img src="images/heart-fill-red.png" style="width: 17px; object-fit: cover; object-position: 0 -1px;" alt="like" />
											<input type="hidden" value="1" class="checkDoReLike${n}" />
											</a>`;
				}else if(result.result > 1){
					dolike.innerHTML = result.result;
					doLikeBox.innerHTML = `<a href="javascript:doReLike(${n},${m});" class="d-inline-block text-center text-white" style="margin: 0;"><img src="images/heart-fill-red.png" style="width: 17px; object-fit: cover; object-position: 0 -1px;" alt="like" />
											<input type="hidden" value="1" class="checkDoReLike${n}" />
											</a>`;
				}else if(result.result == 0){
					likeNumBox.innerHTML = "";
					doLikeBox.innerHTML = `<a href="javascript:doReLike(${n},${m});" class="d-inline-block text-center text-white" style="margin: 0;"><img src="images/heart-fill-red.png" style="width: 17px; object-fit: cover; object-position: 0 -1px;" alt="like" />
											<input type="hidden" value="1" class="checkDoReLike${n}" />
											</a>`;
				}
			})	
		}
					
	}
}
function doShare(){
	const shadow = document.getElementById("shadow");
	const popup = document.getElementById("text-clipboard");
	shadow.style.display = "block";
	popup.style.display = "block";
	document.getElementById("text-clipboard-uri").value = window.location.href;
}

function URIcopy(){
	const copy = document.getElementById("text-clipboard-uri");
	copy.select();
	document.execCommand('copy');
	document.getElementById("noticeCopy").style.color = "#dc3545";
	document.getElementById("noticeCopy").innerHTML = '주소가 복사되었습니다.';
}

function closeCopy(){
	const shadow = document.getElementById("shadow");
	const popup = document.getElementById("text-clipboard");
	shadow.style.display = "none";
	popup.style.display = "none"; 
}

function selectImg(){
	const findImg = document.getElementById("photo");
	findImg.click();
}

function showFile(){
	/*
	const upload = document.querySelector(".upload");
	let filename = upload.files[0];
	const filebox = document.getElementsByClassName("filebox")[0];
	let fileLength = document.getElementsByClassName("fileshow").length;
	
	
	let str = "";
	str += `<div class="fileshow">`;
	str += `<div class="imgshow">`;
	str += `<img src=${URL.createObjectURL(filename)} alt="img">`;
	str += `</div>`;
	str += `<div class="nameshow">`;
	str += `${filename.name}`;
	str += `</div><a href="javascript:imgClose(${fileLength});" class="img-close">X</div>`;	
	filebox.insertAdjacentHTML('beforeend', str);
	*/
	
	const img = document.querySelector("#photo");
	let userImg = document.getElementById("user-img");
	const imgName = img.files[0];
	const imgUrl = URL.createObjectURL(imgName);
	userImg.src = imgUrl;
}

