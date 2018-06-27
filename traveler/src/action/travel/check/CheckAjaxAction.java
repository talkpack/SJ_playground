package action.travel.check;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;
import action.command.CommandUri;
import model.statistics.Keyword;
import model.statistics.StatisticsDao;
import model.travel.travelboard.TravelBoardDao;

public class CheckAjaxAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String regionName = request.getParameter("regionName");
		int flag = Integer.parseInt(request.getParameter("flag"));
		boolean exception = false;
		TravelBoardDao dbPro = TravelBoardDao.getInstance();
		ArrayList<String> cities = dbPro.getCityList(regionName);
		
		try {
			int citySize = cities.size();
			System.out.println("도시 개수 : "+citySize+"개\n");
			
			StatisticsDao statisticDao=StatisticsDao.getInstance();
			List<Keyword> keyword=statisticDao.keywordRank(regionName);
			
			int total=keyword.get(0).getTotal();
			double per[]=new double[5];
			System.out.println("total : "+total);
			for(int i=0;i<keyword.size();i++) {
				per[i]=(double)((keyword.get(i)).getCount())/total*100;
				per[i]=Double.parseDouble(String.format("%.2f",per[i]));
				System.out.println("per["+(i+1)+"] = "+per[i]);
			}
			
			for(int i=0;i<keyword.size();i++) {
				request.setAttribute("city"+(i+1), keyword.get(i).getCity());
				request.setAttribute("value"+(i+1), keyword.get(i).getCount());
				request.setAttribute("per"+(i+1), per[i]);
				
			}
			//해당 뷰에서 사용할 속성
			request.setAttribute("regionName", regionName);
			request.setAttribute("cityList", cities);
			request.setAttribute("citySize", citySize);
		} catch(NullPointerException e) {
			System.out.println("\n<<< 예외 >>> 해당 나라 정보는 DB에 없습니다 !! 결과 : exception = true;\n");
			exception = true;
		}
		
		request.setAttribute("exception", exception);
		if(flag == 1) {
			return CommandUri.checkAjaxView;
		} else if(flag == 2) {
			return CommandUri.checkPlusAjaxView;
		} else {
			return CommandUri.checkMinusAjaxView;
		}
	}
}
