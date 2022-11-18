package edu.kh.project.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 필터 등록 + 필터링할 주소 매핑
@WebFilter(filterName ="loginFilter",   // 필터 이름, 필터가 여러 개 존재할 때, 순서 지정 시 사용 
										// web.xml에서 여러 개의 필터가 존재할 때, 순서 지정
			urlPatterns = {"/member/myPage/*","/member/logout"}) // 필터링할 요청 주소 (패턴 가능)
																 // 주소창에 입력할 경우 필터를 넣은 주소 패턴 적용 
public class LoginFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {	
		// 필터 생성 시 수행 (필터가 정상 작동해서 생성되면 콘솔창에 보이기)
		System.out.println("로그인 필터 생성");
	}

	public void destroy() {	
		// 서버 실행 중 필터 내용 변경 시 수행 후 init()다시 수행
		System.out.println("로그인 필터 파괴"); //(필터가 정상 작동해서 파괴되면 콘솔창에 보이기)
	}

						
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		// 필터는 클라이언트의 요청이 되자마자 
		// 또는 응답이 되기 직전에 필터링 코드를 추가할 수 있는 기능 
		
		// 다운 캐스팅 진행
		// 현재 매개변수 ServletRequest, ServletResponse는 HttpServletRequest,HttpServletResponse의 최상위 부모로 
		// 현재 다형성이 적용된 상태로 다운캐스팅이 필요하다. 
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		// 로그인 여부 확인
		// -> session에 loginMember가 있는지 확인
		HttpSession session = req.getSession();
		
		if(session.getAttribute("loginMember")==null) { // 로그인 X
			
			// 메인 페이지로 리다이렉트
			resp.sendRedirect("/"); 
			
		} else { // 로그인 O
			
			// 연결된 다음 필터로 이동(없으면 Servlet / JSP로 이동)
			chain.doFilter(request, response);
		}
		
		
		
	}


}
