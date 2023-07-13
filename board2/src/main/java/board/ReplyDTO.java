package board;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReplyDTO {
	private int num;
	private int cmtnum;
	private int textnum;
	private int bbsnum;
	private String userid;
	private String userpass;
	private String reply;
	private String uip;
	private String wdate;
	private int likes;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCmtnum() {
		return cmtnum;
	}
	public void setCmtnum(int cmtnum) {
		this.cmtnum = cmtnum;
	}
	public int getTextnum() {
		return textnum;
	}
	public void setTextnum(int textnum) {
		this.textnum = textnum;
	}
	public int getBbsnum() {
		return bbsnum;
	}
	public void setBbsnum(int bbsnum) {
		this.bbsnum = bbsnum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getUip() {
		return uip;
	}
	public void setUip(String uip) {
		this.uip = uip;
	}
	public void setUip() {
		String uip = null;
		try {
			uip = Inet4Address.getLocalHost().getHostAddress();
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
		this.uip = uip;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate() {
		Date today = new Date();
		Locale currentLocale = new Locale("Korean", "KOREA");
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
		this.wdate = formatter.format(today);
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	
	
}
