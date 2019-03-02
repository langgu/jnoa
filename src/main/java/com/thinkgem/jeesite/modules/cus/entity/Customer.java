/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cus.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 客户信息管理Entity
 * @author mjx
 * @version 2019-03-01
 */
public class Customer extends DataEntity<Customer> {
	
	private static final long serialVersionUID = 1L;
	private String username;		// 客户姓名
	private String nation;		// 民族
	private String sex;		// 性别
	private String source;		// 客户来源
	private String tel;		// 手机号码
	private String year;		// 年
	private String month;		// 月
	private String day;		// 日
	
	public Customer() {
		super();
	}

	public Customer(String id){
		super(id);
	}

	@Length(min=0, max=50, message="客户姓名长度必须介于 0 和 50 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=0, max=50, message="民族长度必须介于 0 和 50 之间")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=0, max=50, message="性别长度必须介于 0 和 50 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=50, message="客户来源长度必须介于 0 和 50 之间")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Length(min=0, max=50, message="手机号码长度必须介于 0 和 50 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=10, message="年长度必须介于 0 和 10 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@Length(min=0, max=10, message="月长度必须介于 0 和 10 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@Length(min=0, max=10, message="日长度必须介于 0 和 10 之间")
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
}