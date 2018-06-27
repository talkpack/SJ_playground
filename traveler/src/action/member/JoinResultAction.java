package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;
import action.command.CommandUri;
import model.member.MemberDao;
import model.member.MemberDto;

public class JoinResultAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		MemberDto memberDto=new MemberDto();
		memberDto.setName(request.getParameter("name"));
		memberDto.setId(request.getParameter("id"));
		memberDto.setPwd(request.getParameter("pwd"));
		memberDto.setGender(request.getParameter("gender"));
		memberDto.setYear(request.getParameter("year"));
		memberDto.setMonth(request.getParameter("month"));
		memberDto.setDay(request.getParameter("day"));
		memberDto.setPh1(request.getParameter("ph1"));
		memberDto.setPh2(request.getParameter("ph2"));
		memberDto.setPh3(request.getParameter("ph3"));
		memberDto.setEmail1(request.getParameter("email1"));
		memberDto.setEmail2(request.getParameter("email2"));
		memberDto.setQuestion(request.getParameter("question"));
		memberDto.setAnswer(request.getParameter("answer"));

		System.out.println(memberDto.getWriterGood());
		
		MemberDao memberDao= MemberDao.getInstance();
		boolean result=memberDao.memberInsert(memberDto);
		
		request.setAttribute("result", result);
		
		return CommandUri.joinResultView;
	}

}
