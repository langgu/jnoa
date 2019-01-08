package com.thinkgem.jeesite.modules.infc.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.infc.entity.DataStatus;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@CrossOrigin
@Controller
@RequestMapping(value = "${adminPath}/infc/infclogin")
public class InfcLoginController extends BaseController {

    @Autowired
    private SystemService systemService;

    @ResponseBody
    @RequestMapping(value = "userLogin",method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        DataStatus status = new DataStatus();
        status.setSuccess("false");
        String username = request.getParameter("username");  //获取传递参数 username
        String password = request.getParameter("password");  //获取传递参数 password
        User user = systemService.getUserByLoginName(username);  //先验证用户是否存在

        if(user==null){
            status.setStatusMessage("账号不存在");
        }
        else{
            if (Global.NO.equals(user.getLoginFlag())){
                status.setStatusMessage("该账号已被禁用");
            }
            if (SystemService.validatePassword(password, user.getPassword())){  //验证密码
                status.setStatusMessage("ok");
                status.setSuccess("true");
            }
            else{
                status.setStatusMessage("密码错误");
            }
        }
//        response.setContentType("text/plain");
//        response.setCharacterEncoding("gb2312");
//        PrintWriter out = new PrintWriter(response.getOutputStream());
//        out.print(JsonMapper.toJsonString(status));
//        out.flush();
        return this.renderString(response,status);
    }
}