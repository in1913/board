// 카카오 로그인
function loginWithKakao() {
	Kakao.init('502aa2d5333877f0e0a0673017e24bf2'); // 사용하려는 앱의 JavaScript 키 입력
    Kakao.Auth.authorize({
      redirectUri: 'http://localhost:8080/board/KakaoOauth',
      scope: 'account_email, profile_nickname'
    });
    Kakao.Auth.setAccessToken(token);
  }

window.onload = function () {
  google.accounts.id.initialize({
    client_id: "664366376528-akqm43rhadquji4s4uip3h3353ior23r.apps.googleusercontent.com",
    ux_mode: "redirect",
    login_uri : "http://localhost:8080/board/GoogleOauth"
  });
  google.accounts.id.renderButton(
    document.getElementById("buttonDiv"),
    { theme: "outline", size: "large" }  // customization attributes
  );
  google.accounts.id.prompt(); // also display the One Tap dialog
}


