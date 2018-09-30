package com.jyp.putting.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyp.putting.domain.TableVo;
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
	 * 타석예약현황 조회
	 */
	@RequestMapping(value = "/testver_getroomreservation", method = RequestMethod.POST)
	@ResponseBody
	public RoomReservationList testver_getroomreservation() {
		logger.info("POST - [TEST ver] Read Room reservation");
		RoomReservationList objectVO = new RoomReservationList();// JSON리스트 리턴

		ArrayList<RoomReservation> list = itemService.queryRoomReservations("SC000002");

		objectVO.setList(list);
		objectVO.setSuccess(true);
		objectVO.setTotal_count(list.size());
		return objectVO;
	}

	/**
	 * 타석예약 추가
	 */
	@RequestMapping(value = "/testver_insertroomreservation", method = RequestMethod.POST)
	@ResponseBody
	public String testver_insertroomreservation(@RequestBody RoomReservation roomreserv) {
		logger.info("POST - [TEST ver] Input Room reservation. cust:" + roomreserv.getCustCode() + ", room:"
				+ roomreserv.getReservedRoomNumber());
		String result = itemService.insertRoomReservation("SC000002", roomreserv);
		return result;
	}

}
