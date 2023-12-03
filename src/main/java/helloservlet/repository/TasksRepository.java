package helloservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.plugins.tiff.GeoTIFFTagSet;

import helloservlet.config.MysqlConfig;

import helloservlet.model.TasksModel;
import helloservlet.model.UserModel;

public class TasksRepository {
	public List<TasksModel> getAll(){
		List<TasksModel> listTaskModel = new ArrayList<TasksModel>();
		String 	query = "SELECT\r\n"
				+ "    tasks.id AS tasks_id,\r\n"
				+ "    tasks.name AS tasks_name,\r\n"
				+ "    jobs.name AS jobs_name,\r\n"
				+ "    users.firstname,\r\n"
				+ "    users.lastname,\r\n"
				+ "    users.id AS user_id,\r\n"
				+ "    tasks.start_date AS tasks_start_date,\r\n"
				+ "    tasks.end_date AS tasks_end_date,\r\n"
				+ "    status.name AS status_name\r\n"
				+ "FROM\r\n"
				+ "    tasks\r\n"
				+ "INNER JOIN\r\n"
				+ "    users ON tasks.user_id = users.id\r\n"
				+ " INNER JOIN\r\n"
				+ "    status ON tasks.status_id = status.id\r\n"
				+ "INNER JOIN\r\n"
				+ "	jobs ON tasks.job_id = jobs.id";
		try {
			Connection connection =  MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet =  preparedStatement.executeQuery();
			while(resultSet.next()) {
				TasksModel tasksModel = new TasksModel();
				tasksModel.setId(resultSet.getInt("tasks_id"));
			    tasksModel.setName(resultSet.getString("tasks_name"));
			    tasksModel.setJobName(resultSet.getString("jobs_name"));
			    tasksModel.setFirstName(resultSet.getString("firstname"));
			    tasksModel.setLastName(resultSet.getString("lastname"));
			    tasksModel.setIdUserName(resultSet.getInt("user_id"));
			    tasksModel.setTaskStartDate(resultSet.getDate("tasks_start_date"));
			    tasksModel.setTaskEndDate(resultSet.getDate("tasks_end_date"));
			    tasksModel.setStatus(resultSet.getString("status_name"));
			    
			    listTaskModel.add(tasksModel);
			}
			
			
			
		} catch (Exception e) {
			System.out.println("Lỗi getAlltaskController " +e.getMessage());
		}
		
		return listTaskModel;
	}
	public List<TasksModel> getByIdUser( int id){
		List<TasksModel> listTaskModel = new ArrayList<TasksModel>();
		String 	query = "SELECT\r\n"
				+ "    tasks.id AS tasks_id,\r\n"
				+ "    tasks.name AS tasks_name,\r\n"
				+ "    jobs.name AS jobs_name,\r\n"
				+ "    users.firstname,\r\n"
				+ "    users.lastname,\r\n"
				+ "    users.id AS user_id,\r\n"
				+ "    tasks.start_date AS tasks_start_date,\r\n"
				+ "    tasks.end_date AS tasks_end_date,\r\n"
				+ "    status.name AS status_name\r\n"
				+ "FROM\r\n"
				+ "    tasks\r\n"
				+ "INNER JOIN\r\n"
				+ "    users ON tasks.user_id = users.id\r\n"
				+ " INNER JOIN\r\n"
				+ "    status ON tasks.status_id = status.id\r\n"
				+ "INNER JOIN\r\n"
				+ "	jobs ON tasks.job_id = jobs.id WHERE user_id =?";
		try {
			Connection connection =  MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet =  preparedStatement.executeQuery();
			while(resultSet.next()) {
				TasksModel tasksModel = new TasksModel();
				tasksModel.setId(resultSet.getInt("tasks_id"));
			    tasksModel.setName(resultSet.getString("tasks_name"));
			    tasksModel.setJobName(resultSet.getString("jobs_name"));
			    tasksModel.setFirstName(resultSet.getString("firstname"));
			    tasksModel.setLastName(resultSet.getString("lastname"));
			    tasksModel.setIdUserName(resultSet.getInt("user_id"));
			    tasksModel.setTaskStartDate(resultSet.getDate("tasks_start_date"));
			    tasksModel.setTaskEndDate(resultSet.getDate("tasks_end_date"));
			    tasksModel.setStatus(resultSet.getString("status_name"));
			    
			    listTaskModel.add(tasksModel);
			}
			
			
			
		} catch (Exception e) {
			System.out.println("Lỗi getAlltaskController " +e.getMessage());
		}
		
		return listTaskModel;
	}
	
	//
	public List<TasksModel> getByIdJob( int id){
		List<TasksModel> listTaskModel = new ArrayList<TasksModel>();
		String 	query = "SELECT\r\n"
				+ "    tasks.id AS tasks_id,\r\n"
				+ "    tasks.name AS tasks_name,\r\n"
				+ "    jobs.name AS jobs_name,\r\n"
				+ "    users.firstname,\r\n"
				+ "    users.lastname,\r\n"
				+ "    users.id AS user_id,\r\n"
				+ "    tasks.start_date AS tasks_start_date,\r\n"
				+ "    tasks.end_date AS tasks_end_date,\r\n"
				+ "    status.name AS status_name\r\n"
				+ "FROM\r\n"
				+ "    tasks\r\n"
				+ "INNER JOIN\r\n"
				+ "    users ON tasks.user_id = users.id\r\n"
				+ " INNER JOIN\r\n"
				+ "    status ON tasks.status_id = status.id\r\n"
				+ "INNER JOIN\r\n"
				+ "	jobs ON tasks.job_id = jobs.id WHERE jobs.id =?";
		try {
			Connection connection =  MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet =  preparedStatement.executeQuery();
			while(resultSet.next()) {
				TasksModel tasksModel = new TasksModel();
				tasksModel.setId(resultSet.getInt("tasks_id"));
			    tasksModel.setName(resultSet.getString("tasks_name"));
			    tasksModel.setJobName(resultSet.getString("jobs_name"));
			    tasksModel.setFirstName(resultSet.getString("firstname"));
			    tasksModel.setLastName(resultSet.getString("lastname"));
			    tasksModel.setIdUserName(resultSet.getInt("user_id"));
			    tasksModel.setTaskStartDate(resultSet.getDate("tasks_start_date"));
			    tasksModel.setTaskEndDate(resultSet.getDate("tasks_end_date"));
			    tasksModel.setStatus(resultSet.getString("status_name"));
			    
			    listTaskModel.add(tasksModel);
			}
			
			
			
		} catch (Exception e) {
			System.out.println("Lỗi getAlltaskController " +e.getMessage());
		}
		
		return listTaskModel;
	}
	//
	
	public int deletebyId(int id) {
		int count=0;
		String query="DELETE FROM tasks t WHERE t.id=?";
		try {
			Connection connection= MysqlConfig.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1,id);
			count=preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("Lỗi delte tasks "+e.getLocalizedMessage());
		}
		
		return count;
		
	}
	public int insertTask(int id,String name, String startDate,String endDate, int userId, int jobId, int statusId) {
		 int count=0;
		try {
				String query="INSERT INTO tasks (name, start_date,end_date,user_id,job_id,status_id) VALUES (?,?,?,?,?,?)";
			
			
			//b3: Mở kết nối csdl và truyền câu query cho JDBC thông qua Prepared statement
				Connection connection = MysqlConfig.getConnection();
			
				// b4: truyền câu query vào CSDL vừa kết nối thông qua Prepared Statemnt
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				//gán giá trị cho tham số dấu ? bên trong câu query
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, startDate);
				preparedStatement.setString(3, endDate);
				preparedStatement.setInt(4, userId);
				preparedStatement.setInt(5, jobId);
				preparedStatement.setInt(6, statusId);
				//b5: THông báo cho CSDL biết và thực thi câu Query
				//execute query: dành cho câu truy vấn là cầu select=> luôn trả Result set	
				//execute update: dành cho tất cả câu truy vấn còn lại ngoài câu select ví dụ: insert, update, delete
				count= preparedStatement.executeUpdate();
				
				
		}catch(Exception e){
			System.out.println("Lỗi insert Task "+e.getLocalizedMessage());
			
		}
		return count;
	 }
	public int updateTask(String name, String startDate,String endDate, int userId, int jobId, int statusId, int id) {
		 int count=0;
			try {
					String query="UPDATE tasks set name=?, start_date=?,end_date=?,user_id=?,job_id=?,status_id=? WHERE id=?";
				
				
				//b3: Mở kết nối csdl và truyền câu query cho JDBC thông qua Prepared statement
					Connection connection = MysqlConfig.getConnection();
				
					// b4: truyền câu query vào CSDL vừa kết nối thông qua Prepared Statemnt
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					//gán giá trị cho tham số dấu ? bên trong câu query
					preparedStatement.setString(1, name);
					preparedStatement.setString(2, startDate);
					preparedStatement.setString(3, endDate);
					preparedStatement.setInt(4, userId);
					preparedStatement.setInt(5, jobId);
					preparedStatement.setInt(6, statusId);
					preparedStatement.setInt(7, id);
					//b5: THông báo cho CSDL biết và thực thi câu Query
					//execute query: dành cho câu truy vấn là cầu select=> luôn trả Result set	
					//execute update: dành cho tất cả câu truy vấn còn lại ngoài câu select ví dụ: insert, update, delete
					count= preparedStatement.executeUpdate();
					
					
			}catch(Exception e){
				System.out.println("Lỗi updateTask "+e.getLocalizedMessage());
				
			}
			return count;
	}
	 public List<TasksModel> findById(int id){
			//b2: chuẩn bị câu truy vấn
				String query="SELECT\r\n"
						+ "    tasks.id AS tasks_id,\r\n"
						+ "    tasks.name AS tasks_name,\r\n"
						+ "    jobs.name AS jobs_name,\r\n"
						+ "    jobs.id AS jobs_id,\r\n"
						+ "    users.firstname,\r\n"
						+ "    users.lastname,\r\n"
						+ "    users.id AS user_id ,\r\n"
						+ "    tasks.start_date AS tasks_start_date,\r\n"
						+ "    tasks.end_date AS tasks_end_date,\r\n"
						+ "    status.name AS status_name,\r\n"
						+ "    status.id AS status_id \r\n"
						+ "FROM\r\n"
						+ "    tasks\r\n"
						+ "INNER JOIN\r\n"
						+ "    users ON tasks.user_id = users.id\r\n"
						+ " INNER JOIN\r\n"
						+ "    status ON tasks.status_id = status.id\r\n"
						+ "INNER JOIN\r\n"
						+ "	jobs ON tasks.job_id = jobs.id\r\n"
						+ "WHERE tasks.id =?;";
				
				//b3: Mở kết nối csdl và truyền câu query cho JDBC thông qua Prepared statement
				Connection connection = MysqlConfig.getConnection();
				
				//Tao list UserEntity để lưu trữ từng dòng dữ liệu mình query được
				List<TasksModel> listModels = new  ArrayList<TasksModel>();
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
						TasksModel taskModel= new TasksModel();
						taskModel.setId(resultSet.getInt("tasks_id")); //(tên cột database) lấy giá trị của cột id gắn vào thuộc tính id của userentity 
						//vào thuộc tính
						taskModel.setName(resultSet.getString("tasks_name"));
						taskModel.setFirstName(resultSet.getString("firstname"));
						taskModel.setLastName(resultSet.getString("lastname"));
						taskModel.setIdUserName(resultSet.getInt("user_id"));
						taskModel.setJobName(resultSet.getString("jobs_name"));
						taskModel.setIdJob(resultSet.getInt("jobs_id"));
						taskModel.setTaskStartDate(resultSet.getDate("tasks_start_date"));
						taskModel.setTaskEndDate(resultSet.getDate("tasks_end_date"));
						taskModel.setStatus(resultSet.getString("status_name"));
						taskModel.setIdStatus(resultSet.getInt("status_id"));
						
						listModels.add(taskModel);
					}
			}catch(Exception e){
				System.out.println("Lỗi findbyIdTasks "+e.getLocalizedMessage());
				
			}
				
				return listModels;
		 }
	


}
