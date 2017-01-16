package com.dmm.framework.server.api.inter.service.table;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dmm.framework.server.api.dto.LoginDto;
import com.dmm.framework.server.api.framework.base.IBaseService;
import com.dmm.framework.server.api.model.User;

public interface IUserService extends IBaseService<User>{

	void login(LoginDto model, User user, HttpServletRequest request, HttpServletResponse response);

}
