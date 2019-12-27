package com.tangdao.system.web;

import javax.servlet.http.HttpServletResponse;

import com.tangdao.common.web.ServletUtils;

public abstract class BaseController {
	
	protected String renderResult(String result, String message, Object data) {	
		return ServletUtils.renderResult(result, message, data);	
	}
	
	protected String renderResult(String result, String message) {	
		return this.renderResult(result, message, null);	
	}
	
	protected String renderResult(HttpServletResponse response, String result, String message, Object data) {	
		return ServletUtils.renderResult(response, result, message, data);	
	} 
	
	protected String renderResult(String result, StringBuilder message) {	
		return this.renderResult(result, message != null ? message.toString() : "");	
	}	
	
	protected String renderResult(HttpServletResponse response, String result, String message) {	
	      return ServletUtils.renderResult(response, result, message, null);	
	} 
}
