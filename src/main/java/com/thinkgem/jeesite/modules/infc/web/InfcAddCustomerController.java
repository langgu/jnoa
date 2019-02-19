/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infc.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.infc.entity.DataStatus;
import com.thinkgem.jeesite.modules.cus.entity.Customer;
import com.thinkgem.jeesite.modules.cus.service.CustomerService;
import com.thinkgem.jeesite.modules.supplier.entity.Supplier;
import com.thinkgem.jeesite.modules.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 客户管理管理Controller
 * @author mjx
 * @version 2019-02-09
 */
@CrossOrigin
@Controller
@RequestMapping(value = "${adminPath}/infc/infcAddCustomer")
public class InfcAddCustomerController extends BaseController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "addCustomer",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
		public String addOrderCustomer(@RequestBody Customer customer, HttpServletRequest request, HttpServletResponse response){
		customerService.save(customer);
		//return "redirect:"+Global.getAdminPath()+"/supplier/supplier/?repage";
		DataStatus status = new DataStatus();
		status.setStatusMessage("ok");
		status.setSuccess("true");
		return this.renderString(response,status);
	}

}