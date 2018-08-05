package com.fitnessmanagement.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fitnessmanagement.mapper.CenterMapper;
import com.fitnessmanagement.mapper.ManagerMapper;
import com.fitnessmanagement.service.AccountService;
import com.fitnessmanagement.service.s3.S3Wrapper;
import com.fitnessmanagement.user.Account;
import com.fitnessmanagement.user.Center;
import com.fitnessmanagement.user.Manager;

@Controller
public class AdminController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	ManagerMapper managerMapper;
	
	@Autowired
	CenterMapper centerMapper;
	
	@Autowired
	private S3Wrapper s3Wrapper;
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String adminPage(Model model,HttpServletRequest req){
		
		return "admin/admin";
	}
	//관리자 추가 
	@RequestMapping(value="/admin", method=RequestMethod.POST) 
	public String addManager(Center center,Manager manager,HttpServletRequest req,Model model){
		Account account=new Account();
		account.setId(manager.getId());
		account.setPassword(manager.getPassword());
	
		try{
		accountService.save(account, "ROLE_MANAGER", "MANAGER");
		managerMapper.insertManager(manager);
		centerMapper.insertCenterData(center);
		
		}catch(Exception ex){ 
			ex.printStackTrace();
			model.addAttribute("message","관리자 추가 에러!!");
			return "admin/admin";
		}
	
		model.addAttribute("message","정상적으로 추가되었습니다");
		return "admin/admin";
		
	}
	

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> download(@RequestParam String key) throws IOException {
	     return s3Wrapper.download(key);
	}

}
