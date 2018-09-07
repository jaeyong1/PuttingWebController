package com.jyp.putting.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyp.putting.domain.Player;
import com.jyp.putting.domain.PlayerList;
import com.jyp.putting.service.ItemService;

@Controller
public class ShopManagerJson {

	/*
	 * Controller - Service 연결
	 */
	@Autowired
	private ItemService itemService;


	private static final Logger logger = LoggerFactory.getLogger(ShopManagerJson.class);
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
	
}
