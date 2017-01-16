package com.dmm.framework.server.api.controller.thirdly;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dmm.framework.server.api.dto.LoginDto;
import com.dmm.framework.server.api.dto.thirdly.GitHubUser;
import com.dmm.framework.server.api.framework.base.BaseController;
import com.dmm.framework.server.api.inter.service.table.IMenuService;
import com.dmm.framework.server.api.inter.service.table.IUserService;
import com.dmm.framework.server.api.inter.service.tool.ICacheService;
import com.dmm.framework.server.api.model.User;
import com.dmm.framework.server.api.service.thirdly.GitHubService;
import com.dmm.framework.server.api.springbeans.Config;
import com.dmm.framework.server.api.utils.Const;
import com.dmm.framework.server.api.utils.MyCookie;
import com.dmm.framework.server.api.utils.MyString;
import com.dmm.framework.server.api.utils.Tools;

/**
 * 前后台共用的Controller
 * @author Ehsan
 *
 */
@Controller
public class GitHubController extends BaseController<User> {
	@Autowired
	IMenuService menuService;
	@Autowired
	private ICacheService cacheService;
	@Autowired
	private Config config;
	@Autowired
	private GitHubService githHubService;
	@Autowired
	private IUserService userService;
	
	
	/**
	 * gitHub授权
	 * @throws Exception
	 */
	@RequestMapping("/github/authorize.do")
	public void authorize() throws Exception {
		String authorizeUrl = "https://github.com/login/oauth/authorize?client_id=%s&state=%s";
		String state = Tools.getChar(20);
		cacheService.setStr( MyCookie.getCookie(Const.COOKIE_TOKEN, false, request) + Const.CACHE_AUTHORIZE, state, 10*60);
		response.sendRedirect(String.format(authorizeUrl, config.getClientID(), state));
	}
	@RequestMapping("/github/login.do")
	public String login(@RequestParam String code,@RequestParam String state) throws Exception {
		String myState = cacheService.getStr(MyCookie.getCookie(Const.COOKIE_TOKEN, false, request) + Const.CACHE_AUTHORIZE);
		if(myState == null || !myState.equals(state)){
			request.setAttribute("result", "非法参数，登陆失败！");
			return "WEB-INF/views/result.jsp";
		}else{
			User user = null;
			GitHubUser gitHubUser = githHubService.getUser(githHubService.getAccessToken(code, "").getAccess_token());
			List<User> users = userService.findByMap(Tools.getMap("thirdlyId",Const.GITHUB + gitHubUser.getId()), null, null);
			if(users.size() == 0){
				user = new User();
				user.setUserName(gitHubUser.getLogin());
				user.setTrueName(gitHubUser.getName());
				if(!MyString.isEmpty(gitHubUser.getEmail()))
					user.setEmail(gitHubUser.getEmail());
				user.setPassword("");
				user.setStatus(Byte.valueOf("1"));
				user.setType(Byte.valueOf("1"));
				user.setAvatarUrl(gitHubUser.getAvatar_url());
				user.setThirdlyId(Const.GITHUB + gitHubUser.getId());
				userService.save(user);
			}else{
				user = users.get(0);
			}
			
			// 登陆
			LoginDto model = new LoginDto();
			model.setUserName(user.getUserName());
			model.setRemberPwd("NO");
			userService.login(model, user, request, response);
			
			response.sendRedirect("../admin.do");
		}
		return "";
	}
}
