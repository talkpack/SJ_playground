package action.travel.travelboard;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;
import action.command.CommandUri;
import model.travel.travelboard.TravelBoardDao;
import model.travel.travelboard.TravelBoardDto;

public class TravelListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		TravelBoardDao dbPro = TravelBoardDao.getInstance(); // DB연결
		
		String pageNum = request.getParameter("pageNum"); // 페이지 번호
		System.out.println("pageNum 요청 파라미터 값 구하기 => [ " + pageNum + " ]");
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 5; // 한 페이지 당 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		// 페이지의 시작 글 번호
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize; // 한 페이지의 마지막 글 번호
		System.out.println("현재 페이지 : [" + currentPage + "]");
		System.out.println("시작 글 번호 : [" + startRow + "]");
		System.out.println("마지막 글 번호 : [" + endRow + "] \n");
		int count = 0;
		int number = 0;
		List<TravelBoardDto> articleList = null;
		
		// [ 분기점 (기본 & 검색) ] //
		String text = request.getParameter("textSearch");
		int how = -1;
		if(request.getParameter("howSearch")!=null) {
			how = Integer.parseInt(request.getParameter("howSearch"));
		}
		
		if(text==null) {
		// [ 기본 ]
			System.out.println("<<< 전체 검색 시작 !!! >>>\n");
			count = dbPro.getArticleCount(); // 전체 글 개수
			System.out.println("전체 글 개수 : [" + count + "개]를 알아냈습니다.");

			if (count > 0) { // 현재 페이지의 글 목록
				System.out.println("현재 페이지 글 목록이 0이상이면, 다음 메서드를 호출합니다.\n");
				articleList = dbPro.getArticles(startRow, endRow);
				System.out.println("===== [재진입] 명령어 처리(자바 객체) : ListAction.requestPro() =====\n");
			} else {
				System.out.println("현재 페이지 글 목록이 없으면, Collections.emptyList() 메서드를 호출합니다.");
				articleList = Collections.emptyList();
			}
			number = count - (currentPage - 1) * pageSize; // 글 목록에 표시할 글 번호
			System.out.println("글 목록에 표시할 번호 구하기 => [" + number + "]\n");
		} else {
		// [ 검색 ]
			System.out.println("<<< 검색 과정 시작 !!! >>>\n");
			count = dbPro.getArticleCount(how, text); // 검색 글 개수
			System.out.println("검색 글 개수 : [" + count + "개]를 알아냈습니다.");

			if (count > 0) { // 현재 페이지의 글 목록
				System.out.println("현재 페이지 글 목록이 0이상이면, 다음 메서드를 호출합니다.\n");
				articleList = dbPro.getArticles(startRow, endRow, how, text);
				System.out.println("===== [재진입] 명령어 처리(자바 객체) : ListAction.requestPro() =====\n");
			} else {
				System.out.println("현재 페이지 글 목록이 없으면, Collections.emptyList() 메서드를 호출합니다.");
				articleList = Collections.emptyList();
			}
			number = count - (currentPage - 1) * pageSize; // 글 목록에 표시할 글 번호
			System.out.println("글 목록에 표시할 번호 구하기 => [" + number + "]\n");
			request.setAttribute("text", text);
			request.setAttribute("how", how);
		}

		// 해당 뷰에서 사용할 속성
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		System.out.println("[ travelListView.jsp ]에서 사용할 속성들을 setAttribute 합니다.\n");
		System.out.println("세팅된 속성 : 현재페이지/시작행번호/마지막행번호/전체글개수/페이지크기/표시될 글 번호/[글 목록 List]\n");

		return CommandUri.travelListView;
	}

}
