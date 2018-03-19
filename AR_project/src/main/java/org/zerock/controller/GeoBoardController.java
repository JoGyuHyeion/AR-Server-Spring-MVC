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
import org.zerock.domain.GeoBoardVO;
import org.zerock.domain.PageMaker;
import org.zerock.domain.SearchCriteria;
import org.zerock.service.GeoService;
import org.zerock.util.JsonUtils;
import org.zerock.util.UploadFileUtils;

@Controller
@RequestMapping("geoboard/*")
public class GeoBoardController {

	private static final Logger logger = LoggerFactory.getLogger(GeoBoardController.class);

	@Inject
	private GeoService service;

	@Resource(name = "uploadPath")
	private String uploadPath;

	@Resource(name = "ipPath")
	private String ipPath;

	private JSONArray jsonArray;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

		logger.info(cri.toString());
		System.out.println("GeoBoardController list GET");

		model.addAttribute("list", service.listSearchCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		model.addAttribute("pageMaker", pageMaker);
	}

	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("id_num") int id_num, @ModelAttribute("cri") SearchCriteria cri, Model model)
			throws Exception {
		System.out.println("GeoBoardController readPage GET");
		GeoBoardVO vo = service.read(id_num);
		model.addAttribute(vo);
		model.addAttribute("pinmarker", JsonUtils.jsonPaserPinmark(vo));

	}

	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("id_num") int id_num, SearchCriteria cri, RedirectAttributes rttr)
			throws Exception {
		GeoBoardVO removefile = service.read(id_num);
		String[] removeArr = { removefile.getXml(), removefile.getDat() };
		UploadFileUtils.deleteFile(uploadPath, removeArr);
		System.out.println("GeoBoardController removePage POST");
		service.remove(id_num);

		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("msg", "removePage");

		return "redirect:/geoboard/list";
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(int id_num, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		System.out.println("GeoBoardController modifyPage GET");
		GeoBoardVO vo = service.read(id_num);
		model.addAttribute(vo);
		model.addAttribute("pinmarker", JsonUtils.jsonPaserPinmark(vo));
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagingPOST(GeoBoardVO board, SearchCriteria cri, RedirectAttributes rttr, String[] pName,
			String[] pLat, String[] pLon, @RequestParam("file") MultipartFile... file) throws Exception {

		System.out.println("GeoBoardController modifyPage POST");
		logger.info(cri.toString());

		board = UploadFileUtils.modifyFile(board, service.read(board.getId_num()), uploadPath, file);

		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("msg", "modifyPage");
		logger.info(rttr.toString());
		
		jsonArray = JsonUtils.jsonModifyPinmark(service.read(board.getId_num()), pName, pLat, pLon);

		board.setPinmarker(jsonArray.toJSONString());
		service.modify(board);
		return "redirect:/geoboard/list";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registGET(@ModelAttribute("cri") SearchCriteria cri) throws Exception {
		// jo 파라미터추가 원래는 empty
		System.out.println("GeoBoardController register GET");
		logger.info("regist get ...........");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registPOST(GeoBoardVO board, RedirectAttributes rttr, String[] pName, String[] pLat, String[] pLon,
			@RequestParam("file") MultipartFile... file) throws Exception {

		System.out.println("GeoBoardController register POST");
		logger.info("regist post ...........");
		logger.info(board.toString());

		jsonArray = JsonUtils.registJsonPinmark(pName, pLat, pLon);
		String xml, dat;
		xml = UploadFileUtils.uploadFile(uploadPath, file[0].getOriginalFilename(), file[0].getBytes());
		dat = UploadFileUtils.uploadFile(uploadPath, file[1].getOriginalFilename(), file[1].getBytes());
		board.setXml(xml);
		board.setDat(dat);
		board.setPinmarker(jsonArray.toJSONString());

		service.regist(board);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/geoboard/list";
	}

	@RequestMapping(value = "/gps_service", method = RequestMethod.GET)
	public void gps_serviceGET(@RequestParam("lat") String lat, @RequestParam("lon") String lon, Model model)
			throws Exception {
		System.out.println("GeoBoardController register GET");
		logger.info("regist get ...........");
		model.addAttribute("gpsArray", JsonUtils.getJsonGeoList(service.dtoList(), ipPath, lat, lon));
	}
}
