package helloservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import helloservlet.entity.UserEntity;
import helloservlet.model.UserModel;
import helloservlet.config.MysqlConfig;

/*
 * Chứa tất các câu query liên quan đến User
 * 
 * 
 * select: find
 * where: by
 * --findByEmailAndPassword
 */

public class UserRepository {
	
	 public List<UserModel> findByEmailAndPassword(String email,String password){
		//b2: chuẩn bị câu truy vấn
		String query="SELECT * FROM users u WHERE u.email =? AND u.password =?";
		
		//b3: Mở kết nối csdl và truyền câu query cho JDBC thông qua Prepared statement
		Connection connection = MysqlConfig.getConnection();
		
		//Tao list UserEntity để lưu trữ từng dòng dữ liệu mình query được
		List<UserModel> listUser = new  ArrayList<UserModel>();
	try {
			// b4: truyền câu query vào CSDL vừa kết nối thông qua Prepared Statemnt
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			//gán giá trị cho tham số dấu ? bên trong câu query
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			//b5: THông báo cho CSDL biết và thực thi câu Query
			//execute query: dành cho câu truy vấn là cầu select=> luôn trả Result set	
			//execute update: dành cho tất cả câu truy vấn còn lại ngoài câu select ví dụ: insert, update, delete
			ResultSet resultSet = preparedStatement.executeQuery();
			
			//b6: Duyệt từng dòng dữ liệu và gán vào List này
			while(resultSet.next()) {
				UserModel userModel= new UserModel();
				userModel.setId(resultSet.getInt("id")); //(tên cột database) lấy giá trị của cột id gắn vào thuộc tính id của userentity 
				//vào thuộc tính
				userModel.setFirstName(resultSet.getString("firstname"));
				userModel.setLastName(resultSet.getString("lastname"));
				userModel.setEmail(resultSet.getString("email"));
				userModel.setIdRole(resultSet.getInt("role_id"));
				listUser.add(userModel);
			}
	}catch(Exception e){
		System.out.println("Lỗi findbyemailandpassword "+e.getLocalizedMessage());
		
	}
		
		return listUser;
	}
	 
	 
	 public List<UserModel> findAllUser(){
		 List<UserModel> listUser =new ArrayList<UserModel>();
		 try {
	            Connection connection = MysqlConfig.getConnection();
	            String query = "SELECT u.id, u.email, u.password, u.firstname, u.lastname, r.name\n" +
	                    "FROM users u\n" +
	                    "INNER JOIN roles r\n" +
	                    "ON u.role_id=r.id;";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ResultSet rs = ps.executeQuery();

	            while(rs.next()) {
	                UserModel user = new UserModel();
	                user.setId(rs.getInt("id"));
	                user.setEmail(rs.getString("email"));
	                user.setFirstName(rs.getString("firstname"));
	                user.setLastName(rs.getString("lastname"));
	                user.setRoleName(rs.getString("name"));

	                listUser.add(user);
	            }

	            connection.close();
	        } catch (SQLException e) {
	            System.out.println("Lỗi lấy all user " + e.getMessage());
	        }
	        return listUser;
	 }
	 public int deletebyId(int id) {
			int count=0;
			String query="DELETE FROM users u WHERE u.id=?";
			try {
				Connection connection= MysqlConfig.getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(query);
				preparedStatement.setInt(1,id);
				count=preparedStatement.executeUpdate();
				
				
			} catch (Exception e) {
				System.out.println("Lỗi "+e.getLocalizedMessage());
			}
			
			return count;
								
		}
	 public int insertUser(String firstName, String lastName, String email, String password, int roleId) {
		 int count=0;
			try {
					String query="INSERT INTO users (firstname, lastname, email, password, role_id) VALUES (?,?,?,?,?)";
				
				
				//b3: Mở kết nối csdl và truyền câu query cho JDBC thông qua Prepared statement
					Connection connection = MysqlConfig.getConnection();
				
					// b4: truyền câu query vào CSDL vừa kết nối thông qua Prepared Statemnt
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					//gán giá trị cho tham số dấu ? bên trong câu query
					preparedStatement.setString(1, firstName);
					preparedStatement.setString(2, lastName);
					preparedStatement.setString(3, email);
					preparedStatement.setString(4, password);
					preparedStatement.setInt(5, roleId);
					
					
					//b5: THông báo cho CSDL biết và thực thi câu Query
					//execute query: dành cho câu truy vấn là cầu select=> luôn trả Result set	
					//execute update: dành cho tất cả câu truy vấn còn lại ngoài câu select ví dụ: insert, update, delete
					count= preparedStatement.executeUpdate();
					
					
			}catch(Exception e){
				System.out.println("Lỗi insert Role "+e.getLocalizedMessage());
				
			}
			return count;
	 }
	 public List<UserModel> findById(int id){
		//b2: chuẩn bị câu truy vấn
			String query="SELECT users.id , users.email, users.password  , users.firstname, users.lastname , users.role_id , roles.name \r\n"
					+ "FROM users\r\n"
					+ "JOIN roles ON users.role_id  = roles.id\r\n"
					+ "WHERE users.id=?";
			
			//b3: Mở kết nối csdl và truyền câu query cho JDBC thông qua Prepared statement
			Connection connection = MysqlConfig.getConnection();
			
			//Tao list UserEntity để lưu trữ từng dòng dữ liệu mình query được
			List<UserModel> listUser = new  ArrayList<UserModel>();
		try {
				// b4: truyền câu query vào CSDL vừa kết nối thông qua Prepared Statemnt
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				//gán giá trị cho tham số dấu ? bên trong câu query
				preparedStatement.setInt(1, id);
				
				//b5: THông báo cho CSDL biết và thực thi câu Query
				//execute query: dành cho câu truy vấn là cầu select=> luôn trả Result set	
				//execute update: dành cho tất cả câu truy vấn còn lại ngoài câu select ví dụ: insert, update, delete
				ResultSet resultSet = preparedStatement.executeQuery();
				
				//b6: Duyệt từng dòng dữ liệu và gán vào List này
				while(resultSet.next()) {
					UserModel userModel= new UserModel();
					userModel.setId(resultSet.getInt("id")); //(tên cột database) lấy giá trị của cột id gắn vào thuộc tính id của userentity 
					//vào thuộc tính
					userModel.setFirstName(resultSet.getString("firstname"));
					userModel.setLastName(resultSet.getString("lastname"));
					userModel.setEmail(resultSet.getString("email"));
					userModel.setPassword(resultSet.getString("password"));
					userModel.setIdRole(resultSet.getInt("role_id"));
					userModel.setRoleName(resultSet.getString("name"));
					listUser.add(userModel);
				}
		}catch(Exception e){
			System.out.println("Lỗi findbyemailandpassword "+e.getLocalizedMessage());
			
		}
			
			return listUser;
	 }
	 public int updateUserById(String email, String password, int roleId, String firstName, String lastName, int id) {
		 int count=0;
			try {
					String query="UPDATE users SET email=? , password=?, role_id=?,firstname=?,lastname=?"
							+ "WHERE id=?";
				
				
				//b3: Mở kết nối csdl và truyền câu query cho JDBC thông qua Prepared statement
					Connection connection = MysqlConfig.getConnection();
				
					// b4: truyền câu query vào CSDL vừa kết nối thông qua Prepared Statemnt
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					//gán giá trị cho tham số dấu ? bên trong câu query
					preparedStatement.setString(1, email);
					preparedStatement.setString(2, password);
					preparedStatement.setInt(3, roleId);
					preparedStatement.setString(4, firstName);
					preparedStatement.setString(5, lastName);
					preparedStatement.setInt(6, id);
				
					
					//b5: THông báo cho CSDL biết và thực thi câu Query
					//execute query: dành cho câu truy vấn là cầu select=> luôn trả Result set	
					//execute update: dành cho tất cả câu truy vấn còn lại ngoài câu select ví dụ: insert, update, delete
					count= preparedStatement.executeUpdate();
					
					
			}catch(Exception e){
				System.out.println("Lỗi update Role "+e.getLocalizedMessage());
				
			}
			return count;
		 
	 }
	 

}
