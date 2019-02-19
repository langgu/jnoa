/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infc.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.infc.entity.DataStatus;
import com.thinkgem.jeesite.modules.infc.entity.DataStatusList;
import com.thinkgem.jeesite.modules.supplier.entity.Supplier;
import com.thinkgem.jeesite.modules.supplier.service.SupplierService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 供应商管理Controller
 * @author cwb
 * @version 2019-02-09
 */
@CrossOrigin
@Controller
@RequestMapping(value = "${adminPath}/infc/infcAddSupplier")
public class InfcAddSupplierController extends BaseController {

	@Autowired
	private SupplierService supplierService;

	@RequestMapping(value = "addSupplier",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
		public String addSupplier(@RequestBody Supplier supplier, HttpServletRequest request, HttpServletResponse response){
		supplierService.save(supplier);
		//return "redirect:"+Global.getAdminPath()+"/supplier/supplier/?repage";
		DataStatus status = new DataStatus();
		status.setStatusMessage("ok");
		status.setSuccess("true");
		return this.renderString(response,status);
	}

}