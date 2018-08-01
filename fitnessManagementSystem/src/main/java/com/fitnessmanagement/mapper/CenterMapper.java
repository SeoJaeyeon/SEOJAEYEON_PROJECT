package com.fitnessmanagement.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fitnessmanagement.user.Center;



@Mapper
public interface CenterMapper {
	// center_id | center_name  | center_phone_number | registration_date | end_date   | address            
	@Select("SELECT center_name FROM center WHERE center_id = #{center_id}")
    String findCenterNameByCenterId(@Param("center_id")String center_id);
	
	@Insert("INSERT INTO CENTER VALUES(#{center.center_id}, #{center.center_name}, #{center.center_phone_number}, #{center.address})")
	public void insertCenterData(@Param("center") Center center);
}
