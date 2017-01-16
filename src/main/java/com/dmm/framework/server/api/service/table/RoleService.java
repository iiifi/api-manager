package com.dmm.framework.server.api.service.table;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmm.framework.server.api.framework.base.BaseService;
import com.dmm.framework.server.api.framework.base.IBaseDao;
import com.dmm.framework.server.api.inter.dao.IRoleDao;
import com.dmm.framework.server.api.inter.service.table.IRoleService;
import com.dmm.framework.server.api.model.Role;

@Service
public class RoleService extends BaseService<Role>
		implements IRoleService {
	
	@Autowired
	private IRoleDao roleDao;
	
	@Resource(name="roleDao")
	public void setDao(IBaseDao<Role> dao) {
		super.setDao(dao);
	}
	
	
	@Override
	@Transactional
	public Role get(String id){
		Role model = roleDao.get(id);
		if(model == null)
			 return new Role();
		return model;
	}
}
