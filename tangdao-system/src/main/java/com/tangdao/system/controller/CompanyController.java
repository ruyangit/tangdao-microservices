package com.tangdao.system.controller;
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
//import com.tangdao.system.model.domain.Company;
//import com.tangdao.system.service.ICompanyService;
//
///**
// * 公司Controller
// * @author ruyang
// * @version 2019-08-28
// */
//@Controller
//@RequestMapping(value = "${tangdao.context-path}/sys/company")
//public class CompanyController extends BaseController {
//
//	@Autowired
//	private ICompanyService companyService;
//	
//	/**
//	 * 获取数据
//	 */
//	@ModelAttribute
//	public Company get(String companyCode, boolean isNewRecord) {
//		return companyService.get(companyCode, isNewRecord);
//	}
//	
//	/**
//	 * 查询列表
//	 */
//	@PreAuthorize("hasAuthority('sys:company:view')")
//	@RequestMapping(value = {"list", ""})
//	public String list(Company company, Model model) {
//		model.addAttribute("company", company);
//		return "modules/sys/companyList";
//	}
//	
//	/**
//	 * 查询列表数据
//	 */
//	@PreAuthorize("hasAuthority('sys:company:view')")
//	@RequestMapping(value = "listData")
//	public @ResponseBody List<Company> listData(Company company) {
//		QueryWrapper<Company> queryWrapper = new QueryWrapper<Company>();
//		if (StringUtils.isBlank(company.getParentCode())) {
//			company.setParentCode(Menu.ROOT_CODE);
//		}
//		if (StringUtils.isNotBlank(company.getCompanyName())) {
//			company.setParentCode(null);
//			queryWrapper.like("company_name", company.getCompanyName());
//		}
//		if (StringUtils.isNotBlank(company.getParentCode())) {
//			queryWrapper.eq("parent_code", company.getParentCode());
//		}
//		if (StringUtils.isNotBlank(company.getViewCode())) {
//			queryWrapper.likeRight("view_code", company.getViewCode());
//		}
//		if (StringUtils.isNotBlank(company.getStatus())) {
//			queryWrapper.eq("status", company.getStatus());
//		}
//		queryWrapper.orderByAsc("tree_sort", "company_code");
//		return companyService.select(queryWrapper);
//	}
//
//	/**
//	 * 查看编辑表单
//	 */
//	@PreAuthorize("hasAuthority('sys:company:view')")
//	@RequestMapping(value = "form")
//	public String form(Company company, Model model) {
//		if (StringUtils.isNotBlank(company.getParentCode())) {
//			company.setParent(companyService.get(company.getParentCode()));
//		}
//		if (company.getParent() == null) {
//			company.setParent(new Company(Company.ROOT_CODE));
//		}
//		model.addAttribute("company", company);
//		return "modules/sys/companyForm";
//	}
//
//	/**
//	 * 保存公司
//	 */
//	@PreAuthorize("hasAuthority('sys:company:edit')")
//	@PostMapping(value = "save")
//	public @ResponseBody String save(@Validated Company company) {
//		companyService.saveOrUpdate(company);
//		return renderResult(Global.TRUE, "保存成功！");
//	}
//	
//	/**
//	 * 停用公司
//	 */
//	@PreAuthorize("hasAuthority('sys:company:edit')")
//	@RequestMapping(value = "disable")
//	public @ResponseBody String disable(Company company) {
//		QueryWrapper<Company> queryWrapper = new QueryWrapper<Company>();
//		queryWrapper.eq("status",Company.STATUS_NORMAL);
//		queryWrapper.like("company_code","," + company.getCompanyCode() + ",");
//		long count = companyService.count(queryWrapper);
//		if (count > 0) {
//			return renderResult(Global.FALSE, "该公司包含未停用的子公司！");
//		}
//		company.setStatus(Company.STATUS_DISABLE);
//		companyService.updateById(company);
//		return renderResult(Global.TRUE, "停用成功");
//	}
//	
//	/**
//	 * 启用公司
//	 */
//	@PreAuthorize("hasAuthority('sys:company:edit')")
//	@RequestMapping(value = "enable")
//	public @ResponseBody String enable(Company company) {
//		company.setStatus(Company.STATUS_NORMAL);
//		companyService.updateById(company);
//		return renderResult(Global.TRUE, "启用成功");
//	}
//	
//	/**
//	 * 删除公司
//	 */
//	@PreAuthorize("hasAuthority('sys:company:edit')")
//	@RequestMapping(value = "delete")
//	public @ResponseBody String delete(Company company) {
//		companyService.deleteById(company);
//		return renderResult(Global.TRUE, "删除成功！");
//	}
//	
//	/**
//	 * 获取树结构数据
//	 * @param excludeCode 排除的Code
//	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
//	 * @return
//	 */
//	@RequestMapping(value = "treeData")
//	public @ResponseBody List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
//		QueryWrapper<Company> queryWrapper = new QueryWrapper<Company>();
//		if (StringUtils.isNotBlank(excludeCode)) {
//			queryWrapper.ne("company_code", excludeCode);
//			queryWrapper.notLike("parent_codes", excludeCode);
//		}
//		queryWrapper.ne("status", Company.STATUS_DELETE);
//		
//		queryWrapper.orderByAsc("tree_sort");
//		List<Company> sourceList = companyService.select(queryWrapper);
//		List<Map<String, Object>> targetList = ListUtils.newArrayList();
//		Map<String, Object> tempMap = null;
//		for (Company company : sourceList) {
//			// 过滤被排除的编码（包括所有子级）
//			if (StringUtils.isNotEmpty(excludeCode)){
//				if (company.getCompanyCode().equals(excludeCode)){
//					continue;
//				}
//				if (company.getParentCodes().contains("," + excludeCode + ",")){
//					continue;
//				}
//			}
//		
//			tempMap = MapUtils.newHashMap();
//			tempMap.put("id", company.getCompanyCode());
//			tempMap.put("pId", company.getParentCode());
//			tempMap.put("name", StringUtils.getTreeNodeName(isShowCode, company.getCompanyCode(), company.getCompanyName()));
//			tempMap.put("title", company.getFullName());
//			targetList.add(tempMap);
//		}
//		return targetList;
//	}
//	
//}