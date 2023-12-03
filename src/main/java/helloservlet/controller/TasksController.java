package helloservlet.controller;


import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.entity.JobsEntity;
import helloservlet.entity.StatusEntity;
import helloservlet.model.TasksModel;
import helloservlet.model.UserModel;
import helloservlet.repository.JobsRespository;
import helloservlet.repository.StatusRepository;
import helloservlet.repository.UserRepository;
import helloservlet.service.TasksService;
import helloservlet.service.UserService;

@WebServlet(name="tasksServlet", urlPatterns= {"/task","/task-delete","/task-add","/task-update","/user-details"})

public class TasksController extends HttpServlet{
	private TasksService tasksService = new TasksService();
	private JobsRespository jobsRespository= new JobsRespository();
	private UserRepository userRepository = new UserRepository();
	private UserService userService = new UserService();
	private StatusRepository statusRepository = new StatusRepository();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		System.out.println("Kiểm tra đường dẫn task "+path);
		if(path.equals("/task")) {
			doGetTaskTable(req, resp);
		}else if(path.equals("/task-delete")) {
			doGetTaskDelete(req, resp);
		}else if(path.equals("/task-add")) {
			doGetTaskAdd(req, resp);
		}else if(path.equals("/task-update")) {
			doGetTaskUpdate(req, resp);
		}else if(path.equals("/user-details")) {
			doGetDetails(req,resp);
		}
}
	private void doGetDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id = Integer.parseInt(req.getParameter("id-user"));
		List<TasksModel> listTask = tasksService.getByIdUser(id);
		
		UserModel userModel = userService.findUser(id);
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
		req.setAttribute("iduser", id);
		System.out.println("có vào gettask"+listTask);
		req.getRequestDispatcher("user-details.jsp").forward(req, resp);
		
	}

	private void doGetTaskTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<TasksModel> listTask = tasksService.getAll();
		
		req.setAttribute("listTask", listTask);
		System.out.println("có vào gettask"+listTask);
		req.getRequestDispatcher("task.jsp").forward(req, resp);
		
	}
	
	private void doGetTaskAdd(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		System.out.println("Đã vào do GET Task add");
		
		req.setAttribute("listJob", jobsRespository.getAllJob());
		req.setAttribute("listUser", userRepository.findAllUser());
		req.setAttribute("listStatus",statusRepository.findAll());
		req.getRequestDispatcher("task-add.jsp").forward(req, resp);
		
	}
	private void doGetTaskDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		boolean isSuccess=tasksService.deleteTask(id);
		System.out.println("Kiểm tra Delete");
		resp.sendRedirect(req.getContextPath()+"/task");
		
	}

	private void doGetTaskUpdate(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id= Integer.parseInt(req.getParameter("id")) ;
		TasksModel tasksModel= tasksService.findTask(id);
		req.setAttribute("tasksModel", tasksModel);
		req.setAttribute("listJob", jobsRespository.getAllJob());
		req.setAttribute("listUser", userRepository.findAllUser());
		req.setAttribute("listStatus",statusRepository.findAll());
		
		
		 req.getRequestDispatcher("/task-update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String path=req.getServletPath();
		if(path.equals("/task-add")) {
			doPostTaskAdd(req, resp);
		}else if(path.equals("/task-update")) {
			doPostTaskUpdate(req,resp);
		}
		
	}

	private void doPostTaskAdd(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String nameTask= req.getParameter("nametask");
		int jobId= Integer.parseInt(req.getParameter("job"));
		int userId= Integer.parseInt(req.getParameter("user"));
		int statusId= Integer.parseInt(req.getParameter("status"));
		String endDate= req.getParameter("enddate");
		String startDate= req.getParameter("startdate");
		boolean isSuccess= tasksService.insertTask(statusId, nameTask, startDate, endDate, userId, jobId, statusId);
		req.setAttribute("response", isSuccess ? "Thêm thành công" : "Thêm thất bại");
		System.out.println("Kiểm tra task add "+isSuccess);
		//set lại các thuộc tính cho thẻ select
		req.setAttribute("listJob", jobsRespository.getAllJob());
		req.setAttribute("listUser", userRepository.findAllUser());
		req.setAttribute("listStatus",statusRepository.findAll());
		req.getRequestDispatcher("/task-add.jsp").forward(req, resp);
	}
	
	private void doPostTaskUpdate(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
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
		//int id= Integer.parseInt(req.getParameter("id")) ;
		TasksModel tasksModel= tasksService.findTask(id);
		req.setAttribute("tasksModel", tasksModel);
		req.setAttribute("listJob", jobsRespository.getAllJob());
		req.setAttribute("listUser", userRepository.findAllUser());
		req.setAttribute("listStatus",statusRepository.findAll());
		req.getRequestDispatcher("/task-update.jsp").forward(req, resp);
	}
	}


