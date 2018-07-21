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

}
