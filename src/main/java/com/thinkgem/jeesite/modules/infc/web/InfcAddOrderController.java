/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infc.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cusorder.entity.CustomerOrder;
import com.thinkgem.jeesite.modules.cusorder.service.CustomerOrderService;
import com.thinkgem.jeesite.modules.infc.entity.DataStatus;
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
 * 订单管理Controller
 * @author mjx
 * @version 2019-02-09
 */
//订单保存
@CrossOrigin
@Controller
@RequestMapping(value = "${adminPath}/infc/infcAddOrderCustomer")
public class InfcAddOrderController extends BaseController {

	@Autowired
	private CustomerOrderService customerOrderService;

	@RequestMapping(value = "addOrderCustomer",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
		public String addCustomer(@RequestBody CustomerOrder customerOrder, HttpServletRequest request, HttpServletResponse response){
		customerOrderService.save(customerOrder);
		//return "redirect:"+Global.getAdminPath()+"/supplier/supplier/?repage";
		DataStatus status = new DataStatus();
		status.setStatusMessage("ok");
		status.setSuccess("true");
		return this.renderString(response,status);
	}

}