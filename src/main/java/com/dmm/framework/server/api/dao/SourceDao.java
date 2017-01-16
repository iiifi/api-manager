package com.dmm.framework.server.api.dao;

import org.springframework.stereotype.Repository;

import com.dmm.framework.server.api.framework.base.BaseDao;
import com.dmm.framework.server.api.inter.dao.ISourceDao;
import com.dmm.framework.server.api.model.Source;

@Repository("sourceDao")
public class SourceDao extends BaseDao<Source>
		implements ISourceDao {

}