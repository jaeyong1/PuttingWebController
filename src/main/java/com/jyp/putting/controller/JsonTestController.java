package com.jyp.putting.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyp.putting.domain.Player;
import com.jyp.putting.domain.PlayerList;
import com.jyp.putting.domain.TableVo;
import com.jyp.putting.service.ItemService;
import com.jyp.shopmanager.domain.JSONResponse;
import com.jyp.shopmanager.domain.JSONResponseErrorMessages;
import com.jyp.shopmanager.domain.RoomReservation;
import com.jyp.shopmanager.domain.RoomReservationList;

@Controller
public class JsonTestController {

	/*
	 * Controller - Service 연결
	 */
	@Autowired
	private ItemService itemService;

	private static final Logger logger = LoggerFactory.getLogger(JsonTestController.class);

	/**
	 * Input - Nothing(GET)
	 * 
	 * Output - simple json obj
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jsontest", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> test() {
		logger.info("Get - Return with JSON");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("1", "111");
		map.put("2", 222);
		return map;
	}

	/**
	 * Input - Nothing(POST)
	 * 
	 * Output - List of Player
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jsontest", method = RequestMethod.POST)
	@ResponseBody
	public PlayerList test_post() {
		logger.info("POST - List Return with JSON");
		ArrayList<Player> list = new ArrayList<Player>();
		Player vo = null;
		PlayerList objectVO = new PlayerList();
		// 1번째 데이터
		vo = new Player();
		vo.setDeviceId("devId111");
		vo.setSelectedMapId(1);
		list.add(vo);
		// 2번째 데이터
		vo = new Player();
		vo.setDeviceId("devId22");
		vo.setSelectedMapId(2);
		list.add(vo);

		objectVO.setList(list);
		objectVO.setSuccess(true);
		objectVO.setTotal_count(10);
		return objectVO;

	}

	/*-
	 * PostMan 에서..
	 * POST 메뉴 선택하고, Body -> raw -> JSON(application/json)
	 * {"loginId":null,"locationId":null,"locationName":null,"deviceId":"devId111","selectedMapId":1,"heightData":null,"runState":0}
	 */
	/**
	 * Input - Player
	 * 
	 * Output - other Player
	 * 
	 * @return
	 */

	@RequestMapping(value = "/jsontest_playerpost", method = RequestMethod.POST)
	@ResponseBody
	public Player test_put(@RequestBody Player postedPlayer) {
		logger.info("Put - List Return with JSON");
		logger.info("Player.DevId:" + postedPlayer.getDeviceId() + ", Player.MapId:" + postedPlayer.getSelectedMapId());
		Player vo = null;
		// 1번째 데이터
		vo = new Player();
		vo.setDeviceId("devId222");
		vo.setSelectedMapId(2);
		return vo;

	}

	@RequestMapping(value = "/jsontestinput", method = RequestMethod.POST)
	@ResponseBody
	public TableVo test_put1(@RequestBody TableVo postedPlayer) {
		logger.info("Post - insert id with prefix. with JSON");
		logger.info(">> TableVo.id:" + postedPlayer.getId() + ", TableVo.name:" + postedPlayer.getName());

		TableVo reVo = itemService.jsonTestInsertAndReturn(postedPlayer);
		logger.info("<< TableVo.id:" + reVo.getId() + ", TableVo.name:" + reVo.getName());
		return reVo;

	}

	// 토큰을 사용한 로그인
	@RequestMapping(value = "/shopmanager_emlogin", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse shopmanagerLoginPost(Locale locale, HttpServletRequest request, Model model) {
		logger.info("POST - shopmanager_login");
		JSONResponse response = new JSONResponse();
		encryptPassword("aaa");

		response.success(encryptPassword("aaa"));
		return response;
	}

	private static String encryptPassword(String password) {
		/*- ref: https://stackoverflow.com/questions/4895523/java-string-to-sha1 
		 * 
		 */
		String sha1 = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(password.getBytes("UTF-8"));
			sha1 = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sha1;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	@RequestMapping(value = "/shopmanager_readtest_ok", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse shopmanagerTestReadOkPOST(Locale locale, @RequestHeader("x-access-token") String xtoken,
			HttpServletRequest request, Model model) {
		logger.info("POST - shopmanager_readtest_ok - x-access-token:" + xtoken);
		JSONResponse response = new JSONResponse();
		RoomReservation a = new RoomReservation();

		response.success(a);

		return response;
	}

	@RequestMapping(value = "/shopmanager_readtest_fail", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse shopmanagerTestReadFailPOST(Locale locale, @RequestHeader("x-access-token") String xtoken,
			HttpServletRequest request, Model model) {
		logger.info("POST - shopmanager_readtest_ok - x-access-token:" + xtoken);
		JSONResponse response = new JSONResponse();
		response.error(JSONResponseErrorMessages.LoginAgain);
		return response;
	}
}
