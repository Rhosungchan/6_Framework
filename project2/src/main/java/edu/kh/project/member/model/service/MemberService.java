package edu.kh.project.member.model.service;

import edu.kh.project.member.model.vo.Member;

/*
 	Service Interface ��� ����
 	
 	1. ������Ʈ�� ��Ģ���� �ο��ϱ� ���ؼ� 
 	
 	2. Ŭ�������� ���յ��� ��ȭ��Ű�� ���� 
  	   --> ���� ������ ���(��ü������ ����)	
  	   
   	3. AOP�� ����ϱ� ���� 
   	   --> spring-proxy�� �̿��Ͼ� AOP �ڵ尡 �����ϴµ� 
   	       �� spring-proxy�� Service �������̽��� ��ӹ޾� ���� 
  	
 */
public interface MemberService {

	/** �α��� ����
	 * @param inputMember(Email / Pw)
	 * @return loginMember
	 */
	public abstract Member login(Member inputMember);

	
	/** ȸ�� ���� ����
	 * @param inputMember
	 * @return result
	 */
	public abstract int signUp(Member inputMember);
	
	
}
