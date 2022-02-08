package chapter15;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthCheckInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session!=null) {
			Object authinfo = session.getAttribute("authInfo");
			if(authinfo != null) {
				return true;
			}
		}
		// 세션이 비어있다면 로그인 페이지로 이동
		response.sendRedirect(request.getContextPath()+"/login");
		
		return false;
	}
	
	
	
}
