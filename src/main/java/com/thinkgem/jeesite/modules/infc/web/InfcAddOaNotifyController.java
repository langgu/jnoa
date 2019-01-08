package com.thinkgem.jeesite.modules.infc.web;

import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.infc.entity.DataStatus;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
* @Description:    新建通知通告接口
* @Author:         wfp
* @CreateDate:     2019/1/9 1:10
*/
@CrossOrigin
@Controller
@RequestMapping(value = "${adminPath}/infc/infcAddOaNotify")
public class InfcAddOaNotifyController extends BaseController {

    @Autowired
    private OaNotifyService oaNotifyService;

    @RequestMapping(value = "addOaNotify",method = RequestMethod.GET)
    public String addOaNotify(HttpServletRequest request, HttpServletResponse response){
        DataStatus status = new DataStatus();
        OaNotify oaNotify = new OaNotify();
        String title = request.getParameter("title");    //标题
        String content = request.getParameter("content");   //内容
        String files = request.getParameter("files");    //附件
        String createBy = request.getParameter("createBy");  //发送人
        String userIds = request.getParameter("userIds");  //接收人
        String urgentFlag = request.getParameter("urgentFlag");
        String forwardFlag = request.getParameter("forwardFlag");
        if(title != null && content != null && createBy != null && userIds!= null){
            User user = UserUtils.get(createBy);
            oaNotify.setTitle(title);
            oaNotify.setContent(content);
            oaNotify.setCreateBy(user);
            oaNotifyService.saveByInfc(oaNotify, userIds);
            status.setStatusMessage("ok");
            status.setSuccess("true");
        }
        else{
            status.setStatusMessage("新增失败");
        }
        return this.renderString(response,status);
    }
}
