package com.dmm.framework.server.api.dao;

import org.springframework.stereotype.Repository;

import com.dmm.framework.server.api.framework.base.BaseDao;
import com.dmm.framework.server.api.inter.dao.IArticleDao;
import com.dmm.framework.server.api.model.Article;

@Repository("articleDao")
public class ArticleDao extends BaseDao<Article> implements IArticleDao {
}