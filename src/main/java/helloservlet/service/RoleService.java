package helloservlet.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import helloservlet.entity.RoleEntity;
import helloservlet.entity.UserEntity;
import helloservlet.repository.RoleRepository;
import helloservlet.repository.UserRepository;
	

public class RoleService {
	private RoleRepository roleRepository = new RoleRepository();

	public boolean insert(String roleName, String desc) {
		int count = roleRepository.insertRole(roleName, desc);
		return count > 0;
	}
	public List<RoleEntity> getAllRole(){
		return roleRepository.findAll();
		
	}
	public RoleEntity getById(int id){
		List<RoleEntity> list= roleRepository.findById(id);
		RoleEntity roleEntity= list.get(0);
		return roleEntity;
	}
	public boolean deleteRole(int id) {
		
		return roleRepository.deletebyId(id)>0;
	}
	public boolean updateRole(int id, String roleName, String desc) {
		return roleRepository.updateRole(id, roleName, desc)>0;
	}
}
