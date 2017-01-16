package com.dmm.framework.server.api.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dmm.framework.server.api.framework.JsonResult;
import com.dmm.framework.server.api.framework.MyException;
import com.dmm.framework.server.api.framework.auth.AuthPassport;
import com.dmm.framework.server.api.framework.base.BaseController;
import com.dmm.framework.server.api.inter.service.table.IArticleService;
import com.dmm.framework.server.api.inter.service.table.ICommentService;
import com.dmm.framework.server.api.model.Comment;
import com.dmm.framework.server.api.utils.Const;
import com.dmm.framework.server.api.utils.DateFormartUtil;
import com.dmm.framework.server.api.utils.Page;
import com.dmm.framework.server.api.utils.Tools;

@Controller
@RequestMapping("/user/comment")
public class CommentController extends BaseController<Comment> {
	@Autowired
	private ICommentService commentService;
	@Autowired
	private IArticleService articleService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	@AuthPassport
	public JsonResult list(@ModelAttribute Comment comment, @RequestParam(defaultValue = "1") Integer currentPage) throws MyException {
		
		hasPermission( cacheService.getProject(  articleService.get(  comment.getArticleId()  ).getProjectId() ), view);
		Page page= new Page(15);
		page.setCurrentPage(currentPage);
		return new JsonResult(1, commentService.findByMap(Tools.getMap("articleId", comment.getArticleId()), page, " createTime desc"), page);
	}

	@RequestMapping("/detail.do")
	@ResponseBody
	@AuthPassport
	public JsonResult detail(@ModelAttribute Comment comment) throws MyException {
		Comment model;
		if (!comment.getId().equals(Const.NULL_ID)) {
			model = commentService.get(comment.getId());
			hasPermission( cacheService.getProject(  articleService.get( model.getArticleId() ).getProjectId() ), view);
		} else {
			model = new Comment();
		}
		return new JsonResult(1, model);
	}
	
	@RequestMapping("/addOrUpdate.do")
	@ResponseBody
	@AuthPassport
	public JsonResult addOrUpdate(@ModelAttribute Comment comment) throws MyException {
		hasPermission( cacheService.getProject(  articleService.get(  comment.getArticleId()  ).getProjectId() ) , modArticle);
		comment.setUpdateTime(DateFormartUtil.getDateByFormat(DateFormartUtil.YYYY_MM_DD_HH_mm_ss));
		commentService.update(comment);
		return new JsonResult(1, null);
	}

	@RequestMapping("/delete.do")
	@ResponseBody
	@AuthPassport
	public JsonResult delete(@ModelAttribute Comment comment) throws MyException {
		comment = commentService.get(comment.getId());
		hasPermission( cacheService.getProject(  articleService.get(  comment.getArticleId()  ).getProjectId() ), delArticle);
		comment = commentService.get(comment.getId());
		commentService.delete(comment);
		return new JsonResult(1, null);
	}
}
