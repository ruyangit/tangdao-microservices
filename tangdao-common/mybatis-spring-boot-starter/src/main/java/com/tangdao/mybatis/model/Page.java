package com.tangdao.mybatis.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tangdao.common.lang.ObjectUtils;
import com.tangdao.common.lang.StringUtils;
import com.tangdao.common.web.CookieUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Page() {

	}

	public Page(HttpServletRequest request, HttpServletResponse response, String defaultPageSize) {
		this.setCurrent(1);
		this.setSize(ObjectUtils.toInteger(defaultPageSize));

		String pageNo = request.getParameter("pageNo");
		if (StringUtils.isNumeric(pageNo)) {
			CookieUtils.setCookie(response, "pageNo", pageNo);
			this.setCurrent(Integer.parseInt(pageNo));
		} else {
			if (pageNo != null && StringUtils.isNumeric(pageNo = CookieUtils.getCookie(request, "pageNo"))) {
				this.setCurrent(Integer.parseInt(pageNo));
			}
		}
		String pageSize = request.getParameter("pageSize");
		if (StringUtils.isNumeric(pageSize)) {
			CookieUtils.setCookie(response, "pageSize", pageSize);
			this.setSize(Integer.parseInt(pageSize));
		} else {
			if (pageSize != null && StringUtils.isNumeric(pageSize = CookieUtils.getCookie(request, "pageSize"))) {
				this.setSize(Integer.parseInt(pageSize));
			}
		}
	}

	public Page(HttpServletRequest request, HttpServletResponse response) {
		this(request, response, "20");
	}
}
