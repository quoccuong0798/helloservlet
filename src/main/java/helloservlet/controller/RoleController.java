 package helloservlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import helloservlet.entity.RoleEntity;
import helloservlet.repository.UserRepository;
import helloservlet.service.RoleService;

@WebServlet(name="roleServlet", urlPatterns= {"/role-add","/role","/role-delete","/role-update"})

public class RoleController extends HttpServlet{
	private RoleService roleService = new RoleService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//phần xử lý logic code
		String path=req.getServletPath();
		System.out.println("Kiểm tra "+path);
		if(path.equals("/role-add")) {
			doGetRoleAdd(req, resp);
		}else if(path.equals("/role")) {
			doGetRoleTable(req, resp);
		}else if(path.equals("/role-delete")) {
			doGetRoleDelete(req, resp);
		}else if(path.equals("/role-update")) {
			doGetRoleUpdate(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String path =req.getServletPath();
		if(path.equals("/role-update")) {
			doPostRoleUpdate(req,resp);
		}if(path.equals("/role-add")) {
			doPostRoleAdd(req, resp);
		}
		
		
		}
	
	
	private void doGetRoleTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RoleEntity> list= roleService.getAllRole();
		
		req.setAttribute("listRole", list);
		req.getRequestDispatcher("role-table.jsp").forward(req, resp);
		
	}
//	private void doPostRoleTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doGetRoleTable(req, resp);
		
	private void doGetRoleDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		boolean isSuccess = roleService.deleteRole(id);
		System.out.println("xóa thành công "+isSuccess);
		resp.sendRedirect(req.getContextPath()+"/role");
		
	} 
	private void doGetRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		
	}
	private void doPostRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String roleName= req.getParameter("role-name");
		String desc= req.getParameter("describe");
		boolean isSuccess=roleService.insert(roleName, desc);
		System.out.println("kiểm tra role-add "+isSuccess);
		
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
}
	private void doGetRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id= Integer.parseInt(req.getParameter("id"));
		String roleName= req.getParameter("name");
		String desc= req.getParameter("desc");
		System.out.println(roleName+desc+id); //xuất ra để kiểm tra
		req.setAttribute("id", id);
		req.setAttribute("roleName", roleName);
		req.setAttribute("desc", desc);
		
		System.out.println("Kiểm tra RoleUpdate");
		req.getRequestDispatcher("role-update.jsp").forward(req, resp);
}
	
	private void doPostRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id= Integer.parseInt(req.getParameter("id"));
		String roleName= req.getParameter("role-name");
		String desc= req.getParameter("describe");
		boolean isSuccess=roleService.updateRole(id,roleName,desc);
		System.out.println("kiểm tra role-add "+isSuccess);
		req.setAttribute("isSuccess", isSuccess);
		req.getRequestDispatcher("role-update.jsp").forward(req, resp);
}
	
	
}
