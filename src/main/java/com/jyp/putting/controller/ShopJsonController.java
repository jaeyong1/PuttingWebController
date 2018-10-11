package com.jyp.putting.controller;

import java.util.ArrayList;
import java.util.Locale;

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
import com.jyp.putting.domain.TableVo;
import com.jyp.putting.service.ItemService;
import com.jyp.shopmanager.domain.RoomReservation;
import com.jyp.shopmanager.domain.RoomReservationList;
import com.jyp.shopmanager.domain.SimpleWebRoomReservation;

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

	/**
	 * 타석예약 삭제
	 */
	@RequestMapping(value = "/testver_deleteroomreservation", method = RequestMethod.POST)
	@ResponseBody
	public void testver_deleteroomreservation(@RequestBody RoomReservation roomreserv) {
		logger.info("POST - [TEST ver] Delete Room reservation. pk:" + roomreserv.getReservedSchduleId() + ", cust:"
				+ roomreserv.getCustCode() + ", room:" + roomreserv.getReservedRoomNumber());
		itemService.deleteRoomReservation("SC000002", roomreserv);
		return;
	}

	/**
	 * 타석예약 업데이트
	 */
	@RequestMapping(value = "/testver_updateroomreservation", method = RequestMethod.POST)
	@ResponseBody
	public void testver_updateroomreservation(@RequestBody RoomReservation roomreserv) {
		logger.info("POST - [TEST ver] Update Room reservation. pk:" + roomreserv.getReservedSchduleId() + ", cust:"
				+ roomreserv.getCustCode() + ", room:" + roomreserv.getReservedRoomNumber());
		itemService.updateRoomReservation("SC000002", roomreserv);
		return;
	}

	/**
	 * 타석예약 조회 -웹페이지
	 * 
	 */
	@RequestMapping(value = "/roomstatus", method = RequestMethod.GET)
	public String roomstatus(Locale locale, Model model, HttpServletRequest request, HttpSession session) {
		String shopcode = request.getParameter("shopcode");

		logger.info("Get - View roomstatus. shopcode={}.", shopcode);
		if (shopcode == null) {
			logger.info("Get - fielddata. shopcode is null. > test version. set to shopcode=SC000002");
			shopcode = "SC000002";
		}
		// Get reservation status
		ArrayList<SimpleWebRoomReservation> items = itemService.queryCurrentRoomReservationStatus(shopcode);

		// Return current status list
		System.out.println("created list . #rooms:" + items.size());
		model.addAttribute("items", items);

		return "roomstatus";// .jpp
	}

	@RequestMapping(value = "/shopmanager", method = RequestMethod.GET)
	public String shopmanagerGet(Locale locale, HttpServletRequest request, Model model) {
		logger.info("Get - shopmanager");

		return "shopmanager";
	}
	
	@RequestMapping(value = "/shopmanager2", method = RequestMethod.GET)
	public String shopmanager2Get(Locale locale, HttpServletRequest request, Model model) {
		logger.info("Get - shopmanager");

		return "shopmanager2";
	}
}
