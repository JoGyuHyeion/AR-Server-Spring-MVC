package org.zerock.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.Criteria;
import org.zerock.domain.KuGeoBoardVO;
import org.zerock.domain.SearchCriteria;
import org.zerock.dto.GpsDTO;

@Repository
public class KuGeoDAOImpl implements KuGeoDAO { // MyBatis를 이용해서 처리하는 클래스

	@Inject
	private SqlSession session;

	private static String namespace = "org.zerock.mapper.KuGeoBoardMapper";

	@Override
	public void create(KuGeoBoardVO vo) throws Exception {
		session.insert(namespace + ".kugeocreate", vo);
	}

	@Override
	public KuGeoBoardVO read(Integer id_num) throws Exception {
		return session.selectOne(namespace + ".kugeoread", id_num);
	}

	@Override
	public void update(KuGeoBoardVO vo) throws Exception {
		session.update(namespace + ".kugeoupdate", vo);

	}

	@Override
	public void delete(Integer id_num) throws Exception {
		session.delete(namespace + ".kugeodelete", id_num);

	}

	@Override
	public List<KuGeoBoardVO> listAll() throws Exception {
		return session.selectList(namespace + ".kugeolistAll");

	}

	@Override
	public List<KuGeoBoardVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return session.selectList(namespace + ".kugeolistPage", page);
	}

	@Override
	public List<KuGeoBoardVO> listCriteria(Criteria cri) throws Exception {

		return session.selectList(namespace + ".kugeolistCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".kugeocountPaging", cri);
	}

	@Override
	public List<KuGeoBoardVO> listSearch(SearchCriteria cri) throws Exception {

		return session.selectList(namespace + ".kugeolistSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {

		return session.selectOne(namespace + ".kugeolistSearchCount", cri);
	}

	@Override
	public int Count() throws Exception {
		return session.selectOne(namespace + ".kugeoCount");
	}

	@Override
	public List<GpsDTO> dtoList() throws Exception {
		return session.selectList(namespace + ".DTOlistAll");
	}

}
