package com.fitnessmanagement.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fitnessmanagement.user.Account;



//적절한 View 리턴 및 쿠키 처리
@Service
public class SaveLoginIdHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
	
	 private static final Logger logger = LoggerFactory.getLogger(SaveLoginIdHandler.class);
	static final String REQUEST_PARAM_NAME = "_remember_username";
	static final String COOKIE_NAME = "saved_username";
	static final int DEFAULT_MAX_AGE = 60*60*24*7;
	 
	 private int maxAge = DEFAULT_MAX_AGE;
	 
	 public void setMaxAge(int maxAge) {
	  this.maxAge = maxAge;
	 }
	 

	 @Override
	 public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	   Authentication authentication) throws IOException, ServletException {
	   
		Account user = ((Account) authentication.getPrincipal());
		if(request.getParameter("remember")==null){
			//기억하기 체크 안되어 있으면 쿠키 해제
			response.addCookie(new Cookie(COOKIE_NAME,""));
		}
		else{
			//기억하기 체크 되어있으면 쿠키 추가
			response.addCookie(new Cookie(COOKIE_NAME,user.getId()));
		}
		
		
		 String usertype = user.getType();
		 if(usertype.equals("ADMIN"))
			 new DefaultRedirectStrategy().sendRedirect(request, response, "/admin");
		 if(usertype.equals("MANAGER"))
			 new DefaultRedirectStrategy().sendRedirect(request, response, "/manager?id="+user.getId());
		 if(usertype.equals("TRAINER"))
			 new DefaultRedirectStrategy().sendRedirect(request, response, "/trainer");
		 if(usertype.equals("MEMBER"))
			 new DefaultRedirectStrategy().sendRedirect(request, response, "/member");
	 }
}
