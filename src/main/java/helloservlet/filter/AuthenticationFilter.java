package helloservlet.filter;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.UserEntity;
import helloservlet.model.UserModel;

@WebFilter(filterName="authenFilter", urlPatterns= {"/*"})
public class AuthenticationFilter implements Filter {

	
	private static final List<String> managerPath = Arrays.asList("/job-update", "/job-add","/job-delete","/job-details","/job-details-update",
			"/task-update", "/task-delete", "/task-add","/user","/role","/task","/profile","/job","/index","/profile","/profile-update","/index.jsp");
	private static final List<String> userPath = Arrays.asList("/user","/role", "/job","/index","/task", "/profile","/profile-update","/index.jsp");
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Đã kích hoạt filtter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String path= req.getServletPath();
		if (path.equals("/login") || path.equals("/login.jsp") || path.equals("/logout")|| path.equals("/403.jsp")) {
			chain.doFilter(request, response);
		}else {
				
			 	HttpSession session = req.getSession(false);
			 	String contextPath = req.getContextPath();

				if (session == null) {
		            // Nếu userModel là null, chuyển hướng đến trang đăng nhập
		            resp.sendRedirect( contextPath+"/login.jsp");
		        }else {
		        		UserModel userModel = (UserModel)session.getAttribute("userModel");
		        		if(userModel!=null) {
		        			int idRole=userModel.getIdRole();
			        		
			        		if(idRole==1) {
								chain.doFilter(request, response);
							}else if(idRole==2) {
								if(managerPath.contains(path)) {
									chain.doFilter(request, response);
								}else {
									resp.sendRedirect(contextPath + "/403.jsp");
								}
									
							}else {
								if(userPath.contains(path)) {
									chain.doFilter(request, response);
								}else {
									resp.sendRedirect(contextPath + "/403.jsp");
								}
							}
		        		}else {
		        			resp.sendRedirect( contextPath+"/login.jsp");
		        		}
		        		
		        }
				 
		}
		
		
	
		
		
		
		
		
	
	

	}
}