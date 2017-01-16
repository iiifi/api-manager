package com.dmm.framework.server.api.service.tool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmm.framework.server.api.dto.LoginInfoDto;
import com.dmm.framework.server.api.dto.PickDto;
import com.dmm.framework.server.api.enumeration.InterfaceStatus;
import com.dmm.framework.server.api.enumeration.MonitorType;
import com.dmm.framework.server.api.enumeration.RequestMethod;
import com.dmm.framework.server.api.enumeration.TrueOrFalse;
import com.dmm.framework.server.api.framework.MyException;
import com.dmm.framework.server.api.inter.service.table.IArticleService;
import com.dmm.framework.server.api.inter.service.table.IErrorService;
import com.dmm.framework.server.api.inter.service.table.IMenuService;
import com.dmm.framework.server.api.inter.service.table.IModuleService;
import com.dmm.framework.server.api.inter.service.table.IProjectService;
import com.dmm.framework.server.api.inter.service.table.IRoleService;
import com.dmm.framework.server.api.inter.service.table.IUserService;
import com.dmm.framework.server.api.inter.service.tool.ICacheService;
import com.dmm.framework.server.api.inter.service.tool.IPickService;
import com.dmm.framework.server.api.model.Error;
import com.dmm.framework.server.api.utils.Tools;

/**
 * 下拉选着
 * @author Ehsan
 *
 */
@Service("pickService")
public class PickService implements IPickService{
	@Autowired
	IMenuService menuService;
	@Autowired
	private ICacheService cacheService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IProjectService projectService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IModuleService moduleService;
	@Autowired
	private IArticleService articleService;
	@Autowired
	private IErrorService errorService;

	@Override
	public void getPickList(List<PickDto> picks, String code, String key, LoginInfoDto user) throws MyException {
		PickDto pick = null;
		switch (code) {
//			case "RECOMMENDPROJECT": // 推荐的模块
//				for (Project p : projectService.findByMap(Tools.getMap("type", ProjectType.RECOMMEND.getType() ), null, null)) {
//					pick = new PickDto(p.getId(), p.getName());
//					picks.add(pick);
//				}
//				return;
			case "REQUESTMETHOD": // 枚举 请求方式 post get
				for (RequestMethod status : RequestMethod.values()) {
					pick = new PickDto(status.name(), status.getName(), status.getName());
					picks.add(pick);
				}
				return;
				// 枚举 接口状态
			case "INTERFACESTATUS":
				for (InterfaceStatus status : InterfaceStatus.values()) {
					pick = new PickDto(status.getName(), status.name());
					picks.add(pick);
				}
				return;
			case "TRUEORFALSE":// 枚举true or false
				for (TrueOrFalse status : TrueOrFalse.values()) {
					pick = new PickDto(status.getName(), status.name());
					picks.add(pick);
				}
				return;
			case "MONITORTYPE":// 监控类型
				for (MonitorType monitorType : MonitorType.values()) {
					pick = new PickDto(monitorType.name(), monitorType.getValue()+"", monitorType.getName());
					picks.add(pick);
				}
				return;
			case "ERRORCODE":// 错误码
				for (Error error : errorService.findByMap(
						Tools.getMap("moduleId", key), null, "errorCode asc")) {
					pick = new PickDto(error.getErrorCode(), error.getErrorCode() + "--" + error.getErrorMsg());
					picks.add(pick);
				}
				return;
		}
	}

}
