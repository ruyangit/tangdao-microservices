package com.tangdao.system.controller;
//package com.tangdao.system.web;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.tangdao.common.suports.BaseController;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.tangdao.system.service.ILogService;
//
//
//@Controller
//@RequestMapping(value = "${tangdao.context-path}/sys/log")
//public class LogController extends BaseController {
//
//	@Autowired
//	private ILogService logService;
//	
//	/**
//     * 获取数据
//     */
//    @ModelAttribute
//    public Log get(String id, boolean isNewRecord) {
//        return logService.get(id);
//    }
//    
//	/**
//     * 查询列表
//     */
//    @org.tangdao.common.annotation.Log(ignore = true)
//    @RequestMapping(value = "list")
//    public String list(Log log, Model model) {
//        return "modules/sys/logList";
//    }
//    
//    /**
//     * 查询列表数据
//     */
//    @org.tangdao.common.annotation.Log(ignore = true)
//    @RequestMapping(value = "listData")
//    public @ResponseBody IPage<Log> listData(Log log, HttpServletRequest request, HttpServletResponse response) {
//    	QueryWrapper<Log> queryWrapper = new QueryWrapper<Log>();
//    	queryWrapper.orderByDesc("create_date");
//    	Page<Log> page = new Page<Log>(request, response);
//        return logService.page(page, queryWrapper);
//    }
//    
//    /**
//     * 查看编辑表单
//     */
//    @org.tangdao.common.annotation.Log(ignore = true)
//    @RequestMapping(value = "form")
//    public String form(Log log, Model model) {
//        model.addAttribute("log", log);
//        return "modules/sys/logForm";
//    }
//	
//}
