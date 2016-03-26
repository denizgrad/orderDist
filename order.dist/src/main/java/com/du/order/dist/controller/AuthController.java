package com.du.order.dist.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.du.order.dist.model.LoginForm;

@Controller
public class AuthController {
	@Autowired
	Environment env;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	/**
	 * The request mapper for welcome page
	 * @return
	 */
	@RequestMapping(value = "/siparis", method = RequestMethod.GET)
	  public String siparis() {
	    return "siparis";
	  }
	
	/**
	 * Simply selects the login view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showLogin(Model model, LoginForm loginform) {
		logger.info("Login page");
		if (!model.containsAttribute("error")) {
			model.addAttribute("error", false);
		}
		model.addAttribute("loginAttribute", loginform);
		return "login";
	}
	
	/**
	 * The POST method to submit login credentials.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(Model model, LoginForm loginform, Locale locale, HttpServletRequest request) throws Exception {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG,locale);
		String formattedDate = dateFormat.format(date);
		
		String username = loginform.getUsername();
		String password = loginform.getPassword();
	
		
		logger.info("Login attempt for username "+ username+" at: "+formattedDate);
		
		// A simple authentication manager
		if(username != null && password != null){
			
			if( username.equals(env.getProperty("admin.username")) &&	password.equals(env.getProperty("admin.password")) ){
				// Set a session attribute to check authentication then redirect to the welcome uri; 
				request.getSession().setAttribute("LOGGEDIN_USER", loginform);
				return "redirect:/siparis";
			}else{
				return "redirect:/login.failed";
			}
		}else{
			return "redirect:/login.failed";
		}
	}
		
	/**
	 * The login failed controller
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login.failed", method = RequestMethod.GET)
	public String loginFailed(Model model, LoginForm loginForm) {
		logger.debug("Showing the login failed page");
		model.addAttribute("error", true);
		model.addAttribute("loginAttribute", loginForm);
		return "login";
	}
}
