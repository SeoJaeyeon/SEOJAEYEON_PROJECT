package com.fitnessmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fitnessmanagement.user.Trainer;

@Mapper
public interface TrainerMapper {

	@Select("SELECT * FROM Trainer WHERE id= #{id} and password=#{password}")
	Trainer findTrainerByTrainerId(@Param("id")String id,@Param("password")String password);
}
