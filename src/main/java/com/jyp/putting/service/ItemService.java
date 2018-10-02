package com.jyp.putting.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyp.putting.dao.ItemDao;
import com.jyp.putting.domain.FieldItem;
import com.jyp.putting.domain.Player;
import com.jyp.putting.domain.TableVo;
import com.jyp.shopmanager.domain.RoomReservation;

@Service("itemService")
public class ItemService {

	private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

	@Autowired
	private ItemDao itemDao;

	public List<FieldItem> queryFieldItems(int page) {
		List<FieldItem> itemes1 = itemDao.queryItems();
		System.out.println("DB Length = " + itemes1.size());

		return itemes1;
	}

	public FieldItem queryFieldItems_W_MapId(int mapid) {
		List<FieldItem> itemes = itemDao.queryItems();
		for (FieldItem fieldItem : itemes) {
			if (fieldItem.getId() == mapid) {
				return fieldItem;
			}

		}
		return null;
	}

	public Player queryPlayerItems(String strID, String strPW) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("logingUserId", strID);
		paramMap.put("loginUserPassword", strPW);

		List<Player> items = itemDao.queryPlayerByIDPW(paramMap);

		if (items.size() == 0) {
			// no user DB data
			logger.info("Item Service - queryPlayerItems. Login Failed. ID='{}'", strID);
			return null;
		}

		// Login Success
		logger.info("Item Service - queryPlayerItems. Login Success. ID='{}'", items.get(0).getLoginId());
		return items.get(0);

	}

	public TableVo jsonTestInsertAndReturn(TableVo tv) {
		// insert item
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", "");
		paramMap.put("name", tv.getName());
		String pk = itemDao.insertJSONTestNewItem(paramMap);
		logger.info("Item Service - jsonTestInsertAndReturn. insertJSONTestNewItem. new PK : " + pk);

		// query item
		Map<String, String> paramMap2 = new HashMap<String, String>();
		paramMap2.put("id", pk);
		List<TableVo> rvos = itemDao.queryJSONTestItemWithId(paramMap2);
		if (rvos.size() > 0) {
			logger.info("Item Service - jsonTestInsertAndReturn. queryJSONTestItemWithId. success");
			return rvos.get(0);
		}
		return null;

	}

	// 타석예약 조회
	public ArrayList<RoomReservation> queryRoomReservations(String shopcode) {
		logger.info("Item Service - queryRoomReservations. shopcode='{}'", shopcode);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("shopcode", shopcode);
		List<RoomReservation> items = itemDao.queryRoomReservations(paramMap);

		// Return List(for itemDAO/mySQL) -> ArrayList(for Json)
		ArrayList<RoomReservation> returnArrlist = new ArrayList<RoomReservation>(items);
		return returnArrlist;

	}

	// 타석예약 추가
	public String insertRoomReservation(String shopcode, RoomReservation roomreserv) {
		logger.info("Item Service - insertRoomReservation. shopcode='{}'", shopcode);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("shopcode", shopcode);
		paramMap.put("ReservedSchduleId", roomreserv.getReservedSchduleId() + "");// not_use
		paramMap.put("ReservedRoomNumber", roomreserv.getReservedRoomNumber());
		paramMap.put("ReservedStartTime", roomreserv.getReservedStartTime());
		paramMap.put("ReservedEndTime", roomreserv.getReservedEndTime());
		paramMap.put("CustCode", roomreserv.getCustCode());
		paramMap.put("EmCode", roomreserv.getEmCode());
		paramMap.put("ReservedState", roomreserv.getReservedState());

		String pk = itemDao.insertRoomReservation(paramMap);
		logger.info("Item Service - insertRoomReservation. new PK : " + pk);

		return pk;
	}

	// 타석예약 삭제
	public void deleteRoomReservation(String shopcode, RoomReservation roomreserv) {
		logger.info("Item Service - deleteRoomReservation. shopcode='{}'", shopcode);
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("shopcode", shopcode);
		paramMap.put("ReservedSchduleId", roomreserv.getReservedSchduleId() + "");
		paramMap.put("ReservedRoomNumber", roomreserv.getReservedRoomNumber());// not_use
		paramMap.put("ReservedStartTime", roomreserv.getReservedStartTime());// not_use
		paramMap.put("ReservedEndTime", roomreserv.getReservedEndTime());// not_use
		paramMap.put("CustCode", roomreserv.getCustCode());// not_use
		paramMap.put("EmCode", roomreserv.getEmCode());// not_use
		paramMap.put("ReservedState", roomreserv.getReservedState());// not_use

		itemDao.deleteRoomReservation(paramMap);
		logger.info("Item Service - removeRoomReservation. pk : " + roomreserv.getReservedSchduleId());

		return;
	}

}
