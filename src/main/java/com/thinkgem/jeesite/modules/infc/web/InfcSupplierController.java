/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infc.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.infc.entity.DataStatus;
import com.thinkgem.jeesite.modules.infc.entity.DataStatusList;
import com.thinkgem.jeesite.modules.supplier.entity.Supplier;
import com.thinkgem.jeesite.modules.supplier.service.SupplierService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static org.activiti.engine.impl.util.json.XMLTokener.entity;

/**
 * 供应商管理Controller
 * @author cwb
 * @version 2019-02-09
 */
@Controller
@RequestMapping(value = "${adminPath}/infc/infcSupplier")
public class InfcSupplierController extends BaseController {

	@Autowired
	private SupplierService supplierService;

	@RequestMapping(value = "supplierList",method = RequestMethod.GET)
	public String supplierList(Supplier supplier, HttpServletRequest request, HttpServletResponse response) {
		Page<Supplier> page = supplierService.findPage(new Page<Supplier>(request, response), supplier); 
		/*model.addAttribute("page", page);
		return "modules/supplier/supplierList";*/
		DataStatusList status = new DataStatusList();
		status.setSuccess("true");
		if (page.getList().size()>0){
			status.setStatusMessage("ok");
		}else {
			status.setStatusMessage("暂无数据");
		}
		status.setStatusMessage("ok");

		List<Map<String, Object>> mapList = Lists.newArrayList();
		for(Supplier supplier1 : page.getList()){
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", supplier1.getId());
			//map.put("send_user_name", UserUtils.get(supplier1.getCreateBy().getId()).getName());
			DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
			String reTime = format.format(supplier1.getCreateDate());
			map.put("send_date",reTime);
			map.put("supName",supplier1.getSupName());
			map.put("legalPerson",supplier1.getLegalPerson());
			map.put("supAddress",supplier1.getSupAddress());
			mapList.add(map);
		}
		status.setData(mapList);
		return this.renderString(response,status);
	}



	/*
	@RequiresPermissions("supplier:supplier:edit")
	@RequestMapping(value = "delete")
	public String delete(Supplier supplier, RedirectAttributes redirectAttributes) {
		supplierService.delete(supplier);
		addMessage(redirectAttributes, "删除供应商信息成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/supplier/?repage";
	}*/
/**
 * @description 获取供应商详细信息
 * @author cwb
 * @date 2019/2/918:09
 */
	@ResponseBody
	@RequestMapping(value = "supplierDetails",method = RequestMethod.GET)
	public String get(HttpServletRequest request, HttpServletResponse response) {
		String supplierId = request.getParameter("supplierId");
		Supplier entity = null;
		if (StringUtils.isNotBlank(supplierId)){
			entity = supplierService.get(supplierId);
		}
		if (entity == null){
			entity = new Supplier();
		}

		Map<String,Object> map = Maps.newHashMap();

		map.put("supName",entity.getSupName());
		map.put("legalPerson",entity.getLegalPerson());
		map.put("supAddress",entity.getSupAddress());

		DataStatus status = new DataStatus();
		status.setSuccess("true");
		status.setStatusMessage("ok");
		status.setData(map);
		return this.renderString(response,status);
	}


}