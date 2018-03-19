package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.KuSignBoardVO;
import org.zerock.domain.SearchCriteria;
import org.zerock.dto.GpsDTO;
import org.zerock.persistence.KuSignDAO;

@Service
public class KuSignServiceImpl implements KuSignService {
	@Inject
	private KuSignDAO dao;

	@Override
	public void regist(KuSignBoardVO board) throws Exception {
		dao.create(board);
	}

	@Override
	public KuSignBoardVO read(Integer id_num) throws Exception {
		return dao.read(id_num);
	}

	@Override
	public void modify(KuSignBoardVO board) throws Exception {
		dao.update(board);
	}

	@Override
	public void remove(Integer id_num) throws Exception {
		dao.delete(id_num);
	}

	@Override
	public List<KuSignBoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public List<KuSignBoardVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return dao.countPaging(cri);
	}

	@Override
	public List<KuSignBoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {

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
