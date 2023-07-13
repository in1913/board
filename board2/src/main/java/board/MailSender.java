package board;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	public static void sendMail(String to, String content) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("1996vicky13@gmail.com", "nltbzdjwuvtwaoei");
			}
		});
		
		String receiver = to;
		String title = "비밀번호 전송메일입니다.";
		String contents = content;
		Message message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress("1996vicky13@gmail.com", "관리자", "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(contents, "text/html; charset=utf-8");
			
			
			Transport.send(message);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
