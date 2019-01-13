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
import com.thinkgem.jeesite.modules.infc.entity.DataStatusList;
import com.thinkgem.jeesite.modules.oa.entity.OaTask;
import com.thinkgem.jeesite.modules.oa.entity.OaTaskRecord;
import com.thinkgem.jeesite.modules.oa.service.OaTaskRecordService;
import com.thinkgem.jeesite.modules.oa.service.OaTaskService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 人员任务回复详情Controller
 * @author ctt
 * @version 2019-01-10
 */
@CrossOrigin
@Controller
@RequestMapping(value = "${adminPath}/infc/infcOaTaskRecord")
public class InfcOaTaskRecordController extends BaseController {

	@Autowired
	private OaTaskRecordService oaTaskRecordService;

	@Autowired
	private OaTaskService oaTaskService;

	@ModelAttribute
	public OaTaskRecord get(@RequestParam(required=false) String id) {
		OaTaskRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaTaskRecordService.get(id);
		}
		if (entity == null){
			entity = new OaTaskRecord();
		}
		return entity;
	}

	/**
	 * 查询我的待办/已办任务
	 * @param oaTaskRecord
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "user_task_list",method = RequestMethod.GET)
	public String list(OaTaskRecord oaTaskRecord, HttpServletRequest request, HttpServletResponse response) {
		//手机端传送userid
		//然后根据userId和完成标记查询出本用户对应的任务信息列表
		String userId = request.getParameter("userId");
		String flag = request.getParameter("completeFlag");
		User user = UserUtils.get(userId);
		oaTaskRecord.setReceUser(user);
		oaTaskRecord.setCompleteFlag(flag);
		List<OaTaskRecord> list = oaTaskRecordService.findList(oaTaskRecord);
		ArrayList<OaTask> arrayList = new ArrayList<OaTask>();
		//得到信息条list,然后取出每一条信息条的oa_task_id 根据id获取task对象，取出task的值
		for (OaTaskRecord oaTaskRecord1 :list){
			String  taskid = oaTaskRecord1.getOaTask().getId();
			OaTask oaTask = oaTaskService.get(taskid);
			arrayList.add(oaTask);
		}
		List<Map<String, Object>> data = Lists.newArrayList();
		for (OaTask oaTask :arrayList){
			 Map<String, Object> alldata = Maps.newHashMap();
			 alldata.put("id",oaTask.getId());
			 alldata.put("title",oaTask.getTitle());
			 //alldata.put("content",oaTask.getContent());
			 alldata.put("senduser",UserUtils.get(oaTask.getCreateBy().getId()).getName());
			 alldata.put("forwoadFlag",oaTask.getForwardFlag());
			 DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
			 String reTime = format.format(oaTask.getCreateDate());
			 alldata.put("sendDate",reTime);
			 data.add(alldata);
		}
		DataStatusList status = new DataStatusList();
		status.setSuccess("true");
		if (arrayList.size()>0){
			status.setStatusMessage("ok");
		}else {
			status.setStatusMessage("暂无数据");
		}
		status.setData(data);
		return this.renderString(response,status);
	}


}