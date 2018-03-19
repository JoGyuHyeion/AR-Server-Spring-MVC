package org.zerock.service;

import java.util.List;

import org.zerock.domain.GeoBoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.dto.GpsDTO;

public interface GeoService {// 실제로 서비스 되는 메소드 ( DAO메소드를 활용한다.)

	public void regist(GeoBoardVO board) throws Exception;

	// 게시물 등록
	public GeoBoardVO read(Integer id_num) throws Exception;

	// 게시물 읽기
	public void modify(GeoBoardVO board) throws Exception;

	// 게시물 수정
	public void remove(Integer id_num) throws Exception;

	// 게시물 삭제
	public List<GeoBoardVO> listAll() throws Exception;

	// 모든 게시물 출력
	public List<GeoBoardVO> listCriteria(Criteria cri) throws Exception;

	// 게시물 페이징
	public int listCountCriteria(Criteria cri) throws Exception;

	// 전체 게시물의 숫자 counting
	public List<GeoBoardVO> listSearchCriteria(SearchCriteria cri) throws Exception;

	public int listSearchCount(SearchCriteria cri) throws Exception;

	public List<GpsDTO> dtoList() throws Exception;

	// gps_service 리스트 전체
	public int Count() throws Exception;
	// 게시물 갯수
}