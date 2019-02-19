/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.supplier.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 供应商管理Entity
 * @author cwb
 * @version 2019-02-09
 */
public class Supplier extends DataEntity<Supplier> {
	
	private static final long serialVersionUID = 1L;
	private String supName;		// 供应商名称
	private String regCapital;		// 注册资本
	private String legalPerson;		// 法人
	private String supNature;		// 企业性质
	private String supUrl;		// 网址
	private String supAddress;		// 地址
	private Date setTime;		// 成立时间
	private String zipCode;		// 邮编
	private String contact;		// 联系人
	private String supEmail;		// 电子邮箱
	private String supTel;		// 联系电话
	private String faxNum;		// 传真号码
	private String bankName;		// 开户银行
	private String bankNum;		// 银行卡号
	private String creditRating;		// 资信等级
	private String businessScope;		// 经营范围
	
	public Supplier() {
		super();
	}

	public Supplier(String id){
		super(id);
	}

	@Length(min=0, max=255, message="供应商名称长度必须介于 0 和 255 之间")
	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}
	
	public String getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
	}
	
	@Length(min=0, max=255, message="法人长度必须介于 0 和 255 之间")
	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	
	@Length(min=0, max=255, message="企业性质长度必须介于 0 和 255 之间")
	public String getSupNature() {
		return supNature;
	}

	public void setSupNature(String supNature) {
		this.supNature = supNature;
	}
	
	@Length(min=0, max=255, message="网址长度必须介于 0 和 255 之间")
	public String getSupUrl() {
		return supUrl;
	}

	public void setSupUrl(String supUrl) {
		this.supUrl = supUrl;
	}
	
	@Length(min=0, max=255, message="地址长度必须介于 0 和 255 之间")
	public String getSupAddress() {
		return supAddress;
	}

	public void setSupAddress(String supAddress) {
		this.supAddress = supAddress;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSetTime() {
		return setTime;
	}

	public void setSetTime(Date setTime) {
		this.setTime = setTime;
	}
	
	@Length(min=0, max=255, message="邮编长度必须介于 0 和 255 之间")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Length(min=0, max=255, message="联系人长度必须介于 0 和 255 之间")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Length(min=0, max=255, message="电子邮箱长度必须介于 0 和 255 之间")
	public String getSupEmail() {
		return supEmail;
	}

	public void setSupEmail(String supEmail) {
		this.supEmail = supEmail;
	}
	
	@Length(min=0, max=255, message="联系电话长度必须介于 0 和 255 之间")
	public String getSupTel() {
		return supTel;
	}

	public void setSupTel(String supTel) {
		this.supTel = supTel;
	}
	
	@Length(min=0, max=255, message="传真号码长度必须介于 0 和 255 之间")
	public String getFaxNum() {
		return faxNum;
	}

	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}
	
	@Length(min=0, max=255, message="开户银行长度必须介于 0 和 255 之间")
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@Length(min=0, max=255, message="银行卡号长度必须介于 0 和 255 之间")
	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	
	@Length(min=0, max=10, message="资信等级长度必须介于 0 和 10 之间")
	public String getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}
	
	@Length(min=0, max=255, message="经营范围长度必须介于 0 和 255 之间")
	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	
}