package action.travel.check;

import java.util.Collections;
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

public class CheckListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

		////////// # [ 1. 기본 셋팅 : 세션, 로그인 체크, 객체 생성] //////////
		request.setCharacterEncoding("utf-8");
		int termFlag=Integer.parseInt(request.getParameter("termFlag"));
		int moneyFlag=Integer.parseInt(request.getParameter("termFlag"));
		System.out.println("termFlag : "+termFlag);
		System.out.println("moneyFlag : "+moneyFlag);
		StatisticsDao dbPro = StatisticsDao.getInstance();
		TravelBoardDao dbPro2 = TravelBoardDao.getInstance();
		MemberDao dbPro3 = MemberDao.getInstance();

		////////// # [ 2. 통계] //////////
		int countInfo[] = dbPro.getAllCount();
		int boardCount = dbPro2.getArticleCount();
		int memberCount = dbPro3.memberCount();

		////////// # [ 3. 목록 ] //////////
		String country = request.getParameter("country");
		String city = request.getParameter("city");
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
		String route = request.getParameter("route");
		String pageNum = request.getParameter("pageNum"); // 페이지 번호
		System.out.println("불러온 money 값 : \""+money+"\"");
		System.out.println("pageNum 요청 파라미터 값 구하기 => [ " + pageNum + " ]");
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 5; // 한 페이지 당 글의 개수
		int currentPage = Integer.parseInt(pageNum);// 페이지의 시작 글 번호
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize; // 한 페이지의 마지막 글 번호
		System.out.println("현재 페이지 : [" + currentPage + "]");
		System.out.println("시작 글 번호 : [" + startRow + "]");
		System.out.println("마지막 글 번호 : [" + endRow + "] \n");
		int count = 0;
		int number = 0;
		List<TravelBoardDto> articleList = null;

		// [ 기본 ]
		System.out.println("<<< 키워드 검색 시작 !!! >>>\n");
		count = dbPro2.getArticleCount(country, city, term, money, route); 
		System.out.println("키워드 글 개수 : [" + count + "개]를 알아냈습니다.");

		if (count > 0) { // 현재 페이지의 글 목록
			System.out.println("현재 페이지 글 목록이 0이상이면, 다음 메서드를 호출합니다.\n");
			articleList = dbPro2.getArticles(country, city, term, money, route,startRow, endRow);
			System.out.println("===== [재진입] 명령어 처리(자바 객체) : ListAction.requestPro() =====\n");
		} else {
			System.out.println("현재 페이지 글 목록이 없으면, Collections.emptyList() 메서드를 호출합니다.");
			articleList = Collections.emptyList();
		}
		number = count - (currentPage - 1) * pageSize; // 글 목록에 표시할 글 번호
		System.out.println("글 목록에 표시할 번호 구하기 => [" + number + "]\n");

		////////// # [ 4. 키워드 ] //////////
		TravelBoardDto dto=dbPro2.keywordSetting(term,money,route);
		String termSet=dto.getTerm();
		String moneySet=dto.getMoney();
		String routeSet=dto.getRoute();
				
		////////// # [5. 속성 ] //////////
		// 통계
		request.setAttribute("todayCount", countInfo[0]);
		request.setAttribute("totalCount", countInfo[1]);
		request.setAttribute("boardCount", boardCount);
		request.setAttribute("memberCount", memberCount);
		// 키워드
		request.setAttribute("country", country);
		request.setAttribute("city", city);
		request.setAttribute("term", term);
		request.setAttribute("money", money);
		request.setAttribute("route", route);
		request.setAttribute("termSet", termSet);
		request.setAttribute("moneySet", moneySet);
		request.setAttribute("routeSet", routeSet);
		// 목록
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);

		return CommandUri.checkListView;
	}

}
