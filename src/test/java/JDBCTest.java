import java.sql.*;
import java.util.Date;

/**
 * @author : ys
 * @date : 2019/4/29 15:38 星期一
 **/
public class JDBCTest {
	private static Connection getConn() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT&useSSL=false&characterEncoding=utf8";
		String username = "root";
		String password = "admin";
		Connection conn = null;
		try {
			Class.forName(driver); //classLoader,加载对应驱动
			conn =  DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	private static int insert(String p,String c) {
		Connection conn = getConn();
		int i = 0;
		String sql = "insert into product (product_name,company_name) values(?,?)";
		PreparedStatement pstmt;
		try {
			pstmt =  conn.prepareStatement(sql);
			pstmt.setString(1, p);
			pstmt.setString(2, c);
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	private static int insertSale(int pid,double price) {
		Connection conn = getConn();
		int i = 0;
		String sql = "insert into sale_log (sale_time,product_id,price) values(?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt =  conn.prepareStatement(sql);
			pstmt.setTimestamp(1,new Timestamp(new Date().getTime()));
			pstmt.setInt(2,pid);
			pstmt.setDouble(3,price);
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	public static void main(String[] args) {
		/*System.out.println(insert("产品1","公司A"));
		System.out.println(insert("产品2","公司A"));
		System.out.println(insert("产品3","公司A"));
		System.out.println(insert("产品1","公司B"));
		System.out.println(insert("产品2","公司B"));
		System.out.println(insert("产品1","公司C"));
		System.out.println(insert("产品1","公司D"));
		System.out.println(insert("产品1","公司E"));
		System.out.println(insert("产品1","公司F"));
		System.out.println(insert("产品1","公司G"));*/

		/*System.out.println(insertSale(1,20.1));
		System.out.println(insertSale(1,20.1));
		System.out.println(insertSale(1,20.1));
		System.out.println(insertSale(2,20.2));
		System.out.println(insertSale(2,20.2));
		System.out.println(insertSale(3,20.3));
		System.out.println(insertSale(4,20.4));
		System.out.println(insertSale(5,20.5));*/
		System.out.println(getConn());

	}
}
