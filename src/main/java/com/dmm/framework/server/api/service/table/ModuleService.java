package com.dmm.framework.server.api.service.table;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmm.framework.server.api.dto.PickDto;
import com.dmm.framework.server.api.framework.base.BaseService;
import com.dmm.framework.server.api.framework.base.IBaseDao;
import com.dmm.framework.server.api.inter.dao.IModuleDao;
import com.dmm.framework.server.api.inter.service.table.IModuleService;
import com.dmm.framework.server.api.model.Module;
import com.dmm.framework.server.api.utils.Const;
import com.dmm.framework.server.api.utils.MyString;
import com.dmm.framework.server.api.utils.Page;
import com.dmm.framework.server.api.utils.Tools;

@Service
public class ModuleService extends BaseService<Module>
		implements IModuleService {

	@Autowired
	private IModuleDao dataCenterDao;
	
	@Resource(name="dataCenterDao")
	public void setDao(IBaseDao<Module> dao ) {
		super.setDao(dao);
	}
	
	@Override
	@Transactional
	public Module get(String id){
		Module model = dataCenterDao.get(id);
		if(model == null)
			 return new Module();
		return model;
	}
	
	
	@Override
	@Transactional
	public void getDataCenterPick(List<PickDto> picks,List<String> projectIds, String idPre, String value,String suffix){
		if(MyString.isEmpty(suffix))
			suffix = "";
		PickDto pick = null;
		for (String projectId : projectIds) {
			List<Module> dcs = findByMap(Tools.getMap("projectId", projectId), null, null);
			if(dcs.size()>0){
				pick = new PickDto(Const.SEPARATOR, dcs.get(0).getProjectName());
				picks.add(pick);
			}
			
			for(Module dc : dcs){
				if(MyString.isEmpty(value))
					pick = new PickDto(idPre+dc.getId(), Const.LEVEL_PRE+dc.getName()+suffix);
				else
					pick = new PickDto(idPre+dc.getId(), value.replace("moduleId", dc.getId()).replace("moduleName", dc.getName()).replace("projectId", projectId), Const.LEVEL_PRE+dc.getName()+suffix);
				picks.add(pick);
			}
		}
	}

	@Override
	@Transactional
	public List<String> getList(Byte status, String type, String userId) {
		List<Byte> statuss = null;
		if(status != null){
			statuss= new ArrayList<Byte>();
			statuss.add(status);
		}
		return getListByStatuss(statuss, type, userId);
	}
	
	@Override
	@Transactional
	public List<String> getListByStatuss(List<Byte> statuss, String type, String userId) {
		Page page = new Page();
		page.setSize(2000);// 最多显示钱2000条
		List<String> ids = new ArrayList<String>();
		List<Module> dcs = findByMap(Tools.getMap("status|in", statuss, "type", type, "userId", userId), page, null);
		for(Module dc:dcs){
			ids.add(dc.getId());
		}
		return ids;
	}
	
}
