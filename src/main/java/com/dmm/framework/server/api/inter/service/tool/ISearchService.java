package com.dmm.framework.server.api.inter.service.tool;

import java.io.IOException;
import java.util.List;

import com.dmm.framework.server.api.dto.SearchDto;
import com.dmm.framework.server.api.utils.Page;

public interface ISearchService{

	/**
	 * 根据关键词、分页信息查询
	 * @param keyword
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	List<SearchDto> search(String keyword, Page page) throws Exception;

	/**
	 * 删除索引文件
	 * @param searchDto
	 * @return
	 * @throws IOException 
	 */
	boolean delete(SearchDto searchDto) throws IOException;

	/**
	 * 添加索引文件, 更新索引文件
	 * @param searchDto
	 * @return
	 * @throws IOException 
	 */
	boolean add(SearchDto searchDto) throws IOException;

	boolean update(SearchDto searchDto) throws IOException;

	/**
	 * 重建索引
	 * @return
	 */
	boolean rebuild();

}
