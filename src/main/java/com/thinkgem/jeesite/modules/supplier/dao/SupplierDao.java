/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.supplier.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.supplier.entity.Supplier;

/**
 * 供应商管理DAO接口
 * @author cwb
 * @version 2019-02-09
 */
@MyBatisDao
public interface SupplierDao extends CrudDao<Supplier> {
	
}