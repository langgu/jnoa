/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cusorder.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订货管理Entity
 * @author mjx
 * @version 2019-02-16
 */
public class CustomerOrder extends DataEntity<CustomerOrder> {
	
	private static final long serialVersionUID = 1L;
	private String cusname;		// 客户姓名
	private String goods;		// 货物名称
	private String price;		// 单价
	private String goodsnum;		// 进货量
	private String sumprice;		// 总价
	private Date date;		// 日期
	private Date beginDate;		// 开始 日期
	private Date endDate;		// 结束 日期
	private String ordertype;		// 支付类型
	public CustomerOrder() {
		super();
	}

	public CustomerOrder(String id){
		super(id);
	}

	@Length(min=0, max=255, message="客户姓名长度必须介于 0 和 255 之间")
	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}
	
	@Length(min=0, max=255, message="货物名称长度必须介于 0 和 255 之间")
	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getGoodsnum() {
		return goodsnum;
	}

	public void setGoodsnum(String goodsnum) {
		this.goodsnum = goodsnum;
	}
	
	public String getSumprice() {
		return sumprice;
	}

	public void setSumprice(String sumprice) {
		this.sumprice = sumprice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
}