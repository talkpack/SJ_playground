package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.command.CommandAction;
import action.command.CommandUri;
import model.member.MemberDao;
import model.member.MemberDto;

public class ModifyResultAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String loginId=(String) session.getAttribute("loginId");
		MemberDao memberDao=MemberDao.getInstance();
		MemberDto memberDto=new MemberDto();
		memberDto.setId(loginId);
		memberDto.setPwd(request.getParameter("pwd"));
		memberDto.setPh1(request.getParameter("ph1"));
		memberDto.setPh2(request.getParameter("ph2"));
		memberDto.setPh3(request.getParameter("ph3"));
		memberDto.setEmail1(request.getParameter("email1"));
		memberDto.setEmail2(request.getParameter("email2"));
		memberDto.setQuestion(request.getParameter("question"));
		memberDto.setAnswer(request.getParameter("answer"));
		
		int result=memberDao.memberModify(memberDto);
		
		request.setAttribute("result", result);
		
		return CommandUri.modifyResultView;
	}

}
