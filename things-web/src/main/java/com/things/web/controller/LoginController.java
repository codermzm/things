package com.things.web.controller;

import com.things.frame.core.base.controller.AbstractController;
import com.things.frame.core.base.service.IBaseService;
import com.things.web.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController extends AbstractController<UserEntity> {



	@RequestMapping("/login")
	public ModelAndView index() {
		return new ModelAndView("/Login");
	}

	@Override
	protected IBaseService<UserEntity> getBaseService() {
		return null;
	}
}
