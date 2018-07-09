package com.jyp.putting.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jyp.putting.domiain.FieldItem;
import com.jyp.putting.domiain.Player;
import com.jyp.putting.service.ItemService;
import com.jyp.putting.utils.MQTTPublishTest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	/*
	 * Controller - Service 연결
	 */
	@Autowired
	private ItemService itemService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "redirect:login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(Locale locale, Model model, HttpSession session) {
		logger.info("Get - View Fields {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

		session.setAttribute("playerInfo", null);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public synchronized String loginPost(Locale locale, Model model, HttpServletRequest request, HttpSession session) {
		String strID = request.getParameter("user-Id");
		String rpiID = "1";
		logger.info("Post - View Fields {}. {}", locale, strID);

		new MQTTPublishTest(rpiID, strID);
		boolean successlogin = false;
		// Check Login DB <<<<< HARD coding
		successlogin = true;

		if (successlogin == true) {

			Player player = new Player();// DUMMYitemService.findByUserId(reqID);
			// make Player Object -DUMMY
			player.setLocationId(100);
			player.setLocationName("부천지점");
			player.setDeviceId(1);

			session.setAttribute("playerInfo", player);
			return "redirect:mainmenu";// viewfieldsGet(locale, model);
		} else {
			session.setAttribute("playerInfo", null);
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

		return "mainmenu";// .jpp
	}

	@RequestMapping(value = "/lesson", method = RequestMethod.GET)
	public String lessonGet(Locale locale, Model model) {
		/*-
		 * Theme site : https://startbootstrap.com/template-overviews/1-col-portfolio/
		 * from : https://startbootstrap.com/template-categories/all/
		 */
		logger.info("Get - View Mainmenu {}.", locale);

		int pagenum = 1;
		List<FieldItem> items = itemService.queryFieldItems(pagenum);
		model.addAttribute("items", items);

		return "fieldselect_lesson";// .jpp
	}

	@RequestMapping(value = "/fieldselect", method = RequestMethod.GET)
	public String fieldselectGet(Locale locale, Model model, HttpServletRequest request, HttpSession session) {
		// map ID GET파라미터
		String mapid = request.getParameter("mapid");

		// session 확인
		Player player = (Player) request.getSession().getAttribute("playerInfo");
		player.setSelectedMapId(Integer.parseInt(mapid));
		session.setAttribute("playerInfo", player);

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
