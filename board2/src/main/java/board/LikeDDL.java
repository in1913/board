package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class LikeDDL {
	public static boolean insert(int bbsnum, int textnum, int cmtnum, int replynum, String user) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		
		String sql = "insert into Likes (bbsnum, textnum, cmtnum, replynum, userid) values "
					+ "(?, ?, ?, ?, ?)";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bbsnum);
			ps.setInt(2, textnum);
			ps.setInt(3, cmtnum);
			ps.setInt(4, replynum);
			ps.setString(5,  user);
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
	
	public static int selectText(int bbsnum, int textnum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
		String sql = "select count(*) as cnt from Likes where bbsnum = ? and textnum = ? and cmtnum = -1 and replynum = -1";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bbsnum);
			ps.setInt(2, textnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = Integer.parseInt(rs.getString("cnt"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
			}catch(SQLException e) {}
		}
		
		return result;
	}
	
	public static int selectCmt(int bbsnum, int textnum, int cmtnum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
		String sql = "select count(*) as cnt from Likes where bbsnum = ? and textnum = ? and cmtnum = ? and replynum = -1";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bbsnum);
			ps.setInt(2, textnum);
			ps.setInt(3, cmtnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = Integer.parseInt(rs.getString("cnt"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
			}catch(SQLException e) {}
		}
		
		return result;
	}
	
	public static int selectReply(int bbsnum, int textnum, int cmtnum, int replynum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
		String sql = "select count(*) as cnt from Likes where bbsnum = ? and textnum = ? and cmtnum = ? and replynum = ?";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bbsnum);
			ps.setInt(2, textnum);
			ps.setInt(3, cmtnum);
			ps.setInt(4, replynum);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = Integer.parseInt(rs.getString("cnt"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
			}catch(SQLException e) {}
		}
		
		return result;
	}
	
	public static boolean checkTextLike(int bbsnum, int textnum, String userid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int res = 0;
		
		String sql = "select count(*) as cnt from likes where bbsnum = ? and textnum = ? and cmtnum = -1 and replynum = -1 and userid = ?";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bbsnum);
			ps.setInt(2, textnum);
			ps.setString(3,  userid);
			rs = ps.executeQuery();
			while(rs.next()) {
				res = rs.getInt("cnt");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
			}catch(SQLException e) {}
		}
		
		if(res > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static Vector <LikeDTO> checkCmtLike(int bbsnum, int textnum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select comment.num as num, Likes.userid as userid from comment left join likes on comment.num = likes.cmtnum where comment.bbsnum = ? and comment.textnum = ?";
		
		
		Vector <LikeDTO> data = new Vector <LikeDTO> ();
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bbsnum);
			ps.setInt(2, textnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				LikeDTO ld = new LikeDTO();
				ld.setNum(rs.getInt("num"));
				ld.setUserid(rs.getString("userid"));
				data.add(ld);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
			}catch(SQLException e) {}
		}
		
		return data;
	}
	
	public static Vector <LikeDTO> checkReplyLike(int bbsnum, int textnum, int cmtnum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String sql = "select reply.num as num, likes.userid as userid from reply left join likes on reply.num = likes.replynum where reply.bbsnum = ? and reply.textnum = ? and reply.cmtnum = ?";
		Vector <LikeDTO> data = new Vector <LikeDTO> ();
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bbsnum);
			ps.setInt(2, textnum);
			ps.setInt(3, cmtnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				LikeDTO ld = new LikeDTO();
				ld.setNum(rs.getInt("num"));
				ld.setUserid(rs.getString("userid"));
				data.add(ld);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
			}catch(SQLException e) {}
		}
		return data;
	}
	
	
	public static boolean delete(int bbsnum, int textnum, int cmtnum, int replynum, String userid) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		
		String sql = "delete from likes where bbsnum = ? and textnum = ? and cmtnum = ? and replynum = ? and userid = ?";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bbsnum);
			ps.setInt(2, textnum);
			ps.setInt(3, cmtnum);
			ps.setInt(4, replynum);
			ps.setString(5, userid);
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
