package com.dmm.framework.server.api.dao;

import org.springframework.stereotype.Repository;

import com.dmm.framework.server.api.framework.base.BaseDao;
import com.dmm.framework.server.api.inter.dao.IInterfaceDao;
import com.dmm.framework.server.api.model.Interface;

@Repository("interfaceDao")
public class InterfaceDao extends BaseDao<Interface>
		implements IInterfaceDao {

}