package com.jyp.putting.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jyp.putting.utils.MQTTPublishTest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(Locale locale, Model model) {
		logger.info("Get - View Fields {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public synchronized String loginPost(Locale locale, Model model, HttpServletRequest request) {
		String strID = request.getParameter("user-Id");
		String rpiID = "1";
		logger.info("Post - View Fields {}. {}", locale, strID);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

		new MQTTPublishTest(rpiID, strID);
		boolean successlogin = false;
		// Check Login DB <<<<< HARD coding
		successlogin = true;

		if (successlogin == true) {
			return "redirect:mainmenu";// viewfieldsGet(locale, model);
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/mainmenu", method = RequestMethod.GET)
	public String viewfieldsGet(Locale locale, Model model) {
		/*-
		 * Theme site : https://startbootstrap.com/template-overviews/heroic-features/
		 * from : https://startbootstrap.com/template-categories/all/
		 */
		logger.info("Get - View Mainmenu {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		return "mainmenu";// .jpp
	}

	@RequestMapping(value = "/lesson", method = RequestMethod.GET)
	public String lessonGet(Locale locale, Model model) {
		/*-
		 * Theme site : https://startbootstrap.com/template-overviews/1-col-portfolio/
		 * from : https://startbootstrap.com/template-categories/all/
		 */
		logger.info("Get - View Mainmenu {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		return "fieldselect_lesson";// .jpp
	}

	@RequestMapping(value = "/fieldselect", method = RequestMethod.GET)
	public String fieldselectGet(Locale locale, Model model) {
		/*-
		 * Theme site : https://startbootstrap.com/template-overviews/full-slider/
		 * from : https://startbootstrap.com/template-categories/all/
		 */
		logger.info("Get - View Mainmenu {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		return "fieldselect_fullslider";// .jpp
	}

}
