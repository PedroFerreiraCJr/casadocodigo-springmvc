package br.com.dotofcodex.casadocodigo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public HomeController() {
		super();
	}

	@RequestMapping("/home")
	public String index() {
		logger.info("new request for index of home controller...");
		return "index";
	}

}
