package kr.ac.smu.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.ac.smu.DTO.PreferenceDTO;

@Mapper
public interface CustomMapper {
	@Select("SELECT * FROM preference WHERE userId=#{userId} and customName=#{customName}")
	public PreferenceDTO selectByCustom(@Param("userId") String userId, @Param("customName") String customName);
	
	@Insert("INSERT INTO preference VALUES(#{pre.userId},#{pre.id},#{pre.flag},#{pre.custom},#{pre.customName}")
	public void insertPreference(@Param("pre") PreferenceDTO pre);
}	
