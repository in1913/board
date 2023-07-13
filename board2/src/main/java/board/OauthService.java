package board;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OauthService {
	public static String getKakaoAccessToken (String code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=f228ebc102ad68cac0f8cb96d6f1db3b"); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=http://localhost:8080/board/KakaoOauth"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonObject jsonObj = (JsonObject) JsonParser.parseString(result);
            access_Token = jsonObj.get("access_token").getAsString();
            refresh_Token = jsonObj.get("refresh_token").getAsString();
            
            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }
	
	
	public static String getNaverAccessToken(String code, String state) {
		String result = "";
	    String clientId = "vAA18rocBL8pJBmrDGXD";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "V0rHr0CQ29";//애플리케이션 클라이언트 시크릿값";
	    String redirectURI = null;
		try {
			redirectURI = URLEncoder.encode("http://localhost:8080/board/NaverOauth", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    String access_Token = "";
	    String refresh_Token = "";
	    try {
	        URL url = new URL(apiURL);
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("GET");
	        int responseCode = con.getResponseCode();
	        BufferedReader br;
	        
	        if(responseCode == 200) { // 정상 호출
	            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        } else {  // 에러 발생
	            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	        }
	        String inputLine;
	        StringBuffer resp = new StringBuffer();
	        while ((inputLine = br.readLine()) != null) {
	            resp.append(inputLine);
	        }
	        br.close();
	        if(responseCode == 200) {
	            result = resp.toString();
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    
	    JsonObject jsonObj = (JsonObject) JsonParser.parseString(result);
        access_Token = jsonObj.get("access_token").getAsString();
        refresh_Token = jsonObj.get("refresh_token").getAsString();
        

        System.out.println("access_token : " + access_Token);
        System.out.println("refresh_token : " + refresh_Token);

	    return access_Token;
	}
	
	public static String[] verifyAndGetGoogleJson(String token){
		String email = "";
		String name = "";
		String userid = "";
		JsonFactory gsonFactory = new GsonFactory();
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), gsonFactory)
			    // Specify the CLIENT_ID of the app that accesses the backend:
			    .setAudience(Collections.singletonList("664366376528-akqm43rhadquji4s4uip3h3353ior23r.apps.googleusercontent.com"))
			    // Or, if multiple clients access the backend:
			    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
			    .build();

			// (Receive idTokenString by HTTPS POST)

		GoogleIdToken idToken = null;
		try {
			idToken = verifier.verify(token);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (idToken != null) {
			Payload payload = idToken.getPayload();
	
			System.out.println(payload);
			// {"aud":"664366376528-akqm43rhadquji4s4uip3h3353ior23r.apps.googleusercontent.com","azp":"664366376528-akqm43rhadquji4s4uip3h3353ior23r.apps.googleusercontent.com","email":"1996vicky13@gmail.com","email_verified":true,"exp":1683907362,"iat":1683903762,"iss":"https://accounts.google.com","jti":"40f5d7e3c8ee76e327b38333184b6ef0cfa051f2","nbf":1683903462,"sub":"103180917394415406973","name":"INYOUNG CHOI","picture":"https://lh3.googleusercontent.com/a/AGNmyxZ2yn9SNVXNvE2Xrk9ZXb5b1EAR1tvXwLsbGui7=s96-c","given_name":"INYOUNG","family_name":"CHOI"}
			
			// Print user identifier
			userid = payload.getSubject();
			System.out.println("User ID: " + userid);
	
			// Get profile information from payload
			email = payload.getEmail();
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			name = (String) payload.get("name");
			String pictureUrl = (String) payload.get("picture");
			String locale = (String) payload.get("locale");
		    String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");
			
			System.out.println("email" + email);
			System.out.println("emailVerified" + emailVerified);
			System.out.println("name" + name);
			System.out.println("locale" + locale);
			System.out.println("familyName" + familyName);
			System.out.println("givenName" + givenName);
			
	
			// Use or store profile information
			// ...
		  
		  
		} else {
		  System.out.println("Invalid ID token.");
		}
		
		String result[] = {email, name, userid};
		
		return result;
	}
	
}

