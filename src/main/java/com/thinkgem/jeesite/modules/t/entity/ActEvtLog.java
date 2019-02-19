/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.t.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * tEntity
 * @author cwb
 * @version 2019-02-08
 */
public class ActEvtLog extends DataEntity<ActEvtLog> {
	
	private static final long serialVersionUID = 1L;
	private Long logNr;		// log_nr_
	private String type;		// type_
	private String procDefId;		// proc_def_id_
	private String procInstId;		// proc_inst_id_
	private String executionId;		// execution_id_
	private String taskId;		// task_id_
	private Date timeStamp;		// time_stamp_
	private User user;		// user_id_
	private String data;		// data_
	private String lockOwner;		// lock_owner_
	private Date lockTime;		// lock_time_
	private String isProcessed;		// is_processed_
	
	public ActEvtLog() {
		super();
	}

	public ActEvtLog(String id){
		super(id);
	}

	@NotNull(message="log_nr_不能为空")
	public Long getLogNr() {
		return logNr;
	}

	public void setLogNr(Long logNr) {
		this.logNr = logNr;
	}
	
	@Length(min=0, max=64, message="type_长度必须介于 0 和 64 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="proc_def_id_长度必须介于 0 和 64 之间")
	public String getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	
	@Length(min=0, max=64, message="proc_inst_id_长度必须介于 0 和 64 之间")
	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	
	@Length(min=0, max=64, message="execution_id_长度必须介于 0 和 64 之间")
	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	
	@Length(min=0, max=64, message="task_id_长度必须介于 0 和 64 之间")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="time_stamp_不能为空")
	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	@Length(min=0, max=255, message="lock_owner_长度必须介于 0 和 255 之间")
	public String getLockOwner() {
		return lockOwner;
	}

	public void setLockOwner(String lockOwner) {
		this.lockOwner = lockOwner;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	
	@Length(min=0, max=4, message="is_processed_长度必须介于 0 和 4 之间")
	public String getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(String isProcessed) {
		this.isProcessed = isProcessed;
	}
	
}