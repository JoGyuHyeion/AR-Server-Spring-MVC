package org.zerock.persistence;

import java.util.List;

import org.zerock.domain.AquariumBoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.dto.GpsDTO;

public interface AquariumDAO {

	public void create(AquariumBoardVO vo) throws Exception;
	//게시물 등록
	public AquariumBoardVO read(Integer id_num) throws Exception;
	//게시물 읽기
	public void update(AquariumBoardVO vo) throws Exception;
	//게시물 수정
	public void delete(Integer id_num) throws Exception;
	//게시물 삭제
	public List<AquariumBoardVO> listAll() throws Exception;
	//모든 게시물 출력
	public List<AquariumBoardVO> listPage(int page) throws Exception;
	//게시물 페이지 출력(1페이지)
	public List<AquariumBoardVO> listCriteria(Criteria cri) throws Exception;
	//Criteria 객체를 파라미터로 전달받고 필효안 start perpagenum메소드를 사용하여 페이징 처리
	public int countPaging(Criteria cri)throws Exception;
	//페이징 처리시 페이징 숫자 count
	public List<AquariumBoardVO> listSearch(SearchCriteria cri)throws Exception;
	//search list 처리  
	public int listSearchCount(SearchCriteria cri)throws Exception;
	//search list 페이징 count
	public int Count()throws Exception;
	//게시물 갯수
	public List<GpsDTO> dtoList() throws Exception;
	//gps_service 리스트 전체
}
