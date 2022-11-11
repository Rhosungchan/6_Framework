package edu.kh.project.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.member.model.dao.AjaxDAO;
import edu.kh.project.member.model.vo.Member;

@Service // �����Ͻ� ���� ó�� ���� + bean���
public class AjaxServiceImpl implements AjaxService{

	@Autowired
	private AjaxDAO dao;

	// �̸��� �ߺ� �˻� 
	@Override
	public int emailDupCheck(String memberEmail) {
		
		return dao.emailDupCheck(memberEmail);
	}

	// �г��� �ߺ� �˻� 
	@Override
	public int nicknameDupCheck(String memberNickname) {
		
		return dao.nicknameDupCheck(memberNickname);
	}
	
	// �̸��Ϸ� ȸ�� ���� ��ȸ
	@Override
	public Member selectEmail(String email) {
		return dao.selectEmail(email);
	}

	//  ȸ�� ��� ��ȸ
	@Override
	public List<Member> selectMemberList() {
		return dao.selectMemberList();
	}
	
}
