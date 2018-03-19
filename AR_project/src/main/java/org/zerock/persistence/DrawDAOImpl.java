package org.zerock.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.Criteria;
import org.zerock.domain.DrawBoardVO;
import org.zerock.domain.SearchCriteria;
import org.zerock.dto.GpsDTO;

@Repository
public class DrawDAOImpl implements DrawDAO { // MyBatis를 이용해서 처리하는 클래스

	@Inject
	private SqlSession session;

	private static String namespace = "org.zerock.mapper.DrawBoardMapper";

	@Override
	public void create(DrawBoardVO vo) throws Exception {
		session.insert(namespace + ".drawcreate", vo);
	}

	@Override
	public DrawBoardVO read(Integer id_num) throws Exception {
		return session.selectOne(namespace + ".drawread", id_num);
	}

	@Override
	public void update(DrawBoardVO vo) throws Exception {
		session.update(namespace + ".drawupdate", vo);

	}

	@Override
	public void delete(Integer id_num) throws Exception {
		session.delete(namespace + ".drawdelete", id_num);

	}

	@Override
	public List<DrawBoardVO> listAll() throws Exception {
		return session.selectList(namespace + ".drawlistAll");

	}

	@Override
	public List<DrawBoardVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return session.selectList(namespace + ".drawlistPage", page);
	}

	@Override
	public List<DrawBoardVO> listCriteria(Criteria cri) throws Exception {

		return session.selectList(namespace + ".drawlistCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".drawcountPaging", cri);
	}

	@Override
	public List<DrawBoardVO> listSearch(SearchCriteria cri) throws Exception {

		return session.selectList(namespace + ".drawlistSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {

		return session.selectOne(namespace + ".drawlistSearchCount", cri);
	}

	@Override
	public int Count() throws Exception {
		return session.selectOne(namespace + ".drawCount");
	}

	@Override
	public List<GpsDTO> dtoList() throws Exception {
		return session.selectList(namespace + ".DTOlistAll");
	}

}
