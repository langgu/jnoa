/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cusorder.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cusorder.entity.CustomerOrder;
import com.thinkgem.jeesite.modules.cusorder.dao.CustomerOrderDao;

/**
 * 订货管理Service
 * @author mjx
 * @version 2019-02-16
 */
@Service
@Transactional(readOnly = true)
public class CustomerOrderService extends CrudService<CustomerOrderDao, CustomerOrder> {

	public CustomerOrder get(String id) {
		return super.get(id);
	}
	
	public List<CustomerOrder> findList(CustomerOrder customerOrder) {
		return super.findList(customerOrder);
	}
	
	public Page<CustomerOrder> findPage(Page<CustomerOrder> page, CustomerOrder customerOrder) {
		return super.findPage(page, customerOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(CustomerOrder customerOrder) {
		super.save(customerOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(CustomerOrder customerOrder) {
		super.delete(customerOrder);
	}
	
}