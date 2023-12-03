package helloservlet.service;



import java.util.List;


import helloservlet.model.TasksModel;
import helloservlet.model.UserModel;
import helloservlet.repository.TasksRepository;

public class TasksService {
	TasksRepository tasksRepository = new TasksRepository();
	
	public int countNotStarted(List<TasksModel> listTask) {
		int count =0;
		if(listTask!=null && !listTask.isEmpty()) {
			for (TasksModel tasksModel : listTask) {
				if(tasksModel.getStatus().equals("Chưa thực hiện")) {
					count = count+1;
				}
			}
		}
		return count;
	}
	public int countInProgress(List<TasksModel> listTask) {
		int count =0;
		if(listTask!=null && !listTask.isEmpty()) {
			for (TasksModel tasksModel : listTask) {
				if(tasksModel.getStatus().equals("Đang thực hiện")) {
					count = count+1;
				}
			}
		}
		return count;
	}
	public int countCompleted(List<TasksModel> listTask) {
		int count =0;
		if(listTask!=null && !listTask.isEmpty()) {
			for (TasksModel tasksModel : listTask) {
				if(tasksModel.getStatus().equals("Đã hoàn thành")) {
					count = count+1;
				}
			}
		}
		return count;
	}
	
	
	public int percentNotStarted(List<TasksModel> listTask) {
		int percent=0;
		if(listTask!=null && !listTask.isEmpty()) {
			int count =0;
			int loop=0;
			for(TasksModel tasksModel : listTask) {
				loop=loop+1;
				if(tasksModel.getStatus().equals("Chưa thực hiện")) {
					count = count+1;
				}
			}
			percent =  100*count / loop;
			System.out.println(count+" va "+ loop+"và"+ percent);
		}
		return percent;
	}
	public int percentInProgress(List<TasksModel> listTask) {
		int percent=0;
		if(listTask!=null&&!listTask.isEmpty()) {
		int count =0;
		int loop=0;
		for(TasksModel tasksModel : listTask) {
			loop=loop+1;
			if(tasksModel.getStatus().equals("Đang thực hiện")) {
				count = count+1;
			}
		}
		percent =  100*count / loop;
		}
		return percent;
		
	}
	public int percentCompleted(List<TasksModel> listTask) {
		int percent=0;
		if(listTask!=null&& !listTask.isEmpty()) {
		int count =0;
		int loop=0;
		for(TasksModel tasksModel : listTask) {
			loop=loop+1;
			if(tasksModel.getStatus().equals("Đã hoàn thành")) {
				count = count+1;
			}
		}
		percent =  100*count / loop;
		}
		return percent;
		
	}
	
	
	
	
	public List<TasksModel> getAll(){
		return  tasksRepository.getAll();
		
	}
	public List<TasksModel> getByIdUser(int id){
		return  tasksRepository.getByIdUser(id);
		
	}
	public List<TasksModel> getByIdJob(int id){
		return tasksRepository.getByIdJob(id);
	}
	public TasksModel findTask(int id) {
		TasksModel task = new TasksModel();
		List<TasksModel> list = tasksRepository.findById(id);
		for (TasksModel taskModel : list) {
			task=taskModel;
			break;
		}
		return task;
	}
		public boolean insertTask(int id,String name, String startDate,String endDate, int userId, int jobId, int statusId) {
			int count = tasksRepository.insertTask(id, name, startDate, endDate, userId, jobId, statusId);
			return count > 0;
		}
		public boolean deleteTask(int id) {
			
			return tasksRepository.deletebyId(id)>0;
		}
		
		public boolean updateTask(String name, String startDate,String endDate, int userId, int jobId, int statusId, int id) {
			return tasksRepository.updateTask(name, startDate, endDate, userId, jobId, statusId,id)>0;
			
		}
		
}
