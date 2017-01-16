package com.dmm.framework.server.api.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dmm.framework.server.api.framework.base.BaseController;
import com.dmm.framework.server.api.inter.service.table.IInterfaceService;
import com.dmm.framework.server.api.model.Interface;

@Controller
@RequestMapping("/mock")
public class MockController extends BaseController<Interface>{

	@Autowired
	private IInterfaceService interfaceService;
	
	@RequestMapping("/trueExam.do")
	@ResponseBody
	public void trueExam(@RequestParam String id) throws Exception {
		printMsg(interfaceService.get(id).getTrueExam());
	}
	
	@RequestMapping("/falseExam.do")
	@ResponseBody
	public void falseExam(@RequestParam String id) throws Exception {
		printMsg(interfaceService.get(id).getFalseExam());
	}
}
