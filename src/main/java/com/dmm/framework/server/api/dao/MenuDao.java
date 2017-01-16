package com.dmm.framework.server.api.dao;

import org.springframework.stereotype.Repository;

import com.dmm.framework.server.api.framework.base.BaseDao;
import com.dmm.framework.server.api.inter.dao.IMenuDao;
import com.dmm.framework.server.api.model.Menu;

@Repository("menuDao")
public class MenuDao extends BaseDao<Menu>
		implements IMenuDao {

}