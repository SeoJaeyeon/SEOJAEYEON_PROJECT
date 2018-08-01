package com.fitnessmanagement.user;

import lombok.Data;

@Data
public class Center {
// center_id | center_name  | center_phone_number | registration_date | end_date   | address            
	private String center_id;
	private String center_name;
	private String center_phone_number;
	private String address;
}
