package com.jyp.putting.dao;

import java.util.List;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import com.jyp.putting.domain.FieldItem; 

import org.springframework.stereotype.Repository;

@Repository // DAO라고 명시 (DAO를 스프링에서 인식시켜줌)
public class ItemDao {
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

}
