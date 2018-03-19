package org.zerock.controller;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.KuSignBoardVO;
import org.zerock.domain.PageMaker;
import org.zerock.domain.SearchCriteria;
import org.zerock.service.KuSignService;
import org.zerock.util.JsonUtils;
import org.zerock.util.UploadFileUtils;

@Controller
@RequestMapping("kusignboard/*")
public class KuSignBoardController {

	private static final Logger logger = LoggerFactory.getLogger(KuSignBoardController.class);

	@Inject
	private KuSignService service;

	@Resource(name = "uploadPath")
	private String uploadPath;

	@Resource(name = "ipPath")
	private String ipPath;

	private JSONArray jsonArray;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

		logger.info(cri.toString());
		System.out.println("KuSignBoardController list GET");

		model.addAttribute("list", service.listSearchCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		model.addAttribute("pageMaker", pageMaker);
	}

	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("id_num") int id_num, @ModelAttribute("cri") SearchCriteria cri, Model model)
			throws Exception {
		System.out.println("KuSignBoardController readPage GET");
		KuSignBoardVO vo = service.read(id_num);
		model.addAttribute(vo);
		model.addAttribute("picName", JsonUtils.jsonParserPicture(vo, 0));
	}

	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("id_num") int id_num, SearchCriteria cri, RedirectAttributes rttr)
			throws Exception {
		KuSignBoardVO removefile = service.read(id_num);
		String[] removeKarmarkger = {removefile.getKarmarker() };
		String[] removePicture = JsonUtils.jsonParserPicture(removefile, 0);
		UploadFileUtils.deleteFile(uploadPath, removeKarmarkger);
		UploadFileUtils.deleteFile(uploadPath, removePicture);
		System.out.println("KuSignBoardController removePage POST");
		service.remove(id_num);

		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("msg", "removePage");

		return "redirect:/kusignboard/list";
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(int id_num, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		System.out.println("KuSignBoardController modifyPage GET");
		KuSignBoardVO vo = service.read(id_num);
		model.addAttribute(vo);
		model.addAttribute("picName", JsonUtils.jsonParserPicture(vo, 0));
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagingPOST(KuSignBoardVO board, SearchCriteria cri, RedirectAttributes rttr,
			@RequestParam("file") MultipartFile[] file, @RequestParam("img") MultipartFile... img) throws Exception {

		logger.info(cri.toString());
		board = UploadFileUtils.modifyFile(board, service.read(board.getId_num()), uploadPath, file, img);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());

		rttr.addFlashAttribute("msg", "modifyPage");

		logger.info(rttr.toString());
		service.modify(board);
		return "redirect:/kusignboard/list";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registGET(@ModelAttribute("cri") SearchCriteria cri) throws Exception {
		// jo 파라미터추가 원래는 empty
		System.out.println("KuSignBoardController register GET");
		logger.info("regist get ...........");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registPOST(KuSignBoardVO board, RedirectAttributes rttr, @RequestParam("file") MultipartFile[] file,
			@RequestParam("img") MultipartFile... img) throws Exception {
		System.out.println("KuSignBoardController register POST");
		logger.info("regist post ...........");
		logger.info(board.toString());
		String picture, karmarker;
		karmarker = UploadFileUtils.uploadFile(uploadPath, file[0].getOriginalFilename(), file[0].getBytes());

		jsonArray = UploadFileUtils.uploadJsonImageFile(uploadPath, img);

		board.setKarmarker(karmarker);
		board.setPicture(jsonArray.toJSONString());

		service.regist(board);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/kusignboard/list";
	}

	@RequestMapping(value = "/gps_service", method = RequestMethod.GET)
	public void gps_serviceGET(@RequestParam("lat") String lat, @RequestParam("lon") String lon, Model model)
			throws Exception {
		System.out.println("KuSignBoardController register GET");
		logger.info("regist get ...........");
		model.addAttribute("gpsArray", JsonUtils.getJsonKuPictureList(service.dtoList(), ipPath, lat, lon));
	}

}
