/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infc.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.OaTask;
import com.thinkgem.jeesite.modules.oa.service.OaTaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 任务信息Controller
 * @author wfp
 * @version 2019-01-09
 */
@Controller
@RequestMapping(value = "${adminPath}/infc/infcoaTask")
public class InfcOaTaskController extends BaseController {

	@Autowired
	private OaTaskService oaTaskService;
	
	@ModelAttribute
	public OaTask get(@RequestParam(required=false) String id) {
		OaTask entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaTaskService.get(id);
		}
		if (entity == null){
			entity = new OaTask();
		}
		return entity;
	}

	@RequiresPermissions("oa:oaTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaTask oaTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaTask> page = oaTaskService.findPage(new Page<OaTask>(request, response), oaTask); 
		model.addAttribute("page", page);
		return "modules/oa/oaTaskList";
	}




}