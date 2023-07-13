package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ReplyDDL {
	public static boolean insert(ReplyDTO dto) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		
		String sql = "insert into reply"
				+ "(cmtnum, textnum, bbsnum, userid, reply, uip) values"
				+ "(?, ?, ?, ?, ?, ?)";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getCmtnum());
			ps.setInt(2, dto.getTextnum());
			ps.setInt(3,  dto.getBbsnum());
			ps.setString(4,  dto.getUserid());
			ps.setString(5,  dto.getReply());
			ps.setString(6,  dto.getUip());
			flag = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn !=null) conn.close();
				if(ps !=null) ps.close();
			}catch(SQLException e) {}
		}
		
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static Vector <ReplyDTO> select(int cmtnum, int textnum, int bbsnum){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from reply where cmtnum = ? and textnum = ? and bbsnum = ?";
		
		Vector <ReplyDTO> data = new Vector <ReplyDTO> ();
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cmtnum);
			ps.setInt(2, textnum);
			ps.setInt(3, bbsnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				ReplyDTO rd = new ReplyDTO();
				rd.setNum(rs.getInt("num"));
				rd.setUserid(rs.getString("userid"));
				rd.setUserpass(rs.getString("userpass"));
				rd.setReply(rs.getString("reply"));
				rd.setUip(rs.getString("uip"));
				rd.setWdate(rs.getString("wdate"));
				rd.setLikes(rs.getInt("likes"));
				data.add(rd);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
				if(rs != null) rs.close();
			}catch(SQLException e) {}
		}
		return data;
	}
	
	public static boolean ModiReply(int replynum, int cmtnum, int textnum, String reply, int bbsnum) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		
		String sql = "update reply set reply = ? where num = ? and cmtnum = ? and textnum = ? and bbsnum = ?";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, reply);
			ps.setInt(2, replynum);
			ps.setInt(3, cmtnum);
			ps.setInt(4, textnum);
			ps.setInt(5, bbsnum);
			flag = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
			}catch(SQLException e) {}
		}
		
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public static boolean DeleteReply(int replynum, int cmtnum, int textnum, int bbsnum) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		String sql = "delete from reply where num = ? and cmtnum = ? and textnum = ? and bbsnum = ?";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, replynum);
			ps.setInt(2, cmtnum);
			ps.setInt(3, textnum);
			ps.setInt(4, bbsnum);
			flag = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
			}catch(SQLException e) {}
		}
		
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean updateReplyLike(int bbsnum, int textnum, int cmtnum,int replynum, int likes) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		
		String sql = "update reply set likes = ? where bbsnum = ? and textnum = ? and cmtnum = ? and num = ?";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, likes);
			ps.setInt(2,  bbsnum);
			ps.setInt(3, textnum);
			ps.setInt(4, cmtnum);
			ps.setInt(5, replynum);
			flag = ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
			}catch(SQLException e) {}	
		}
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}
}
