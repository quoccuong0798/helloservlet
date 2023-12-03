package helloservlet.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.RemoteEndpoint.Basic;

import com.google.gson.Gson;

import helloservlet.payload.BasicResponse;
import helloservlet.service.RoleService;
@WebServlet(name="apiRoleController",urlPatterns= {"/api/role"})
public class ApiRoleController extends HttpServlet {
	
	//khởi tạo đối tượng RoleService
	RoleService roleService = new RoleService();
	
	
	/**
	 * {
	 * "statusCode":200,
	 * "message":"",
	 * "data":true => dynamic
	 * }
	 * 
	 * payload: request, response
	 */ 
	//thư viện GSon của gg hỗ trợ chuyển đổi từ class sang json
	private Gson gson = new Gson();
	
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id =Integer.parseInt(req.getParameter("id"));
		boolean isSuccess=roleService.deleteRole(id);
		
		BasicResponse basicResponse = new BasicResponse();
		basicResponse.setStatusCode(200);
		basicResponse.setMessage("");
		basicResponse.setData(isSuccess);
		
		//parse ve
		String dataJson= gson.toJson(basicResponse);
		
		
		// trả data dang String	
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");  //hỗ trợ tiếng việt
        out.print(dataJson);
        out.flush();
		
		System.out.println("Kiểm tra delete " +id);
	}
	

}
