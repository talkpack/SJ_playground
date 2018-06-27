package action.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;
import action.command.CommandUri;
import model.member.MemberDao;

public class IdCheckAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		System.out.println(request.getParameter("id"));
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String id=request.getParameter("id");
		
		MemberDao memberDao = MemberDao.getInstance();
		response.getWriter().write(memberDao.idCheck(id) + "");
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			System.out.println("쿠키 있음!!!!!!!!!!!!!!!!");
			for(int i = 0; i < cookies.length; i++){
				if(cookies[i].getName().equals("mycookie")){
					Cookie cookie = new Cookie("mycookie", String.valueOf(memberDao.idCheck(id)));
					cookie.setMaxAge(30);// 30초
					response.addCookie(cookie);
				} else {
					Cookie cookie = new Cookie("mycookie",String.valueOf(memberDao.idCheck(id)));
					cookie.setMaxAge(30);// 30초
					response.addCookie(cookie);
				}
			}
		}
	
		System.out.println(memberDao.idCheck(id));
		
		for(int i = 0; i < cookies.length; i++) {
			System.out.print(cookies[i].getName()+"="+cookies[i].getValue()+";");
		}System.out.println();
		
		return CommandUri.idCheckView;
	}
}
