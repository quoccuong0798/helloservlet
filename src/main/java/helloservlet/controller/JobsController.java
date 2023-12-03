package helloservlet.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.InternalFrameUI;

import org.apache.catalina.User;

import helloservlet.entity.JobsEntity;
import helloservlet.entity.RoleEntity;
import helloservlet.model.TasksModel;
import helloservlet.model.UserModel;
import helloservlet.repository.JobsRespository;
import helloservlet.repository.StatusRepository;
import helloservlet.repository.UserRepository;
import helloservlet.service.JobsService;
import helloservlet.service.TasksService;

@WebServlet(name="jobsServlet", urlPatterns= {"/job","/job-delete","/job-add","/job-update","/job-details","/job-details-update"})

public class JobsController extends HttpServlet{
	private JobsService jobsService = new JobsService();
	private TasksService tasksService = new TasksService();
	private JobsRespository jobsRespository= new JobsRespository();
	private UserRepository userRepository = new UserRepository();
	private StatusRepository statusRepository = new StatusRepository();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		System.out.println("Kiểm tra đường dẫn jobs "+path);
		if(path.equals("/job")) {
			doGetJobTable(req, resp);
		}else if(path.equals("/job-delete")) {
			doGetJobDelete(req,resp);
		}else if(path.equals("/job-add")) {
			doGetJobAdd(req,resp);
		}else if(path.equals("/job-update")) {
			doGetJobUpdate(req, resp);
		}else if (path.equals("/job-details")) {
			doGetJobDetails(req,resp);
		}else if (path.equals("/job-details-update")) {
			doGetJobDetailsUpdate(req,resp);
		}
	}
	
	private void doGetJobAdd(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		System.out.println("Đã vào do GET Job add");
		req.getRequestDispatcher("job-add.jsp").forward(req, resp);
		
	}

	private void doGetJobDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		boolean isSuccess=jobsService.deleteJob(id);
		System.out.println("Kiểm tra Delete");
		resp.sendRedirect(req.getContextPath()+"/job");
		
	}

	private void doGetJobTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<JobsEntity> list= jobsService.getAll();
		
		req.setAttribute("listJobs", list);
		req.getRequestDispatcher("job.jsp").forward(req, resp);
		
	}
	private void doGetJobUpdate(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id= Integer.parseInt(req.getParameter("id")) ;
		String name= req.getParameter("name");
		String startDate=req.getParameter("start_date");
		String endDate= req.getParameter("end_date");
		req.setAttribute("name", name);
		req.setAttribute("id", id);
		req.setAttribute("start_date", startDate);
		req.setAttribute("end_date", endDate);
		
		
		 req.getRequestDispatcher("/job-update.jsp").forward(req, resp);
	}
	private void doGetJobDetails(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idJob= Integer.parseInt(req.getParameter("idJob"));
		List<TasksModel> tasksModels = tasksService.getByIdJob(idJob);
		
		// Tính phần trăm cho từng trạng thái
        int percentNotStarted = tasksService.percentNotStarted(tasksModels);
        int percentCompleted =tasksService.percentCompleted(tasksModels);
        int percentInProgress = tasksService.percentInProgress(tasksModels);
        System.out.println("Kiểm tra phần trăm" +percentCompleted);
        req.setAttribute("percentNotStarted", percentNotStarted);
        req.setAttribute("percentCompleted", percentCompleted);
        req.setAttribute("percentInProgress", percentInProgress);
		
		req.setAttribute("tasksModels", tasksModels);
		
		
		System.out.println("Đã vào dogetjob details");
		
		
		 req.getRequestDispatcher("/job-details.jsp").forward(req, resp);
	}
	private void doGetJobDetailsUpdate(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idTask= Integer.parseInt(req.getParameter("id")) ;
		TasksModel tasksModel= tasksService.findTask(idTask);
		//int idJob= tasksModel.getIdJob();
		req.setAttribute("tasksModel", tasksModel);
		req.setAttribute("listUser", userRepository.findAllUser());
		req.setAttribute("listStatus",statusRepository.findAll());
		
		
		 req.getRequestDispatcher("/job-details-update.jsp").forward(req, resp);
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String path=req.getServletPath();
		if(path.equals("/job-add")) {
			doPostJobAdd(req, resp);
		}else if(path.equals("/job-update")) {
			doPostJobUpdate(req, resp);
		}else if(path.equals("/job-details-update")) {
			doPostJobDetailsUpdate(req, resp);
		}
	}
	private void doPostJobUpdate(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		int id= Integer.parseInt(req.getParameter("id")) ;
		String name= req.getParameter("name");
		String startDate=req.getParameter("start_date");
		String endDate= req.getParameter("end_date");
		boolean isSuccess= jobsService.updateJob(id, name, startDate, endDate);
		req.setAttribute("response", isSuccess ? "Cập nhật thành công" : "Cập nhật thất bại");
		req.getRequestDispatcher("/job-add.jsp").forward(req, resp);
	}
	
	private void doPostJobDetailsUpdate(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		int id= Integer.parseInt(req.getParameter("id")) ;
		String nameTask= req.getParameter("nametask");
		int jobId= Integer.parseInt(req.getParameter("job"));
		int userId= Integer.parseInt(req.getParameter("user"));
		int statusId= Integer.parseInt(req.getParameter("status"));
		String endDate= req.getParameter("enddate");
		String startDate= req.getParameter("startdate");
		boolean isSuccess= tasksService.updateTask(nameTask, startDate, endDate, userId, jobId, statusId,id);
		req.setAttribute("response", isSuccess ? "Cập nhật thành công" : "Cập nhật thất bại");
		System.out.println("Kiểm tra task update "+isSuccess);
		
		//set lại các thuộc tính cho thẻ select
		//int id= Integer.parseInt(req.getParameter("id")) ;
		TasksModel tasksModel= tasksService.findTask(id);
		req.setAttribute("tasksModel", tasksModel);
		req.setAttribute("listUser", userRepository.findAllUser());
		req.setAttribute("listStatus",statusRepository.findAll());
		req.getRequestDispatcher("/job-details-update.jsp").forward(req, resp);
	}

	private void doPostJobAdd(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String name= req.getParameter("name");
		String startDate= req.getParameter("start_date");
		String endDate= req.getParameter("end_date");
		System.out.println("Kiểm tra "+startDate);
		boolean isSuccess= jobsService.insertJob(name, startDate, endDate);
		req.setAttribute("response", isSuccess ? "Thêm thành công" : "Thêm thất bại");
		req.getRequestDispatcher("/job-add.jsp").forward(req, resp);
//		int roleId= Integer.parseInt(req.getParameter("role_id"));
//		System.out.println("Kiểm tra do Post add "+roleId);
//		boolean isSuccess= userService.insertUser(firstName, lastName, email, password, roleId);
//		req.setAttribute("response", isSuccess ? "Thêm thành công" : "Thêm thất bại");
//		List<RoleEntity> listRole= roleService.getAllRole();
//		req.setAttribute("roles", listRole);
//		req.getRequestDispatcher("/user-add.jsp").forward(req, resp);
		
		
	}
}
