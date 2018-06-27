package action.travel.check;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.command.CommandAction;
import action.command.CommandUri;
import model.member.MemberDao;
import model.statistics.StatisticsDao;
import model.travel.travelboard.TravelBoardDao;
import model.travel.travelboard.TravelBoardDto;

public class CheckWriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		////////// # [ 1. 기본 셋팅 : 세션, 로그인 체크] //////////
		request.setCharacterEncoding("utf-8");
		int termFlag=Integer.parseInt(request.getParameter("termFlag"));
		int moneyFlag=Integer.parseInt(request.getParameter("termFlag"));
		System.out.println("termFlag : "+termFlag);
		System.out.println("moneyFlag : "+moneyFlag);
		HttpSession session=request.getSession();
		String loginId=(String) session.getAttribute("loginId");
		System.out.println("CheckWriteFormAction에서 loginId : "+loginId);
		
		
		////////// # [ 2. 통계] //////////
		StatisticsDao dbPro = StatisticsDao.getInstance();
		TravelBoardDao dbPro2 = TravelBoardDao.getInstance();
		MemberDao dbPro3=MemberDao.getInstance();
		int countInfo[] = dbPro.getAllCount();
		int boardCount = dbPro2.getArticleCount();
		int memberCount=dbPro3.memberCount();
		
		
		////////// # [ 3. 키워드  ] //////////
		String country=request.getParameter("country");
		String city=request.getParameter("city");
		String term=null;
		String money=null;
		if(termFlag==1) {
			term=request.getParameter("term");
		}else if(termFlag==2){
			term=request.getParameter("termDirect");
		}
		if(moneyFlag==1) {
			money=request.getParameter("money");
		}else if(moneyFlag==2){
			money=request.getParameter("moneyDirect");
		}
		String route=request.getParameter("route");
		System.out.println("checkWriteFormAction 에 들어온 country : \""+country+"\"");
		System.out.println("checkWriteFormAction 에 들어온 term : \""+term+"\"");
		System.out.println("checkWriteFormAction 에 들어온 money : \""+money+"\"");
		TravelBoardDto dto=dbPro2.keywordSetting(term,money,route);
		String termSet=dto.getTerm();
		String moneySet=dto.getMoney();
		String routeSet=dto.getRoute();
		
		////////// # [4. 속성 ] //////////
		//통계
		request.setAttribute("todayCount", countInfo[0]);
		request.setAttribute("totalCount", countInfo[1]);
		request.setAttribute("boardCount", boardCount);
		request.setAttribute("memberCount", memberCount);
		//로그인
		request.setAttribute("loginId", loginId);
		//키워드
		request.setAttribute("country", country);
		request.setAttribute("city", city);
		request.setAttribute("term", term);
		request.setAttribute("money", money);
		request.setAttribute("route", route);
		request.setAttribute("termSet", termSet);
		request.setAttribute("moneySet", moneySet);
		request.setAttribute("routeSet", routeSet);
		
	
		return CommandUri.checkWriteFormView;
	}

}
