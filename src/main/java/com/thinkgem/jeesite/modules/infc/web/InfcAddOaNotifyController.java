package com.thinkgem.jeesite.modules.infc.web;

import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.infc.entity.DataStatus;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStreamReader;
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

    @RequestMapping(value = "addOaNotify",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public String addOaNotify(@RequestBody OaNotify oaNotify, HttpServletRequest request, HttpServletResponse response){
        //后台接收
//        InputStreamReader reader=new InputStreamReader(request.getInputStream(),"UTF-8");
//        char [] buff=new char[1024];
//        int length=0;
//        while((length=reader.read(buff))!=-1){
//            String x=new String(buff,0,length);
//            System.out.println(x);
//        }
        DataStatus status = new DataStatus();
        String title = oaNotify.getTitle();    //标题
        String content = oaNotify.getContent();   //内容
        String files = oaNotify.getFiles();    //附件
        String createBy = oaNotify.getSendUserId();  //发送人
        String userIds = oaNotify.getReceUserIds();  //接收人
        String urgentFlag = oaNotify.getUrgentFlag();   //是否为紧急通知
        if(title != null && content != null && createBy != null && userIds!= null){
            User user = UserUtils.get(createBy);
            oaNotify.setCreateBy(user);
            oaNotify.setCreateDate(new Date());
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
