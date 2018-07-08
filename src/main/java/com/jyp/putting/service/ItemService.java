package com.jyp.putting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyp.putting.dao.ItemDao;
import com.jyp.putting.domiain.FieldItem;

@Service("itemService")
public class ItemService {

	@Autowired
	private ItemDao itemDao;

	public List<FieldItem> queryFieldItems(int page) {
		List<FieldItem> itemes = new ArrayList<FieldItem>();

		itemDao.queryItems();
		
		// query DB.... with page num..
		// TEMP data
		itemes.add(new FieldItem(1, "바닷바람CC", "1홀",
				"난이도 : ★★☆☆☆<br>전라남도 서남쪽 해남 바닷가에 자리잡은 바닷바람CC. 한반도 남쪽의 구릉지대에 있고 해양성 기후로 따듯하기 때문에 부드러운 잔디가 고르게 형성되 있습니다. 특히 1홀은 약간의 바닷모래를 머금은 필드의 끝자락에 위치하므로 조금더 강한 퍼팅이 필요합니다.",
				"10,20,30,10,20"));
		itemes.add(new FieldItem(2, "민둥산CC", "4홀",
				"난이도 : ★★★☆☆<br>중국 상하이 북쪽에 위치한 민둥산CC. 과거에 자연 목초지였으나 축산업이 번성하면서 가축을 키운적도 있었지만 최근 주변환경을 재 정비하고 새롭게 문을 열었습니다. 아직 잔디가 풍성하지 않고 지면이 고르지 않은 부분이 많습니다. 4홀 주변은 단단한 지반에 잔디가 짧아 세심한 퍼팅이 필요합니다.",
				"10,20,30,10,20"));
		itemes.add(new FieldItem(3, "Project Three", "N홀",
				"난이도 : ★★★☆☆<br>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis, temporibus, dolores, at, praesentium ut unde repudiandae voluptatum sit ab debitis suscipit fugiat natus velit excepturi amet commodi deleniti alias possimus!",
				"10,20,30,10,20"));
		itemes.add(new FieldItem(4, "Project Three", "N홀",
				"난이도 : ★★★☆☆<br>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis, temporibus, dolores, at, praesentium ut unde repudiandae voluptatum sit ab debitis suscipit fugiat natus velit excepturi amet commodi deleniti alias possimus!",
				"10,20,30,10,20"));

		return itemes;
	}
}
