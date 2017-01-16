package com.dmm.framework.server.api.inter.service.table;

import com.dmm.framework.server.api.framework.base.IBaseService;
import com.dmm.framework.server.api.model.Log;

public interface ILogService extends IBaseService<Log>{

	void recover(Log log);

}
