package org.zerock.service;

import java.util.List;

import org.zerock.domain.DrawBoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.dto.GpsDTO;

public interface DrawService {// 실제로 서비스 되는 메소드 ( DAO메소드를 활용한다.)

	public void regist(DrawBoardVO board) throws Exception;

	// 게시물 등록
	public DrawBoardVO read(Integer id_num) throws Exception;

	// 게시물 읽기
	public void modify(DrawBoardVO board) throws Exception;

	// 게시물 수정
	public void remove(Integer id_num) throws Exception;

	// 게시물 삭제
	public List<DrawBoardVO> listAll() throws Exception;

	// 모든 게시물 출력
	public List<DrawBoardVO> listCriteria(Criteria cri) throws Exception;

	// 게시물 페이징
	public int listCountCriteria(Criteria cri) throws Exception;

	// 전체 게시물의 숫자 counting
	public List<DrawBoardVO> listSearchCriteria(SearchCriteria cri) throws Exception;

	public int listSearchCount(SearchCriteria cri) throws Exception;

	public List<GpsDTO> dtoList() throws Exception;

	// gps_service 리스트 전체
	public int Count() throws Exception;
	// 게시물 갯수
}
