/**
 * 
 */
package com.basic.framework.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author YeRunhua
 *
 */
@Controller
@RequestMapping(value = "/error", method = RequestMethod.GET)
public class ErrorController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping(value = "/403")
	public ModelAndView error403(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.error("403");
		ModelAndView page = new ModelAndView("/error/403");
		return page;
	}
	
}