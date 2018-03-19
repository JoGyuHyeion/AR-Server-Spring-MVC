package org.zerock.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		 System.out.println("loginInterceptor post handle........................");
		HttpSession session = request.getSession();

		ModelMap modelMap = modelAndView.getModelMap();
		Object userVO = modelMap.get("userVO");
		//로그인후 파로 readPage로 가기위해 UserController에서 필요한 정보를 받아온다
		Criteria cri = (Criteria) modelMap.get("cri");
		Object id_num = modelMap.get("id_num");

		if (userVO != null) {

			logger.info("new login success");
			session.setAttribute(LOGIN, userVO);

			if (request.getParameter("useCookie") != null) {

				logger.info("remember me................");
				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				loginCookie.setPath("/");
				loginCookie.setMaxAge(60 * 60 * 24 * 7);//60초60분24시간7주일간 보관총 7주일
				response.addCookie(loginCookie);//쿠키는 반드시 HttpServletResponse에 담겨서 전송한다.
			} // response.sendRedirect("/");
			Object dest = session.getAttribute("dest");

//			response.sendRedirect(dest != null ? (String) dest : "/sboard/readPage"+gotoReadPage(id_num, cri));	//로그인 되면 list로 화면이 바뀐다.
			response.sendRedirect(dest != null ? (String) dest : "/");	//로그인 되면 list로 화면이 바뀐다.
			 System.out.println("loginInterceptor post handle........................dest:"+dest);
	
		}
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("loginInterceptor pre handle........................");
		HttpSession session = request.getSession();

		if (session.getAttribute(LOGIN) != null) {
			logger.info("clear login data before");
			session.removeAttribute(LOGIN);
		}

		return true;
	}
	private String gotoReadPage(Object id_num,Criteria cri){
		String path="?id_num="+id_num+"&page="+cri.getPage()+"&perPageNum="+cri.getPerPageNum();
		return path;
	}
}
