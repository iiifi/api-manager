package com.dmm.framework.server.api.inter.service.table;

import java.util.List;
import java.util.Map;

import com.dmm.framework.server.api.dto.MenuDto;
import com.dmm.framework.server.api.dto.PickDto;
import com.dmm.framework.server.api.framework.MyException;
import com.dmm.framework.server.api.framework.base.IBaseService;
import com.dmm.framework.server.api.model.Menu;

public interface IMenuService extends IBaseService<Menu>{

	/**
	 * 
	 * @param picks
	 * @param radio 是否是单选
	 * @param code	需要选着的pick代码 
	 * @param key	pick二级关键字（如类型、父节点等）
	 * @param def	默认值
	 * @param notNull 单选是否可以为空
	 * @return
	 * @throws MyException 
	 */
	String pick(List<PickDto> picks, String radio, String code, String key, String def, String notNull) throws MyException;

	List<MenuDto> getLeftMenu(Map<String, Object> map);

}
