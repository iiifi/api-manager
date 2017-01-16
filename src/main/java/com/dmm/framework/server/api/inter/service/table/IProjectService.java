package com.dmm.framework.server.api.inter.service.table;

import java.util.List;

import com.dmm.framework.server.api.framework.base.IBaseService;
import com.dmm.framework.server.api.model.Project;

public interface IProjectService extends IBaseService<Project>{

	List<String> getProjectIdByUid(String userId);

	List<String> getProjectIdByType(int type);
	
}
