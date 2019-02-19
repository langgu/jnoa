/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.t.web;

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
import com.thinkgem.jeesite.modules.t.entity.ActEvtLog;
import com.thinkgem.jeesite.modules.t.service.ActEvtLogService;

/**
 * tController
 * @author cwb
 * @version 2019-02-08
 */
@Controller
@RequestMapping(value = "${adminPath}/t/actEvtLog")
public class ActEvtLogController extends BaseController {

	@Autowired
	private ActEvtLogService actEvtLogService;
	
	@ModelAttribute
	public ActEvtLog get(@RequestParam(required=false) String id) {
		ActEvtLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = actEvtLogService.get(id);
		}
		if (entity == null){
			entity = new ActEvtLog();
		}
		return entity;
	}
	
	@RequiresPermissions("t:actEvtLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(ActEvtLog actEvtLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ActEvtLog> page = actEvtLogService.findPage(new Page<ActEvtLog>(request, response), actEvtLog); 
		model.addAttribute("page", page);
		return "modules/t/actEvtLogList";
	}

	@RequiresPermissions("t:actEvtLog:view")
	@RequestMapping(value = "form")
	public String form(ActEvtLog actEvtLog, Model model) {
		model.addAttribute("actEvtLog", actEvtLog);
		return "modules/t/actEvtLogForm";
	}

	@RequiresPermissions("t:actEvtLog:edit")
	@RequestMapping(value = "save")
	public String save(ActEvtLog actEvtLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, actEvtLog)){
			return form(actEvtLog, model);
		}
		actEvtLogService.save(actEvtLog);
		addMessage(redirectAttributes, "保存t成功");
		return "redirect:"+Global.getAdminPath()+"/t/actEvtLog/?repage";
	}
	
	@RequiresPermissions("t:actEvtLog:edit")
	@RequestMapping(value = "delete")
	public String delete(ActEvtLog actEvtLog, RedirectAttributes redirectAttributes) {
		actEvtLogService.delete(actEvtLog);
		addMessage(redirectAttributes, "删除t成功");
		return "redirect:"+Global.getAdminPath()+"/t/actEvtLog/?repage";
	}

}