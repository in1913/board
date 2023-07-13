package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MemberDDL {
	public boolean insert(MembersDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DBConnect().getConn(); // Connection 객체에서 conn 받아오기
			String query = "insert into members"
							+ " (userid, userpass, username, useremail, postcode, addr, detailaddr, tel, uip, photo)"
							+ " values"
							+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getUserpass());
			pstmt.setString(3, dto.getUsername());
			pstmt.setString(4, dto.getUseremail());
			pstmt.setInt(5, dto.getPostcode());
			pstmt.setString(6, dto.getAddr());
			pstmt.setString(7, dto.getDetailaddr());
			pstmt.setString(8, dto.getTel());
			pstmt.setString(9, dto.getUip());
			pstmt.setString(10, dto.getPhoto());
			flag = pstmt.executeUpdate();
			System.out.println(pstmt);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e){}
		}
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean insertSns(MembersDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DBConnect().getConn(); // Connection 객체에서 conn 받아오기
			String query = "insert into members"
							+ " (snsuser, userid, userpass, username, useremail, postcode, addr, detailaddr, tel, uip, photo)"
							+ " values"
							+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getSnsuser());
			pstmt.setString(2, dto.getUserid());
			pstmt.setString(3, dto.getUserpass());
			pstmt.setString(4, dto.getUsername());
			pstmt.setString(5, dto.getUseremail());
			pstmt.setInt(6, dto.getPostcode());
			pstmt.setString(7, dto.getAddr());
			pstmt.setString(8, dto.getDetailaddr());
			pstmt.setString(9, dto.getTel());
			pstmt.setString(10, dto.getUip());
			pstmt.setString(11, dto.getPhoto());
			flag = pstmt.executeUpdate();
			System.out.println(pstmt);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e){}
		}
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	// select 
	public static Vector <MembersDTO> getSelect(String str){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from members where userid = ?";
		Vector <MembersDTO> data = new Vector <> ();
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, str);
			rs = ps.executeQuery();
			while(rs.next()) {
				MembersDTO mb = new MembersDTO();
				mb.setSnsuser(rs.getString("snsuser"));
				mb.setNum(rs.getInt("num"));
				mb.setUserid(rs.getString("userid"));
				mb.setUserpass(rs.getString("userpass"));
				mb.setUsername(rs.getString("username"));
				mb.setUseremail(rs.getString("useremail"));
				mb.setPostcode(rs.getInt("postcode"));
				mb.setAddr(rs.getString("addr"));
				mb.setDetailaddr(rs.getString("detailaddr"));
				mb.setTel(rs.getString("tel"));
				mb.setLevel(rs.getInt("level"));
				mb.setUip(rs.getString("uip"));
				mb.setWdate(rs.getString("wdate"));
				mb.setPhoto(rs.getString("photo"));
				data.add(mb);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(ps != null) ps.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			}catch(SQLException e){}
		}
		return data;
	}
	
	// select 
		public static Vector <MembersDTO> getSelectSnsUser(String str){
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select * from members where snsuser = ?";
			Vector <MembersDTO> data = new Vector <> ();
			try {
				conn = new DBConnect().getConn();
				ps = conn.prepareStatement(sql);
				ps.setString(1, str);
				rs = ps.executeQuery();
				while(rs.next()) {
					MembersDTO mb = new MembersDTO();
					mb.setSnsuser(rs.getString("snsuser"));
					mb.setNum(rs.getInt("num"));
					mb.setUserid(rs.getString("userid"));
					mb.setUserpass(rs.getString("userpass"));
					mb.setUsername(rs.getString("username"));
					mb.setUseremail(rs.getString("useremail"));
					mb.setPostcode(rs.getInt("postcode"));
					mb.setAddr(rs.getString("addr"));
					mb.setDetailaddr(rs.getString("detailaddr"));
					mb.setTel(rs.getString("tel"));
					mb.setLevel(rs.getInt("level"));
					mb.setUip(rs.getString("uip"));
					mb.setWdate(rs.getString("wdate"));
					mb.setPhoto(rs.getString("photo"));
					data.add(mb);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try{
					if(ps != null) ps.close();
					if(conn != null) conn.close();
					if(rs != null) rs.close();
				}catch(SQLException e){}
			}
			return data;
		}
	
	// select 
	public static Vector <MembersDTO> getSelect(String str1, String str2, int opt){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		if(opt == 1) {
			sql = "select userid, userpass from members where username = ? and useremail = ?";
		}else {
			sql = "select userid, userpass from members where username = ? and useremail = ?";
		}
		Vector <MembersDTO> data = new Vector <> ();
		conn = new DBConnect().getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, str1);
			ps.setString(1, str2);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				MembersDTO mb = new MembersDTO();
				mb.setNum(rs.getInt("num"));
				mb.setUserid(rs.getString("userid"));
				mb.setUserpass(rs.getString("userpass"));
				mb.setUsername(rs.getString("username"));
				mb.setUseremail(rs.getString("useremail"));
				mb.setPostcode(rs.getInt("postcode"));
				mb.setAddr(rs.getString("addr"));
				mb.setDetailaddr(rs.getString("detailaddr"));
				mb.setTel(rs.getString("tel"));
				mb.setLevel(rs.getInt("level"));
				mb.setUip(rs.getString("uip"));
				mb.setWdate(rs.getString("wdate"));
				mb.setPhoto(rs.getString("photo"));
				data.add(mb);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(ps != null) ps.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			}catch(SQLException e){}
		}
		return data;
	}
	
	public static String selectPhoto(String userid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String fileName = "";
		
		String sql = "select photo from members where userid = ?";
		
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				fileName = rs.getString("photo");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
			}catch(SQLException e) {}
		}
		
		return fileName;
	}
	
	// select 
		public static Vector <MembersDTO> getSelectAll(){
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select * from members";
			Vector <MembersDTO> data = new Vector <> ();
			try {
				conn = new DBConnect().getConn();
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					MembersDTO mb = new MembersDTO();
					mb.setNum(rs.getInt("num"));
					mb.setSnsuser(rs.getString("snsuser"));
					mb.setUserid(rs.getString("userid"));
					mb.setUserpass(rs.getString("userpass"));
					mb.setUsername(rs.getString("username"));
					mb.setUseremail(rs.getString("useremail"));
					mb.setPostcode(rs.getInt("postcode"));
					mb.setAddr(rs.getString("addr"));
					mb.setDetailaddr(rs.getString("detailaddr"));
					mb.setTel(rs.getString("tel"));
					mb.setLevel(rs.getInt("level"));
					mb.setUip(rs.getString("uip"));
					mb.setWdate(rs.getString("wdate"));
					mb.setPhoto(rs.getString("photo"));
					data.add(mb);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try{
					if(ps != null) ps.close();
					if(conn != null) conn.close();
					if(rs != null) rs.close();
				}catch(SQLException e){}
			}
			return data;
		}
	
		// 회원리스트 출력
		   public static Vector<MembersDTO> getSelectAll(int limitNum, int listNum){
		      Connection conn = null;
		      PreparedStatement ps = null;
		      ResultSet rs = null;
		      String sql = "select * from members order by num asc limit ?, ?";
		      Vector<MembersDTO> data = new Vector<>();
		      
		      
		      try {
		         conn = new DBConnect().getConn();
		         ps = conn.prepareStatement(sql);
		         ps.setInt(1, limitNum);
		         ps.setInt(2, listNum);
		         rs = ps.executeQuery();
		         
		         while(rs.next()) {
		            MembersDTO mb = new MembersDTO();
		            mb.setNum(rs.getInt("num"));
		            mb.setSnsuser(rs.getString("snsuser"));
		            mb.setUserid(rs.getString("userid"));
		            mb.setUserpass(rs.getString("userpass"));
		            mb.setUsername(rs.getString("username"));
		            mb.setUseremail(rs.getString("useremail"));
		            mb.setPostcode(rs.getInt("postcode"));
		            mb.setAddr(rs.getString("addr"));
		            mb.setDetailaddr(rs.getString("detailaddr"));
		            mb.setTel(rs.getString("tel"));
		            mb.setLevel(rs.getInt("level"));
		            mb.setUip(rs.getString("uip"));
		            mb.setWdate(rs.getString("wdate"));
		            mb.setPhoto(rs.getString("photo"));
		            data.add(mb);
		         }
		         
		      }catch(Exception e) {
		         e.printStackTrace();
		      }finally {
		         try {
		            if(rs != null) rs.close();
		            if(ps != null) ps.close();
		            if(conn != null) conn.close();
		         }catch(SQLException e) {
		            
		         }
		      }
		      return data;
		      
		   }
	
	public boolean update(MembersDTO dto, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		String query = "";
		try {
			conn = new DBConnect().getConn(); // Connection 객체에서 conn 받아오기
			if(dto.getUserpass() == null || dto.getUserpass().isEmpty()) {
				query = "update members set"
						+ " username = ?, useremail = ?, postcode = ?, addr = ?, detailaddr = ?, tel = ?, uip = ?, wdate = ?, photo = ?"
						+ " where userid = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, dto.getUsername());
				pstmt.setString(2, dto.getUseremail());
				pstmt.setInt(3, dto.getPostcode());
				pstmt.setString(4, dto.getAddr());
				pstmt.setString(5, dto.getDetailaddr());
				pstmt.setString(6, dto.getTel());
				pstmt.setString(7, dto.getUip());
				pstmt.setString(8,  dto.getWdate());
				pstmt.setString(9,  dto.getPhoto());
				pstmt.setString(10,  userid);
			}else {
				query = "update members set"
						+ " userpass = ?, username = ?, useremail = ?, postcode = ?, addr = ?, detailaddr = ?, tel = ?, uip = ?, wdate = ?"
						+ " where userid = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, dto.getUserpass());
				pstmt.setString(2, dto.getUsername());
				pstmt.setString(3, dto.getUseremail());
				pstmt.setInt(4, dto.getPostcode());
				pstmt.setString(5, dto.getAddr());
				pstmt.setString(6, dto.getDetailaddr());
				pstmt.setString(7, dto.getTel());
				pstmt.setString(8, dto.getUip());
				pstmt.setString(9,  dto.getWdate());
				pstmt.setString(10,  dto.getPhoto());
				pstmt.setString(11,  userid);
			}
				flag = pstmt.executeUpdate();
				System.out.println(pstmt);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e){}
		}
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean updateForSnsUser(MembersDTO dto, String snsUser) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		String query = "";
		try {
			conn = new DBConnect().getConn(); // Connection 객체에서 conn 받아오기
			if(dto.getUserpass() == null || dto.getUserpass().isEmpty()) {
				query = "update members set"
						+ " username = ?, useremail = ?, postcode = ?, addr = ?, detailaddr = ?, tel = ?, uip = ?, wdate = ?, photo = ?, userid = ?"
						+ " where snsuser = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, dto.getUsername());
				pstmt.setString(2, dto.getUseremail());
				pstmt.setInt(3, dto.getPostcode());
				pstmt.setString(4, dto.getAddr());
				pstmt.setString(5, dto.getDetailaddr());
				pstmt.setString(6, dto.getTel());
				pstmt.setString(7, dto.getUip());
				pstmt.setString(8,  dto.getWdate());
				pstmt.setString(9,  dto.getPhoto());
				pstmt.setString(10,  dto.getUserid());
				pstmt.setString(11,  snsUser);
			}else {
				query = "update members set"
						+ " userpass = ?, username = ?, useremail = ?, postcode = ?, addr = ?, detailaddr = ?, tel = ?, uip = ?, wdate = ?, photo = ?, userid = ?"
						+ " where snsuser = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, dto.getUserpass());
				pstmt.setString(2, dto.getUsername());
				pstmt.setString(3, dto.getUseremail());
				pstmt.setInt(4, dto.getPostcode());
				pstmt.setString(5, dto.getAddr());
				pstmt.setString(6, dto.getDetailaddr());
				pstmt.setString(7, dto.getTel());
				pstmt.setString(8, dto.getUip());
				pstmt.setString(9,  dto.getWdate());
				pstmt.setString(10,  dto.getPhoto());
				pstmt.setString(11,  dto.getUserid());
				pstmt.setString(12,  snsUser);
			}
				flag = pstmt.executeUpdate();
				System.out.println(pstmt);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e){}
		}
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static int updateLevel(MembersDTO dto, String userid) {
		Connection conn = null;
		PreparedStatement ps = null;
		int flag = 0;
		String query = "update members set level = ? where userid = ?";
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(query);
			ps.setInt(1, dto.getLevel());
			ps.setString(2, userid);
			flag = ps.executeUpdate();
			System.out.println(ps);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch(SQLException e){}
		}
		if(flag > 0) {
			return 1;
		}else {
			return 0;
		}
	}
	
	
	public static int updatePw(String password, String userid) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		int flag = 0;
		String sql = "update members set userpass = ? where userid = ?";
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2,  userid);
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
			return 1;
		}else {
			return 0;
		}
		
	}
	
	// 회원로그인 성공 실패 판단
	public int checkLogin(MembersDTO dto) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int checkUser = 0;
		String sql = "select * from members where userid = ? and userpass = ?";
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1,  dto.getUserid());
			ps.setString(2,  dto.getUserpass());
			rs = ps.executeQuery();
			if(rs.next()) {
				checkUser = rs.getInt("level");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch(SQLException e){}
		}
		
		return checkUser;
	}
	
	public boolean useridCheck(String userid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userid from members where userid = ?";
		boolean result = false;
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1,  userid);
			rs = ps.executeQuery();
			if(rs.next()) {
				result = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch(SQLException e){}
		}
		return result;
	}
	
	public String snsUserCheck(String snsUser) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userid from members where snsuser = ?";
		String result = null;
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1,  snsUser);
			rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString("userid");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch(SQLException e){}
		}
		return result;
	}
	
	public static String findMem(String Col1,String Col2,String ques1, String ques2, String res) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from members where " + Col1 + " = ? and " + Col2 + " = ?";
		String result = "";
	
		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ques1);
			ps.setString(2, ques2);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getString(res);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch(SQLException e){}
		}
		return result;
	}

	
}
