//package com.tangdao.system.web;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.tangdao.common.config.Global;
//import org.tangdao.common.suports.BaseController;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.tangdao.system.service.IConfigService;
//
///**
// * <p>
// * 参数配置表 前端控制器
// * </p>
// *
// * @author ruyang
// * @since 2019-07-02
// */
//@Controller
//@RequestMapping("${tangdao.context-path}/sys/config")
//public class ConfigController extends BaseController {
//
//	@Autowired
//	private IConfigService configService;
//	
//	@ModelAttribute
//	public Config get(String id, boolean isNewRecord) {
//		return configService.get(id, isNewRecord);
//	}
//	
//	@RequestMapping("/list")
//	public String list(Config config, Model model) {
//		return "modules/sys/configList";
//	}
//
//	@RequestMapping("/listData")
//	public @ResponseBody IPage<Config> listData(Config config) {
//		QueryWrapper<Config> queryWrapper = new QueryWrapper<Config>();
//		if(StringUtils.isNotBlank(config.getStatus())) {
//			queryWrapper.eq("status", config.getStatus());
//		}
//		if(StringUtils.isNotBlank(config.getConfigName())) {
//			queryWrapper.likeRight("config_name", config.getConfigName());
//		}
//		return this.configService.page(config.getPage(), queryWrapper);
//	}
//	
//	@RequestMapping("/form")
//	public String form(Config config, Model model) {
//		model.addAttribute("config", config);
//		return "modules/sys/configForm";
//	}
//	
//	@PostMapping(value = "save")
//	public @ResponseBody String save(@Validated Config config) {
//		configService.saveOrUpdate(config);
//		return renderResult(Global.TRUE, "保存成功");
//	}
//	
//	@PostMapping(value = "delete")
//	public @ResponseBody String delete(Config config) {
//		configService.deleteById(config);
//		return renderResult(Global.TRUE, "删除成功");
//	}
//	
//	@PostMapping(value = "disable")
//	public @ResponseBody Object disable(Config config) {
//		config.setStatus(Config.STATUS_DISABLE);
//		configService.updateById(config);
//		return renderResult(Global.TRUE, "停用成功");
//	}
//
//	@PostMapping(value = "enable")
//	public @ResponseBody Object enable(Config config) {
//		config.setStatus(Config.STATUS_NORMAL);
//		configService.updateById(config);
//		return renderResult(Global.TRUE, "启用成功");
//	}
//}
