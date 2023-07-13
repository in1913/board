package board;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OCIEmailTest {
	 
    // Replace FROM with your "From" address.
    // This address must be added to Approved Senders in the console.
    static final String FROM = "admin@ioracle.cloud";
    static final String FROMNAME = "bbs관리자";
 
    // Replace TO with a recipient address.
    static final String TO = "barbor@hanmail.net";
 
    // Replace smtp_username with your Oracle Cloud Infrastructure SMTP username generated in console.
    static final String SMTP_USERNAME = "ocid1.user.oc1..aaaaaaaat5qleu6gmjvubhm6dehmc5mlntvzvb7gjy7x4s3dnthrirdyufva@ocid1.tenancy.oc1..aaaaaaaauu5a7rvyvwvgggjvdv5ram5kl6zqgbh2fbrt4vjckvjs6t5y3npq.ct.com";
 
    // Replace smtp_password with your Oracle Cloud Infrastructure SMTP password generated in console.
    static final String SMTP_PASSWORD = "!hAoq;8S:w>)qYG-CE10";
 
    // Oracle Cloud Infrastructure Email Delivery hostname.
    static final String HOST = "smtp.email.ap-chuncheon-1.oci.oraclecloud.com";
 
    // The port you will connect to on the SMTP endpoint. Port 25 or 587 is allowed.
    static final int PORT = 587;
 
       static final String SUBJECT = "이메일 받아줘요....";
       static final String BODY = String.join(
 
           System.getProperty("line.separator"),
           "<h1>OCI Email Delivery test</h1>",
           "<p>This email was sent with OCI Email Delivery using the ",
           "<a href='Javamail'>https://github.com/javaee/javamail'>Javamail Package</a>",
          " for <a href='Javahttps://www.java.com'>Java</a>."
 
       );
 
    public static void main(String[] args) throws Exception {
 
        // Create a Properties object to contain connection configuration information.
 
       Properties props = System.getProperties();
       props.put("mail.transport.protocol", "smtp");
       props.put("mail.smtp.port", PORT);
 
       //props.put("mail.smtp.ssl.enable", "true"); //the default value is false if not set
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.auth.login.disable", "true");  //the default authorization order is "LOGIN PLAIN DIGEST-MD5 NTLM". 'LOGIN' must be disabled since Email Delivery authorizes as 'PLAIN'
       props.put("mail.smtp.starttls.enable", "true");   //TLSv1.2 is required
       props.put("mail.smtp.starttls.required", "true");  //Oracle Cloud Infrastructure required
 
        // Create a Session object to represent a mail session with the specified properties.
       Session session = Session.getDefaultInstance(props);
 
        // Create a message with the specified information.
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM,FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY,"text/html");
 
        // Create a transport.
        Transport transport = session.getTransport();
           
 
        // Send the message.
 
        try
        {
 
            System.out.println("Sending Email now...standby...");
 
 
            // Connect to OCI Email Delivery using the SMTP credentials specified.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);    
 
            // Send email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
 
        }
 
        catch (Exception ex) {
 
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
 
        }
 
        finally
 
        {
 
            // Close & terminate the connection.
            transport.close();
 
        }
 
    }
 
}