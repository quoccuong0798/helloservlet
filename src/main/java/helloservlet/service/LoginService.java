package helloservlet.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.entity.UserEntity;
import helloservlet.model.UserModel;
import helloservlet.repository.UserRepository;

public class LoginService {
	private UserRepository userRepository = new UserRepository();
	
	public UserModel checkLogin(String email, String password,String remember, HttpServletResponse resp) {
		
		List<UserModel> list = userRepository.findByEmailAndPassword(email, password);
		UserModel userModel = list.stream().findFirst().orElse(null);
		boolean isSuccess =list.size()>0;
		//Neu khac null tuc la nguoi dung co check vao luu tai khoan
		if(isSuccess && remember!=null ) {
			Cookie ckEmail = new Cookie("email", email);
			Cookie ckPassword = new Cookie("password", password);
			
			resp.addCookie(ckEmail);
			resp.addCookie(ckPassword);
		}
		
		
		return userModel ;
		
	}

}
