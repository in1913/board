package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class PopularDDL {
	public static Vector <PopularDTO> selectByCount() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select *, '3' as bbsnum from bbsfree union all select *, '4' as bbsnum from bbsCol union all select *, '1' as bbsnum from bbsnotice order by count desc;";
		
		Vector <PopularDTO> data = new Vector <PopularDTO> ();
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				PopularDTO bd = new PopularDTO();
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
				bd.setBbsnum(rs.getInt("bbsnum"));
				data.add(bd);
				
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
	
	public static Vector <PopularDTO> selectByCountLimit(int limitNum, int listNum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select *, '3' as bbsnum from bbsfree union all select *, '4' as bbsnum from bbsCol union all select *, '1' as bbsnum from bbsnotice order by count desc limit ? , ?";
		
		Vector <PopularDTO> data = new Vector <PopularDTO> ();
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1,  limitNum);
			ps.setInt(2,  listNum);
			rs = ps.executeQuery();
			while(rs.next()) {
				PopularDTO bd = new PopularDTO();
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
				bd.setBbsnum(rs.getInt("bbsnum"));
				data.add(bd);
				
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
}
