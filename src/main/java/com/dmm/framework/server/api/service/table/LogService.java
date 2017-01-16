package com.dmm.framework.server.api.service.table;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmm.framework.server.api.framework.base.BaseService;
import com.dmm.framework.server.api.framework.base.IBaseDao;
import com.dmm.framework.server.api.inter.dao.IInterfaceDao;
import com.dmm.framework.server.api.inter.dao.ILogDao;
import com.dmm.framework.server.api.inter.service.table.ILogService;
import com.dmm.framework.server.api.model.Interface;
import com.dmm.framework.server.api.model.Log;

import net.sf.json.JSONObject;

@Service
public class LogService extends BaseService<Log>
		implements ILogService {
	@Autowired
	private IInterfaceDao interfaceDao;
	@Autowired
	private ILogDao logDao;
	
	@Resource(name="logDao")
	public void setDao(IBaseDao<Log> dao) {
		super.setDao(dao);
	}

	@Override
	@Transactional
	public Log get(String id){
		Log model = logDao.get(id);
		if(model == null)
			 return new Log();
		return model;
	}
	
	
	@Override
	@Transactional
	public void recover(Log log){
		log = get(log.getId());
		if(log.getModelClass().equals("Interface")){//恢复接口
			JSONObject json = JSONObject.fromObject(log.getContent());
			Interface inter = (Interface) JSONObject.toBean(json,Interface.class);
			// 删除旧数据，在插入新数据
			Interface oldInter = interfaceDao.get(inter.getId());
			if( oldInter!= null){
				interfaceDao.delete(oldInter);
				interfaceDao.gethibernateTemplate().getSessionFactory().getCurrentSession().flush();
			}
			interfaceDao.save(inter);
		}
	}
}
