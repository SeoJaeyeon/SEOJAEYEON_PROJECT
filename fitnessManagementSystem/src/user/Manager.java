package com.fitnessmanagement.user;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.useRepresentation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.fitnessmanagement.mapper.ManagerMapper;

import lombok.Data;

@Data
public class Manager extends Account{
	
	
	private String name;
	private String center_id;
	private String phoneNumber;
	
	@Override
	public String toString(){
		return this.getId()+","+this.getPassword()+","+this.getType()+","+   this.getName()+", "+this.getCenter_id()+", "+this.getPhoneNumber()+".";
	}
}


