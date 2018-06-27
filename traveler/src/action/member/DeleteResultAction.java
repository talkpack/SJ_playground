package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.command.CommandAction;
import action.command.CommandUri;
import model.member.MemberDao;

public class DeleteResultAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String loginId=(String) session.getAttribute("loginId");
		String pwd=request.getParameter("pwd");
		MemberDao memberDao=MemberDao.getInstance();
		boolean res=memberDao.memberDelete(loginId,pwd);
		
		if(res==true) {
			session.invalidate();
		}
		
		request.setAttribute("res", res);
		
		return CommandUri.deleteResultView;
	}
}
