package action.travel.travelboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.command.CommandAction;
import action.command.CommandUri;
import model.member.MemberDao;
import model.statistics.StatisticsDao;
import model.travel.travelboard.TravelBoardDao;
import model.travel.travelboard.TravelBoardDto;

public class TravelModifyFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		////////// # [ 1. 기본 셋팅 : 세션, 로그인 체크, 검색이면 관련 속성 세팅] //////////
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int num = Integer.parseInt(request.getParameter("num"));
		if (request.getParameter("howSearch") != null) {
			String text = request.getParameter("textSearch");
			int how = Integer.parseInt(request.getParameter("howSearch"));
			request.setAttribute("text", text);
			request.setAttribute("how", how);
		}
		request.setAttribute("num", num);
		TravelBoardDao dbPro = TravelBoardDao.getInstance();
		StatisticsDao dbPro2 = StatisticsDao.getInstance();
		MemberDao dbPro3 = MemberDao.getInstance(); // 회원 수

		////////// # [ 2. 로그인 체크] //////////
		System.out.println("num : " + num);
		System.out.println("pageNum : " + pageNum);
		System.out.println("loginId : " + loginId);
		String dbPwd = dbPro.passWriter(num, loginId);
		System.out.println("작성자 권한 비밀번호 : " + dbPwd);

		////////// # [ 3. 통계 불러오기 ] //////////
		int boardCount = dbPro.getArticleCount();
		int countInfo[] = dbPro2.getAllCount();
		int memberCount = dbPro3.memberCount();

		////////// # [ 4. 키워드 불러오기 ] //////////
		TravelBoardDto travelBoardDto = dbPro.getContent(num);
		TravelBoardDto dto = dbPro.keywordSetting(travelBoardDto.getTerm(), travelBoardDto.getMoney(),
				travelBoardDto.getRoute());
		travelBoardDto.setTerm(dto.getTerm());
		travelBoardDto.setMoney(dto.getMoney());
		travelBoardDto.setRoute(dto.getRoute());

		////////// # [ 5. 속성 ] //////////
		request.setAttribute("todayCount", countInfo[0]);
		request.setAttribute("totalCount", countInfo[1]);
		request.setAttribute("boardCount", boardCount);
		request.setAttribute("memberCount", memberCount);
		request.setAttribute("article", travelBoardDto);
		request.setAttribute("dbPwd", dbPwd);
		request.setAttribute("pageNum", pageNum);

		return CommandUri.travelModifyFormView;
	}

}
