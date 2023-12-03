package helloservlet.entity;

import java.sql.Date;

public class TasksEntity {
	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
	private int userId;
	private int jobsId;
	private  int statusId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getJobsId() {
		return jobsId;
	}
	public void setJobsId(int jobsId) {
		this.jobsId = jobsId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	

}
