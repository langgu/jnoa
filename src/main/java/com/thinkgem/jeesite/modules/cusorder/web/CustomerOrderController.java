/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cusorder.web;

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
import com.thinkgem.jeesite.modules.cusorder.entity.CustomerOrder;
import com.thinkgem.jeesite.modules.cusorder.service.CustomerOrderService;

/**
 * 订货管理Controller
 * @author mjx
 * @version 2019-03-01
 */
@Controller
@RequestMapping(value = "${adminPath}/cusorder/customerOrder")
public class CustomerOrderController extends BaseController {

	@Autowired
	private CustomerOrderService customerOrderService;
	
	@ModelAttribute
	public CustomerOrder get(@RequestParam(required=false) String id) {
		CustomerOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = customerOrderService.get(id);
		}
		if (entity == null){
			entity = new CustomerOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("cusorder:customerOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(CustomerOrder customerOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CustomerOrder> page = customerOrderService.findPage(new Page<CustomerOrder>(request, response), customerOrder); 
		model.addAttribute("page", page);
		return "modules/cusorder/customerOrderList";
	}

	@RequiresPermissions("cusorder:customerOrder:view")
	@RequestMapping(value = "form")
	public String form(CustomerOrder customerOrder, Model model) {
		model.addAttribute("customerOrder", customerOrder);
		return "modules/cusorder/customerOrderForm";
	}

	@RequiresPermissions("cusorder:customerOrder:edit")
	@RequestMapping(value = "save")
	public String save(CustomerOrder customerOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, customerOrder)){
			return form(customerOrder, model);
		}
		customerOrderService.save(customerOrder);
		addMessage(redirectAttributes, "保存订货管理成功");
		return "redirect:"+Global.getAdminPath()+"/cusorder/customerOrder/?repage";
	}
	
	@RequiresPermissions("cusorder:customerOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(CustomerOrder customerOrder, RedirectAttributes redirectAttributes) {
		customerOrderService.delete(customerOrder);
		addMessage(redirectAttributes, "删除订货管理成功");
		return "redirect:"+Global.getAdminPath()+"/cusorder/customerOrder/?repage";
	}

}