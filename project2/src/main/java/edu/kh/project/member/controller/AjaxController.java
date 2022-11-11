package edu.kh.project.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import edu.kh.project.member.model.service.AjaxService;
import edu.kh.project.member.model.vo.Member;


@Controller // ��û -> �˸��� ���� -> ��� ��ȯ -> �˸��� view ���� ���� + bean ��� 
public class AjaxController {

	@Autowired
	private AjaxService service;
	
	// �̸��� �ߺ� �˻� 
	@GetMapping("/emailDupCheck")
	@ResponseBody // ��ȯ�Ǵ� ���� jsp ��ΰ� �ƴ� �� ��ü�� �ν�
	public int emailDupCheck(String memberEmail) {
		                   // data: {"memberEmail" : memberEmail.value}
		// System.out.println(memberEmail);
		
		int result = service.emailDupCheck(memberEmail);
		
		// @ResponseBody ������̼� ���п�
		// result�� View Resolver�� ���޵��� �ʰ� ȣ���ߴ� ajax �Լ��� ��ȯ��
		return result;
	}
	
	
	// �г��� �ߺ��˻� 
	@GetMapping("/nicknameDupCheck")
	@ResponseBody // ��ȯ�Ǵ� ���� jsp ��ΰ� �ƴ� �� ��ü�� �ν�
	public int nicknameDupCheck(String memberNickname) {
	
		int result = service.nicknameDupCheck(memberNickname);
		return result;
	}
	
	// �̸��Ϸ� ȸ�� ���� ��ȸ(JSON,GSON Ȱ��)
	@PostMapping("/selectEmail")
	@ResponseBody
	public String selectEmail(String email) {
		
		Member member = service.selectEmail(email);
		
		// System.out.println(member);
		
		// JSON �������� Member��ü �ۼ�
		// {"memberEmail" : "user01@kh.or.kr", "memberNickname" : "������"}
		// String result = "{\"memberEmail\" : \"user01@kh.or.kr\", \"memberNickname\" : \"123\"}";
		// return result;
		
		// GSON ���̺귯���� �̿��ؼ� Member��ü -> JSON ��ȯ (String)
		return new Gson().toJson(member);
	}
	
//	// �̸��Ϸ� ȸ�� ���� ��ȸ(jackson-databind Ȱ��)
//		@PostMapping("/selectEmail")
//		@ResponseBody
//		public Member selectEmail(String email) {
//			
//			// jackson�̶�?
//			// JS��ü <-> Java��ü <-> JSON
//			
//			Member member = service.selectEmail(email);
//			
//			return member; 
//			// Java��ü ��ȯ �� Jackson ���̺귯���� JS��ü�� ��ȯ
//		
//		}
	
	
	// ȸ�� ��� ��ȸ 
	@GetMapping("/selectMemberList")
	@ResponseBody
	public String selectMemberList() {
		
		List<Member> memberList = service.selectMemberList();
		
		// ��ü 1���� ǥ�� == JSON
		// ��ü �������� ��� �迭 == JSONArray
		// "[{},{},{}]"
		
		return new Gson().toJson(memberList);
	}
	
	
	
	
}
