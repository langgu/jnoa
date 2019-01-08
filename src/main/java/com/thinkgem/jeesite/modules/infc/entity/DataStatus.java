package com.thinkgem.jeesite.modules.infc.entity;

import com.google.common.collect.Lists;

import java.util.List;

public class DataStatus {

    private String success;        //是否成功
    private String statusMessage;  //状态信息
    private List<Object> data = Lists.newArrayList();	;            //数据集

    public DataStatus(){
        this.success = "false";
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
