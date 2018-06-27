package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;
import action.command.CommandUri;
import model.member.MemberDao;

public class IdSearchResultAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String year=request.getParameter("year");
		String month=request.getParameter("month");
		String day=request.getParameter("day");
		
		
		
		MemberDao memberdao=MemberDao.getInstance();
		String id=memberdao.searchId(name, year, month, day);
		
		request.setAttribute("name", name);
		request.setAttribute("id", id);
		
		System.out.println("class action.member.IdSearchResultAction");
		
		return CommandUri.idSearchResultView;
	}

}
