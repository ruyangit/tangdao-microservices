//package com.tangdao.system.web;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
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
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.tangdao.system.model.domain.Post;
//import com.tangdao.system.service.IPostService;
//
///**
// * 岗位Controller
// * @author ruyang
// * @version 2019-08-23
// */
//@Controller
//@RequestMapping(value = "${tangdao.context-path}/sys/post")
//public class PostController extends BaseController {
//
//	@Autowired
//	private IPostService postService;
//	
//	/**
//	 * 获取数据
//	 */
//	@ModelAttribute
//	public Post get(String postCode, boolean isNewRecord) {
//		return postService.get(postCode, isNewRecord);
//	}
//	
//	/**
//	 * 查询列表
//	 */
//	@PreAuthorize("hasAuthority('sys:post:view')")
//	@RequestMapping(value = {"list", ""})
//	public String list(Post post, Model model) {
//		model.addAttribute("post", post);
//		return "modules/sys/postList";
//	}
//	
//	/**
//	 * 查询列表数据
//	 */
//	@PreAuthorize("hasAuthority('sys:post:view')")
//	@RequestMapping(value = "listData")
//	public @ResponseBody IPage<Post> listData(Post post, HttpServletRequest request, HttpServletResponse response) {
//		QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>();
//		if (StringUtils.isNotBlank(post.getPostName())) {
//			queryWrapper.like("post_name", post.getPostName());
//		}
//		if (StringUtils.isNotBlank(post.getPostType())) {
//			queryWrapper.eq("post_type", post.getPostType());
//		}
//		if (StringUtils.isNotBlank(post.getStatus())) {
//			queryWrapper.eq("status", post.getStatus());
//		}
//		return postService.page(post.getPage(), queryWrapper);
//	}
//
//	/**
//	 * 查看编辑表单
//	 */
//	@PreAuthorize("hasAuthority('sys:post:view')")
//	@RequestMapping(value = "form")
//	public String form(Post post, Model model) {
//		model.addAttribute("post", post);
//		return "modules/sys/postForm";
//	}
//
//	/**
//	 * 保存岗位
//	 */
//	@PreAuthorize("hasAuthority('sys:post:edit')")
//	@PostMapping(value = "save")
//	public @ResponseBody String save(@Validated Post post) {
//		postService.saveOrUpdate(post);
//		return renderResult(Global.TRUE, "保存成功！");
//	}
//	
//	/**
//	 * 停用岗位
//	 */
//	@PreAuthorize("hasAuthority('sys:post:edit')")
//	@RequestMapping(value = "disable")
//	public @ResponseBody String disable(Post post) {
//		post.setStatus(Post.STATUS_DISABLE);
//		postService.updateById(post);
//		return renderResult(Global.TRUE, "停用成功");
//	}
//	
//	/**
//	 * 启用岗位
//	 */
//	@PreAuthorize("hasAuthority('sys:post:edit')")
//	@RequestMapping(value = "enable")
//	public @ResponseBody String enable(Post post) {
//		post.setStatus(Post.STATUS_NORMAL);
//		postService.updateById(post);
//		return renderResult(Global.TRUE, "启用成功");
//	}
//	
//	/**
//	 * 删除岗位
//	 */
//	@PreAuthorize("hasAuthority('sys:post:edit')")
//	@RequestMapping(value = "delete")
//	public @ResponseBody String delete(Post post) {
//		postService.deleteById(post);
//		return renderResult(Global.TRUE, "删除成功！");
//	}
//	
//}