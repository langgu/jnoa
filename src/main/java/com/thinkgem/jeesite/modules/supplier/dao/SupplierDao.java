/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.supplier.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.supplier.entity.Supplier;

/**
 * 供应商DAO接口
 * @author lv
 * @version 2019-02-11
 */
@MyBatisDao
public interface SupplierDao extends CrudDao<Supplier> {
	
}