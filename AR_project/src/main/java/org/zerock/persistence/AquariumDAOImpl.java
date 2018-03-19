package org.zerock.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.AquariumBoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.dto.GpsDTO;

@Repository
public class AquariumDAOImpl implements AquariumDAO { // MyBatis를 이용해서 처리하는 클래스

	@Inject
	private SqlSession session;

	private static String namespace = "org.zerock.mapper.AquariumBoardMapper";

	@Override
	public void create(AquariumBoardVO vo) throws Exception {
		session.insert(namespace + ".aquacreate", vo);
	}

	@Override
	public AquariumBoardVO read(Integer id_num) throws Exception {
		return session.selectOne(namespace + ".aquaread", id_num);
	}

	@Override
	public void update(AquariumBoardVO vo) throws Exception {
		session.update(namespace + ".aquaupdate", vo);

	}

	@Override
	public void delete(Integer id_num) throws Exception {
		session.delete(namespace + ".aquadelete", id_num);

	}

	@Override
	public List<AquariumBoardVO> listAll() throws Exception {
		return session.selectList(namespace + ".aqualistAll");

	}

	@Override
	public List<AquariumBoardVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return session.selectList(namespace + ".aqualistPage", page);
	}

	@Override
	public List<AquariumBoardVO> listCriteria(Criteria cri) throws Exception {

		return session.selectList(namespace + ".aqualistCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".aquacountPaging", cri);
	}

	@Override
	public List<AquariumBoardVO> listSearch(SearchCriteria cri) throws Exception {

		return session.selectList(namespace + ".aqualistSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {

		return session.selectOne(namespace + ".aqualistSearchCount", cri);
	}

	@Override
	public int Count() throws Exception {
		return session.selectOne(namespace + ".aquaCount");
	}

	@Override
	public List<GpsDTO> dtoList() throws Exception {
		return session.selectList(namespace + ".DTOlistAll");
	}

}
