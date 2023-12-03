package helloservlet.service;

import java.util.List;

import helloservlet.entity.JobsEntity;

import helloservlet.repository.JobsRespository;

public class JobsService {
	JobsRespository jobsRespository= new JobsRespository();
	
	public List<JobsEntity> getAll(){
		jobsRespository.getAllJob();
		return jobsRespository.getAllJob();
	}
		public boolean insertJob(String name,String startDate,String endDate) {
			int count = jobsRespository.insertJob(name, startDate, endDate);
			return count > 0;
		}
//		
		public boolean deleteJob(int id) {
			
			return jobsRespository.deletebyId(id)>0;
		}
		
		public boolean updateJob(int id, String name, String startDate, String endDate) {
			return jobsRespository.updateById(id, name, startDate, endDate)>0;
		}
		
	

}
