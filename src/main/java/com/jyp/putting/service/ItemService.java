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
}
