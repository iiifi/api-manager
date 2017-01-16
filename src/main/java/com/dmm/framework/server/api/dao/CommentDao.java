package com.dmm.framework.server.api.dao;

import org.springframework.stereotype.Repository;

import com.dmm.framework.server.api.framework.base.BaseDao;
import com.dmm.framework.server.api.inter.dao.ICommentDao;
import com.dmm.framework.server.api.model.Comment;

@Repository("commentDao")
public class CommentDao extends BaseDao<Comment> implements ICommentDao {

}