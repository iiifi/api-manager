package com.dmm.framework.server.api.dao;

import org.springframework.stereotype.Repository;

import com.dmm.framework.server.api.framework.base.BaseDao;
import com.dmm.framework.server.api.inter.dao.ISettingDao;
import com.dmm.framework.server.api.model.Setting;

@Repository("settingDao")
public class SettingDao extends BaseDao<Setting>
		implements ISettingDao {

}