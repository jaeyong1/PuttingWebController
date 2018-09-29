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
import com.jyp.putting.domain.TableVo;
import com.jyp.putting.service.ItemService;

@Controller
public class JsonController {

	/*
	 * Controller - Service 연결
	 */
	@Autowired
	private ItemService itemService;

	private static final Logger logger = LoggerFactory.getLogger(JsonController.class);

	/**
	 * Input - Nothing(POST)
	 * 
	 * Output - List of Player
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getroomreservation", method = RequestMethod.POST)
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

}
