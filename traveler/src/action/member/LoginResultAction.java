package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.command.CommandAction;
import action.command.CommandUri;
import model.member.MemberDao;
import model.travel.travelboard.TravelBoardDao;

public class LoginResultAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		MemberDao memberDao= MemberDao.getInstance();
		int result=memberDao.loginCheck(id, pwd);
		
		if(result==1) {
			TravelBoardDao dbPro = TravelBoardDao.getInstance();
			MemberDao dbPro2 = MemberDao.getInstance();
			
			
			int articleCount = dbPro.getArticleCount(id);
			int goodCount = dbPro2.getGoodCount(id);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginId", id);
			session.setAttribute("articleCount", articleCount);
			session.setAttribute("goodCount", goodCount);
		}
	
		request.setAttribute("result", result);
		request.setAttribute("id", id);
		
		return CommandUri.loginResultView;
	}
}
