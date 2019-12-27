package com.tangdao.system.controller;
//package com.tangdao.system.web;
//
//import java.io.IOException;
//import java.io.OutputStream;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.tangdao.common.config.Global;
//import org.tangdao.common.image.CaptchaUtils;
//import org.tangdao.modules.sys.utils.ValidCodeUtils;
//
//@Controller
//public class ValidCodeController {
//
//	/**
//	 * 获取图片验证码
//	 * 
//	 * @参数 uuid 必填
//	 * 
//	 * @param request
//	 * @param response
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	@RequestMapping({ "/validCode" })
//	public void validCode(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String validCode = request.getParameter(ValidCodeUtils.VALID_CODE);
//		if (StringUtils.isNotBlank(validCode)) {
//			 boolean result = ValidCodeUtils.validate(request, ValidCodeUtils.VALID_CODE, validCode, false);
//	         response.getOutputStream().print(result ? Global.TRUE : Global.FALSE);
//			return;
//		}
//
//		long ctm = System.currentTimeMillis();
//
//		response.setContentType("image/png");
//		response.setHeader("Cache-Control", "no-cache, no-store");
//		response.setHeader("Pragma", "no-cache");
//		response.setDateHeader("Last-Modified", ctm);
//		response.setDateHeader("Date", ctm);
//		response.setDateHeader("Expires", ctm);
//		String generateCaptcha = CaptchaUtils.generateCaptcha((OutputStream) response.getOutputStream());
//
//        request.getSession().setAttribute(ValidCodeUtils.VALID_CODE, generateCaptcha);
//
//	}
//}
