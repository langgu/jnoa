/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cus.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cus.entity.Customer;

/**
 * 客户信息管理DAO接口
 * @author mjx
 * @version 2019-03-01
 */
@MyBatisDao
public interface CustomerDao extends CrudDao<Customer> {
	
}