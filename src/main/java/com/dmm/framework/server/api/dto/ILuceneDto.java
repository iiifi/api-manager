package com.dmm.framework.server.api.dto;

import com.dmm.framework.server.api.inter.service.tool.ICacheService;

public interface ILuceneDto {
	public SearchDto toSearchDto(ICacheService cacheService);
}
