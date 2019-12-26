//package com.tangdao.system.web;
//
//
//import javax.servlet.http.HttpServletRequest;
//
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
//import com.tangdao.system.model.domain.DictType;
//import com.tangdao.system.service.IDictTypeService;
//
///**
// * <p>
// * 字典类型表 前端控制器
// * </p>
// *
// * @author ruyang
// * @since 2019-07-02
// */
//@Controller
//@RequestMapping("${tangdao.context-path}/sys/dictType")
//public class DictTypeController extends BaseController {
//
//	@Autowired
//	IDictTypeService dictTypeService;
//	
//	@ModelAttribute
//	public DictType get(String id, boolean isNewRecord) {
//		return dictTypeService.get(id, isNewRecord);
//	}
//	
//	@RequestMapping(value = "list")
//	public String list(DictType dictType, Model model){
//		model.addAttribute("dictType", dictType);
//		return "modules/sys/dictTypeList";
//	}
//	
//	
//	@RequestMapping(value = "listData")
//	public @ResponseBody IPage<DictType> listData(DictType dictType){
//		QueryWrapper<DictType> queryWrapper = new QueryWrapper<DictType>();
//		
//		if(StringUtils.isNotBlank(dictType.getDictName())) {
//			queryWrapper.like("dict_name", dictType.getDictName());
//		}
//		if(StringUtils.isNotBlank(dictType.getDictType())) {
//			queryWrapper.like("dict_type", dictType.getDictType());
//		}
//		
//		return dictTypeService.page(dictType.getPage(), queryWrapper);
//	}
//	
//	@RequestMapping(value = "form")
//	public String form(DictType dictType, Model model){
//		model.addAttribute("dictType", dictType);
//		return "modules/sys/dictTypeForm";
//	}
//	
//	@PostMapping(value = "save")
//	public @ResponseBody String save(@Validated DictType dictType, HttpServletRequest request){
//		dictTypeService.saveOrUpdate(dictType);
//		return this.renderResult(Global.TRUE, "保存成功");
//	}
//	
//	@PostMapping(value = "delete")
//	public @ResponseBody String delete(DictType dictType){
//		dictTypeService.delete(dictType);
//		return this.renderResult(Global.TRUE, "删除成功");
//	}
//	
//	@PostMapping(value = "disable")
//	public @ResponseBody String disable(DictType dictType){
//		dictType.setStatus(DictType.STATUS_DISABLE);
//		dictTypeService.updateById(dictType);
//		return this.renderResult(Global.TRUE, "停用成功");
//	}
//	
//	@PostMapping(value = "enable")
//	public @ResponseBody String enable(DictType dictType){
//		dictType.setStatus(DictType.STATUS_NORMAL);
//		dictTypeService.updateById(dictType);
//		return this.renderResult(Global.TRUE, "启用成功");
//	}
//}
