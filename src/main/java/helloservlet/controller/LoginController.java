package helloservlet.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helloservlet.entity.UserEntity;
import helloservlet.model.UserModel;
import helloservlet.service.LoginService;

@WebServlet(name="loginController",urlPatterns  = {"/login"})
	public class LoginController extends HttpServlet {
		
	private LoginService loginService = new LoginService();
		
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String contextPath = req.getContextPath();
		if(session!=null) {
			
	        resp.sendRedirect(contextPath + "/index");
		}else {
			 Cookie[] cookies = req.getCookies();

		        String savedEmail = null;
		        String savedPassword = null;
		        if (cookies != null) {
		        	System.out.println("có vào cookie");
		            for (Cookie cookie : cookies) {
		                if (cookie.getName().equals("email")) {
		                    savedEmail = cookie.getValue();
		                } else if (cookie.getName().equals("password")) {
		                    savedPassword = cookie.getValue();
		                }
		            }
		        }
		        System.out.println("kiểm tra cookie"+savedEmail);
		        req.setAttribute("savedEmail", savedEmail);
		        req.setAttribute("savedPassword", savedPassword);
		        req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
		String email= req.getParameter("email");
		String password= req.getParameter("password");
		String remember= req.getParameter("remember");
		UserModel  userModel =loginService.checkLogin(email, password, remember, resp);
		
		System.out.println( "Mong điều thần kỳ có thể xảy ra");
		HttpSession session = req.getSession();
		session.setAttribute("userModel", userModel);
		if(userModel!=null) {
			resp.sendRedirect(req.getContextPath()+"/index");
		}else {
			req.setAttribute("response", userModel!=null ? "Đăng nhập thành công" : "Đăng nhập thất bại");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		
	}	
}


