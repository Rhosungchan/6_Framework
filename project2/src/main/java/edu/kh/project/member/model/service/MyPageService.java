package edu.kh.project.member.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import edu.kh.project.member.model.vo.Member;

// �������� ���� + �������� ��� + AOP ��� ��� 
public interface MyPageService {


	
	/* ȸ�� ���� ���� ����
	 * @param inputMember
	 * @return result
	 */
	/* public abstract */ int updateInfo(Member inputMember);

	/** ��й�ȣ ���� ����
	 * @param paramMap
	 * @return
	 */
	int changePw(Map<String, Object> paramMap);

	/** ȸ�� Ż�� ���� 
	 * @param memberNo
	 * @param memberPw
	 * @return
	 */
	int memberDelete(int memberNo, String memberPw); 
	
	
	
}
