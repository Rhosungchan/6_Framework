package edu.kh.project.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.project.member.model.service.MemberService;
import edu.kh.project.member.model.service.MemberServiceImpl;
import edu.kh.project.member.model.vo.Member;

// ȸ�� ���� ��û�� ���� Controller
// Controller : ���������̼� ���̾�
//              �� ���ø����̼����� ���� ���� Ŭ���̾�Ʈ�� ��û��
//              �˸��� ���񽺷� �����ϰ� ���񽺿��� ��ȯ�� ����� ����
//              �˸��� ȭ������ �����ϴ� ����� �����ϴ� ���� 

// Controller ������̼� : �����Ϸ����� ���� Ŭ������ Controller���� �˷���
// + bean ��� (Spring�� ��ü�� ���� ����)
@Controller
public class MemberController {

	// * �������� ����� Service ��ü ���� 
	
	// @Autowired
	// bean scanning�� ���� bean���� ��ϵ� ��ü �� 
	// �˸��� ��ü�� DI(������ ����)���ִ� ������̼�

	// �ڵ� ���� ��Ģ :  Ÿ���� ���ų� ��� ������ bean�� �ڵ����� DI
	
	@Autowired
	private MemberService service;
	
	//	@RequestMapping : Ŭ���̾�Ʈ�� ��û�� ó���� Ŭ����/�޼��带 �����ϴ� ������̼�
	// == Handler Mapping
	
	
	// ** �Ķ���͸� ���� �޴� ���
	// 1. HttpServletRequest�� �̿��ϴ� ���
	
	
	// �α��� ��û(POST)
	// @RequestMapping(value="/member/login", method = RequestMethod.POST)
	// ���
	public String login(HttpServletRequest req) {

		String inputEmail = req.getParameter("inputEmail");
		String inputPw = req.getParameter("inputPw");
		
		System.out.println(inputEmail);
		System.out.println(inputPw);
		
		
		// * forward  ��� : prefix / suffix�� ������ ������ jsp��� 
		// * redirect ��� : "redirect : ��û�ּ�";  
		return "redirect:/" ;
	}
	
	
	// 2.@RequestParam ������̼� ��� 
	// - �޼��� �Ű������� ���޹��� �Ķ���͸� �����ϴ� ������̼�
	
	// [�Ӽ�]
	   // value : ���� ���� input �±��� name �Ӽ���
	   
	   // required : �Էµ� name �Ӽ��� �Ķ���� �ʼ� ���� ����(�⺻�� true)
	   // -> required = true�� �Ķ���Ͱ� �������� �ʴ´ٸ� 400 Bad Request ���� �߻�
	   // -> required = true�� �Ķ���Ͱ� null�� ��쿡�� 400 Bad Request
	   // -> required = false�� ��� ���޵� �Ķ���Ͱ� ������ null
	
	   // defaultValue : �Ķ���� �� ��ġ�ϴ� name �Ӽ� ���� ���� ��쿡 ������ �� ����.
	   // -> required = false�� ��� ���
	
	   // * @RequestParam �����ϱ� * 
	   // ���� : �Ű� ���� �̸� == input name �Ӽ� ��
	
	
//	@RequestMapping(value="/member/login", method = RequestMethod.POST)
	public String login(@RequestParam("inputEmail") String email, 
			@RequestParam(value="inputPw2", required=false, defaultValue="1234") String Pw, String inputPw) {
		
		
		System.out.println(email);
		System.out.println(Pw);
		System.out.println(inputPw);
		
		return "redirect:/";
	}
	
//	@RequestParam ������ �̿��ؼ� ª�� �ڵ� �ۼ� ���� 
//	@RequestMapping(value="/member/login", method = RequestMethod.POST)
	public String login( String inputEmail, String inputPw) {
		
		
		System.out.println(inputEmail);
		System.out.println(inputPw);
		
		return "redirect:/";
	}
	
	
	// 
	// == @PostMapping(value="/member/login", method=RequestMethod.POST)
//	@PostMapping("/member/login") // POST ����� /member/login ��û�� ����
//	@GetMapping("/member/login")  // GET ����� /member/login ��û�� ����
	
	
//	---------------------------------------------------------------------2022.10.25
	// 3. @ModelAttribute ������̼� ���
	
	// [�ۼ���]
	// - @ModelAttribute VOŸ�� �Ű� ������
	// -> �Ķ������ name �Ӽ� ���� ������ VO�� �ʵ��� ���ٸ� 
	//    �ش� VO�� ��ü�� �ʵ忡 �Ķ���͸� ����
	
	// [����]
	// 1. name �Ӽ� ���� �ʵ� ���� ���ƾ� ��.
	// 2. VO�� �ݵ�� �⺻ �����ڰ� �����ؾ� ��.
	// 3. VO�� �ݵ�� setter�� �����ؾ� ��.
	
	
	// * @ModelAttribute ������̼� ������ ����!
	// == Ŀ�ǵ� ��ü 
	
	@PostMapping("/member/login")
	public String login(/* @ModelAttribute */ Member inputMember) {
		
//		System.out.println(inputMember);
		
		// [ Servlet ������Ʈ ] 
		// Service ��ü ���� 
		// try ~catch ���ο� �ڵ� �ۼ�
		
		
		// [ Spring ������Ʈ ]
		
		
		// ���� ȣ�� �� ��� ��ȯ �ޱ�
		Member loginMember = service.login(inputMember);
		
		// �α��� ���� �� loginMember�� ���ǿ� �߰� 
		
		// �α��� ���� �� "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�." ���ǿ� �߰�
		
		return "redirect:/";
	}
	
	
	
}
