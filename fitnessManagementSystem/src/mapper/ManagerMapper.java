package com.fitnessmanagement.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fitnessmanagement.user.Manager;

@Mapper
public interface ManagerMapper {
	
	@Select("SELECT * FROM MANAGER WHERE id= #{id}")
	Manager findManagerByManagerId(@Param("id")String id);
	
	@Insert("INSERT INTO Manager VALUES(#{manager.id},#{manager.name},#{manager.center_id},#{manager.phoneNumber})")
	public void insertManager(@Param("manager") Manager manager);
	

}
