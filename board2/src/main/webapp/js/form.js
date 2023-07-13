// 정규식
// 아이디 영문, 숫자만 가능, 4자리 이상 8자 이하
const uid = /^[a-zA-Z0-9]{4,8}$/;

// 비밀번호
const upw = /^[a-zA-Z0-9]{8,15}$/;

// 이름
const uname = /^[가-힣a-zA-Z]{2, 15}$/;

// 이메일 
const uemail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;


// 전화번호
const utel = /^01([0|1|6|7|8|9/])-?([0-9]{3,4})-?([0-9]{4})$/;

window.onload = function(){
	if(getCookies("user")){
		const form = document.loginform
		form.userid.value = getCookies("user");
		document.loginform.huid.checked = true;
	}
}


function register(){
 	// 변수
	const userid = document.getElementById("userid").value;
	const userpass = document.getElementById("userpass").value;
	const reuserpass = document.getElementById("reuserpass").value;
	const username = document.getElementById("username").value;
	const useremail = document.getElementById("useremail").value;
	const postcode = document.getElementById("postcode").value;
	const useridok = document.getElementById("useridok").value;
	const addr = document.getElementById("addr").value;
	const detailaddr = document.getElementById("detailaddr").value;
	const tel1 = document.getElementById("tel1").value;
	const tel2 = document.getElementById("tel2").value;
	const tel3 = document.getElementById("tel3").value;
	const tel = tel1 + "-" + tel2 + "-" + tel3;
	const warning = document.getElementsByClassName("warning-text");
	const dupliid = document.getElementById("duplicheck").value;
	let cnt = 0;
 	// 아이디 확인
 	if(userid == ""){
		warning[0].innerHTML = "아이디를 입력해주세요.";
		warning[0].style.color = "red";
		return false;
	}else{
		warning[0].innerHTML = "";
	}
	
	if(dupliid == "notcheck"){
		warning[0].innerHTML = "중복확인을 해주세요.";
		warning[0].style.color = "red";
		cnt += 1;
	}else{
		warning[0].innerHTML = "";
	}
	 
	// 비밀번호 확인
 	if(userpass == ""){
		warning[1].innerHTML = "비밀번호를 입력해주세요.";
		warning[1].style.color = "red";
		return false;
	}else{
		warning[1].innerHTML = "";
	}
	 
	// 비밀번호 재확인
 	if(reuserpass == ""){
		warning[2].innerHTML = "비밀번호를 확인해주세요.";
		warning[2].style.color = "red";
		return false;
	}else{
		warning[2].innerHTML = "";
	}
	 
	// 이름 확인
 	if(username == ""){
		warning[3].innerHTML = "이름을 입력해주세요.";
		warning[3].style.color = "red";
		return false;
	}else{
		warning[3].innerHTML = "";
	}
	 
	// 이메일 확인
 	if(useremail == ""){
		warning[4].innerHTML = "이메일을 입력해주세요.";
		warning[4].style.color = "red";
		return false;
	}else{
		warning[4].innerHTML = "";
	}
	
	// 주소 확인
 	if(postcode == ""){
		warning[5].innerHTML = "주소를 입력해주세요.";
		warning[5].style.color = "red";
		return false;
	}else{
		warning[5].innerHTML = "";
	}
	
	// 상세주소 확인
 	if(detailaddr == ""){
		warning[6].innerHTML = "상세주소를 입력해주세요.";
		warning[6].style.color = "red";
		return false;
	}else{
		warning[6].innerHTML = "";
	}
	
	// 전화번호 확인
 	if(!utel.test(tel)){
		warning[7].innerHTML = "전화번호를 확인해주세요.";
		warning[7].style.color = "red";
		tel1 = "";		
		tel2 = "";		
		tel3 = "";		
		return false;
	}else{
		warning[7].innerHTML = "";
	}
	
	
	let list = [userid, userpass, reuserpass, username, useremail, addr, detailaddr, tel1, tel2, tel3];
	
	for(i = 0; i < list.length; i++){
		if(list[i] == ""){
			cnt += 1;
		}
	}
	
	if(cnt == 0){
		document.getElementById("tel").value = tel;
		document.registerform.submit();	
	}
	
};




function register2(){
 	// 변수
	const userid = document.getElementById("userid");
	const userpass = document.getElementById("userpass");
	const reuserpass = document.getElementById("reuserpass");
	const username = document.getElementById("username");
	const useremail = document.getElementById("useremail");
	const postcode = document.getElementById("postcode");
	const useridok = document.getElementById("useridok");
	const addr = document.getElementById("addr");
	const detailaddr = document.getElementById("detailaddr");
	const tel1 = document.getElementById("tel1");
	const tel2 = document.getElementById("tel2");
	const tel3 = document.getElementById("tel3");
	const tel = tel1.value + "-" + tel2.value + "-" + tel3.value;
	const warning = document.getElementsByClassName("warning-text");
	
 	// 아이디 확인
 	if(userid.value == ""){
		warning[0].innerHTML = "아이디를 입력해주세요.";
		warning[0].style.color = "red";
		return false;
	}else{
		warning[0].innerHTML = "";
	}
	
	// 이름 확인
 	if(username.value == ""){
		warning[3].innerHTML = "이름을 입력해주세요.";
		warning[3].style.color = "red";
		return false;
	}else{
		warning[3].innerHTML = "";
	}
	 
	// 이메일 확인
 	if(useremail.value == ""){
		warning[4].innerHTML = "이메일을 입력해주세요.";
		warning[4].style.color = "red";
		return false;
	}else{
		warning[4].innerHTML = "";
	}
	
	// 주소 확인
 	if(postcode.value == ""){
		warning[5].innerHTML = "주소를 입력해주세요.";
		warning[5].style.color = "red";
		return false;
	}else{
		warning[5].innerHTML = "";
	}
	
	// 상세주소 확인
 	if(detailaddr.value == ""){
		warning[6].innerHTML = "상세주소를 입력해주세요.";
		warning[6].style.color = "red";
		return false;
	}else{
		warning[6].innerHTML = "";
	}
	
	// 전화번호 확인
 	if(!utel.test(tel)){
		warning[7].innerHTML = "전화번호를 확인해주세요.";
		warning[7].style.color = "red";
		tel1.value = "";		
		tel2.value = "";		
		tel3.value = "";		
		return false;
	}else{
		warning[7].innerHTML = "";
	}
	
	document.getElementById("tel").value = tel;
	document.registerform.submit();
};

function register3(){
 	// 변수
	const userid = document.getElementById("userid");
	const username = document.getElementById("username");
	const useremail = document.getElementById("useremail");
	const postcode = document.getElementById("postcode");
	const useridok = document.getElementById("useridok");
	const addr = document.getElementById("addr");
	const detailaddr = document.getElementById("detailaddr");
	const tel1 = document.getElementById("tel1");
	const tel2 = document.getElementById("tel2");
	const tel3 = document.getElementById("tel3");
	const tel = tel1.value + "-" + tel2.value + "-" + tel3.value;
	const warning = document.getElementsByClassName("warning-text");
	let cnt = 0;
	const dupliid = document.getElementById("duplicheck").value;
	
 	// 아이디 확인
 	if(userid.value == ""){
		warning[0].innerHTML = "아이디를 입력해주세요.";
		warning[0].style.color = "red";
		return false;
	}else{
		warning[0].innerHTML = "";
	}
	
	if(dupliid == "notcheck"){
		warning[0].innerHTML = "중복확인을 해주세요.";
		warning[0].style.color = "red";
		cnt += 1;
	}else{
		warning[0].innerHTML = "";
	}
	
	// 이름 확인
 	if(username.value == ""){
		warning[1].innerHTML = "이름을 입력해주세요.";
		warning[1].style.color = "red";
		return false;
	}else{
		warning[1].innerHTML = "";
	}
	 
	// 이메일 확인
 	if(useremail.value == ""){
		warning[2].innerHTML = "이메일을 입력해주세요.";
		warning[2].style.color = "red";
		return false;
	}else{
		warning[2].innerHTML = "";
	}
	
	// 주소 확인
 	if(postcode.value == ""){
		warning[3].innerHTML = "주소를 입력해주세요.";
		warning[3].style.color = "red";
		return false;
	}else{
		warning[3].innerHTML = "";
	}
	
	// 상세주소 확인
 	if(detailaddr.value == ""){
		warning[4].innerHTML = "상세주소를 입력해주세요.";
		warning[4].style.color = "red";
		return false;
	}else{
		warning[4].innerHTML = "";
	}
	
	// 전화번호 확인
 	if(!utel.test(tel)){
		warning[5].innerHTML = "전화번호를 확인해주세요.";
		warning[5].style.color = "red";
		tel1.value = "";		
		tel2.value = "";		
		tel3.value = "";		
		return false;
	}else{
		warning[5].innerHTML = "";
	}
	
	let list = [userid, username, useremail, addr, detailaddr, tel1, tel2, tel3];
	
	for(i = 0; i < list.length; i++){
		if(list[i] == ""){
			cnt += 1;
		}
	}
	
	if(cnt == 0){
		document.getElementById("tel").value = tel;
		document.registerform.submit();	
	}
};

function registerCheck(n){
	const userid = document.getElementById("userid");
	const userpass = document.getElementById("userpass");
	const reuserpass = document.getElementById("reuserpass");
	const useremail = document.getElementById("useremail");
	const warning = document.getElementsByClassName("warning-text");
	if(n == 1){
		if(!uid.test(userid.value)){
			warning[0].innerHTML = "영문 대소문자, 숫자만 가능, 4자리 이상 8이하";
			warning[0].style.color = "red";
			return false;
		}else{
			warning[0].innerHTML = "";
		}
	}
	if(n == 2){
		if(!upw.test(userpass.value)){
			warning[1].innerHTML = "영문 대소문자, 숫자만 가능, 8자리 이상 15이하";
			warning[1].style.color = "red";	
			return false;
		}else{
			warning[1].innerHTML = "";
			warning[1].style.color = "red";	
		}
	}
	if(n == 3){
		if(reuserpass.value != userpass.value){
			warning[2].innerHTML = "비밀번호가 같지 않습니다.";
			warning[2].style.color = "red";	
			return false;
		}else{
			warning[2].innerHTML = "";
			warning[2].style.color = "red";
		}
	}
	if(n == 4){
		if(!uemail.test(useremail.value)){
			warning[4].innerHTML = "이메일 형식에 맞춰 입력해주세요.";
			warning[4].style.color = "red";
			return false;
		}else{
			warning[4].innerHTML = "";
			warning[4].style.color = "red";
		}
	}
}


function sDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var address = ''; // 주소 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    address = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    address = data.jibunAddress;
                }
                var extraRoadAddr = ""; //참고항목 
                //도로명에 동,로,가가 있는 경우 추가
                if(data.bname !== '' &&/[동|로|가]$/g.test(data.bname)){
               	extraRoadAddr += data.bname;
            	}
            	//건물명, 공동주택 추가
	            if(data.bname !== '' && data.apartment === 'Y'){
	               extraRoadAddr += (extraRoadAddr !== ''?','+data.buildingName : data.buildingName);
	            }
	                //표시할 참고항목이 있을 경우
	            if(extraRoadAddr !== ''){
	               extraRoadAddr = ' (' + extraRoadAddr + ')';
	            }

                 // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("addr").value = address;
                document.getElementById("addr").value += extraRoadAddr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailaddr").focus();
            }
        }).open();
}

function loginSubmit(){
	const form = document.loginform
	const warning = document.getElementsByClassName("warning-text");
	const is_checked = document.loginform.huid.checked;
	
	// 아이디 확인
 	if(form.userid.value == ""){
		warning[0].innerHTML = "아이디를 입력해주세요.";
		warning[0].style.color = "red";	
		return false;
	}else{
		warning[0].innerHTML = "";
		warning[0].style.color = "red";	
	}
	 
	// 비밀번호 확인
 	if(form.userpass.value == ""){
		warning[1].innerHTML = "비밀번호를 입력해주세요.";
		warning[1].style.color = "red";	
		return false;
	}else{
		warning[1].innerHTML = "";
		warning[1].style.color = "red";	
	}
	
	if(is_checked){
		setCookie('user', form.userid.value, '3');
	}else{
		delCookie('user');
	}
	document.loginform.submit();
}

function isChecked(){
	const is_checked = document.loginform.huid.checked;
	const warning = document.getElementsByClassName("warning-public")[0];
	
	if(is_checked){
		warning.innerHTML = "공공장소에서는 추천하지 않습니다.";
	}else{
		warning.innerHTML = "";
	}
}

function idCheck(){
	const userid = document.getElementById("userid").value;
	const warning = document.getElementsByClassName("warning-text")[0];
	const idCheck = document.getElementById("duplicheck");
	if(!uid.test(userid)){
		warning.innerHTML = "영문 대소문자, 숫자만 가능, 4자리 이상 8이하";
		warning.style.color = "red";
	}else{
		fetch("/board/MemComfirm", {
		headers: {"Content-Type" : "application/json"},
		method : "post",
		body : JSON.stringify({ 
			userid : userid
			})
		})
		.then((res) => res.json())
		.then((result) => {
				if(result == 1){
					warning.innerHTML = "이미 사용중인 아이디입니다.";
					warning.style.color = "red";	
				}else{
					warning.innerHTML = "[" +userid + "]는 사용가능한 아이디입니다.";
					warning.style.color = "green";
					
				}
		});
		
		idCheck.value = "check";
	}
 }
function getEmailClose(){
	document.getElementsByClassName("get-email")[0].style.display = "none";
}

function findId(){
	const uname = document.getElementById("username").value;
	const uemail = document.getElementsByClassName("useremail")[0].value;
	const findMessage = document.getElementsByClassName("find-message")[0];
	const getEmail = document.getElementsByClassName("get-email")[0];
	fetch("/board/FindId", {
		headers: {"Content-Type" : "application/json"},
		method : "post",
		body : JSON.stringify({
			username : uname, 
			useremail : uemail
			})
	})
	.then((res) => res.json())
	.then((result) => {
			if(result == 0){	
				getEmail.style.display = "block";
				findMessage.innerHTML = "입력하신 정보와 일치하는 아이디가 없습니다.";
			}else{
				getEmail.style.display = "block";
				findMessage.innerHTML = "아이디는 [" + result.userid + "]입니다.";
			}	
		});
}

function findPw(){
	const uid = document.getElementById("userid").value;
	const uemail = document.getElementsByClassName("useremail")[1].value;
	const findMessage = document.getElementsByClassName("find-message")[0];
	const getEmail = document.getElementsByClassName("get-email")[0];
	
	fetch("/board/FindPw", {
		headers : {"Content-Type" : "application/json"},
		method : "post",
		body : JSON.stringify({userid : uid, useremail : uemail})
	}).then((res) => res.json())
	.then((result) =>{
			if(result == 0){	
				getEmail.style.display = "block";
				findMessage.innerHTML = "입력하신 정보와 일치하는 비밀번호가 없습니다.";
			}else{
				getEmail.style.display = "block";
				findMessage.innerHTML = "새로운 비밀번호가 <br> 입력된 이메일 주소로 전송되었습니다. <br> 보안을 위해 로그인 후 비밀번호를 변경해주세요.";
			}
			
		});
}