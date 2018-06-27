package action.travel.travelboard;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;
import action.command.CommandUri;
import model.travel.travelboard.TravelBoardDao;

public class FavoriteAjaxAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int flag = Integer.parseInt(request.getParameter("flag"));
		int num=Integer.parseInt(request.getParameter("num"));
		System.out.println("num : "+num);
		String writer=request.getParameter("id");
		TravelBoardDao travelBoardDao=TravelBoardDao.getInstance();
		travelBoardDao.favoriteBoard(flag,num);
		travelBoardDao.favoriteWriter(flag,writer);
		if(flag == 1) {
			return CommandUri.favoritePlusAjaxView;
		} else  {
			return CommandUri.favoriteMinusAjaxView;
		} 
	}

}
