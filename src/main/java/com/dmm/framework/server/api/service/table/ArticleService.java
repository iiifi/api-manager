package com.dmm.framework.server.api.service.table;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmm.framework.server.api.framework.base.BaseService;
import com.dmm.framework.server.api.framework.base.IBaseDao;
import com.dmm.framework.server.api.inter.dao.IArticleDao;
import com.dmm.framework.server.api.inter.service.table.IArticleService;
import com.dmm.framework.server.api.inter.service.tool.ILuceneService;
import com.dmm.framework.server.api.model.Article;

@Service
public class ArticleService extends BaseService<Article>
		implements IArticleService,ILuceneService<Article> {
	@Resource(name="articleDao")
	IArticleDao webPageDao;

	@Resource(name="articleDao")
	public void setDao(IBaseDao<Article> dao) {
		super.setDao(dao);
	}
	
	@Override
	@Transactional
	public Article get(String id){
		Article model = dao.get(id);
		if(model == null)
			 return new Article();
		return model;
	}

	@Override
	@Transactional
	public List<Article> getAll() {
		return webPageDao.findByMap(null, null, null);
	}
	
	
	
}
