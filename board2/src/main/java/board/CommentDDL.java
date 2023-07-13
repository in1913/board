package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class CommentDDL {
	public static boolean insert(CommentDTO dto,int textnum, int bbsnum) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		
		String sql = "insert into comment"
				+ "(textnum, bbsnum, userid, comment, uip) values"
				+ "("+ textnum + ", " + bbsnum +", ?, ?, ?)";
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1,  dto.getUserid());
			ps.setString(2,  dto.getComment());
			ps.setString(3,  dto.getUip());
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
	
	public static Vector <CommentDTO> select(int textnum, int bbsnum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from comment where textnum = ? and bbsnum = ?";
		Vector <CommentDTO> data = new Vector <> ();
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, textnum);
			ps.setInt(2, bbsnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				CommentDTO cd = new CommentDTO();
				cd.setNum(rs.getInt("num"));
				cd.setUserid(rs.getString("userid"));
				cd.setComment(rs.getString("comment"));
				cd.setUip(rs.getString("uip"));
				cd.setWdate(rs.getString("wdate"));
				cd.setLikes(rs.getInt("likes"));
				data.add(cd);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			}catch(SQLException e) {}
		}
		return data;
		
	}
	
	public static int CommentCount(int textnum, int bbsnum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
		String sql = "select count(*) as cnt from comment where textnum = " + textnum + " and bbsnum = " + bbsnum + "";
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getInt("cnt");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			}catch(SQLException e) {}
		}
		return result;
	}
	
	public static boolean DeleteCmtFree(int bbsnum, int num, int textnum) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		String sql = "delete from comment where bbsnum = ? and num = ? and textnum = ?";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bbsnum);
			ps.setInt(2, num);
			ps.setInt(3, textnum);
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
	
	public static boolean ModiCmt(int cmtnum, int textnum, String comment, int bbsnum) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		
		String sql = "update comment set comment = ? where num = ? and textnum = ? and bbsnum = ?";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, comment);
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
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean updateCmtLike(int bbsnum, int textnum,int cmtnum, int likes) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		
		String sql = "update comment set likes = ? where bbsnum = ? and textnum = ? and num = ?";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, likes);
			ps.setInt(2,  bbsnum);
			ps.setInt(3, textnum);
			ps.setInt(4, cmtnum);
			flag = ps.executeUpdate();
			System.out.println(ps);
			
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
