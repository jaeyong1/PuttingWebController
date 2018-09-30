package com.jyp.putting.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyp.putting.service.ItemService;
import com.jyp.shopmanager.domain.RoomReservation;
import com.jyp.shopmanager.domain.RoomReservationList;

@Controller
public class ShopJsonController {

	/*
	 * Controller - Service 연결
	 */
	@Autowired
	private ItemService itemService;

	private static final Logger logger = LoggerFactory.getLogger(ShopJsonController.class);

	/**
	 * Input - Nothing(POST)
	 * 
	 * Output - List of Player
	 * 
	 * @return
	 */
	@RequestMapping(value = "/testver_getroomreservation", method = RequestMethod.POST)
	@ResponseBody
	public RoomReservationList test_post() {
		logger.info("POST - [TEST ver] Read Room reservation");
		RoomReservationList objectVO = new RoomReservationList();//JSON리스트 리턴
		
		ArrayList<RoomReservation> list = itemService.queryRoomReservations("SC000002");
		
		objectVO.setList(list);
		objectVO.setSuccess(true);
		objectVO.setTotal_count(list.size());
		return objectVO;
	}

}
