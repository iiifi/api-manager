package com.dmm.framework.server.api.service.table;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmm.framework.server.api.framework.base.BaseService;
import com.dmm.framework.server.api.framework.base.IBaseDao;
import com.dmm.framework.server.api.inter.dao.IProjectDao;
import com.dmm.framework.server.api.inter.service.table.IProjectService;
import com.dmm.framework.server.api.model.Project;
import com.dmm.framework.server.api.utils.Page;
import com.dmm.framework.server.api.utils.Tools;

@Service
public class ProjectService extends BaseService<Project>
		implements IProjectService {

	@Autowired
	private IProjectDao projectDao;
	
	@Resource(name="projectDao")
	public void setDao(IBaseDao<Project> dao ) {
		super.setDao(dao);
	}
	
	@Override
	@Transactional
	public Project get(String id){
		Project model = projectDao.get(id);
		if(model == null)
			 return new Project();
		return model;
	}
	
	/**
	 * 根据用户ID查询所有该用户加入的项目、创建的项目
	 */
	@Override
	@Transactional
	public List<String> getProjectIdByUid(String userId) {
		List<String> ids = new ArrayList<String>();
		List<Project> ps =  queryByHql("from Project where userId=:userId or id in (select projectId from ProjectUser where userId=:userId)",
				Tools.getMap("userId", userId), null);
		for(Project p:ps){
			ids.add(p.getId());
		}
		return ids;
	}
	
	@Override
	@Transactional
	public List<String> getProjectIdByType(int type) {
		Page page = new Page();
		List<String> ids = new ArrayList<String>();
		List<Project> ps = findByMap(Tools.getMap("type", type), page, null);
		for(Project p:ps){
			ids.add(p.getId());
		}
		return ids;
	}
	
}
