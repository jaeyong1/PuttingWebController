package com.jyp.putting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jyp.putting.domiain.FieldItem;

@Repository("itemDao")
public class ItemDao {
	/* Test Code */
	public List<FieldItem> queryItems() {
		return null;
		// return sqlSession.selectList("selectPartsItemList"); /* SQL 쿼리 선택, 실행
		// */
	}
}
