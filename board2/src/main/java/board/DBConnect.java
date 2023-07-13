package board;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnect {
	// 필드
	private DataSource dataSource;
	
	// 생성자
	public DBConnect() {
		try {
			// Context, lookup
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/bbs");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 메소드
	public Connection getConn() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			System.out.println("new DB 접속 성공");
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		DBConnect dbConnect = new DBConnect();
		dbConnect.getConn();
	}
	
//	private final static String uid = "root";
//	private final static String pass = "whoareyou1085!";
//	private final static String url = "jdbc:mysql://localhost:3306/mybbsDB?useUnicode=true&characterEncoding=UTF-8";
//	private final static String driver = "com.mysql.cj.jdbc.Driver";
//	
//	private Connection conn;
//	
//	// 생성자에서 Connection
//	public DBConnect() {
//		
		
//		try {
//			Context context = new InitialContext();
//			DataSource source  = (DataSource) context.lookup("java:comp/env/jdbc/bbs");
//			conn = source.getConnection();
//			System.out.println("new DB 접속 성공");
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	// 메소드
//	public Connection getConn() {
//		return conn;
//	}
}
