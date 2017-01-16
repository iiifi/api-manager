package com.dmm.framework.server.api.dao;

import org.springframework.stereotype.Repository;

import com.dmm.framework.server.api.framework.base.BaseDao;
import com.dmm.framework.server.api.inter.dao.IProjectDao;
import com.dmm.framework.server.api.model.Project;

@Repository("projectDao")
public class ProjectDao extends BaseDao<Project>
		implements IProjectDao {

}