package org.zerock.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.domain.SignBoardVO;
import org.zerock.dto.GpsDTO;

@Repository
public class SignDAOImpl implements SignDAO { // MyBatis를 이용해서 처리하는 클래스

	@Inject
	private SqlSession session;

	private static String namespace = "org.zerock.mapper.SignBoardMapper";

	@Override
	public void create(SignBoardVO vo) throws Exception {
		session.insert(namespace + ".signcreate", vo);
	}

	@Override
	public SignBoardVO read(Integer id_num) throws Exception {
		return session.selectOne(namespace + ".signread", id_num);
	}

	@Override
	public void update(SignBoardVO vo) throws Exception {
		session.update(namespace + ".signupdate", vo);

	}

	@Override
	public void delete(Integer id_num) throws Exception {
		session.delete(namespace + ".signdelete", id_num);

	}

	@Override
	public List<SignBoardVO> listAll() throws Exception {
		return session.selectList(namespace + ".signlistAll");

	}

	@Override
	public List<SignBoardVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return session.selectList(namespace + ".signlistPage", page);
	}

	@Override
	public List<SignBoardVO> listCriteria(Criteria cri) throws Exception {

		return session.selectList(namespace + ".signlistCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".signcountPaging", cri);
	}

	@Override
	public List<SignBoardVO> listSearch(SearchCriteria cri) throws Exception {

		return session.selectList(namespace + ".signlistSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {

		return session.selectOne(namespace + ".signlistSearchCount", cri);
	}

	@Override
	public int Count() throws Exception {
		return session.selectOne(namespace + ".signCount");
	}

	@Override
	public List<GpsDTO> dtoList() throws Exception {
		return session.selectList(namespace + ".DTOlistAll");
	}

}
