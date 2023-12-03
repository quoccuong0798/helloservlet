package helloservlet.controller;

import java.io.IOException;
import java.util.List;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helloservlet.entity.RoleEntity;
import helloservlet.model.TasksModel;
import helloservlet.model.UserModel;
import helloservlet.repository.JobsRespository;
import helloservlet.repository.StatusRepository;
import helloservlet.repository.UserRepository;
import helloservlet.service.RoleService;
import helloservlet.service.TasksService;
import helloservlet.service.UserService;

@WebServlet(name="userServlet", urlPatterns= {"/user","/user-add","/user-delete","/user-update","/profile","/profile-update","/user-details-update"})
public class UserController extends HttpServlet {
		UserService userService= new UserService();
		RoleService roleService = new RoleService();
		TasksService tasksService = new TasksService();
		StatusRepository statusRepository= new StatusRepository();
		JobsRespository jobsRespository = new JobsRespository();
		UserRepository userRepository = new UserRepository();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
			String path=req.getServletPath();
			System.out.println("Kiểm tra đường dẫn"+path);
			if(path.equals("/user")) {
				doGetUserTable(req,resp);
			}else if(path.equals("/user-add")) {
				doGetUserAdd(req, resp);
			}else if(path.equals("/user-delete")) {
				doGetUserDelete(req,resp);
			}else if(path.equals("/user-update")) {
				doGetUserUpdate(req,resp);
			}else if(path.equals("/profile")) {
				doGetProfile(req,resp);
			}else if(path.equals("/profile-update")) {
				doGetProfileUpdate(req,resp);
			}else if(path.equals("/user-details-update")) {
				doGetUserDetailsUpdate(req, resp);
			}
			
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String path=req.getServletPath();
		System.out.println("Kiểm tra đường dẫn doPost user "+path);
		if(path.equals("/user-add")) {
			doPostUserAdd(req,resp);
		}else if(path.equals("/user-update")) {
			doPostUserUpdate(req,resp);
		}else if(path.equals("/profile-update")) {
			doPostProfileUpdate(req, resp);
		}else if(path.equals("/user-details-update")) {
			doPostUserDetailsUpdate(req, resp);
		}
	}
	private void doPostUserUpdate(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName= req.getParameter("firstname");
		String lastName= req.getParameter("lastname");
		String email= req.getParameter("email");
		String password= req.getParameter("password");
		String nameRole = req.getParameter("namerole");
		int roleId= Integer.parseInt(req.getParameter("role_id"));
		int id= Integer.parseInt(req.getParameter("id"));
		boolean isSuccess= userService.updateUser(email, password, roleId, firstName, lastName, id);
		req.setAttribute("response", isSuccess ? "Cập nhật thành công" : "Cập nhật thất bại");
		// set lại các thuộc tính cho thẻ select
		 req.setAttribute("email",email );
		 req.setAttribute("firstname",firstName );
		 req.setAttribute("lastname",lastName );
		 req.setAttribute("id", id);
		 req.setAttribute("namerole", nameRole);
		 req.setAttribute("idrole", roleId);
		 req.setAttribute("password", password);
		 
		 List<RoleEntity> listRole= roleService.getAllRole();
		 req.setAttribute("roles", listRole);
		 req.getRequestDispatcher("/user-update.jsp").forward(req, resp);
		
	}
	private void doGetUserDetailsUpdate(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id= Integer.parseInt(req.getParameter("id")) ;
		TasksModel tasksModel= tasksService.findTask(id);
		req.setAttribute("tasksModel", tasksModel);
		req.setAttribute("listJob", jobsRespository.getAllJob());
//		req.setAttribute("listUser", userRepository.findAllUser());
		req.setAttribute("listStatus",statusRepository.findAll());
		
		
		 req.getRequestDispatcher("/user-details-update.jsp").forward(req, resp);
	}
	private void doGetProfile(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// Lấy session
		HttpSession session = req.getSession(false);
		
		UserModel userModel = (UserModel)session.getAttribute("userModel");
		int idRole = userModel.getIdRole();
		int idUser= userModel.getId();
		
		RoleEntity roleEntity= roleService.getById(idRole);
		 
		List<TasksModel> listTask = tasksService.getByIdUser(idUser);
			
			
			// Tính phần trăm cho từng trạng thái
	        int percentNotStarted = tasksService.percentNotStarted(listTask);
	        int percentInProgress =tasksService.percentInProgress(listTask);
	        int percentCompleted = tasksService.percentCompleted(listTask);
	        System.out.println("Kiểm tra phần trăm" +percentCompleted);
	        req.setAttribute("percentNotStarted", percentNotStarted);
	        req.setAttribute("percentCompleted", percentCompleted);
	        req.setAttribute("percentInProgress", percentInProgress);
	        
	        req.setAttribute("listTask", listTask);
		 
	        req.setAttribute("userModel", userModel);
	        req.setAttribute("roleEntity", roleEntity);
		 //lấy tất cả role để truyền vào Thẻ select
		 req.getRequestDispatcher("/profile.jsp").forward(req, resp);
		
	}
	private void doGetProfileUpdate(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		int id= Integer.parseInt(req.getParameter("id")) ;
		TasksModel tasksModel= tasksService.findTask(id);
		req.setAttribute("tasksModel", tasksModel);
		req.setAttribute("listStatus",statusRepository.findAll());
		req.getRequestDispatcher("/profile-update.jsp").forward(req, resp);
		
	
	}
	private void doPostProfileUpdate(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String nameTask= req.getParameter("nametask");
		int jobId= Integer.parseInt(req.getParameter("job"));
		int userId= Integer.parseInt(req.getParameter("user"));
		int statusId= Integer.parseInt(req.getParameter("status"));
		String endDate= req.getParameter("enddate");
		String startDate= req.getParameter("startdate");
		boolean isSuccess= tasksService.updateTask(nameTask, startDate, endDate, userId, jobId, statusId,id);
		req.setAttribute("response", isSuccess ? "Cập thành công" : "Cập nhật thất bại");
		System.out.println("Kiểm tra profile update "+isSuccess);
		resp.sendRedirect(req.getContextPath()+"/profile");
		
	}
	private void doGetUserUpdate(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id= Integer.parseInt(req.getParameter("id")) ;
		 UserModel user= userService.findUser(id);
		 String email= user.getEmail();
		 String firstname= user.getFirstName();
		 String lastname= user.getLastName();
		 int idRole =user.getIdRole();
		 String nameRole= user.getRoleName();
		 String password= user.getPassword();
		 req.setAttribute("email",email );
		 System.out.println("cường test "+email+firstname+id);
		 req.setAttribute("firstname",firstname );
		 req.setAttribute("lastname",lastname );
		 req.setAttribute("id", id);
		 req.setAttribute("namerole", nameRole);
		 req.setAttribute("idrole", idRole);
		 req.setAttribute("password", password);
		 
		 //lấy tất cả role để truyền vào Thẻ select
		 List<RoleEntity> listRole= roleService.getAllRole();
		 req.setAttribute("roles", listRole);
		 req.getRequestDispatcher("/user-update.jsp").forward(req, resp);
		 
		
		
	}
	private void doPostUserAdd(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String firstName= req.getParameter("firstname");
		String lastName= req.getParameter("lastname");
		String email= req.getParameter("email");
		String password= req.getParameter("password");
		int roleId= Integer.parseInt(req.getParameter("role_id"));
		System.out.println("Kiểm tra do Post add "+roleId);
		boolean isSuccess= userService.insertUser(firstName, lastName, email, password, roleId);
		req.setAttribute("response", isSuccess ? "Thêm thành công" : "Thêm thất bại");
		List<RoleEntity> listRole= roleService.getAllRole();
		req.setAttribute("roles", listRole);
		req.getRequestDispatcher("/user-add.jsp").forward(req, resp);
		
		
	}
	private void doGetUserTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<UserModel> list= userService.getAllUser();
		req.setAttribute("listUser", list);
		req.getRequestDispatcher("user-table.jsp").forward(req, resp);
		
	}
	private void doGetUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RoleEntity> listRole= roleService.getAllRole();
		req.setAttribute("roles", listRole);
		System.out.println("Đã vào do GEt Useradd");
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		
	}
	private void doGetUserDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id= Integer.parseInt(req.getParameter("id"));
		boolean isSuccess=userService.deleteUser(id);
		System.out.println("Kiểm tra deltete user"+isSuccess);
		resp.sendRedirect(req.getContextPath()+"/user");
		
	}
	
	private void doPostUserDetailsUpdate(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		int id= Integer.parseInt(req.getParameter("id")) ;
		String nameTask= req.getParameter("nametask");
		int jobId= Integer.parseInt(req.getParameter("job"));
		int userId= Integer.parseInt(req.getParameter("user"));
		int statusId= Integer.parseInt(req.getParameter("status"));
		String endDate= req.getParameter("enddate");
		String startDate= req.getParameter("startdate");
		boolean isSuccess= tasksService.updateTask(nameTask, startDate, endDate, userId, jobId, statusId,id);
		req.setAttribute("response", isSuccess ? "Thêm thành công" : "Thêm thất bại");
		System.out.println("Kiểm tra task update "+isSuccess);
		
		//set lại các thuộc tính cho thẻ select
		TasksModel tasksModel= tasksService.findTask(id);
		req.setAttribute("tasksModel", tasksModel);
		req.setAttribute("listJob", jobsRespository.getAllJob());
//		req.setAttribute("listUser", userRepository.findAllUser()); 
		req.setAttribute("listStatus",statusRepository.findAll());
		req.getRequestDispatcher("/user-details-update.jsp").forward(req, resp);
	}

	
	

}
