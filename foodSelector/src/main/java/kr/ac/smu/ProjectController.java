package kr.ac.smu;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomMapEditor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import kr.ac.smu.DTO.DataDTO;
import kr.ac.smu.DTO.PlaceDTO;
import kr.ac.smu.DTO.PreferenceDTO;
import kr.ac.smu.mybatis.mapper.CustomMapper;
import kr.ac.smu.mybatis.mapper.PlaceMapper;


@Controller
public class ProjectController {
	Logger logger = LoggerFactory.getLogger(ProjectController.class);
	static final String appKey="";
	
	@Autowired
	CustomMapper customMapper;
	@Autowired
	PlaceMapper placeMapper;
	
	@RequestMapping(value="/crandom", method=RequestMethod.GET,produces = "application/json; charset=utf8")
	@ResponseBody
	public String completeRandom() throws ParseException{
		RestTemplate restTemplate = new RestTemplate(); 
		 
		HttpHeaders headers = new HttpHeaders(); 
		headers.add("Content-Type", "application/json;charset=UTF-8");

		headers.set("Authorization", appKey); //appKey 설정 ,KakaoAK kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk 이 형식 준수
		 
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();

		parameters.add("query", "%EA%B9%80%EC%B9%98%EC%B0%8C%EA%B0%9C");
		parameters.add("category_group_code", "FD6");
		parameters.add("x", "127.06283102249932");
		parameters.add("y", "37.514322572335935");


		HttpEntity entity = new HttpEntity("parameters", headers); 
		URI url=URI.create("https://dapi.kakao.com/v2/local/search/keyword.json?query=%EA%B9%80%EC%B9%98%EC%B0%8C%EA%B0%9C&category_group_code=FD6&x=127.213123&y=65.213123"); 
		//x -> x좌표, y -> y좌표 
		 //x=127.213123&y=65.213123
		ResponseEntity response= restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		//String 타입으로 받아오면 JSON 객체 형식으로 넘어옴
		
		JSONParser jsonParser = new JSONParser(); 
		JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString()); 
		//저는 Body 부분만 사용할거라 getBody 후 JSON 타입을 인자로 넘겨주었습니다
		//헤더도 필요하면 getBody()는 사용할 필요가 없겠쥬
		
		JSONArray docuArray = (JSONArray) jsonObject.get("documents");
		//documents만 뽑아오고  
		 
		JSONObject obj=(JSONObject) docuArray.get(0);

		return docuArray.get(0).toString();
		
		
	}
	
	@RequestMapping(value="/test", method=RequestMethod.POST,produces="application/json;charset=UTF-8" )
	@ResponseBody
	public Map<String,String> test(HttpServletRequest req) throws ParseException{
		Map<String, String> data=new HashMap<String, String>();
		data.put("x", req.getParameter("x").toString());
		data.put("y", req.getParameter("y").toString());
	
		System.out.println("sdad");
		logger.info("asd");
		return data;
	}
	
	// 완전 랜덤
	@RequestMapping(value="/completerandom", method=RequestMethod.POST,produces="application/json;charset=UTF-8" )
	@ResponseBody
	public Map<Integer,PlaceDTO> completeRandom(HttpServletRequest req) throws ParseException{
		Map<Integer, PlaceDTO> data=new HashMap<Integer, PlaceDTO>();
		RestTemplate restTemplate = new RestTemplate(); 

		HttpHeaders headers = new HttpHeaders(); 

		headers.add("Content-Type", "application/json;charset=UTF-8");
		headers.set("Authorization", appKey); //appKey 설정 ,KakaoAK kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk 이 형식 준수

		HttpEntity entity = new HttpEntity("parameters", headers); 
		URI url=URI.create("https://dapi.kakao.com/v2/local/search/keyword.json?query=%EA%B9%80%EC%B9%98%EC%B0%8C%EA%B0%9C&category_group_code=FD6&x="+req.getParameter("x")+"&y="+req.getParameter("y")); 
		//x -> x좌표, y -> y좌표 

		ResponseEntity response= restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		//String 타입으로 받아오면 JSON 객체 형식으로 넘어옴

		JSONParser jsonParser = new JSONParser(); 
		JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString()); 

		//저는 Body 부분만 사용할거라 getBody 후 JSON 타입을 인자로 넘겨주었습니다

		//헤더도 필요하면 getBody()는 사용할 필요가 없겠쥬
		JSONArray docuArray = (JSONArray) jsonObject.get("documents");
		//documents만 뽑아오고  

		JSONObject obj=(JSONObject) docuArray.get(0);
		PlaceDTO info=new PlaceDTO();

		info.setId(obj.get("id").toString());	
		info.setPlace_name(obj.get("place_name").toString());
		info.setCategory_name(obj.get("category_name").toString());
		info.setPhone(obj.get("phone").toString());
		info.setAddress_name(obj.get("address_name").toString());
		info.setRoad_address_name(obj.get("road_address_name").toString());
		info.setX(obj.get("x").toString());
		info.setY(obj.get("y").toString());
		info.setPlace_url(obj.get("place_url").toString());
		info.setDistance(obj.get("distance").toString());

		data.put(0,info);
		logger.info(new Date()+"/completerandom POST Request");
		return data;
	}
	
	
	@RequestMapping(value="/batis", method=RequestMethod.GET)
	@ResponseBody
	public PreferenceDTO testMybatis(){
		PreferenceDTO pd=null;

		pd=customMapper.selectByCustom("0000", "abc");

		
		return pd;
	}
	



}
