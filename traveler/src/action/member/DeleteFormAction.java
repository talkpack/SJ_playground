package action.member;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.command.CommandAction;
import action.command.CommandUri;
import model.member.MemberDao;
import model.member.MemberDto;
import model.statistics.StatisticsDao;
import model.travel.travelboard.TravelBoardDao;
import model.travel.travelboard.TravelBoardDto;

public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String loginId=(String) session.getAttribute("loginId");
		
		MemberDao memberDao=MemberDao.getInstance();
		MemberDto memberDto=memberDao.mypageSelect(loginId);
		Date date=new Date();
		SimpleDateFormat dayFormat=new SimpleDateFormat("yyyy 년  MM 월  dd 일  ");
		SimpleDateFormat timeFormat=new SimpleDateFormat("KK:mm aa");
		
		String day=dayFormat.format(date);
		String time=timeFormat.format(date);
		String regDate=dayFormat.format(memberDto.getJoinDate());
		System.out.println("modify : "+memberDto.getName());
		
		//내 활동 내역 가져오기
				TravelBoardDao dbPro = TravelBoardDao.getInstance(); // DB연결
				List<TravelBoardDto> articleList = null;
				int count = dbPro.getArticleCount(loginId); // 전체 글 개수
				System.out.println("내 활동 내역 개수 : "+count);
				if (count > 0) { // 현재 페이지의 글 목록
						articleList = dbPro.getArticles(loginId);
				} else {
						articleList = Collections.emptyList();
				}
				//속성
				request.setAttribute("count", new Integer(count));
				request.setAttribute("articleList", articleList);
		request.setAttribute("regDate", regDate);
		request.setAttribute("day", day);
		request.setAttribute("time", time);
		request.setAttribute("MemberDto", memberDto);
		return CommandUri.deleteFormView;
	}

}
