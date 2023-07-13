package board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.antkorwin.commonutils.exceptions.BaseException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CreateSnsUser {
	public static String[] getKakaoJson(String token) throws BaseException {
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		String email = "";
		String idStr = "";
		String nickname = "";
	    //access_token을 이용하여 사용자 정보 조회
	    try {
	       URL url = new URL(reqURL);
	       HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	       conn.setRequestMethod("POST");
	       conn.setDoOutput(true);
	       conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

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
	       
	       long id = jsonObj.get("id").getAsLong();
	       idStr = Long.toString(id);
	       nickname = jsonObj.get("kakao_account").getAsJsonObject().get("profile").getAsJsonObject().get("nickname").getAsString();
	       boolean hasEmail = jsonObj.get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
	       
 
	       if(hasEmail){
	    	   email = jsonObj.get("kakao_account").getAsJsonObject().get("email").getAsString();
	       }

	      
	       br.close();

	       } catch (IOException e) {
	            e.printStackTrace();
	       }
	    
	    String[] result = {idStr, email, nickname}; 
	    return result;
	}
	
	public static String[] getNaverJson(String token) throws BaseException{
		String reqURL = "https://openapi.naver.com/v1/nid/me";
		String result = "";
		
		String id = "";
		String nickname = "";
		String email = "";
		String tel = "";
		String name = "";
		
	    //access_token을 이용하여 사용자 정보 조회
	    try {
	       URL url = new URL(reqURL);
	       HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	       conn.setRequestMethod("POST");
	       conn.setDoOutput(true);
	       conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

	       //결과 코드가 200이라면 성공
	       int responseCode = conn.getResponseCode();
	       System.out.println("responseCode : " + responseCode);

	       //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	       BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	       String line = "";
	       

	       while ((line = br.readLine()) != null) {
	           result += line;
	       }
	       System.out.println("response body : " + result);

	       JsonObject jsonObj = (JsonObject) JsonParser.parseString(result);
	       
	       id = jsonObj.get("response").getAsJsonObject().get("id").getAsString();
	       nickname = jsonObj.get("response").getAsJsonObject().get("nickname").getAsString();
	       email = jsonObj.get("response").getAsJsonObject().get("email").getAsString();
	       tel = jsonObj.get("response").getAsJsonObject().get("mobile").getAsString();
	       name = jsonObj.get("response").getAsJsonObject().get("name").getAsString();
	       
	       br.close();

	       } catch (IOException e) {
	            e.printStackTrace();
	       }
	    
	    String[] res = {id, nickname, email, tel, name};
	   
	    return res;
	}
	
	
    public static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    public static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    public static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);
        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
 
	
	
	
	
	
}
