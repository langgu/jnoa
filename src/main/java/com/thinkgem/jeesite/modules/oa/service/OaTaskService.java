/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.OaTask;
import com.thinkgem.jeesite.modules.oa.dao.OaTaskDao;
import com.thinkgem.jeesite.modules.oa.entity.OaTaskRecord;
import com.thinkgem.jeesite.modules.oa.dao.OaTaskRecordDao;

/**
 * 任务信息Service
 * @author ctt
 * @version 2019-01-10
 */
@Service
@Transactional(readOnly = true)
public class OaTaskService extends CrudService<OaTaskDao, OaTask> {

	@Autowired
	private OaTaskRecordDao oaTaskRecordDao;
	
	public OaTask get(String id) {
		OaTask oaTask = super.get(id);
		oaTask.setOaTaskRecordList(oaTaskRecordDao.findList(new OaTaskRecord(oaTask)));
		return oaTask;
	}
	
	public List<OaTask> findList(OaTask oaTask) {
		return super.findList(oaTask);
	}
	
	public Page<OaTask> findPage(Page<OaTask> page, OaTask oaTask) {
		return super.findPage(page, oaTask);
	}
	
	@Transactional(readOnly = false)
	public void save(OaTask oaTask) {
		super.save(oaTask);
		for (OaTaskRecord oaTaskRecord : oaTask.getOaTaskRecordList()){
			if (oaTaskRecord.getId() == null){
				continue;
			}
			if (OaTaskRecord.DEL_FLAG_NORMAL.equals(oaTaskRecord.getDelFlag())){
				if (StringUtils.isBlank(oaTaskRecord.getId())){
					oaTaskRecord.setOaTask(oaTask);
					oaTaskRecord.preInsert();
					oaTaskRecordDao.insert(oaTaskRecord);
				}else{
					oaTaskRecord.preUpdate();
					oaTaskRecordDao.update(oaTaskRecord);
				}
			}else{
				oaTaskRecordDao.delete(oaTaskRecord);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(OaTask oaTask) {
		super.delete(oaTask);
		oaTaskRecordDao.delete(new OaTaskRecord(oaTask));
	}
	
}