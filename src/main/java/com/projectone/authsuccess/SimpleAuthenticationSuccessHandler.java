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
		
		System.out.println("================== successful auth ==================");
		
		//authentication.getName();
		//authentication.getCredentials();
		
		 if (authentication != null) {
			 String authUrl="";
			 try
			 {
				 if(authentication.getName().equalsIgnoreCase("admin"))
				 {
					 authUrl = "admin";
					 System.out.println("authUrl = \"admin\"");
				 }
				 else
				 {
					 authUrl = "user";
					 System.out.println("authUrl = \"user\"");
				 }
				 
			 httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/"+authUrl);
			 }
			 catch (Exception e) {
					e.printStackTrace();
				}
		 }		
		
	}

}
