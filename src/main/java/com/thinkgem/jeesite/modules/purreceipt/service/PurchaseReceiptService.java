/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.purreceipt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.purreceipt.entity.PurchaseReceipt;
import com.thinkgem.jeesite.modules.purreceipt.dao.PurchaseReceiptDao;

/**
 * 进货订单管理Service
 * @author lvyan
 * @version 2019-02-16
 */
@Service
@Transactional(readOnly = true)
public class PurchaseReceiptService extends CrudService<PurchaseReceiptDao, PurchaseReceipt> {

	public PurchaseReceipt get(String id) {
		return super.get(id);
	}
	
	public List<PurchaseReceipt> findList(PurchaseReceipt purchaseReceipt) {
		return super.findList(purchaseReceipt);
	}
	
	public Page<PurchaseReceipt> findPage(Page<PurchaseReceipt> page, PurchaseReceipt purchaseReceipt) {
		return super.findPage(page, purchaseReceipt);
	}
	
	@Transactional(readOnly = false)
	public void save(PurchaseReceipt purchaseReceipt) {
		super.save(purchaseReceipt);
	}
	
	@Transactional(readOnly = false)
	public void delete(PurchaseReceipt purchaseReceipt) {
		super.delete(purchaseReceipt);
	}
	
}