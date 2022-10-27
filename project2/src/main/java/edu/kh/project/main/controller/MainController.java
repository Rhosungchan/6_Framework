package edu.kh.project.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// Controller ������̼� : �����Ϸ����� ���� Ŭ������ Controller���� �˷���
//                          + bean ��� (Spring�� ��ü�� ���� ����)
@Controller
public class MainController {
	
	// forward �� Controller�� ��ȯ���� 
	// String �Ǵ� ModelAndView �� �� �ϳ��̴�.
	
	// GET ��� "/"�� ��û�� ���� �ش� �޼��忡�� ó��
		// == Handler Mapping
		@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainPage() {
		
			// ���� ������ ��û�� �ʿ��� �ڵ� �ۼ�...
			
			// [ forward ��� ]
 			// - View Resolver�� prefix / suffix�� ������ jsp ��θ� �ۼ� 
			
			// /WEB-INF/views/common/main.jsp
			// prefix : /WEB-INF/views/
			// suffix : .jsp
		return "common/main";
	}
		
	
}		
