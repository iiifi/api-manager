package com.dmm.framework.server.api.dao;

import org.springframework.stereotype.Repository;

import com.dmm.framework.server.api.framework.base.BaseDao;
import com.dmm.framework.server.api.inter.dao.IModuleDao;
import com.dmm.framework.server.api.model.Module;

@Repository("dataCenterDao")
public class ModuleDao extends BaseDao<Module>
		implements IModuleDao {

}