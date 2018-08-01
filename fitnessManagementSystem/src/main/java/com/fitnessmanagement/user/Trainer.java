package com.fitnessmanagement.user;

import com.fitnessmanagement.mapper.TrainerMapper;

import lombok.Data;

@Data
public class Trainer extends Account{
	private String closed_day;
	
	private TrainerMapper trainerMapper;	
	private String name;
	private String gender;
	private String phone_number;
	private String center_id;


}
