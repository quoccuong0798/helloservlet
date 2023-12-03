package helloservlet.service;

import java.util.ArrayList;
import java.util.List;

import helloservlet.entity.RoleEntity;
import helloservlet.model.UserModel;
import helloservlet.repository.UserRepository;
	
	

public class UserService {
	UserRepository userRepository = new UserRepository();
	public List<UserModel> getAllUser(){
		return userRepository.findAllUser();
		
	}
	
//	public List<String>getFirstNameList(){
//		List<String> firstNameList= new ArrayList<String>();
//		List<UserModel> userList =this.getAllUser();
//		
//		if(userModels.size()>0) {
//			for(UserModel userModel:userList) {
//				String fullName=user
//				
//			}
//		}
//	}

	
	public boolean deleteUser(int id) {
		return userRepository.deletebyId(id)>0;
		 
	}
	public boolean insertUser(String firstName, String lastName, String email, String password, int roleId) {
		int count = userRepository.insertUser(firstName, lastName, email, password, roleId);
		return count >0;
	}
	public UserModel  findUser(int id){
		List<UserModel> listUser = userRepository.findById(id);
		UserModel user= new UserModel();
		for (UserModel userModel : listUser) {
			user=userModel;
			break;
		}
		
		return user;
		
	}
	public boolean updateUser(String email, String password, int roleId, String firstName, String lastName, int id) {
		int count=userRepository.updateUserById(email, password, roleId, firstName, lastName, id);
		
		
		return count>0;
	}
}
