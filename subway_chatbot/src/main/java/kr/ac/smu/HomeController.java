package kr.ac.smu;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.net.URI;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.LogManager;

import org.aspectj.lang.annotation.DeclareError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.assistant.v1.model.RuntimeIntent;

import kr.ac.smu.mapper.JSONMapper;
import kr.ac.smu.mapper.RealTimeArrivalList;
import kr.ac.smu.pf.KeyboardVO;
import kr.ac.smu.pf.MessageVO;
import kr.ac.smu.pf.RequestMessageVO;
//import kr.ac.smu.pf.RequestMessageVO;
import kr.ac.smu.pf.ResponseMessageVO;


@RestController
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	//테스트
	@RequestMapping(value="/watson", method=RequestMethod.GET ,produces = "application/json;charset=UTF-8")
	public String watson(){
		LogManager.getLogManager().reset();

	    // Set up Assistant service.
	    Assistant service = new Assistant("2018-08-06");
	    service.setUsernameAndPassword("", // replace with service username
	                                   ""); // replace with service password
	    String workspaceId = ""; // replace with workspace ID

	    // Start assistant with empty message.
	    MessageOptions options = new MessageOptions.Builder(workspaceId).build();
	    

	   


	        String inputText = "홍대입구역인데 언제와?";
	        InputData input = new InputData.Builder(inputText).build();
	        options = new MessageOptions.Builder(workspaceId).input(input).build();
	     
	        // Send message to Assistant service.
	        MessageResponse response = service.message(options).execute();      
	        String responseText = response.getOutput().getText().get(0);
	        List<RuntimeIntent> responseIntents = response.getIntents();

	        // If an intent was detected, print it to the console.
	        if(responseIntents.size() > 0) {
	          System.out.println("Detected intent: #" + responseIntents.get(0).getIntent());
	        }

	    return response.getOutput().getText().get(0);
	    
	}
	//테스트
	@RequestMapping(value="/subway", method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public JsonNode test() throws JsonParseException, JsonMappingException, IOException{
		RestTemplate restTemplate = new RestTemplate(); 
		 
		HttpHeaders headers = new HttpHeaders(); 
		headers.add("Content-type", "application/json; charset=UTF-8");
		
		 
		HttpEntity entity = new HttpEntity("parameters", headers); 

		logger.info(URLEncoder.encode("서울","UTF-8"));
		URI url=URI.create("http://swopenapi.seoul.go.kr/api/subway//json/realtimeStationArrival/0/5/"+URLEncoder.encode("서울","UTF-8")); 
		//x -> x좌표, y -> y좌표 
		 
		ResponseEntity response= restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		//String 타입으로 받아오면 JSON 객체 형식으로 넘어옴
	
		ObjectMapper obj=new ObjectMapper();
		RealTimeArrivalList rl=new RealTimeArrivalList();
		//d=obj.readValue(response.getBody(), JSONMapper.class);
		
		JsonNode node=obj.readValue(response.getBody().toString(), JsonNode.class);
		JsonNode realtimeArrivalList=node.get("realtimeArrivalList");
	
		return realtimeArrivalList.get(0);
		
	}  
	
	@RequestMapping(value = "/keyboard", method = RequestMethod.GET, produces="application/json; charset=UTF-8")
	public KeyboardVO keyboard()
	{
		KeyboardVO keyboard=new KeyboardVO();
		keyboard.setType("text");
		return keyboard;
	}
	
	@RequestMapping(value = "/message", method = RequestMethod.POST, produces="application/json; charset=UTF-8")
	@ResponseBody
	public ResponseMessageVO message(@RequestBody RequestMessageVO vo) throws JsonParseException, JsonMappingException, IOException
	{
		ResponseMessageVO res_vo=new ResponseMessageVO();
		MessageVO mes_vo=new MessageVO();
		KeyboardVO keyboard=new KeyboardVO();
		keyboard.setType("text");
		res_vo.setKeyboard(keyboard);
		if(!vo.getType().equals("text"))
		{
			//텍스트 타입만 허용할 것이기 때문에
			
			mes_vo.setText("텍스트 타입만 허용하고 있습니다");

			res_vo.setMessage(mes_vo);
			return res_vo;
		}
		
		//텍스트 요청 받
		String query=vo.getContent();		
		

	    // Set up Assistant service.
	    Assistant service = new Assistant("2018-08-06");
	    service.setUsernameAndPassword("", // replace with service username
	                                   ""); // replace with service password
	    String workspaceId = ""; // replace with workspace ID

	    // Start assistant with empty message.
	    MessageOptions options = new MessageOptions.Builder(workspaceId).build();
	    
	    InputData input = new InputData.Builder(query).build();
	    options = new MessageOptions.Builder(workspaceId).input(input).build();
	     
	        // Send message to Assistant service.
	    MessageResponse mes_response = service.message(options).execute();      
	    String responseText = mes_response.getOutput().getText().get(0);
	    List<RuntimeIntent> responseIntents = mes_response.getIntents();

	        // If an intent was detected, print it to the console.
	    if(responseIntents.size() > 0) {
	    System.out.println("Detected intent: #" + responseIntents.get(0).getIntent());
	    }

	    String station= mes_response.getOutput().getText().get(0);
		

		RestTemplate restTemplate = new RestTemplate(); 
		 
		HttpHeaders headers = new HttpHeaders(); 
		headers.add("Content-type", "application/json; charset=UTF8");
		try{
		String encodeStation=URLEncoder.encode(station,"UTF-8");
		HttpEntity entity = new HttpEntity("parameters", headers); 
		//http://swopenapi.seoul.go.kr/api/subway/sample/json/realtimeStationArrival/0/5/서울
		URI url=URI.create("http://swopenapi.seoul.go.kr/api/subway//json/realtimeStationArrival/0/5/"+encodeStation); 
		//x -> x좌표, y -> y좌표 
		 
		ResponseEntity response= restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		//String 타입으로 받아오면 JSON 객체 형식으로 넘어옴
		ObjectMapper obj=new ObjectMapper();
		RealTimeArrivalList rl=new RealTimeArrivalList();
		//d=obj.readValue(response.getBody(), JSONMapper.class);
		
		JsonNode node=obj.readValue(response.getBody().toString(), JsonNode.class);
		JsonNode realtimeArrivalList=node.get("realtimeArrivalList");
		mes_vo.setText(realtimeArrivalList.get(0).get("trainLineNm")+" -> "+realtimeArrivalList.get(0).get("arvlMsg2"));
		res_vo.setMessage(mes_vo);
		return res_vo;
		
		}catch(NullPointerException exNull){
			mes_vo.setText("올바른 역 명을 입력해주세요");
			res_vo.setMessage(mes_vo);
			return res_vo;
		}
		catch(Exception ex){
			ex.printStackTrace();
			mes_vo.setText("서버뻑가");
			res_vo.setMessage(mes_vo);
			return res_vo;
		}

	}
	
}
