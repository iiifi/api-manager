package com.dmm.framework.server.api.service.table;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmm.framework.server.api.framework.base.BaseService;
import com.dmm.framework.server.api.framework.base.IBaseDao;
import com.dmm.framework.server.api.inter.dao.IErrorDao;
import com.dmm.framework.server.api.inter.service.table.IErrorService;
import com.dmm.framework.server.api.model.Error;

@Service
public class ErrorService extends BaseService<Error>
		implements IErrorService {
	@Resource(name="errorDao")
	IErrorDao errorDao;
	
	@Resource(name="errorDao")
	public void setDao(IBaseDao<Error> dao) {
		super.setDao(dao);
	}
	
	@Override
	@Transactional
	public Error get(String id){
		Error model = errorDao.get(id);
		if(model == null)
			 return new Error();
		return model;
	}
}
