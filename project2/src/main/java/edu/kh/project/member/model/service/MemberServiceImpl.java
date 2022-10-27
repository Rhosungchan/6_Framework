package edu.kh.project.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dao.MemberDAO;
import edu.kh.project.member.model.vo.Member;


// @Service()          => bean ��� �� �̸��� Ŭ���������� ��� (memberServiceImpl)
// @Service("�̸�")    => bean ��� �� �̸��� ������ �̸����� ���

@Service()  // Service ���̾�, ����Ͻ� ������ ���� Ŭ�������� ��� + bean ���
public class MemberServiceImpl implements MemberService{

	// MemberDAO bean�� ������ ����(DI)
	@Autowired
	private MemberDAO dao;
	
	
	// spring-security.xml���� ����� bean�� ������ ����(DI)
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	
	// �α��� ����
	@Override
	public Member login(Member inputMember) {

//		System.out.println("�Է��� ��й�ȣ : " + inputMember.getMemberPw());
//		System.out.println("��ȣȭ ��й�ȣ : " + bcrypt.encode(inputMember.getMemberPw()));
		
		
		
		// bcrypt �̿� �� �α��� ���
		
		// 1. �̸����� ��ġ�ϴ� ȸ�� ������ DB���� ��ȸ
		//    �ݵ�� ��й�ȣ(MEMBER_PW) ���ԵǾ�� ��.
		
		Member loginMember = dao.login(inputMember.getMemberEmail()); 
		
		// 2. �Է� ���� ��й�ȣ (��)
		//    ��ȸ�� ��ȣȭ�� ��й�ȣ ��
		//    -> BCryptPasswordEncoder.matchs(��, ��ȣ��) �̿� 
		//       -> ������ true, �ƴϸ� false
		
		
		if(loginMember != null) {	// ���̵� ���� �Է�
			
			if(bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
				// 3-1. ��й�ȣ�� ��ġ�ϸ� ��ȸ�� ȸ�� ���� ��ȯ
				//      ��, ��й�ȣ�� ����
				loginMember.setMemberPw(null);
				
			} else {
				// 3-2. ��й�ȣ�� ��ġ���� ������ null�� ��ȯ
				loginMember = null;
			}
		}
		
		
		return loginMember;
	}

	/*
	 	[ ���������� Ʈ������� ó���ϴ� ��� ] 
	
	 	- ������ Ʈ����� ó�� 
	 	  1) <tx:advice>�� �̿��� XML �ۼ� ���
	 	  2) @Transactional ������̼� �ۼ� ���
	 	      = Ŭ����, �޼��� ���� �ۼ�   
	          = ���� : AOP�� �̿��� ��� -> Service Interface �ʿ�
	                   Ʈ����� �Ŵ����� bean���� ��ϵǾ� �־�� ��.
	                   (root-context.xml)
	                   
        @Transactional ������̼� Ư¡
        - ���ܰ� �߻��� ��� rollback �ڵ� ����
        - ������ �⺻���� RuntimeException
          => SQL ���� �� �߻��ϴ� ���� == SQLException(RuntimeException ����)
                                          (������ ���� X)
          => �ٸ� ���ܿ��� rollback�� ����ǰ� �ʹٸ� 
             rollbackFor = ���� Ŭ���� �ۼ��ϸ� �ȴ�.    
	 
	 */ 
	
	
	
	// ȸ������ ���� 
	@Transactional(rollbackFor = Exception.class) // ��� ���� �߻� �� �ѹ� 
	@Override
	public int signUp(Member inputMember) {
		
		// ��� ��ȣ ��ȣȭ
		String emcPw = bcrypt.encode(inputMember.getMemberPw());
		inputMember.setMemberPw(emcPw);
		
		// DAO ȣ�� �� ��� ��ȯ �ޱ�
		int result = dao.signUp(inputMember);
		
		return result;
	}

	
	
	
	
}
