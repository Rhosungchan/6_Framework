package edu.kh.project.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.member.model.vo.Member;

@Repository // �������� bean ��� + ����(IOC)
public class MyPageDAO {

		@Autowired // ���������κ��� bean�� ���� ����(DI)
		private SqlSessionTemplate sqlSession;

		
		
		/** ȸ�� ���� ���� DAO
		 * @param inputMember
		 * @return result
		 */
		public int updateInfo(Member inputMember) {
			
			return sqlSession.update("myPageMapper.updateInfo", inputMember);
		}



		/** ��ȣȭ�� ��й�ȣ ��ȸ DAO
		 * @param memberNo
		 * @return encPw
		 */
		public String selectEncPw(int memberNo) {
			
			return sqlSession.selectOne("myPageMapper.selectEncPw",memberNo);
		}



		/** ��й�ȣ ���� DAO
		 * @param paramMap
		 * @return result
		 */
		public int changePw(Map<String, Object> paramMap) {
			return sqlSession.update("myPageMapper.changePw", paramMap);
		}



		/** ȸ�� Ż�� DAO
		 * @param memberNo
		 * @return result
		 */
		public int memberDelete(int memberNo) {
			return sqlSession.update("myPageMapper.memberDelete", memberNo);
		}



	
}
