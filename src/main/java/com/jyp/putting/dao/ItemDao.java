package com.jyp.putting.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jyp.putting.domain.FieldItem;
import com.jyp.putting.domain.Player;
import com.jyp.putting.domain.TableVo;
import com.jyp.shopmanager.domain.RoomReservation;

@Repository // DAO라고 명시 (DAO를 스프링에서 인식시켜줌)
public class ItemDao {

	private static final Logger logger = LoggerFactory.getLogger(ItemDao.class);

	@Inject // 의존관계를 자동으로 연결(JAVA에서 제공) ==@autowired (Spring에서 제공)
	private SqlSession sqlSession;

	/* Test Code */
	public List<FieldItem> queryItems() {
		return sqlSession.selectList("selectItemList");
	}

	/* Test Code */
	public void insertItem(FieldItem vo) {
		sqlSession.insert("insertItem", vo);
	}

	public List<Player> queryPlayerByIDPW(Map<String, String> paramMap) {
		logger.info("queryPlayerByIDPW");
		return sqlSession.selectList("queryPlayerByIDPW", paramMap);
	}

	/* JSon Test */
	public synchronized String insertJSONTestNewItem(Map<String, String> paramMap) {
		// make new pk & query pk
		sqlSession.insert("increasePK_Table1", paramMap);
		// String pk = "VIP" + (sqlSession.selectOne("queryPK_Table1"));
		Long pknum = Long.parseLong((String) sqlSession.selectOne("queryPK_Table1"));
		String pk = String.format("VIP%07d", pknum);
		logger.info("DAO PK :" + pk);

		// insert item
		paramMap.put("id", pk);
		sqlSession.insert("insert_table1", paramMap);
		return pk;
	}

	public List<TableVo> queryJSONTestItemWithId(Map<String, String> paramMap) {
		List<TableVo> arrVo = sqlSession.selectList("querytableWithPK", paramMap);
		logger.info("DAO arrVo.size() :" + arrVo.size());
		return arrVo;
	}

	// 타석예약 조회
	public List<RoomReservation> queryRoomReservations(Map<String, String> paramMap) {
		List<RoomReservation> arrVo = sqlSession.selectList("queryRoomReservations", paramMap);
		logger.info("DAO arrVo.size() :" + arrVo.size());
		/*-
				for (RoomReservation roomReservation : arrVo) {
					logger.info("  - dao rid: " + roomReservation.getReservedSchduleId() + ", room:" + roomReservation.getReservedRoomNumber());
				}
		*/
		return arrVo;
	}

	// 타석예약 추가
	public synchronized String insertRoomReservation(Map<String, String> paramMap) {
		// insert item
		sqlSession.insert("insertRoomReservation", paramMap);
		Long pknum = Long.parseLong((String) sqlSession.selectOne("queryPK_RoomReservations"));
		String pk = String.format("%d", pknum);
		logger.info("DAO PK :" + pk);
		return pk;
	}
}
