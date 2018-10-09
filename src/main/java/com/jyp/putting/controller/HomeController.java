package com.jyp.putting.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyp.putting.domain.FieldItem;
import com.jyp.putting.domain.Player;
import com.jyp.putting.domain.PlayerList;
import com.jyp.putting.service.ItemService;
import com.jyp.putting.utils.MQTTMonitor;

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

	/** MQTT클래스의 클라이언트 객체 */
	private static MQTTMonitor mqttclient = null;

	/** MQTT 클라이언트 객체 리턴 */
	public static MQTTMonitor getMqttclient() {
		return mqttclient;
	}

	/** 초기화 함수 */
	/*-
	 * 
	 * [MQTT를 임시로 시작하지 않기위해 static{}를 막음]
	 * 
	 */
	/*-
	static {
		// MQTT Fatal 감시 데몬 시작
		Thread mqttfatalth = new Thread(new MQTTFatalCheckDaemonThread());
		mqttfatalth.start();
	
		// MQTT Broker 서버연결
		mqttclient = new MQTTMonitor();
	}
	*/

	/**
	 * MQTT에 Exception 발생시 재시작
	 */
	public static void restartMQTT() {
		logger.info("restartMQTT()");
		mqttclient.MQTTdisconnect();
		mqttclient = null;
		mqttclient = new MQTTMonitor();
		if (mqttclient.MQTTisconnected() != true) {
			MQTTMonitor.setRequestRestartMQTT(true);
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

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
		session.setAttribute("playerInfo", player);
		return "redirect:mainmenu";

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

		FieldItem myitems;
		myitems = itemService.queryFieldItems_W_MapId(Integer.parseInt(mapid));
		if (myitems != null) {
			return myitems.getHeightdata();
		}
		return "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,DUMMY";
	}

}

/**
 * MQTT클래스에서 Fatal에러가 발생하면 재시작 할수 있도록 스레드 띄움
 */
class MQTTFatalCheckDaemonThread implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(MQTTFatalCheckDaemonThread.class);

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1500); // every 1.5 sec

				if (MQTTMonitor.isRequestRestartMQTT() == true) {
					logger.info("==================================");
					logger.info("MQTT Fatal flag is setted. Restart");
					logger.info("==================================");
					MQTTMonitor.setRequestRestartMQTT(false);
					HomeController.restartMQTT();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		} // while
	}

}
