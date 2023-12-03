package helloservlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.model.TasksModel;
import helloservlet.service.TasksService;

@WebServlet(name="indexController", urlPatterns= {"/index"})

public class IndexController extends HttpServlet {
	TasksService tasksService = new TasksService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		 List<TasksModel> listTask=tasksService.getAll();
		 int countNotStarted = tasksService.countNotStarted(listTask);
		 int countInProgress = tasksService.countInProgress(listTask);
		 int countCompleted  = tasksService.countCompleted(listTask);
		 
		 int total = countNotStarted+countCompleted+countInProgress;
		 
		 req.setAttribute("countNotStarted", countNotStarted);
		 req.setAttribute("countInProgress", countInProgress);
		 req.setAttribute("countCompleted", countCompleted);
		 req.setAttribute("percentCompleted", tasksService.percentCompleted(listTask));
		 req.setAttribute("percentInProgress", tasksService.percentInProgress(listTask));
		 req.setAttribute("percentNotStarted", tasksService.percentNotStarted(listTask));

		 String path= req.getContextPath();
		 req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
