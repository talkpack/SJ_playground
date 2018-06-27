package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;
import action.command.CommandUri;
import model.member.MemberDao;

public class PwSearchResultAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String ph1=request.getParameter("ph1");
		String ph2=request.getParameter("ph2");
		String ph3=request.getParameter("ph3");
		String question=request.getParameter("question");
		String answer=request.getParameter("answer");
		
		MemberDao memberDao=MemberDao.getInstance();
		String pwd=memberDao.searchPw(id, ph1, ph2, ph3, question, answer);
		
		request.setAttribute("id", id);
		request.setAttribute("pwd", pwd);
		
		return CommandUri.pwSearchResultView;
	}
}
