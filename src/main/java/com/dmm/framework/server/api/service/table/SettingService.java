package com.dmm.framework.server.api.service.table;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmm.framework.server.api.framework.base.BaseService;
import com.dmm.framework.server.api.framework.base.IBaseDao;
import com.dmm.framework.server.api.inter.dao.ISettingDao;
import com.dmm.framework.server.api.inter.service.table.ISettingService;
import com.dmm.framework.server.api.model.Setting;

@Service
public class SettingService extends BaseService<Setting>
		implements ISettingService {

	@Autowired
	private ISettingDao settingDao;

	@Resource(name="settingDao")
	public void setDao(IBaseDao<Setting> dao) {
		super.setDao(dao);
	}
	
	@Override
	@Transactional
	public Setting get(String id){
		Setting model = settingDao.get(id);
		if(model == null)
			 return new Setting();
		return model;
	}
}
