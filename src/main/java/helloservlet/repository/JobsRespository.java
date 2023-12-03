package helloservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.JobsEntity;
import helloservlet.entity.RoleEntity;

public class JobsRespository {
	public List<JobsEntity> getAllJob(){
		List<JobsEntity> listJobs = new ArrayList<JobsEntity>();
		String query = "SELECT * FROM jobs";
		try {
			Connection connection =  MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet =  preparedStatement.executeQuery();
			while(resultSet.next()) {
				JobsEntity jobsEntity = new JobsEntity();
				jobsEntity.setId(resultSet.getInt(1));
				jobsEntity.setName(resultSet.getString(2));
				jobsEntity.setStartDate(resultSet.getDate(3));
				jobsEntity.setEndDate(resultSet.getDate(4));
				listJobs.add(jobsEntity);
			}
			
			
			
		} catch (Exception e) {
			System.out.println("Lỗi getAllJobController " +e.getMessage());
		}
		
		return listJobs;
	}
	public int deletebyId(int id) {
		int count=0;
		String query="DELETE FROM jobs j WHERE j.id=?";
		try {
			Connection connection= MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1,id);
			count=preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("Lỗi delte job "+e.getLocalizedMessage());
		}
		
		return count;
		
	}
	public int updateById(int id, String name, String startDate, String endDate) {
		 int count=0;
			try {
					String query="UPDATE jobs set name=?, start_date=?,end_date=? WHERE id=?";
				
				
				//b3: Mở kết nối csdl và truyền câu query cho JDBC thông qua Prepared statement
					Connection connection = MysqlConfig.getConnection();
				
					// b4: truyền câu query vào CSDL vừa kết nối thông qua Prepared Statemnt
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					//gán giá trị cho tham số dấu ? bên trong câu query
					preparedStatement.setString(1, name);
					preparedStatement.setString(2, startDate);
					preparedStatement.setString(3, endDate);
					preparedStatement.setInt(4, id);
					//b5: THông báo cho CSDL biết và thực thi câu Query
					//execute query: dành cho câu truy vấn là cầu select=> luôn trả Result set	
					//execute update: dành cho tất cả câu truy vấn còn lại ngoài câu select ví dụ: insert, update, delete
					count= preparedStatement.executeUpdate();
					
					
			}catch(Exception e){
				System.out.println("Lỗi updateJobs "+e.getLocalizedMessage());
				
			}
			return count;
	}
	public int insertJob(String name,String startDate,String endDate) {
		 int count=0;
		try {
				String query="INSERT INTO jobs (name, start_date,end_date) VALUES (?,?,?)";
			
			
			//b3: Mở kết nối csdl và truyền câu query cho JDBC thông qua Prepared statement
				Connection connection = MysqlConfig.getConnection();
			
				// b4: truyền câu query vào CSDL vừa kết nối thông qua Prepared Statemnt
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				//gán giá trị cho tham số dấu ? bên trong câu query
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, startDate);
				preparedStatement.setString(3, endDate);
				
				//b5: THông báo cho CSDL biết và thực thi câu Query
				//execute query: dành cho câu truy vấn là cầu select=> luôn trả Result set	
				//execute update: dành cho tất cả câu truy vấn còn lại ngoài câu select ví dụ: insert, update, delete
				count= preparedStatement.executeUpdate();
				
				
		}catch(Exception e){
			System.out.println("Lỗi insert Job "+e.getLocalizedMessage());
			
		}
		return count;
	 }

}
