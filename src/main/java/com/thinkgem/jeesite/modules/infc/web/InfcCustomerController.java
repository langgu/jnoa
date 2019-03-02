/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infc.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cus.entity.Customer;
import com.thinkgem.jeesite.modules.cus.service.CustomerService;
import com.thinkgem.jeesite.modules.infc.entity.DataStatus;
import com.thinkgem.jeesite.modules.infc.entity.DataStatusList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 客户管理Controller
 * @author mjx
 * @version 2019-02-09
 */
@Controller
@RequestMapping(value = "${adminPath}/infc/infcCustomer")
public class InfcCustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "customerList",method = RequestMethod.GET)
    public String customerList(Customer customer, HttpServletRequest request, HttpServletResponse response) {
        Page<Customer> page =customerService.findPage(new Page<Customer>(request, response), customer);
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
        for(Customer customer1 : page.getList()){
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", customer1.getId());
            //map.put("send_user_name", UserUtils.get(supplier1.getCreateBy().getId()).getName());
            DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            String reTime = format.format(customer1.getCreateDate());
            map.put("send_date",reTime);
            map.put("username",customer1.getUsername());
            map.put("sex",customer1.getSex());
            map.put("nation",customer1.getNation());
            map.put("tel",customer1.getTel());
            map.put("remarks",customer1.getRemarks());
            mapList.add(map);
        }
        status.setData(mapList);
        return this.renderString(response,status);
    }

    /*@RequestMapping(value = "customerDetail",method = RequestMethod.GET)
    public String customerDetailList(Customer customer, HttpServletRequest request, HttpServletResponse response) {
        Page<Customer> page =customerService.findPage(new Page<Customer>(request, response), customer);
		*//*model.addAttribute("page", page);
		return "modules/supplier/supplierList";*//*
        DataStatusList status = new DataStatusList();
        status.setSuccess("true");
        *//*if (page.getList().size()>0){
            status.setStatusMessage("ok");
        }else {
            status.setStatusMessage("暂无数据");
        }
        status.setStatusMessage("ok");*//*

        List<Map<String, Object>> mapList = Lists.newArrayList();
        for(Customer customer1 : page.getList()){
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", customer1.getId());
            //map.put("send_user_name", UserUtils.get(supplier1.getCreateBy().getId()).getName());
            DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            String reTime = format.format(customer1.getCreateDate());
            map.put("send_date",reTime);
            map.put("username",customer1.getUsername());
            map.put("nation",customer1.getNation());
            map.put("tel",customer1.getTel());
            mapList.add(map);
        }
        status.setData(mapList);
        return this.renderString(response,status);
    }*/
    /**
     * 客户管理详情Controller
     * @author mjx
     * @version 2019-02-09
     */
    @ResponseBody
    @RequestMapping(value = "customerDetail",method = RequestMethod.GET)
    public String get(HttpServletRequest request, HttpServletResponse response) {
        String customerId = request.getParameter("customerId");
        Customer entity = null;
        if (StringUtils.isNotBlank(customerId)){
            entity = customerService.get(customerId);
        }
        if (entity == null){
            entity = new Customer();
        }

        Map<String,Object> map = Maps.newHashMap();

        map.put("username",entity.getUsername());
        map.put("nation",entity.getNation());
        map.put("sex",entity.getSex());
        map.put("tel",entity.getTel());
        map.put("source",entity.getSource());
        DataStatus status = new DataStatus();
        status.setSuccess("true");
        status.setStatusMessage("ok");
        status.setData(map);
        return this.renderString(response,status);
    }
	/*@RequiresPermissions("supplier:supplier:view")
	@RequestMapping(value = "form")
	public String form(Supplier supplier, Model model) {
		model.addAttribute("supplier", supplier);
		return "modules/supplier/supplierForm";
	}



	@RequiresPermissions("supplier:supplier:edit")
	@RequestMapping(value = "delete")
	public String delete(Supplier supplier, RedirectAttributes redirectAttributes) {
		supplierService.delete(supplier);
		addMessage(redirectAttributes, "删除供应商信息成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/supplier/?repage";
	}*/

}