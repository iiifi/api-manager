package com.dmm.framework.server.api.inter.service.tool;

import java.util.List;

import com.dmm.framework.server.api.model.Module;
import com.dmm.framework.server.api.model.Project;
import com.dmm.framework.server.api.model.Setting;
import com.dmm.framework.server.api.model.User;

public interface ICacheService{

	boolean setStr(String key, String value, int expireTime);
	String getStr(String key);
	boolean delObj(String key);

	Setting getSetting(String key);

	List<Setting> getSetting();

	String getModuleName(String moduleId);

	Module getModule(String moduleId);

	boolean delObj(String key, String field);

	Object getObj(String key);

	Object setObj(String key, Object value, int expireTime);
	Object setObj(String key, String field, Object value, int expireTime);
	void delStr(String string);
	
	boolean flushDB();
	Object getObj(String string, String string2);
	Project getProject(String projectId);
	User getUser(String userId);
}
