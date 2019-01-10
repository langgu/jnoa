/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import com.thinkgem.jeesite.modules.oa.entity.OaTask;
import com.thinkgem.jeesite.common.utils.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.OaTaskRecord;
import com.thinkgem.jeesite.modules.oa.dao.OaTaskRecordDao;
import com.thinkgem.jeesite.modules.oa.entity.OaTaskReply;
import com.thinkgem.jeesite.modules.oa.dao.OaTaskReplyDao;

/**
 * 人员任务回复详情Service
 * @author ctt
 * @version 2019-01-10
 */
@Service
@Transactional(readOnly = true)
public class OaTaskRecordService extends CrudService<OaTaskRecordDao, OaTaskRecord> {

	@Autowired
	private OaTaskReplyDao oaTaskReplyDao;

	@Autowired
	private OaTaskRecordDao oaTaskRecordDao;

	@Autowired
	private OaTaskService oaTaskService;

	public OaTaskRecord get(String id) {
		OaTaskRecord oaTaskRecord = super.get(id);
		oaTaskRecord.setOaTaskReplyList(oaTaskReplyDao.findList(new OaTaskReply(oaTaskRecord)));
		return oaTaskRecord;
	}

	public List<OaTaskRecord> findList(OaTaskRecord oaTaskRecord) {
		return super.findList(oaTaskRecord);
	}

	public Page<OaTaskRecord> findPage(Page<OaTaskRecord> page, OaTaskRecord oaTaskRecord) {
		return super.findPage(page, oaTaskRecord);
	}

	@Transactional(readOnly = false)
	public void save(OaTaskRecord oaTaskRecord) {
		/**
		 * ctt
		 * 在字表数据保存时，将部分主表的数据取过来进行保存更新
		 * 字段包含
		 * 发送人  ---  创建者
		 * 任务标题---任务标题
		 * 任务内容---任务内容
		 *发送时间---发送时间
		 */
		super.save(oaTaskRecord);
		for (OaTaskReply oaTaskReply : oaTaskRecord.getOaTaskReplyList()){
			if (oaTaskReply.getId() == null){
				continue;
			}
			if (OaTaskReply.DEL_FLAG_NORMAL.equals(oaTaskReply.getDelFlag())){
				if (StringUtils.isBlank(oaTaskReply.getId())){
					oaTaskReply.setOaTask(oaTaskRecord);
					oaTaskReply.preInsert();
					oaTaskReplyDao.insert(oaTaskReply);
				}else{
					oaTaskReply.preUpdate();
					oaTaskReplyDao.update(oaTaskReply);
				}
			}else{
				oaTaskReplyDao.delete(oaTaskReply);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(OaTaskRecord oaTaskRecord) {
		super.delete(oaTaskRecord);
		oaTaskReplyDao.delete(new OaTaskReply(oaTaskRecord));
	}

	/**
	* @Description:    新增app端口的任务回复记录
	* @Author:         wfp
	* @CreateDate:     2019/1/10 20:48
	*/
	public void saveByInfcReply(OaTaskReply oaTaskReply) {
		oaTaskReply.setId(IdGen.uuid());
		oaTaskReplyDao.insert(oaTaskReply);
	}
}