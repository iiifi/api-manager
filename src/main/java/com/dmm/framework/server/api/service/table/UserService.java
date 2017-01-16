package com.dmm.framework.server.api.service.table;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmm.framework.server.api.dto.LoginDto;
import com.dmm.framework.server.api.dto.LoginInfoDto;
import com.dmm.framework.server.api.framework.base.BaseService;
import com.dmm.framework.server.api.framework.base.IBaseDao;
import com.dmm.framework.server.api.inter.dao.IUserDao;
import com.dmm.framework.server.api.inter.service.table.IProjectService;
import com.dmm.framework.server.api.inter.service.table.IProjectUserService;
import com.dmm.framework.server.api.inter.service.table.IRoleService;
import com.dmm.framework.server.api.inter.service.table.IUserService;
import com.dmm.framework.server.api.inter.service.tool.ICacheService;
import com.dmm.framework.server.api.model.User;
import com.dmm.framework.server.api.springbeans.Config;
import com.dmm.framework.server.api.utils.Aes;
import com.dmm.framework.server.api.utils.Const;
import com.dmm.framework.server.api.utils.MyCookie;

@Service
public class UserService extends BaseService<User>
		implements IUserService {
	@Autowired
	private ICacheService cacheService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private Config config;
	@Autowired
	private IProjectService projectService;
	@Autowired
	private IProjectUserService projectUserService;
	
	@Resource(name="userDao")
	IUserDao userDao;
	
	@Resource(name="userDao")
	public void setDao(IBaseDao<User> dao) {
		super.setDao(dao);
	}

	@Override
	@Transactional
	public User get(String id){
		User model = userDao.get(id);
		if(model == null)
			 return new User();
		return model;
	}
	
	@Override
	public void login(LoginDto model, User user, HttpServletRequest request, HttpServletResponse response) {
		String token  = Aes.encrypt(user.getId());
		MyCookie.addCookie(Const.COOKIE_TOKEN, token, response);
		// 将用户信息存入缓存
		cacheService.setObj(Const.CACHE_USER + user.getId(), new LoginInfoDto(user, roleService, projectService, projectUserService), config.getLoginInforTime());
		MyCookie.addCookie(Const.COOKIE_USERID, user.getId(), response);
		MyCookie.addCookie(Const.COOKIE_USERNAME, model.getUserName(), response);
		MyCookie.addCookie(Const.COOKIE_REMBER_PWD, model.getRemberPwd() , response);
		
		if (model.getRemberPwd().equals("YES")) {
			MyCookie.addCookie(Const.COOKIE_PASSWORD, model.getPassword(), true, response);
		} else {
			MyCookie.deleteCookie(Const.COOKIE_PASSWORD, request, response);
		}
		model.setSessionAdminName(model.getUserName());
	}
}
