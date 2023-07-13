package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class BbsColDDL {
	public static boolean insert(BbsColDTO dto) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		
		try {
			conn = new DBConnect().getConn();
			String sql = "insert into bbsCol"
					+ " (userid, uip, title, content) values"
					+ " (?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getUserid());
			ps.setString(2, dto.getUip());
			ps.setString(3, dto.getTitle());
			ps.setString(4, dto.getContent());
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
	
	
	public static Vector <BbsColDTO> getSelectAll(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from bbsCol";
		Vector <BbsColDTO> data = new Vector <> ();
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BbsColDTO bd = new BbsColDTO();
				bd.setNum(rs.getInt("num"));
				bd.setUserid(rs.getString("userid"));
				bd.setUserpass(rs.getString("userpass"));
				bd.setUsername(rs.getString("username"));
				bd.setUseremail(rs.getString("useremail"));
				bd.setWdate(rs.getString("wdate"));
				bd.setUip(rs.getString("uip"));
				bd.setCount(rs.getInt("count"));
				bd.setTitle(rs.getString("title"));
				bd.setContent(rs.getString("content"));
				bd.setLikes(rs.getInt("likes"));
				data.add(bd);
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
	
	public static Vector <BbsColDTO> getSelectLimit(int limitNum, int listNum){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from bbsCol order by num asc limit ? , ?";
		Vector <BbsColDTO> data = new Vector <> ();
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1,  limitNum);
			ps.setInt(2,  listNum);
			rs = ps.executeQuery();
			while(rs.next()) {
				BbsColDTO bd = new BbsColDTO();
				bd.setNum(rs.getInt("num"));
				bd.setUserid(rs.getString("userid"));
				bd.setUserpass(rs.getString("userpass"));
				bd.setUsername(rs.getString("username"));
				bd.setUseremail(rs.getString("useremail"));
				bd.setWdate(rs.getString("wdate"));
				bd.setUip(rs.getString("uip"));
				bd.setCount(rs.getInt("count"));
				bd.setTitle(rs.getString("title"));
				bd.setContent(rs.getString("content"));
				bd.setLikes(rs.getInt("likes"));
				data.add(bd);
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
	
	
	
	public static Vector <BbsColDTO> getSelectByNum(int num){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from bbsCol where num = ?";
		
		Vector <BbsColDTO> data = new Vector <> ();
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			while(rs.next()) {
				BbsColDTO bd = new BbsColDTO();
				bd.setNum(rs.getInt("num"));
				bd.setUserid(rs.getString("userid"));
				bd.setUserpass(rs.getString("userpass"));
				bd.setUsername(rs.getString("username"));
				bd.setUseremail(rs.getString("useremail"));
				bd.setWdate(rs.getString("wdate"));
				bd.setUip(rs.getString("uip"));
				bd.setCount(rs.getInt("count"));
				bd.setTitle(rs.getString("title"));
				bd.setContent(rs.getString("content"));
				data.add(bd);
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
	
	
	public static boolean DbCount(int num) {
		PreparedStatement ps = null;
		Connection conn = null;
		
		int flag = 0;
		
		String sql = "update bbsCol set count = count + 1 where num = ?";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			flag = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {}
		}
			
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public static boolean DeleteTextFree(int num) {
		Connection conn1 = null;
		PreparedStatement ps1 = null;
		int flag1 = 0;
		
		String sql1 = "delete from bbsCol where num = ?";
		
		
		try {
			conn1 = new DBConnect().getConn();
			ps1 = conn1.prepareStatement(sql1);
			ps1.setInt(1, num);
			flag1 = ps1.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn1 != null) conn1.close();
				if(ps1 != null) ps1.close();
			}catch(SQLException e) {}
		}
		
		
		
		
		Connection conn2 = null;
		PreparedStatement ps2 = null;
		int flag2 = 0;
		String sql2 = "delete from comment where bbsnum = 4 and num = ?";
		
		try {
			conn2 = new DBConnect().getConn();
			ps2 = conn2.prepareStatement(sql2);
			ps2.setInt(1, num);
			flag2 = ps2.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn2 != null) conn2.close();
				if(ps2 != null) ps2.close();
			}catch(SQLException e) {}
		}
		
		if(flag1 + flag2 == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	
	// num이 매개변수인 update 문 만들기 게시글 수정
	public static boolean ModifiedText(BbsColDTO dto) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		String sql = "update bbsCol set content = ?, title = ? where num = ?";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1,  dto.getContent());
			ps.setString(2,  dto.getTitle());
			ps.setInt(3, dto.getNum());
			flag = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {}
		}
		
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean updateLike(int textnum, int Like) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		
		String sql = "update bbsCol set likes = ? where num = ?";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Like);
			ps.setInt(2, textnum);
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
