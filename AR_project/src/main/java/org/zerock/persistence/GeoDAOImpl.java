package org.zerock.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.Criteria;
import org.zerock.domain.GeoBoardVO;
import org.zerock.domain.SearchCriteria;
import org.zerock.dto.GpsDTO;

@Repository
public class GeoDAOImpl implements GeoDAO { // MyBatis를 이용해서 처리하는 클래스

	@Inject
	private SqlSession session;

	private static String namespace = "org.zerock.mapper.GeoBoardMapper";

	@Override
	public void create(GeoBoardVO vo) throws Exception {
		session.insert(namespace + ".geocreate", vo);
	}

	@Override
	public GeoBoardVO read(Integer id_num) throws Exception {
		return session.selectOne(namespace + ".georead", id_num);
	}

	@Override
	public void update(GeoBoardVO vo) throws Exception {
		session.update(namespace + ".geoupdate", vo);

	}

	@Override
	public void delete(Integer id_num) throws Exception {
		session.delete(namespace + ".geodelete", id_num);

	}

	@Override
	public List<GeoBoardVO> listAll() throws Exception {
		return session.selectList(namespace + ".geolistAll");

	}

	@Override
	public List<GeoBoardVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return session.selectList(namespace + ".geolistPage", page);
	}

	@Override
	public List<GeoBoardVO> listCriteria(Criteria cri) throws Exception {

		return session.selectList(namespace + ".geolistCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".geocountPaging", cri);
	}

	@Override
	public List<GeoBoardVO> listSearch(SearchCriteria cri) throws Exception {

		return session.selectList(namespace + ".geolistSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {

		return session.selectOne(namespace + ".geolistSearchCount", cri);
	}

	@Override
	public int Count() throws Exception {
		return session.selectOne(namespace + ".geoCount");
	}

	@Override
	public List<GpsDTO> dtoList() throws Exception {
		return session.selectList(namespace + ".DTOlistAll");
	}

}
