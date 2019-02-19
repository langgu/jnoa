/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infc.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cusorder.entity.CustomerOrder;
import com.thinkgem.jeesite.modules.cusorder.service.CustomerOrderService;
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
 * 订单管理Controller
 * @author mjx
 * @version 2019-02-09
 */
@Controller
@RequestMapping(value = "${adminPath}/infc/infcCustomerOrder")
public class InfcCustomerOrderController extends BaseController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @RequestMapping(value = "customerOrderList",method = RequestMethod.GET)
    public String customerorderList(CustomerOrder customerOrder, HttpServletRequest request, HttpServletResponse response) {
        Page<CustomerOrder> page =customerOrderService.findPage(new Page<CustomerOrder>(request, response), customerOrder);
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
        for(CustomerOrder customerOrder1 : page.getList()){
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", customerOrder1.getId());
            //map.put("send_user_name", UserUtils.get(supplier1.getCreateBy().getId()).getName());
//            DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
          /*String reTime = format.format(customerOrder1.getCreateDate());*/
//            map.put("send_date",reTime);
            map.put("cusname",customerOrder1.getCusname());
            map.put("goods",customerOrder1.getGoods());
            map.put("goodsnum",customerOrder1.getGoodsnum());
            map.put("price",customerOrder1.getPrice());
            /*map.put("remarks",customerOrder1.getRemarks());*/
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
     * 订单详情Controller
     * @author mjx
     * @version 2019-02-09
     */
    @ResponseBody
    @RequestMapping(value = "customerOrderDetail",method = RequestMethod.GET)
    public String get(HttpServletRequest request, HttpServletResponse response) {
        String customerId = request.getParameter("customerOrderId");
        CustomerOrder entity = null;
        if (StringUtils.isNotBlank(customerId)){
            entity = customerOrderService.get(customerId);
        }
        if (entity == null){
            entity = new CustomerOrder();
        }

        Map<String,Object> map = Maps.newHashMap();

        map.put("cusname",entity.getCusname());
        map.put("goods",entity.getGoods());
        map.put("goodsnum",entity.getGoodsnum());
        map.put("price",entity.getPrice());
        /*map.put("sumprice",entity.getSumprice());*/
        /*map.put("date",entity.getDate());*/
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
	}*/



	/*@RequiresPermissions("supplier:supplier:edit")
	@RequestMapping(value = "delete")
	public String delete(Supplier supplier, RedirectAttributes redirectAttributes) {
		supplierService.delete(supplier);
		addMessage(redirectAttributes, "删除供应商信息成功");
		return "redirect:"+Global.getAdminPath()+"/supplier/supplier/?repage";
	}*/

}