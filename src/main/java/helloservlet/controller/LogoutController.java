package helloservlet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name="logoutController", urlPatterns= {"/logout"})
public class LogoutController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		System.out.println("Kiểm tra đường dẫn logout"+path);
		HttpSession session = req.getSession(false);
        if (session != null) {
            // Hủy session
            session.invalidate();
        }

        // Chuyển hướng đến trang đăng nhập hoặc trang chính
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
	

}
