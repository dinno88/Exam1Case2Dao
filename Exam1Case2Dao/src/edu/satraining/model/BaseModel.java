package edu.satraining.model;
import java.util.Date;

public class BaseModel{
	private String createdBy;
	private Date createdTime;
	private String updatedBy;
	private Date updatedTime;
	public BaseModel(){}

	public BaseModel(String createdBy, Date createdTime){
		this.createdBy = createdBy;
		this.createdTime = createdTime;
	}
	public BaseModel(String createdBy, Date createdTime, String updatedBy, Date updatedTime){
		this(createdBy, createdTime);
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
	}
	public String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public Date getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(Date createdTime){
		this.createdTime = createdTime;
	}
	public String getUpdatedBy(){
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy){
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedTime(){
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime){
		this.updatedTime = updatedTime;
	}
}