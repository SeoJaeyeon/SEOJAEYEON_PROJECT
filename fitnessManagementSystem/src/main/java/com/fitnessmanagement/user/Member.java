package com.fitnessmanagement.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.fitnessmanagement.mapper.MemberMapper;

import lombok.Data;

@Data
public class Member extends Account{

	private int pt;
	private String name;
	private String gender;
	private String phone_number;
	private String center_id;

}
