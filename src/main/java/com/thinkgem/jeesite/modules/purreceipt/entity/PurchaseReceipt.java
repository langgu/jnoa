/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.purreceipt.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 进货订单管理Entity
 * @author lvyan
 * @version 2019-02-25
 */
public class PurchaseReceipt extends DataEntity<PurchaseReceipt> {
	
	private static final long serialVersionUID = 1L;
	private String supplier;		// 供应商
	private String goodsName;		// 货物名称
	private String goodsType;		// 进货类型
	private String unitPrice;		// 货物单价（元）
	private String goodsNum;		// 进货数量（个/斤）
	private String totalPrice;		// 总价
	private Date recDate;		// 进货时间
	private String purchasePerson;		// 采购人
	private String payMethod;		// 支付方式
	private String paySum;		// 总价之和
	private Date beginRecDate;		// 开始 进货时间
	private Date endRecDate;		// 结束 进货时间
	
	public PurchaseReceipt() {
		super();
	}

	public PurchaseReceipt(String id){
		super(id);
	}

	@Length(min=0, max=2000, message="供应商长度必须介于 0 和 2000 之间")
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	@Length(min=0, max=255, message="货物名称长度必须介于 0 和 255 之间")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	@Length(min=0, max=255, message="进货类型长度必须介于 0 和 255 之间")
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}
	
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}
	
	@Length(min=0, max=255, message="采购人长度必须介于 0 和 255 之间")
	public String getPurchasePerson() {
		return purchasePerson;
	}

	public void setPurchasePerson(String purchasePerson) {
		this.purchasePerson = purchasePerson;
	}
	
	@Length(min=0, max=255, message="支付方式长度必须介于 0 和 255 之间")
	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	public String getPaySum() {
		return paySum;
	}

	public void setPaySum(String paySum) {
		this.paySum = paySum;
	}
	
	public Date getBeginRecDate() {
		return beginRecDate;
	}

	public void setBeginRecDate(Date beginRecDate) {
		this.beginRecDate = beginRecDate;
	}
	
	public Date getEndRecDate() {
		return endRecDate;
	}

	public void setEndRecDate(Date endRecDate) {
		this.endRecDate = endRecDate;
	}
		
}