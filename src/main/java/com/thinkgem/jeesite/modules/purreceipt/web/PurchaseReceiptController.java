/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.purreceipt.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.purreceipt.entity.PurchaseReceipt;
import com.thinkgem.jeesite.modules.purreceipt.service.PurchaseReceiptService;

/**
 * 进货订单管理Controller
 * @author lvyan
 * @version 2019-02-16
 */
@Controller
@RequestMapping(value = "${adminPath}/purreceipt/purchaseReceipt")
public class PurchaseReceiptController extends BaseController {

	@Autowired
	private PurchaseReceiptService purchaseReceiptService;
	
	@ModelAttribute
	public PurchaseReceipt get(@RequestParam(required=false) String id) {
		PurchaseReceipt entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = purchaseReceiptService.get(id);
		}
		if (entity == null){
			entity = new PurchaseReceipt();
		}
		return entity;
	}
	
	@RequiresPermissions("purreceipt:purchaseReceipt:view")
	@RequestMapping(value = {"list", ""})
	public String list(PurchaseReceipt purchaseReceipt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PurchaseReceipt> page = purchaseReceiptService.findPage(new Page<PurchaseReceipt>(request, response), purchaseReceipt); 
		model.addAttribute("page", page);
		return "modules/purreceipt/purchaseReceiptList";
	}

	@RequiresPermissions("purreceipt:purchaseReceipt:view")
	@RequestMapping(value = "form")
	public String form(PurchaseReceipt purchaseReceipt, Model model) {
		model.addAttribute("purchaseReceipt", purchaseReceipt);
		return "modules/purreceipt/purchaseReceiptForm";
	}

	@RequiresPermissions("purreceipt:purchaseReceipt:edit")
	@RequestMapping(value = "save")
	public String save(PurchaseReceipt purchaseReceipt, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, purchaseReceipt)){
			return form(purchaseReceipt, model);
		}
		purchaseReceiptService.save(purchaseReceipt);
		addMessage(redirectAttributes, "保存进货订单管理成功");
		return "redirect:"+Global.getAdminPath()+"/purreceipt/purchaseReceipt/?repage";
	}
	
	@RequiresPermissions("purreceipt:purchaseReceipt:edit")
	@RequestMapping(value = "delete")
	public String delete(PurchaseReceipt purchaseReceipt, RedirectAttributes redirectAttributes) {
		purchaseReceiptService.delete(purchaseReceipt);
		addMessage(redirectAttributes, "删除进货订单管理成功");
		return "redirect:"+Global.getAdminPath()+"/purreceipt/purchaseReceipt/?repage";
	}

}