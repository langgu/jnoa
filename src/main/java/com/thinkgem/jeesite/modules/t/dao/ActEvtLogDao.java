/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.t.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.t.entity.ActEvtLog;

/**
 * tDAO接口
 * @author cwb
 * @version 2019-02-08
 */
@MyBatisDao
public interface ActEvtLogDao extends CrudDao<ActEvtLog> {
	
}