//package com.tangdao.system.web;
//
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
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
//import com.tangdao.system.model.domain.Office;
//import com.tangdao.system.service.IOfficeService;
//
///**
// * 机构Controller
// * @author ruyang
// * @version 2019-08-24
// */
//@Controller
//@RequestMapping(value = "${tangdao.context-path}/sys/office")
//public class OfficeController extends BaseController {
//
//	@Autowired
//	private IOfficeService officeService;
//	
//	/**
//	 * 获取数据
//	 */
//	@ModelAttribute
//	public Office get(String officeCode, boolean isNewRecord) {
//		return officeService.get(officeCode, isNewRecord);
//	}
//	
//	/**
//	 * 机构管理主页面
//	 */
//	@PreAuthorize("hasAuthority('sys:office:view')")
//	@RequestMapping(value = "index")
//	public String index(Model model) {
//		return "modules/sys/officeIndex";
//	}
//	
//	/**
//	 * 查询列表
//	 */
//	@PreAuthorize("hasAuthority('sys:office:view')")
//	@RequestMapping(value = {"list", ""})
//	public String list(Office office, Model model) {
//		model.addAttribute("office", office);
//		return "modules/sys/officeList";
//	}
//	
//	/**
//	 * 查询列表数据
//	 */
//	@PreAuthorize("hasAuthority('sys:office:view')")
//	@RequestMapping(value = "listData")
//	public @ResponseBody List<Office> listData(Office office) {
//		QueryWrapper<Office> queryWrapper = new QueryWrapper<Office>();
//		if (StringUtils.isBlank(office.getParentCode())) {
//			office.setParentCode(Menu.ROOT_CODE);
//		}
//		if (StringUtils.isNotBlank(office.getOfficeName())) {
//			office.setParentCode(null);
//			queryWrapper.like("office_name", office.getOfficeName());
//		}
//		if(StringUtils.isNotBlank(office.getParentCode())){
//			queryWrapper.eq("parent_code", office.getParentCode());
//		}
//		if (StringUtils.isNotBlank(office.getFullName())) {
//			office.setParentCode(null);
//			queryWrapper.like("full_name", office.getFullName());
//		}
//		if (StringUtils.isNotBlank(office.getViewCode())) {
//			queryWrapper.likeRight("view_code", office.getViewCode());
//		}
//		if (StringUtils.isNotBlank(office.getOfficeType())) {
//			queryWrapper.eq("office_type", office.getOfficeType());
//		}
//		if (StringUtils.isNotBlank(office.getStatus())) {
//			queryWrapper.eq("status", office.getStatus());
//		}
//		queryWrapper.orderByAsc("tree_sort", "office_code");
//		return officeService.select(queryWrapper);
//	}
//
//	/**
//	 * 查看编辑表单
//	 */
//	@PreAuthorize("hasAuthority('sys:office:view')")
//	@RequestMapping(value = "form")
//	public String form(Office office, Model model) {
//		if (StringUtils.isNotBlank(office.getParentCode())) {
//			office.setParent(officeService.get(office.getParentCode()));
//		}
//		if (office.getParent() == null) {
//			office.setParent(new Office(Office.ROOT_CODE));
//		}
//		model.addAttribute("office", office);
//		return "modules/sys/officeForm";
//	}
//
//	/**
//	 * 保存机构
//	 */
//	@PreAuthorize("hasAuthority('sys:office:edit')")
//	@PostMapping(value = "save")
//	public @ResponseBody String save(@Validated Office office) {
//		officeService.saveOrUpdate(office);
//		return renderResult(Global.TRUE, "保存成功！");
//	}
//	
//	/**
//	 * 停用机构
//	 */
//	@PreAuthorize("hasAuthority('sys:office:edit')")
//	@RequestMapping(value = "disable")
//	public @ResponseBody String disable(Office office) {
//		QueryWrapper<Office> queryWrapper = new QueryWrapper<Office>();
//		queryWrapper.eq("status",Office.STATUS_NORMAL);
//		queryWrapper.like("office_code","," + office.getOfficeCode() + ",");
//		long count = officeService.count(queryWrapper);
//		if (count > 0) {
//			return renderResult(Global.FALSE, "该机构包含未停用的子机构！");
//		}
//		office.setStatus(Office.STATUS_DISABLE);
//		officeService.updateById(office);
//		return renderResult(Global.TRUE, "停用成功");
//	}
//	
//	/**
//	 * 启用机构
//	 */
//	@PreAuthorize("hasAuthority('sys:office:edit')")
//	@RequestMapping(value = "enable")
//	public @ResponseBody String enable(Office office) {
//		office.setStatus(Office.STATUS_NORMAL);
//		officeService.updateById(office);
//		return renderResult(Global.TRUE, "启用成功");
//	}
//	
//	/**
//	 * 删除机构
//	 */
//	@PreAuthorize("hasAuthority('sys:office:edit')")
//	@RequestMapping(value = "delete")
//	public @ResponseBody String delete(Office office) {
//		officeService.deleteById(office);
//		return renderResult(Global.TRUE, "删除成功！");
//	}
//	
//	/**
//	 * 获取机构树结构数据
//	 * @param excludeCode		排除的ID
//	 * @param parentCode	上级Code
//	 * @param isAll			是否显示所有机构（true：不进行权限过滤）
//	 * @param officeTypes	机构类型（1：省级公司；2：市级公司；3：部门）
//	 * @param companyCode	仅查询公司下的机构
//	 * @param isShowCode	是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
//	 * @param isShowFullName 是否显示全机构名称
//	 * @param isLoadUser	是否加载机构下的用户
//	 * @param postCode		机构下的用户过滤岗位
//	 * @param roleCode		机构下的用户过滤角色
//	 * @return
//	 */
//	@RequestMapping(value = "treeData")
//	public @ResponseBody List<Map<String, Object>> treeData(String excludeCode, String parentCode, Boolean isAll,
//			String officeTypes, String companyCode, String isShowCode, String isShowFullName,
//			Boolean isLoadUser, String postCode, String roleCode, String ctrlPermi) {
//		QueryWrapper<Office> queryWrapper = new QueryWrapper<Office>();
//		if (StringUtils.isNotBlank(excludeCode)) {
//			queryWrapper.ne("office_code", excludeCode);
//			queryWrapper.notLike("parent_codes", excludeCode);
//		}
//		queryWrapper.ne("status", Office.STATUS_DELETE);
//		
//		queryWrapper.orderByAsc("tree_sort");
////		if (!(isAll != null && isAll) || Global.isStrictMode()){
////			officeService.addDataScopeFilter(where, ctrlPermi);
////		}
//		List<Office> sourceList = officeService.select(queryWrapper);
//		List<Map<String, Object>> targetList = ListUtils.newArrayList();
//		Map<String, Object> tempMap = null;
//		for (Office office : sourceList) {
//			// 过滤被排除的编码（包括所有子级）
//			if (StringUtils.isNotEmpty(excludeCode)){
//				if (office.getOfficeCode().equals(excludeCode)){
//					continue;
//				}
//				if (office.getParentCodes().contains("," + excludeCode + ",")){
//					continue;
//				}
//			}
//			// 根据父节点过滤数据
//			if (StringUtils.isNotBlank(parentCode)){
//				if (!office.getOfficeCode().equals(parentCode)){
//					continue;
//				}
//				if (!office.getParentCodes().contains("," + parentCode + ",")){
//					continue;
//				}
//			}
//			// 根据部门类型过滤数据
//			if (StringUtils.isNotBlank(officeTypes)){
//				if (!StringUtils.inString(office.getOfficeType(), officeTypes.split(","))){
//					continue;
//				}
//			}
//		
//			tempMap = MapUtils.newHashMap();
//			tempMap.put("id", office.getOfficeCode());
//			tempMap.put("pId", office.getParentCode());
//			String name = office.getOfficeName();
//			if ("true".equals(isShowFullName) || "1".equals(isShowFullName)){
//				name = office.getFullName();
//			}
//			tempMap.put("name", StringUtils.getTreeNodeName(isShowCode, office.getViewCode(), name));
//			tempMap.put("title", office.getFullName());
//			// 一次性后台加载用户，提高性能(推荐方法)
//			if (isLoadUser != null && isLoadUser) {
//				tempMap.put("isParent", true);
////				List<Map<String, Object>> userList;
////				userList = empUserController.treeData("u_", office.getOfficeCode(), office.getOfficeCode(), 
////						companyCode, postCode, roleCode, isAll, isShowCode, ctrlPermi);
////				targetList.addAll(userList);
//			}
//			targetList.add(tempMap);
//		}
//		return targetList;
//	}
//	
//}