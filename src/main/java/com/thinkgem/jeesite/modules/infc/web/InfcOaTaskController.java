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
import com.thinkgem.jeesite.modules.oa.dao.OaTaskDao;
import com.thinkgem.jeesite.modules.oa.entity.OaTask;
import com.thinkgem.jeesite.modules.oa.entity.OaTaskRecord;
import com.thinkgem.jeesite.modules.oa.entity.OaTaskReply;
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
import java.util.List;
import java.util.Map;

/**
 * 任务信息Controller
 * @author ctt
 * @version 2019-01-10
 */
@Controller
@RequestMapping(value = "${adminPath}/infc/infcoaTask")
public class InfcOaTaskController extends BaseController {

	@Autowired
	private OaTaskService oaTaskService;

	@Autowired
	private OaTaskDao oaTaskDao;

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



	/**
	 * 查询当前用户已发布的任务
	 */
	@ResponseBody
	@RequestMapping(value = "task_own_list",method = RequestMethod.GET)
	public String ownlist(OaTask oaTask, HttpServletRequest request, HttpServletResponse response){
		//手机端传送userid
		String userId = request.getParameter("userid");
		User user = UserUtils.get(userId);
		//获取创建者为当前id的通知通告列表
		oaTask.setCreateBy(user);
		List<Map<String, Object>> data = Lists.newArrayList();
		List<OaTask> list = oaTaskService.findList(oaTask);
		for (OaTask oaTask1:list){
			//OaTask oaTask1 = oaTaskService.get(list.get(i).getId());
			Map<String, Object> data_detail = Maps.newHashMap();
			data_detail.put("id",oaTask1.getId());
			data_detail.put("title",oaTask1.getTitle());
			data_detail.put("CompleteFlag",oaTask1.getCompleteFlag());
			//data_detail.put("content",oaTask1.getContent());
			//data_detail.put("senduser",UserUtils.get(oaTask1.getCreateBy().getId()).getName());
			data_detail.put("forwoadFlag",oaTask.getForwardFlag());
			DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
			String reTime = format.format(oaTask1.getCreateDate());
			data_detail.put("sendDate",reTime);
			data.add(data_detail);
		}
		DataStatusList status = new DataStatusList();
		status.setSuccess("true");
		if (list.size()>0){
			status.setStatusMessage("ok");
		}else {
			status.setStatusMessage("暂无数据");
		}
		status.setData(data);
		return this.renderString(response,status);

	}

   /**
    * 查询代办任务详情
    */
	@ResponseBody
	@RequestMapping(value = "task_record_detail",method = RequestMethod.GET)
	public String task_detail(OaTask oaTask, HttpServletRequest request, HttpServletResponse response) {
		String oaTaskId = request.getParameter("id");
		OaTask entity = null;
		if (StringUtils.isNotBlank(oaTaskId)) {
			entity = oaTaskService.get(oaTaskId);
		}
		if (entity == null) {
			entity = new OaTask();
		}

		Map<String, Object> map = Maps.newHashMap();
		map.put("title", entity.getTitle());
		map.put("content", entity.getContent());
		map.put("senduser",UserUtils.get(entity.getCreateBy().getId()).getName());
		DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String reTime = format.format(oaTask.getCreateDate());
		map.put("sendDate",reTime);
		map.put("forwoadFlag",oaTask.getForwardFlag());
		map.put("file",oaTask.getFiles());
		DataStatus dataStatus = new DataStatus();
		dataStatus.setSuccess("true");
		dataStatus.setStatusMessage("ok");
		dataStatus.setData(map);
		return this.renderString(response,dataStatus);
	}

	/**
	 * 查询任务详情以及回复列表
	 */
	@ResponseBody
	@RequestMapping(value = "task_reply_detail",method = RequestMethod.GET)
	public String task_reply_detail(OaTask oaTask, HttpServletRequest request, HttpServletResponse response){
		String oaTaskId = request.getParameter("id");
		OaTask entity = null;
		if (StringUtils.isNotBlank(oaTaskId)) {
			entity = oaTaskService.get(oaTaskId);
		}
		if (entity == null) {
			entity = new OaTask();
		}
		Map<String, Object> map = Maps.newHashMap();
		//任务详情
		map.put("title", entity.getTitle());
		map.put("forwoadFlag",oaTask.getForwardFlag());
		DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String reTime = format.format(oaTask.getCreateDate());
		map.put("sendDate",reTime);
		map.put("id",oaTask.getId());

		//任务回复列表
		List<OaTaskRecord> list = oaTask.getOaTaskRecordList();
		List<Map<String, Object>> data = Lists.newArrayList();
		for (OaTaskRecord oaTaskRecord :list)
		{
			//得到本项目的第I位接收人的回复列表
			List<OaTaskReply> replylist = oaTaskRecord.getOaTaskReplyList();
			for (OaTaskReply oaTaskReply : replylist)
			{
				Map<String, Object> map1 = Maps.newHashMap();
				map1.put("user",UserUtils.get(oaTaskReply.getCreateBy().getId()).getName());
				map1.put("content",oaTaskReply.getReplyContent());
				map1.put("ReplyFlag",oaTaskReply.getReplyFlag());
				String reTime2 = format.format(oaTask.getCreateDate());
				map1.put("date",reTime2);
				data.add(map1);
			}

		}
		map.put("reply_list",data);
		DataStatus dataStatusList = new DataStatus();
		dataStatusList.setSuccess("true");
		if (data.size()>0){
			dataStatusList.setStatusMessage("ok");
		}else {
			dataStatusList.setStatusMessage("暂无回复数据");
		}
		dataStatusList.setData(map);
		return this.renderString(response,dataStatusList);
	}


}