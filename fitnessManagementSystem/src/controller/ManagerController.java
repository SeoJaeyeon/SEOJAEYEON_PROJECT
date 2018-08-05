package com.fitnessmanagement.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fitnessmanagement.mapper.AccountMapper;
import com.fitnessmanagement.mapper.ManagerMapper;
import com.fitnessmanagement.mapper.MemberMapper;
import com.fitnessmanagement.mapper.TrainerMapper;
import com.fitnessmanagement.service.AccountService;
import com.fitnessmanagement.service.s3.S3Wrapper;
import com.fitnessmanagement.user.Account;
import com.fitnessmanagement.user.Manager;
import com.fitnessmanagement.user.Member;
import com.fitnessmanagement.user.Trainer;

@Controller
public class ManagerController {
	
	private Manager manager;
	@Autowired
	private AccountService accountService;
	@Autowired
	private ManagerMapper managerMapper;	
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private TrainerMapper trainerMapper;
	@Autowired
	private S3Wrapper s3Service;
	
	@GetMapping("/manager")
	public String manager(@RequestParam("id")String id,HttpServletRequest req, HttpServletResponse res){

		System.out.println(id);
		this.manager=managerMapper.findManagerByManagerId(id);
		System.out.println(manager.getCenter_id()+"/////");

		return "manager/manager";
	}
	
	@GetMapping("/manager/addUser")
	public String addUserByGet(){
		return "manager/addUser";
	}
	
	@PostMapping("/manager/addUser")
	public String addUserByPose(Member member,Model model){
		Account account=new Account();
		account.setId(member.getId());
		account.setPassword(member.getPassword());
		member.setCenter_id(this.manager.getCenter_id());
		try{
			accountService.save(account, "ROLE_MEMBER", "MEMBER");
			memberMapper.insertMember(member);
		}catch(Exception ex){
			ex.printStackTrace();
			model.addAttribute("message","사용자 추가 에러!!!");
			return "/manager/addUser";
		}
		
		model.addAttribute("message","정상적으로 등록되었습니다.");
		return "/manager/addUser";
	}

	@GetMapping("/manager/addTrainer")
	public String addTrainerByGET(){
		return "/manager/addTrainer";
	}
	@PostMapping("/manager/addTrainer")
	public String addTrainer(Trainer trainer,Model model,HttpServletRequest req,@RequestParam("file") MultipartFile multipartFile){
		Account account=new Account();
		account.setId(trainer.getId());
		account.setPassword(trainer.getPassword());
		trainer.setCenter_id(this.manager.getCenter_id());
		try{
			accountService.save(account, "ROLE_TRAINER", "TRAINER");
			trainerMapper.insertTrainer(trainer);
			s3Service.upload(multipartFile, "traienr");
		}catch(Exception ex){
			ex.printStackTrace();
			model.addAttribute("message","사용자 추가 에러!!!");
			return "/manager/addTrainer";
		}
		model.addAttribute("message","정상적으로 추가되었습니다!!");
		return "/manager/addTrainer";
	}

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        return s3Service.upload(multipartFile, "trainer");
    }
	
}
