//package com.tangdao.system.web;
//
//import java.util.List;
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
//import com.tangdao.system.model.domain.DictData;
//import com.tangdao.system.service.IDictDataService;
//
///** 
// * @ClassName: DictDataController.java 
// * @Description: TODO(这里用一句话描述这个类的作用) 
// * @author ruyang
// * @date 2018年12月28日 下午3:17:41
// *  
// */
//@Controller
//@RequestMapping(value = "${tangdao.context-path}/sys/dictData")
//public class DictDataController extends BaseController {
//
//	@Autowired
// 	IDictDataService dictDataService;
//	
//	@ModelAttribute
//	public DictData get(String id, boolean isNewRecord) {
//		return dictDataService.get(id, isNewRecord);
//	}
//	
//	@RequestMapping(value = "list")
//	public String list(DictData dictData, Model model){
//		return "modules/sys/dictDataList";
//	}
//	
//	@RequestMapping(value = "listData")
//	public @ResponseBody List<DictData> listData(DictData dictData){
//		QueryWrapper<DictData> queryWrapper = new QueryWrapper<DictData>();
//		
//		if(StringUtils.isNotBlank(dictData.getDictType())) {
//			queryWrapper.eq("dict_type", dictData.getDictType());
//		}
//		queryWrapper.orderByAsc("dict_sort");
//		return dictDataService.select(queryWrapper);
//	}
//	
//	@RequestMapping(value = "form")
//	public String form(DictData dictData, Model model){
//		model.addAttribute("dictData", dictData);
//		return "modules/sys/dictDataForm";
//	}
//	
//	@PostMapping(value = "save")
//	public @ResponseBody String save(@Validated DictData dictData){
//		dictDataService.saveOrUpdate(dictData);
//		return this.renderResult(Global.TRUE, "保存成功");
//	}
//	
//	@PostMapping(value = "delete")
//	public @ResponseBody Object delete(DictData dictData){
//		dictDataService.deleteById(dictData);
//		return this.renderResult(Global.TRUE, "删除成功");
//	}
//	
//	@PostMapping(value = "disable")
//	public @ResponseBody Object disable(DictData dictData){
//		dictData.setStatus(DictData.STATUS_DISABLE);
//		dictDataService.updateById(dictData);
//		return this.renderResult(Global.TRUE, "停用成功");
//	}
//	
//	@PostMapping(value = "enable")
//	public @ResponseBody Object enable(DictData dictData){
//		dictData.setStatus(DictData.STATUS_NORMAL);
//		dictDataService.updateById(dictData);
//		return this.renderResult(Global.TRUE, "启用成功");
//	}
//	
//}
