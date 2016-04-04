package com.du.order.dist.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.du.order.dist.model.util.LoginForm;
import com.du.order.dist.service.ServiceProvider;

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
	
	@RequestMapping(value = "/teslimat", method = RequestMethod.GET)
	  public String teslimat() {
	    return "teslimat";
	  }
	@RequestMapping(value = "/popupBarcode", method = RequestMethod.GET)
	  public String popupBarcode() {
	    return "popupBarcode";
	  }
	
	@RequestMapping(value = "/popupStatusUpdate", method = RequestMethod.GET)
	  public String popupStatusUpdate() {
	    return "popupStatusUpdate";
	  }
	
	@RequestMapping(value = "/popupSiparisDetayi", method = RequestMethod.GET)
	  public String popupSiparisDetayi() {
	    return "popupSiparisDetayi";
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
	@RequestMapping(value = "/login", method = RequestMethod.POST)
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
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String loginFailed(HttpServletRequest request,HttpServletResponse response) {
		logger.info("User logging out: "+ServiceProvider.getCurrentUserName() );
		  request.getSession().removeAttribute("LOGGEDIN_USER");
		  ServiceProvider.setCurrentUserName(null);
		  return "redirect:/";
	}
}
