package com.dmm.framework.server.api.dao;

import org.springframework.stereotype.Repository;

import com.dmm.framework.server.api.framework.base.BaseDao;
import com.dmm.framework.server.api.inter.dao.IProjectUserDao;
import com.dmm.framework.server.api.model.ProjectUser;

@Repository("projectUserDao")
public class ProjectUserDao extends BaseDao<ProjectUser> implements IProjectUserDao {
}