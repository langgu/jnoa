/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cusorder.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cusorder.entity.CustomerOrder;

/**
 * 订货管理DAO接口
 * @author mjx
 * @version 2019-02-16
 */
@MyBatisDao
public interface CustomerOrderDao extends CrudDao<CustomerOrder> {
	
}