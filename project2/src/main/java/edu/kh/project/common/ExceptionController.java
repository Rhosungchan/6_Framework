package edu.kh.project.common;

import java.sql.SQLException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// ���� ó���� ��Ʈ�ѷ�
@ControllerAdvice
public class ExceptionController {

	
	// ������Ʈ ���ο��� �߻��ϴ� ��� ���� ó��
 	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		
		// �Ű����� Exception e : �߻��� ���� ���� �޴� �Ű�����
		e.printStackTrace();
		
		model.addAttribute("errorMessage","���� �̿� �� ������ �߻��Ͽ����ϴ�.");
		model.addAttribute("e",e);
		
		return "common/error";
 	}
 	
 	
 	
 	// ���� ���� �������� ó�� ���� ============================================
// 	@ExceptionHandler(SQLException.class)
// 	public String exceptionHandler(Exception e, Model model) {
// 		
// 		// �Ű����� Exception e : �߻��� ���� ���� �޴� �Ű�����
// 		e.printStackTrace();
// 		
// 		model.addAttribute("errorMessage","���� �̿� �� ������ �߻��Ͽ����ϴ�.");
// 		model.addAttribute("e",e);
// 		
// 		return "common/error";
// 	}
// 	@ExceptionHandler(RuntimeException.class)
// 	public String exceptionHandler(Exception e, Model model) {
// 		
// 		// �Ű����� Exception e : �߻��� ���� ���� �޴� �Ű�����
// 		e.printStackTrace();
// 		
// 		model.addAttribute("errorMessage","���� �̿� �� ������ �߻��Ͽ����ϴ�.");
// 		model.addAttribute("e",e);
// 		
// 		return "common/error";
// 	}
	
}
