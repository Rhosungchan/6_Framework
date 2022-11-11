package edu.kh.project.member.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.member.model.vo.Member;

@Repository // DB �����ϴ� ���� + bean ��� 
public class AjaxDAO {

	@Autowired // ���� �ڷ����� bean���� ��ϵǾ� ������ �ڵ����� DI
	private SqlSessionTemplate sqlSession;

	// SqlSessionTemplate : Ŀ�ؼ� + ���̹�Ƽ�� + ������ TX ����
	

	/** �̸��� �ߺ� �˻� DAO
	 * @param memberEmail
	 * @return
	 */
	public int emailDupCheck(String memberEmail) {
		return sqlSession.selectOne("ajaxMapper.emailDupCheck", memberEmail);
	}

	/** �г��� �ߺ� �˻� DAO
	 * @param memberNickname
	 * @return
	 */
	public int nicknameDupCheck(String memberNickname) {
		return sqlSession.selectOne("ajaxMapper.nicknameDupCheck",memberNickname);
	}

	/** �̸��Ϸ� ȸ�� ���� ��ȸ DAO
	 * @param email
	 * @return
	 */
	public Member selectEmail(String email) {
		return sqlSession.selectOne("ajaxMapper.selectEmail" ,email);
	}

	/** ȸ�� ��� ��ȸ DAO
	 * @return
	 */
	public List<Member> selectMemberList() {
		// selectList() : ��ȸ ����� �� ���� resultType �Ǵ� resultMap�� �´� 
		// VO��ü�� ��� List�� �߰��Ͽ� ��ȯ 
		
		
		return sqlSession.selectList("ajaxMapper.selectMemberList");
	}
	
	
	
}
