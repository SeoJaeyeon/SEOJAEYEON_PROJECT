package com.fitnessmanagement.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fitnessmanagement.user.Trainer;

@Mapper
public interface TrainerMapper {

	@Select("SELECT * FROM Trainer WHERE id= #{id} and password=#{password}")
	Trainer findTrainerByTrainerId(@Param("id")String id,@Param("password")String password);
	
	/*id           | varchar(15) | NO   | PRI | NULL    |       |
	| name         | varchar(5)  | YES  |     | NULL    |       |
	| center_id    | varchar(10) | YES  | MUL | NULL    |       |
	| birthdate    | date        | YES  |     | NULL    |       |
	| gender       | varchar(1)  | YES  |     | NULL    |       |
	| closed_day   | varchar(1)  | YES  |     | NULL    |       |
	| phone_number*/
	@Insert("INSERT INTO TRAINER VALUE(#{trainer.id},#{trainer.name},#{trainer.center_id},#{trainer.birthdate},#{trainer.gender},#{trainer.closed_day},#{trainer.phone_number})")
	void insertTrainer(@Param("trainer")Trainer trainer);
}
