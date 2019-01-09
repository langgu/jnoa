/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.infc.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.infc.entity.DataStatus;
import com.thinkgem.jeesite.modules.infc.entity.DataStatusList;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;

/**
* @Description:    用户信息接口
* @Author:         wfp
* @CreateDate:     2019/1/8 18:13
*/
@CrossOrigin
@Controller
@RequestMapping(value = "${adminPath}/infc/infcuser")
public class InfcUserController extends BaseController {

	@Autowired
	private SystemService systemService;

	@Autowired
	private OfficeService officeService;

	/**
	* @Description:    用户个人信息接口
	* @Author:         wfp
	* @CreateDate:     2019/1/8 18:31
	*/
	@RequestMapping(value = "getUserInfo",method = RequestMethod.GET)
	public String getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		DataStatus status = new DataStatus();
		Map<String, Object> data = Maps.newHashMap();
		String userName = request.getParameter("userName");  //获取传递参数 userName
		User user = systemService.getUserByLoginName(userName);
		if(user != null){
			status.setStatusMessage("ok");
			status.setSuccess("true");
			data.put("userId",user.getId());
			data.put("userName",user.getLoginName());
			data.put("name",user.getName());
			data.put("position",user.getPosition());
			data.put("officeId",user.getOffice().getId());
			data.put("officeName",user.getOffice().getName());
		}
		else{
			status.setStatusMessage("失败");
		}
		status.setData(data);
		return this.renderString(response,status);
	}

	/**
	* @Description:    部门及人员信息
	* @Author:         wfp
	* @CreateDate:     2019/1/8 18:32
	*/
    @RequestMapping(value = "getOfficeAndUser")
    public String getOfficeAndUser(HttpServletRequest request, HttpServletResponse response){
        DataStatusList status = new DataStatusList();
        List<Map<String, Object>> data = Lists.newArrayList();
        List<Office> list = officeService.findList(true);
        for(Office office: list){
            Map<String,Object> map = Maps.newHashMap();
            map.put("id", office.getId());
            map.put("pId", office.getParentId());
            map.put("name", office.getName());
            map.put("type", "部门");
            data.add(map);
            List<User> userList = systemService.findUserByOfficeId(office.getId());
            for (User user : userList){
                Map<String, Object> map2 = Maps.newHashMap();
                map2.put("id", IdGen.uuid());
                map2.put("pId", office.getId());
                map2.put("name",user.getName());
                map2.put("type", "人员");
                data.add(map2);
            }
        }
        status.setSuccess("true");
        status.setStatusMessage("ok");
        status.setData(data);
        return this.renderString(response,status);
    }
}
