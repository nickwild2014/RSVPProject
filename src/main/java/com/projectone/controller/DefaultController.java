package com.projectone.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projectone.database.jpa.UserTableRepository;
import com.projectone.model.UserRegisterationDetails;


@Controller
public class DefaultController {
	
//	 @GetMapping("/")
//	    public String home1() {
//	        return "/home";
//	    }
	
	@Autowired
    UserTableRepository userTableRepository;
	
    @GetMapping("/")
    public String home1() {
        return "/login";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }


    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
    
    @GetMapping("/admin/accountCreation")
    public String accountCreation(Model model) {
    	model.addAttribute("accountCreation", new UserRegisterationDetails());
        return "accountCreation";
    }
    
    @GetMapping("/admin/registeredUsers")
    public String registeredUsers(Model model) {
    	List<UserRegisterationDetails> obj = userTableRepository.findAll();
        model.addAttribute("usertableobj", obj);
        return "table";
        
    }
    
    
    @PostMapping("/admin/userRegisterationDetails")
    public String greetingSubmit(@ModelAttribute UserRegisterationDetails userRegisterationDetailsOne,
    		BindingResult result, RedirectAttributes redirectAttributes,Model model) {
    	System.out.println("userRegisterationDetails ====="+userRegisterationDetailsOne.getName());
    	System.out.println("userRegisterationDetails getEmail ====="+userRegisterationDetailsOne.getEmail());
    	System.out.println("userRegisterationDetails getPhoneNumber ====="+userRegisterationDetailsOne.getPhoneNumber());
    	System.out.println("userRegisterationDetails getDateOfBirth ====="+userRegisterationDetailsOne.getDateOfBirth());
    	System.out.println("userRegisterationDetails getAddress ====="+userRegisterationDetailsOne.getAddress());
    	System.out.println("userRegisterationDetails getCity ====="+userRegisterationDetailsOne.getCity());
    	System.out.println("userRegisterationDetails getState ====="+userRegisterationDetailsOne.getState());
    	System.out.println("userRegisterationDetails getZipCode ====="+userRegisterationDetailsOne.getZipCode());
    	System.out.println("userRegisterationDetails getGender ====="+userRegisterationDetailsOne.getGender());
    	System.out.println("userRegisterationDetails getCountry ====="+userRegisterationDetailsOne.getCountry());
    	System.out.println("userRegisterationDetails getPassword ====="+userRegisterationDetailsOne.getPassword());
    	
    
    	
//    	List<UserTable> obj = userTableRepository.findByEmailAddress("Bauer","Jack");
//        StringBuilder sb = new StringBuilder();
//        for (UserTable userTable : obj) {
//        	sb.append(userTable+" 8888888888888");
//        	sb.append("\n");
//    	}
//        System.out.println(sb.toString());
    	
    	if(userRegisterationDetailsOne.getName().equalsIgnoreCase("admin"))
    	{
    		UserRegisterationDetails obj = new UserRegisterationDetails();
        	obj.setName("admin");
    		model.addAttribute("accountCreation", obj);
            return "accountCreation";
    	}
    	userTableRepository.save(new UserRegisterationDetails(userRegisterationDetailsOne.getName(), 
    			userRegisterationDetailsOne.getEmail(),
    			userRegisterationDetailsOne.getPhoneNumber(), 
    			userRegisterationDetailsOne.getDateOfBirth(), userRegisterationDetailsOne.getGender(),
    			userRegisterationDetailsOne.getAddress(), userRegisterationDetailsOne.getCity(),
    			userRegisterationDetailsOne.getState(), userRegisterationDetailsOne.getZipCode(), 
    			userRegisterationDetailsOne.getCountry(),userRegisterationDetailsOne.getPassword()));
    	
    	model.addAttribute("accountCreation", new UserRegisterationDetails());
    	
    	System.out.println("result.getFieldError() "+result.getFieldError());
   	    //redirectAttributes.addAttribute("message", "Success");
   	    //redirectAttributes.addAttribute("alertClass", "alert-success");
   	    model.addAttribute("message", "Success");
    	return "accountCreation";
    	
        //return "result";
    }
}