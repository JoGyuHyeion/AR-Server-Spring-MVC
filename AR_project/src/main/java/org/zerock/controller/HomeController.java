package org.zerock.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.dto.CountDTO;
import org.zerock.service.AquariumService;
import org.zerock.service.DrawService;
import org.zerock.service.GeoService;
import org.zerock.service.KuAquariumService;
import org.zerock.service.KuGeoService;
import org.zerock.service.KuSignService;
import org.zerock.service.SignService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private AquariumService aquariumService;
	@Inject
	private DrawService drawService;
	@Inject
	private GeoService geoService;
	@Inject
	private SignService sginService;
	@Inject
	private KuAquariumService kuAquariumService;
	@Inject
	private KuGeoService kuGeoService;
	@Inject
	private KuSignService kuSignService;

	private Date date;
	private CountDTO cDto;

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);

		date = new Date();
		cDto = new CountDTO();
		cDto.setAquarium(aquariumService.Count());
		cDto.setDraw(drawService.Count());
		cDto.setGeo(geoService.Count());
		cDto.setSign(sginService.Count());
		cDto.setKuAquarium(kuAquariumService.Count());
		cDto.setKuGeo(kuGeoService.Count());
		cDto.setKuSign(kuSignService.Count());

		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("count", cDto);

		return "home";
	}

	@RequestMapping(value = "/doA", method = RequestMethod.GET)
	public String doA(Locale locale, Model model) {

		System.out.println("doA....................");

		return "home";
	}

	@RequestMapping(value = "/doB", method = RequestMethod.GET)
	public String doB(Locale locale, Model model) {

		System.out.println("doB....................");

		model.addAttribute("result", "DOB RESULT");

		return "home";
	}

}
