package com.projectone.authsuccess;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication)
			throws IOException, ServletException {
		 if (authentication != null) {
			 try
			 {
			 httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/"+authentication.getName());
			 }
			 catch (Exception e) {
					e.printStackTrace();
				}
		 }		
		
	}

}
