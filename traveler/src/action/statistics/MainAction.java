package action.statistics;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.command.CommandAction;
import action.command.CommandUri;
import model.member.MemberDao;
import model.statistics.StatisticsDao;
import model.travel.travelboard.TravelBoardDao;

public class MainAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		//////////# [ 1. 통계] //////////
		// 객체생성
		StatisticsDao dbPro = StatisticsDao.getInstance(); // 오늘/누적 방문자수 
		TravelBoardDao dbPro2 = TravelBoardDao.getInstance(); // 게시물 수 
		MemberDao dbPro3=MemberDao.getInstance(); //회원 수 
		// 메서드 호출 
		updateVisitor(request, dbPro); // 방문자 갱신 
		// 모든 통계 불러오기 
		int countInfo[] = dbPro.getAllCount();
		int boardCount = dbPro2.getArticleCount();
		int memberCount=dbPro3.memberCount();
		List<String> contentRank=dbPro.contentRank();
		List<String> writerRank=dbPro.writerRank();
		
		//////////# [ 2. 속성] //////////
		request.setAttribute("todayCount", countInfo[0]);
		request.setAttribute("totalCount", countInfo[1]);
		request.setAttribute("boardCount", boardCount);
		request.setAttribute("memberCount", memberCount);
		request.setAttribute("contentRank",contentRank);
		request.setAttribute("writerRank",writerRank);
		
		return CommandUri.mainView;
	}
	
	// [ 방문자 갱신 메서드 ] //
	public void updateVisitor(HttpServletRequest request, StatisticsDao dbPro) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		//String loginId=(String) session.getAttribute("loginId");
		if (session.isNew()) { // 새로운 브라우저(세션)가 요청되었을 때,
			System.out.println("새로운 세션이 감지!");
			dbPro.updateVisitorCount();
		} else {
			System.out.println("기존 세션이라서 카운팅 안함.");
		}
	}
}


