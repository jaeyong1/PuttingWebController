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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyp.putting.domain.FieldItem;
import com.jyp.putting.domain.Player;
import com.jyp.putting.service.ItemService;
import com.jyp.putting.utils.MQTTMonitor;
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

	static MQTTMonitor smc = new MQTTMonitor();

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private FieldItem myitems;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "redirect:login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(Locale locale, Model model, HttpSession session) {
		logger.info("Get - Login page", locale);
		session.setAttribute("popupclosemsg", ""); // No popup message
		session.setAttribute("playerInfo", null); // No Login info
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public synchronized String loginPost(Locale locale, Model model, HttpServletRequest request, HttpSession session) {
		String strID = request.getParameter("user-Id");
		String strPW = request.getParameter("login-pw");
		if (strID.equals("") && strPW.equals("")) {
			strID = "blank";
			strPW = "blank";
		}
		logger.info("Post - Try to login ID : {}, PW length : {} (bytes)", strID, strPW.length());

		// DB Check
		Player player = itemService.queryPlayerItems(strID, strPW);

		// Login Failed
		if (player == null) {
			logger.info("Post - Try to login ID : {}. Failed", strID);
			session.setAttribute("popupclosemsg", "로그인실패");
			return "redirect:login";
		}
		session.setAttribute("popupclosemsg", "");
		// Login Success
		new MQTTPublishTest("1", strID);
		session.setAttribute("playerInfo", player);
		return "redirect:mainmenu";// viewfieldsGet(locale, model);

	}

	@RequestMapping(value = "/mainmenu", method = RequestMethod.GET)
	public String viewfieldsGet(Locale locale, HttpServletRequest request, Model model) {
		/*-
		 * Theme site : https://startbootstrap.com/template-overviews/heroic-features/
		 * from : https://startbootstrap.com/template-categories/all/
		 */
		logger.info("Get - View Mainmenu {}.", locale);

		// session 확인
		Player player = (Player) request.getSession().getAttribute("playerInfo");
		if (player == null) {
			logger.info("Get - /fieldselect. Not login session. redirect to login page.");
			return "login";
		}

		return "mainmenu";// .jpp
	}

	@RequestMapping(value = "/lesson", method = RequestMethod.GET)
	public String lessonGet(Locale locale, HttpServletRequest request, Model model) {
		/*-
		 * Theme site : https://startbootstrap.com/template-overviews/1-col-portfolio/
		 * from : https://startbootstrap.com/template-categories/all/
		 */
		logger.info("Get - View Mainmenu {}.", locale);

		// session 확인
		Player player = (Player) request.getSession().getAttribute("playerInfo");
		if (player == null) {
			logger.info("Get - /fieldselect. Not login session. redirect to login page.");
			return "login";
		}
		int pagenum = 1;
		List<FieldItem> items = itemService.queryFieldItems(pagenum);
		model.addAttribute("items", items);

		return "fieldselect_lesson";
	}

	@RequestMapping(value = "/fieldselect", method = RequestMethod.GET)
	public String fieldselectGet(Locale locale, Model model, HttpServletRequest request, HttpSession session) {
		// session 확인
		Player player = (Player) request.getSession().getAttribute("playerInfo");
		if (player == null) {
			logger.info("Get - /fieldselect. Not login session. redirect to login page.");
			return "login";
		}

		// map ID GET파라미터
		String mapid = request.getParameter("mapid");

		player.setSelectedMapId(Integer.parseInt(mapid));
		session.setAttribute("playerInfo", player);

		/*-
		 * Theme site : https://startbootstrap.com/template-overviews/full-slider/
		 * from : https://startbootstrap.com/template-categories/all/
		 */
		logger.info("Get - View Mainmenu {}.", locale);

		return "fieldselect_fullslider";// .jpp
	}

	@RequestMapping(value = "/fielddata", method = RequestMethod.GET)
	@ResponseBody
	public synchronized String fieldDataGet(Locale locale, HttpServletRequest request, Model model) {
		String mapid = request.getParameter("mapid");
		if (mapid == null) {
			logger.info("Get - fielddata. mapid is null !!!!. change to mapid=1");
			mapid = "1";
		} else {
			logger.info("Get - fielddata. Raspberrypi access. mapid={}.", mapid);
		}

		myitems = itemService.queryFieldItems_W_MapId(Integer.parseInt(mapid));
		if (myitems != null) {
			return myitems.getHeightdata();
		}
		return "10,20,30,40,50,60,70,80,90,80,70,60,50,40,30,20,DUMMY";
	}
}
