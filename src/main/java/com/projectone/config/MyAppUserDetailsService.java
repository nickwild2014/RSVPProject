package com.projectone.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projectone.database.jpa.UserTableRepository;
import com.projectone.model.UserRegisterationDetails;

@Service
public class MyAppUserDetailsService implements UserDetailsService{

	@Autowired
    UserTableRepository userTableRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws  UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("username ================== "+username);
		String role;
		
		 String userNameDublicate = "";
		 String password = "";
		
		if(username.equalsIgnoreCase("admin"))
		{
			userNameDublicate = username;
			role = "ROLE_ADMIN";
			System.out.println("if condition username ================== "+username);
		}
		else
		{
			role = "ROLE_USER";
			System.out.println("else condition username ================== "+username);
			userNameDublicate = username;
			//userNameDublicate = "subhash";
			//throw new UsernameNotFoundException("UUSER NOT FOUND");
		}
		
		
    	List<UserRegisterationDetails> obj = userTableRepository.findByName(username);
      for (UserRegisterationDetails userTable : obj) {
    	  System.out.println("userTable.getName() "+userTable.getName());
    	  System.out.println("userTable.getPassword() "+userTable.getPassword());
    	  password = userTable.getPassword();
  	}
      
      if(password==null || password.isEmpty())
      {
    	  password = "password";
      }
      
		//UserInfo activeUserInfo = userInfoDAO.getActiveUser(userName); database
		GrantedAuthority authority = new SimpleGrantedAuthority(role);//activeUserInfo.getRole()
		UserDetails userDetails = (UserDetails)new User(userNameDublicate,
				password, Arrays.asList(authority));
		System.out.println("userDetails.toString() "+userDetails.toString());
		return userDetails;
		
//		return new UserDetails() {
//			
//			private static final long serialVersionUID = 2059202961588104658L;
//
//			@Override
//			public boolean isEnabled() {
//				return true;
//			}
//			
//			@Override
//			public boolean isCredentialsNonExpired() {
//				return true;
//			}
//			
//			@Override
//			public boolean isAccountNonLocked() {
//				return true;
//			}
//			
//			@Override
//			public boolean isAccountNonExpired() {
//				return true;
//			}
//			
//			@Override
//			public String getUsername() {
//				return userNameDublicate;
//			}
//			
//			@Override
//			public String getPassword() {
//				return "password";
//			}
//			
//			@Override
//			public Collection<? extends GrantedAuthority> getAuthorities() {
//				List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
//				auths.add(new SimpleGrantedAuthority(role));
//				return auths;
//			}
//		};
	}
}
