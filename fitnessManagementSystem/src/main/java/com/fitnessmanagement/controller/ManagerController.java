package com.fitnessmanagement.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fitnessmanagement.mapper.AccountMapper;
import com.fitnessmanagement.mapper.ManagerMapper;
import com.fitnessmanagement.mapper.MemberMapper;
import com.fitnessmanagement.security.AccountService;
import com.fitnessmanagement.user.Account;
import com.fitnessmanagement.user.Manager;
import com.fitnessmanagement.user.Member;

@Controller
public class ManagerController {
	
	private Manager manager;
	@Autowired
	AccountService accountService;
	@Autowired
	private ManagerMapper managerMapper;	
	@Autowired
	private MemberMapper memberMapper;
	
	@GetMapping("/manager")
	public String manager(@RequestParam("id")String id,HttpServletRequest req, HttpServletResponse res){

		System.out.println(id);
		this.manager=managerMapper.findManagerByManagerId(id);
		System.out.println(manager.getCenter_id()+"/////");

		return "manager/manager";
	}
	
	@GetMapping("/manager/addUser")
	public String addUserByGet(){
		return "manager/addUser";
	}
	//회원추가기능	
	@PostMapping("/manager/addUser")
	public String addUserByPoset(Member member,Model model){
		Account account=new Account();
		account.setId(member.getId());
		account.setPassword(member.getPassword());
		member.setCenter_id(this.manager.getCenter_id());
		try{
			accountService.save(account, "ROLE_MEMBER", "MEMBER");
			memberMapper.insertMember(member);
		}catch(Exception ex){
			ex.printStackTrace();
			model.addAttribute("message","사용자 추가 에러!!!");
			return "/manager/addUser";
		}
		
		model.addAttribute("message","정상적으로 등록되었습니다.");
		return "/manager/addUser";
	}

	@GetMapping("/manager/addTrainer")
	public String test(){
		return "/manager/addTrainer";
	}
}
