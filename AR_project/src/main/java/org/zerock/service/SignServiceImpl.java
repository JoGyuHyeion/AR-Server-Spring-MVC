package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.domain.SignBoardVO;
import org.zerock.dto.GpsDTO;
import org.zerock.persistence.SignDAO;

@Service
public class SignServiceImpl implements SignService {
	@Inject
	private SignDAO dao;

	@Override
	public void regist(SignBoardVO board) throws Exception {
		dao.create(board);
	}

	@Override
	public SignBoardVO read(Integer id_num) throws Exception {
		return dao.read(id_num);
	}

	@Override
	public void modify(SignBoardVO board) throws Exception {
		dao.update(board);
	}

	@Override
	public void remove(Integer id_num) throws Exception {
		dao.delete(id_num);
	}

	@Override
	public List<SignBoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public List<SignBoardVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return dao.countPaging(cri);
	}

	@Override
	public List<SignBoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {

		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {

		return dao.listSearchCount(cri);
	}

	@Override
	public List<GpsDTO> dtoList() throws Exception {
		return dao.dtoList();
	}

	@Override
	public int Count() throws Exception {
		return dao.Count();
	}
}
