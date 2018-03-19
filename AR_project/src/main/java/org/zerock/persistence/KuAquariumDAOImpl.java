package org.zerock.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.KuAquariumBoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.dto.GpsDTO;

@Repository
public class KuAquariumDAOImpl implements KuAquariumDAO { // MyBatis를 이용해서 처리하는 클래스

	@Inject
	private SqlSession session;

	private static String namespace = "org.zerock.mapper.KuAquariumBoardMapper";

	@Override
	public void create(KuAquariumBoardVO vo) throws Exception {
		session.insert(namespace + ".kuaquacreate", vo);
	}

	@Override
	public KuAquariumBoardVO read(Integer id_num) throws Exception {
		return session.selectOne(namespace + ".kuaquaread", id_num);
	}

	@Override
	public void update(KuAquariumBoardVO vo) throws Exception {
		session.update(namespace + ".kuaquaupdate", vo);

	}

	@Override
	public void delete(Integer id_num) throws Exception {
		session.delete(namespace + ".kuaquadelete", id_num);

	}

	@Override
	public List<KuAquariumBoardVO> listAll() throws Exception {
		return session.selectList(namespace + ".kuaqualistAll");

	}

	@Override
	public List<KuAquariumBoardVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return session.selectList(namespace + ".kuaqualistPage", page);
	}

	@Override
	public List<KuAquariumBoardVO> listCriteria(Criteria cri) throws Exception {

		return session.selectList(namespace + ".kuaqualistCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".aquacountPaging", cri);
	}

	@Override
	public List<KuAquariumBoardVO> listSearch(SearchCriteria cri) throws Exception {

		return session.selectList(namespace + ".kuaqualistSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {

		return session.selectOne(namespace + ".kuaqualistSearchCount", cri);
	}

	@Override
	public int Count() throws Exception {
		return session.selectOne(namespace + ".kuaquaCount");
	}

	@Override
	public List<GpsDTO> dtoList() throws Exception {
		return session.selectList(namespace + ".DTOlistAll");
	}

}
