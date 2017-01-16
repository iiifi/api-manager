package com.dmm.framework.server.api.dao;

import org.springframework.stereotype.Repository;

import com.dmm.framework.server.api.framework.base.BaseDao;
import com.dmm.framework.server.api.inter.dao.ILogDao;
import com.dmm.framework.server.api.model.Log;

@Repository("logDao")
public class LogDao extends BaseDao<Log>
		implements ILogDao {

}