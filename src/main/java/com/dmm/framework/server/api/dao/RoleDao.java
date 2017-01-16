package com.dmm.framework.server.api.dao;

import org.springframework.stereotype.Repository;

import com.dmm.framework.server.api.framework.base.BaseDao;
import com.dmm.framework.server.api.inter.dao.IRoleDao;
import com.dmm.framework.server.api.model.Role;

@Repository("roleDao")
public class RoleDao extends BaseDao<Role>
		implements IRoleDao {

}