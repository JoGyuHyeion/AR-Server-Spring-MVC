package org.zerock.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.domain.KuSignBoardVO;
import org.zerock.dto.GpsDTO;

@Repository
public class KuSignDAOImpl implements KuSignDAO { // MyBatis를 이용해서 처리하는 클래스

	@Inject
	private SqlSession session;

	private static String namespace = "org.zerock.mapper.KuSignBoardMapper";

	@Override
	public void create(KuSignBoardVO vo) throws Exception {
		session.insert(namespace + ".kusigncreate", vo);
	}

	@Override
	public KuSignBoardVO read(Integer id_num) throws Exception {
		return session.selectOne(namespace + ".kusignread", id_num);
	}

	@Override
	public void update(KuSignBoardVO vo) throws Exception {
		session.update(namespace + ".kusignupdate", vo);

	}

	@Override
	public void delete(Integer id_num) throws Exception {
		session.delete(namespace + ".kusigndelete", id_num);

	}

	@Override
	public List<KuSignBoardVO> listAll() throws Exception {
		return session.selectList(namespace + ".kusignlistAll");

	}

	@Override
	public List<KuSignBoardVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return session.selectList(namespace + ".kusignlistPage", page);
	}

	@Override
	public List<KuSignBoardVO> listCriteria(Criteria cri) throws Exception {

		return session.selectList(namespace + ".kusignlistCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".kusigncountPaging", cri);
	}

	@Override
	public List<KuSignBoardVO> listSearch(SearchCriteria cri) throws Exception {

		return session.selectList(namespace + ".kusignlistSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {

		return session.selectOne(namespace + ".kusignlistSearchCount", cri);
	}

	@Override
	public int Count() throws Exception {
		return session.selectOne(namespace + ".kusignCount");
	}

	@Override
	public List<GpsDTO> dtoList() throws Exception {
		return session.selectList(namespace + ".DTOlistAll");
	}

}
