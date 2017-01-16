package com.dmm.framework.server.api.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dmm.framework.server.api.dto.LoginInfoDto;
import com.dmm.framework.server.api.enumeration.ProjectStatus;
import com.dmm.framework.server.api.framework.JsonResult;
import com.dmm.framework.server.api.framework.MyException;
import com.dmm.framework.server.api.framework.base.BaseController;
import com.dmm.framework.server.api.inter.service.table.IMenuService;
import com.dmm.framework.server.api.inter.service.table.IProjectService;
import com.dmm.framework.server.api.model.Project;
import com.dmm.framework.server.api.utils.MyString;
import com.dmm.framework.server.api.utils.Page;
import com.dmm.framework.server.api.utils.Tools;

@Controller("forntProjectController")
@RequestMapping("/front/project")
public class ProjectController extends BaseController<Project> {
	@Autowired
	IMenuService menuService;
	@Autowired
	private IProjectService projectService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult list(@RequestParam(defaultValue="1") int currentPage, 
			@RequestParam(defaultValue="false") boolean myself, String name) throws MyException{
		
		Page page= new Page(15);
		page.setCurrentPage(currentPage);
		LoginInfoDto user =  Tools.getUser();
		
		if(user != null && myself){
			if(MyString.isEmpty(name)){
				return new JsonResult(1, 
						projectService.queryByHql("select new Project(id, name, type, remark, userId, createTime, cover) from Project where userId=:userId or id in (select projectId from ProjectUser where userId=:userId)", 
								Tools.getMap("userId", user.getId()), page)
						, page);

			}else{
				return new JsonResult(1, 
						projectService.queryByHql("select new Project(id, name, type, remark, userId, createTime, cover) from Project where (userId=:userId or id in (select projectId from ProjectUser where userId=:userId)) and name like :name", 
						Tools.getMap("userId", user.getId(), "name|like", name), page)
						, page);

			}
		}
		// 未登陆用户，查看推荐的项目
		else{
			return new JsonResult(1,
				projectService.findByMap(Tools.getMap("status", ProjectStatus.RECOMMEND.getStatus(), "name|like", name),
						"new Project(id, name, type, remark, userId, createTime, cover)" ,page, null), page);
		}
		
	}
}
