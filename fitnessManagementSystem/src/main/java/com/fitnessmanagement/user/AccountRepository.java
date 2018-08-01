package com.fitnessmanagement.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fitnessmanagement.mapper.AccountMapper;

import lombok.Data;

@Repository
@Data
public class AccountRepository {
	
	@Autowired
	AccountMapper accountMapper;
	
	Account account;
	
	public Account saveAccount(Account account){
		this.account=account;
		return this.account;
	}
	
	public Account save(Account account,String role){
		accountMapper.insertUser(account);
		accountMapper.insertUserAutority(account.getId(), role);
		return account;
	}

	public Account findById(String username) {
		// TODO Auto-generated method stub
		return accountMapper.readAccount(username);
	}
	
	public List<String> findAuthoritiesByID(String username){
		return accountMapper.readAutorities(username);
	}
}
