package com.things.web.controller.common;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.things.common.log.Logger;
import com.things.common.log.LoggerFactory;
import com.things.common.verificationcode.VerificationCode;
import com.things.frame.core.exception.ControllerException;
import com.things.frame.core.status.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 验证码
 */
@Controller
@RequestMapping("/VerificationCode")
public class VerificationCodeController {

	private static final Logger log = LoggerFactory.getLogger(VerificationCodeController.class);
	
	@RequestMapping(value = "/img", method = RequestMethod.GET)
	public void img(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false) != null ? request.getSession(false) : request.getSession();
		
		String code = VerificationCode.buildAndWrite(response.getOutputStream());
		if (StringUtils.isEmpty(code)) {
			log.error("生成验证码为空!");
			throw new ControllerException("生成验证码失败!");
		}
		
		setResponseHeaders(response);
		session.setAttribute(Constant.VerificationCodeKey,code.toUpperCase());
	}

	protected void setResponseHeaders(HttpServletResponse response) {
		response.setContentType("image/png");
		response.setHeader("Cache-Control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		long time = System.currentTimeMillis();
		response.setDateHeader("Last-Modified", time);
		response.setDateHeader("Date", time);
		response.setDateHeader("Expires", time);
	}
}
