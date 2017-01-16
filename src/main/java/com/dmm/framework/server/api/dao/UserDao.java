package com.dmm.framework.server.api.dao;

import org.springframework.stereotype.Repository;

import com.dmm.framework.server.api.framework.base.BaseDao;
import com.dmm.framework.server.api.inter.dao.IUserDao;
import com.dmm.framework.server.api.model.User;

@Repository("userDao")
public class UserDao extends BaseDao<User>
		implements IUserDao {

}