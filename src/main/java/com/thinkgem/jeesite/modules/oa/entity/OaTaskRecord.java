/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 任务信息Entity
 * @author wfp
 * @version 2019-01-09
 */
public class OaTaskRecord extends DataEntity<OaTaskRecord> {
	
	private static final long serialVersionUID = 1L;
	private OaTask oaTaskId;		// 任务ID 父类
	private String title;		// 任务标题
	private String content;		// 任务内容
	private String files;		// 附件
	private String sendUserId;		// 发送人
	private String receUserId;		// 接受人
	private Date sendDate;		// 发送时间
	private String readFlag;		// 阅读标记
	private Date readDate;		// 阅读时间
	private String forwardFlag;		// 是否是转发
	private String completeFlag;		// 任务完成标记，&lsquo;0&rsquo;，未完成，&lsquo;1&rsquo;完成
	
	public OaTaskRecord() {
		super();
	}

	public OaTaskRecord(String id){
		super(id);
	}

	public OaTaskRecord(OaTask oaTaskId){
		this.oaTaskId = oaTaskId;
	}

	@Length(min=0, max=64, message="任务ID长度必须介于 0 和 64 之间")
	public OaTask getOaTaskId() {
		return oaTaskId;
	}

	public void setOaTaskId(OaTask oaTaskId) {
		this.oaTaskId = oaTaskId;
	}
	
	@Length(min=0, max=255, message="任务标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=2000, message="任务内容长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=2000, message="附件长度必须介于 0 和 2000 之间")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
	@Length(min=0, max=64, message="发送人长度必须介于 0 和 64 之间")
	public String getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}
	
	@Length(min=0, max=64, message="接受人长度必须介于 0 和 64 之间")
	public String getReceUserId() {
		return receUserId;
	}

	public void setReceUserId(String receUserId) {
		this.receUserId = receUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	@Length(min=0, max=1, message="阅读标记长度必须介于 0 和 1 之间")
	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	
	@Length(min=0, max=1, message="是否是转发长度必须介于 0 和 1 之间")
	public String getForwardFlag() {
		return forwardFlag;
	}

	public void setForwardFlag(String forwardFlag) {
		this.forwardFlag = forwardFlag;
	}
	
	@Length(min=0, max=1, message="任务完成标记，&lsquo;0&rsquo;，未完成，&lsquo;1&rsquo;完成长度必须介于 0 和 1 之间")
	public String getCompleteFlag() {
		return completeFlag;
	}

	public void setCompleteFlag(String completeFlag) {
		this.completeFlag = completeFlag;
	}
	
}