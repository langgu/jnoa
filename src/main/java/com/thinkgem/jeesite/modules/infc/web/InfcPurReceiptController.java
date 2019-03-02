/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infc.web;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.infc.entity.DataStatus;
import com.thinkgem.jeesite.modules.infc.entity.DataStatusList;
import com.thinkgem.jeesite.modules.purreceipt.entity.PurchaseReceipt;
import com.thinkgem.jeesite.modules.purreceipt.service.PurchaseReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 供应商管理Controller
 * @author cwb
 * @version 2019-02-09
 */
@Controller
@RequestMapping(value = "${adminPath}/infc/infcPurReceipt")
public class InfcPurReceiptController extends BaseController {

	@Autowired
	private PurchaseReceiptService purchaseReceiptService;

	@RequestMapping(value = "PurReceiptList",method = RequestMethod.GET)
	public String list(PurchaseReceipt purchaseReceipt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PurchaseReceipt> page = purchaseReceiptService.findPage(new Page<PurchaseReceipt>(request, response), purchaseReceipt);
		//return "modules/supplier/supplierList";
		DataStatusList status = new DataStatusList();
		status.setSuccess("true");
		if (page.getList().size()>0){
			status.setStatusMessage("ok");
		}else {
			status.setStatusMessage("暂无数据");
		}
		status.setStatusMessage("ok");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Double Sum = 0.00;
		for(PurchaseReceipt purchaseReceipt1 : page.getList()){

			Map<String, Object> map = Maps.newHashMap();
			map.put("id", purchaseReceipt1.getId());
			//map.put("send_user_name", UserUtils.get(supplier1.getCreateBy().getId()).getName());
			DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
	//		String reTime = format.format(purchaseReceipt1.getCreateDate());
			//map.put("send_date",reTime);
			map.put("goodsName",purchaseReceipt1.getGoodsName());
			map.put("totalPrice",purchaseReceipt1.getTotalPrice());
			map.put("goodsType",purchaseReceipt1.getGoodsType());
			/*Double price = Double.valueOf(purchaseReceipt1.getTotalPrice());
			Sum = Sum+price;*/
			Date da = purchaseReceipt1.getRecDate();
			String strDate = format.format(da);
			map.put("date",strDate);
//			map.put("date",purchaseReceipt1.getRecDate());
			/*String strSum = Sum.toString();
			purchaseReceipt1.setPaySum(strSum);
			map.put("paySum",strSum);//将总价之和加入map，传至app*/
			mapList.add(map);
			purchaseReceiptService.update(purchaseReceipt1);//将总价之和存入数据库
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
 * @description 获取订单详细信息
 * @author lvyan
 * @date 2019/2/918:09
 */
	@ResponseBody
	@RequestMapping(value = "PurReceiptDetails",method = RequestMethod.GET)
	public String get(HttpServletRequest request, HttpServletResponse response) {
		String supplierId = request.getParameter("supplierId");
		PurchaseReceipt entity = null;
		if (StringUtils.isNotBlank(supplierId)){
			entity = purchaseReceiptService.get(supplierId);
		}
		if (entity == null){
			entity = new PurchaseReceipt();
		}

		Map<String,Object> map = Maps.newHashMap();

		map.put("supplier",entity.getSupplier());
		map.put("goodsName",entity.getGoodsName());
		map.put("goodsType",entity.getGoodsType());
		map.put("unitPrice",entity.getUnitPrice());
		map.put("goodsNum",entity.getGoodsNum());
		map.put("totalPrice",entity.getTotalPrice());
		Date da = entity.getRecDate();
		String strDate = (new SimpleDateFormat("yyyy-MM-dd").format(da));
		map.put("date",strDate);
//		map.put("date",entity.getRecDate());
		map.put("purchasePerson",entity.getPurchasePerson());
		map.put("payMethod",entity.getPayMethod());

		DataStatus status = new DataStatus();
		status.setSuccess("true");
		status.setStatusMessage("ok");
		status.setData(map);
		return this.renderString(response,status);
	}

}