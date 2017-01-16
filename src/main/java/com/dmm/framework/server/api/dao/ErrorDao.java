package com.dmm.framework.server.api.dao;

import org.springframework.stereotype.Repository;

import com.dmm.framework.server.api.framework.base.BaseDao;
import com.dmm.framework.server.api.inter.dao.IErrorDao;
import com.dmm.framework.server.api.model.Error;

@Repository("errorDao")
public class ErrorDao extends BaseDao<Error> implements IErrorDao {

}