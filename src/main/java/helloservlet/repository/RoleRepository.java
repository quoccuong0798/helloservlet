package helloservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.RoleEntity;

public class RoleRepository {
	
	public int deletebyId(int id) {
		int count=0;
		String query="DELETE FROM roles r WHERE r.id=?";
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
	public int insertRole(String roleName, String desc ) {
		 int count=0;
		try {
				String query="INSERT INTO roles (name, description) VALUES (?,?)";
			
			
			//b3: Mở kết nối csdl và truyền câu query cho JDBC thông qua Prepared statement
				Connection connection = MysqlConfig.getConnection();
			
				// b4: truyền câu query vào CSDL vừa kết nối thông qua Prepared Statemnt
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				//gán giá trị cho tham số dấu ? bên trong câu query
				preparedStatement.setString(1, roleName);
				preparedStatement.setString(2, desc);
				
				//b5: THông báo cho CSDL biết và thực thi câu Query
				//execute query: dành cho câu truy vấn là cầu select=> luôn trả Result set	
				//execute update: dành cho tất cả câu truy vấn còn lại ngoài câu select ví dụ: insert, update, delete
				count= preparedStatement.executeUpdate();
				
				
		}catch(Exception e){
			System.out.println("Lỗi insert Role "+e.getLocalizedMessage());
			
		}
		return count;
	 }
	//update Role
	public int updateRole(int id,String roleName, String desc) {
		 int count=0;
		try {
				String query="UPDATE roles SET Name=? , description=?"
						+ "WHERE id=?";
			
			
			//b3: Mở kết nối csdl và truyền câu query cho JDBC thông qua Prepared statement
				Connection connection = MysqlConfig.getConnection();
			
				// b4: truyền câu query vào CSDL vừa kết nối thông qua Prepared Statemnt
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				//gán giá trị cho tham số dấu ? bên trong câu query
				preparedStatement.setString(1, roleName);
				preparedStatement.setString(2, desc);
				preparedStatement.setInt(3, id);
				
				//b5: THông báo cho CSDL biết và thực thi câu Query
				//execute query: dành cho câu truy vấn là cầu select=> luôn trả Result set	
				//execute update: dành cho tất cả câu truy vấn còn lại ngoài câu select ví dụ: insert, update, delete
				count= preparedStatement.executeUpdate();
				
				
		}catch(Exception e){
			System.out.println("Lỗi update Role "+e.getLocalizedMessage());
			
		}
		return count;
	 }
	//end update role
	
	public List<RoleEntity> findAll(){
		List<RoleEntity> listRole = new ArrayList<RoleEntity>();
		String query = "SELECT * FROM roles";
		try {
			Connection connection =  MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet =  preparedStatement.executeQuery();
			while(resultSet.next()) {
				RoleEntity roleEntity = new RoleEntity();
				roleEntity.setId(resultSet.getInt(1));
				roleEntity.setName(resultSet.getString(2));
				roleEntity.setDesc(resultSet.getString(3));
				
				listRole.add(roleEntity);
			}
			
			
			
		} catch (Exception e) {
			System.out.println("Lỗi findAll" +e.getMessage());
		}
		
		return listRole;
	}
	public List<RoleEntity>findById(int id){
		List<RoleEntity> listRole = new ArrayList<RoleEntity>();
		String query = "SELECT * FROM roles WHERE id=?";
		try {
			Connection connection =  MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			//gán giá trị cho tham số dấu ? bên trong câu query
			preparedStatement.setInt(1, id);
			ResultSet resultSet =  preparedStatement.executeQuery();
			while(resultSet.next()) {
				RoleEntity roleEntity = new RoleEntity();
				roleEntity.setId(resultSet.getInt(1));
				roleEntity.setName(resultSet.getString(2));
				roleEntity.setDesc(resultSet.getString(3));
				
				listRole.add(roleEntity);
			}
			
			
			
		} catch (Exception e) {
			System.out.println("Lỗi findByidroles " +e.getMessage());
		}
		
		return listRole;
	}
	
}
