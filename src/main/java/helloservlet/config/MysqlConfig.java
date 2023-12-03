package helloservlet.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConfig {
	/**
	 * 
	 * @author Cường
	 * class dùng để khai báo thông tin cấu hình  tạo kết nối tới CSDL
	 */
	public static Connection getConnection() {
		//Khai báo Driver sử dụng cho JDBC ( từ khóa  tên driver class.forName )
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Khai báo thông tin csdl mà JDBC sẽ kết nối tới
			return DriverManager.getConnection("jdbc:mysql://localhost:3307/crm_app", "root", "admin123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi kết nối cơ dở dữ liệu: "+e.getLocalizedMessage());
		}
		
		return null;
	}
	
	
	
}
