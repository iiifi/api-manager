package com.dmm.framework.server.api.inter.service.tool;

import java.util.List;

import com.dmm.framework.server.api.dto.ILuceneDto;

public interface ILuceneService<T extends ILuceneDto>{
	public List<T> getAll();
}
