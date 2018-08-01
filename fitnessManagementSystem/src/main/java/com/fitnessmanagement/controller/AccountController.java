package com.fitnessmanagement.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessmanagement.mapper.AccountMapper;
import com.fitnessmanagement.security.AccountService;
import com.fitnessmanagement.user.Account;
import com.fitnessmanagement.user.AccountRepository;

@RestController
public class AccountController {
	
	@Autowired
	AccountService accountService;

	@Autowired
	AccountMapper accountMapper;
	
	
	//ADMIN 계정 부여 
	@GetMapping("/create")
	public Account create(){
		Account account=new Account();
		account.setId("admin");
		account.setPassword(패스워드);
		accountService.save(account, "ROLE_ADMIN", "ADMIN");
		return account;
	}


}
