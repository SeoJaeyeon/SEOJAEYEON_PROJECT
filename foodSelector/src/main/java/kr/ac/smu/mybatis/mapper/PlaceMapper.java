package kr.ac.smu.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.ac.smu.DTO.PlaceDTO;

@Mapper
public interface PlaceMapper {
	
	
	@Select("SELECT* FROM info WHERE id=#{id}")
	public PlaceDTO selectByPlaceId(@Param("id") String id);
	

}
