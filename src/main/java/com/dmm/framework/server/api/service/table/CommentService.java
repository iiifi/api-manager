package com.dmm.framework.server.api.service.table;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmm.framework.server.api.framework.base.BaseService;
import com.dmm.framework.server.api.framework.base.IBaseDao;
import com.dmm.framework.server.api.inter.dao.ICommentDao;
import com.dmm.framework.server.api.inter.service.table.ICommentService;
import com.dmm.framework.server.api.model.Comment;

@Service
public class CommentService extends BaseService<Comment>
		implements ICommentService {
	@Resource(name="commentDao")
	ICommentDao commentDao;
	
	@Resource(name="commentDao")
	public void setDao(IBaseDao<Comment> dao) {
		super.setDao(dao);
	}
	
	@Override
	@Transactional
	public Comment get(String id){
		Comment model = commentDao.get(id);
		if(model == null)
			 return new Comment();
		return model;
	}
}
