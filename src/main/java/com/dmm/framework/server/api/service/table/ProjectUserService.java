package com.dmm.framework.server.api.service.table;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmm.framework.server.api.framework.base.BaseService;
import com.dmm.framework.server.api.framework.base.IBaseDao;
import com.dmm.framework.server.api.inter.dao.IProjectUserDao;
import com.dmm.framework.server.api.inter.service.table.IProjectUserService;
import com.dmm.framework.server.api.model.ProjectUser;

@Service
public class ProjectUserService extends BaseService<ProjectUser>
		implements IProjectUserService{
	@Resource(name="projectUserDao")
	IProjectUserDao projectUserDao;

	@Resource(name="projectUserDao")
	public void setDao(IBaseDao<ProjectUser> projectUserDao) {
		super.setDao(projectUserDao);
	}
	
	@Override
	@Transactional
	public ProjectUser get(String id){
		ProjectUser model = projectUserDao.get(id);
		if(model == null)
			 return new ProjectUser();
		return model;
	}
	
}
