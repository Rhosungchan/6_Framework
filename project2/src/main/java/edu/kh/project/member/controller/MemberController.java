package edu.kh.project.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
@SessionAttributes({"loginMember","message","test2"})
// -> Model�� �߰��� �Ӽ� �� key�� ��ġ�ϴ� �Ӽ��� session scope �Ӽ����� �߰�
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
	
	// ******����******
	// Controller �޼��� �Ű������� ��ġ�� �ۼ��ϸ� 
	// �ڵ����� �����ǰų� ���� �� �ִ� ����
	// -> Spring Container���� Argument Resolver(�Ű����� �ذ��) �����ؼ�
	//    �����ϰ� ó���� 
	
	@PostMapping("/member/login")
	public String login(/* @ModelAttribute */ Member inputMember, Model model, RedirectAttributes ra, 
			             @RequestParam(value="saveId", required=false) String saveId, // üũ�ڽ� �� ������
			             HttpServletResponse resp, // ��Ű ���޿�
			             @RequestHeader(value="referer") String referer) // 
						 {
						 
		
		// Model : ������ ���޿� ��ü 
		//         - �����͸� Map�������� �����Ͽ� �����ϴ� ��ü
		//         - request scope�� �⺻��
		//           + @SessionAttribute ������̼ǰ� �Բ� �ۼ� �� 
		//              session scope�� ��ȯ ����
		
		// RedirectAttributes
		// - �����̷�Ʈ �� ���� �����ϴ� �뵵�� ��ü
		// - ���� �� : request scope
		// - redirect �� : session scope
		// - ���� �� : request scope
		
		
		// [ Servlet ������Ʈ ] 
		// Service ��ü ���� 
		// try ~catch ���ο� �ڵ� �ۼ�
		
		
		// [ Spring ������Ʈ ]
		
		
		// ���� ȣ�� �� ��� ��ȯ �ޱ�
		Member loginMember = service.login(inputMember);
		
		String path = null; // �����̷�Ʈ ��θ� ������ ����
		
		if(loginMember != null) {
			
			path = "/"; // ���� ������
			
			// �α��� ���� �� loginMember�� ���ǿ� �߰� 
			
			// addAttribute("k", V)  == req.setAttribute("K",V)
			model.addAttribute("loginMember", loginMember);
			// -> request scope ����
			
			// @SessionAttribute("loginMember")Ŭ���� ���� �߰�
			//  ->session scope�� ��ȯ
			
			//=============================================================
			// ��Ű ����
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			
			if(saveId != null) { // üũ �Ǿ��� ��
				
				// 1�⵿�� ��Ű ����
				cookie.setMaxAge(60*60*24*365);
				
				
			} else { // üũ �ȵǾ��� ��
				// 0�� ���� ��Ű ���� -> ������ ���ÿ� ���� 
				// -> Ŭ���̾�Ʈ�� ��Ű ������ ���� 
				cookie.setMaxAge(0);
				
			}
			// ��Ű�� ���Ǵ� ��� ���� 
			cookie.setPath("/"); // localhost �ؿ� ��� ��ο��� ��� 
			
			// ������ ��Ű�� ���� ��ü�� ��Ƽ� Ŭ���̾�Ʈ���� ����
			resp.addCookie(cookie);
			
			//=============================================================
		} else {
			// ���� : HttpServletRequest req;
			//        req.getHeader("referer");
			// new : @RequestHeader(value="referer") String referer
			//       path = referer;
			
			path = referer; // �α��� ��û �� ������ �ּ�(referer)
			
			
			// �α��� ���� �� "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�." ���ǿ� �߰�
			// model.addAttribute("message","���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			// -> ���� ������ �ּҿ� message �� ����
			// -> RedirectAttributes�� ��ȯ
		    
			ra.addFlashAttribute("message","���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			
			// addFlashAttribute() : ��� session scope�� �߰�
		}
		
		
		
		return "redirect:" + path;
	}
	
	// �α��� ������ �̵� 
	@GetMapping("/member/login")
	public String loginPage() {
		
		return "member/login";
	}
	
	// �α׾ƿ� 
	@GetMapping("/member/logout")
	public String logout(SessionStatus status) {
		
		// ���� : 
		// HttpServletRequest req;
		// HttpSession session = req.getSession();
		// session.invalidate();
		// �ȵ�.... Why? @SessionAttibute�� session scope�� ��ϵ� ���� ��ȿȭ ��Ű����
		//               SessionStatus��� ������ ��ü�� �̿��ؾ� �Ѵ�.
		
		status.setComplete(); // ���� ��ȿȭ
	
		
		
		return "redirect:/";
	}
	

	// ȸ�� ���� ������
		@GetMapping("/member/signUp")
		public String signUpPage() {
			return "member/signUp";
		}
	
	// ȸ��  ���� 
	@PostMapping("/member/signUp")
	public String signUp(Member inputMember /*Ŀ�ǵ� ��ü*/, 
		                 String[] memberAddress/*name �Ӽ� ���� memberAddress�� ���� �迭�� ��ȯ */,
		                 RedirectAttributes ra, 
		                 @RequestHeader("referer") String referer) {
		
		// �ѱ��� ������ ���� 
		// -> POST ��û �� ���ڵ� ó�� �ʿ� -> ���ڵ� ���� ó��(web.xml)
		
		// Spring�� 
		// 1) ���� name �Ӽ��� ���� input �±��� ���� 
		//    ��, ��, ��, .... �ڵ����� �ϳ��� ���ڿ��� �������.
		
		// 2) input type="text"�� ���� �ۼ����� ���� ���
		//    null�� �ƴ� ��ĭ("")���� ���� ���´�.
		
		// �ּҰ� �ۼ����� ���� ��� == null
		if(inputMember.getMemberAddress().equals(",,")) {
			
			inputMember.setMemberAddress(null);
			
		// �ּҰ� �ۼ��� ��� ==> �ּ�,,�ּ�,,�ּ�
		} else {
			inputMember.setMemberAddress(String.join(",,", memberAddress));
		}
		
		// ���� ȣ�� �� ��� ��ȯ �ޱ�
		int result = service.signUp(inputMember);
		
		String path = null; // �����̷�Ʈ ��� ���� 
		String message = null; // ������ �޼��� ���� ���� 
		
		
		if(result > 0) { // ���� ��
			path="/";
			message = "ȸ������ ����";
			
		} else {  // ���� ��
			path=referer;
			message = "ȸ������ ����...";
			
			// ���� �������� ���ư��� �� �Է��ߴ� ���� ���� ����
			inputMember.setMemberPw(null); // ��й�ȣ ���� 
			ra.addFlashAttribute("tempMember",inputMember);
		}
		
		ra.addFlashAttribute("message",message);
		
		
		return "redirect:"+path;
	}
	
	/*        ========================================================================================
	 		     [ ������ ���� ó�� ��� ] (3����, �ߺ� ��� ����)
	 			 
	 			 	
	 			 1���� : try-catch / throws ���� ó�� ���� 
	 			         -> �޼��� ���� ó�� 
	 			         
	 			         
	 			 2���� : @ExceptionHandler ������̼�
	 			         -> Ŭ���� ���� ó�� 
	 			            - �ϳ��� ��Ʈ�ѷ����� �߻��ϴ� ���ܸ� �ϳ��� �޼��忡 ��Ƽ� ó��
	 			 
	 			 
	 			 3���� : @ControllerAdvice ������̼� 
	 			         -> ����(�� ���ø����̼�)���� �߻��ϴ� ���ܸ� ��Ƽ� ó��
	 			            - ������ Ŭ������ �ۼ� 
	 			 
	          ========================================================================================
	 */ 
	
	// MemberController���� �߻��ϴ� ���ܸ� ��Ƽ� ó��
//	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		
		// �Ű����� Exception e : �߻��� ���� ���� �޴� �Ű�����
		e.printStackTrace();
		
		model.addAttribute("errorMessage","ȸ������ ���� �̿� �� ������ �߻��Ͽ����ϴ�.");
		model.addAttribute("e",e);
		
		return "common/error";
	}
	
	
	
	
	
	
	
}

