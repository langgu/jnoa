/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.purreceipt.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.purreceipt.entity.PurchaseReceipt;

/**
 * 进货订单管理DAO接口
 * @author lvyan
 * @version 2019-02-16
 */
@MyBatisDao
public interface PurchaseReceiptDao extends CrudDao<PurchaseReceipt> {
	
}