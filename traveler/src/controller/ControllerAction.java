package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.command.CommandAction;
import model.statistics.StatisticsDao;

public class ControllerAction extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private StatisticsDao dbPro;

	//명령어와 명령어 처리 클래스를 쌍으로 저장해두는 MAP
	private Map<String, Object> commandMap = 
			new HashMap<String, Object>();
	/*
	 * 명령어와 처리클래스가 매핑되어 있는 
	 * properties파일(CommandPro.properties)을 읽어
	 * Map객체인 commandMap에 저장한다.
	 */
	
	//web.xml에서 propertyConfig에 해당하는 init-param의 값을 읽어온다.
	public void init(ServletConfig config) throws ServletException{
		String props = config.getInitParameter("propertyConfig");
		System.out.println("===== [진입] 컨트롤러(서블릿) : ControllerAction.init() =====");
		System.out.println("처음 서블릿 생성할 때 톰켓에서 init()메서드를 호출!\n");
		//명령어와 커리클래스의 매핑 정보를 저장할 Properties객체 생성
		Properties pr = new Properties();
		FileInputStream f = null;
		String path = config.getServletContext().getRealPath("/WEB-INF");
		System.out.println("web.xml에서 <init-param>으로 매핑한");
		System.out.println("propertyConfig 초기 파라미터 값은 아래와 같다.");
		System.out.println(" => [ "+props+" ]\n");
		System.out.println("/WEB-INF 실제 경로는 아래와 같다.");
		System.out.println(" => [ "+path+" ]\n");
		try{
			f = new FileInputStream(new File(path, props));
			System.out.println("위의 경로로 FileInputStream 합니다.");
			//commandPro.properties파일의 정보를 Properties객체에 저장
			pr.load(f);
			System.out.println("commandPro.properties파일의 정보를 Properties객체에 저장했습니다.\n");
		} catch(IOException e){
			throw new ServletException(e);
		} finally{
			if(f != null) try{ f.close(); } catch (IOException e){}
		}
		
		//Iterator 객체사용
		Iterator<Object> keyIter = pr.keySet().iterator();
		System.out.println("Properties 객체를 참조하여 iterator()하여 출력합니다.");
		while(keyIter.hasNext()){
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			try{
				//가져온 문자열을 클래스로 만듬
				Class<?> commandClass = Class.forName(className);
				System.out.println(commandClass);
				//만들어진 해당 클래스의 객체 생성
				Object commandInstance = 
						commandClass.newInstance(); 
				
				//생성된 객체를 commandMap에 저장
				commandMap.put(command, commandInstance);
			} catch(ClassNotFoundException e){
				throw new ServletException(e);
			} catch(InstantiationException e){
				throw new ServletException(e);
			} catch(IllegalAccessException e){
				throw new ServletException(e);
			}
		}
		System.out.println("\n가져온 문자열을 클래스로 만들고,");
		System.out.println("그와 동시에 위의 클래스들을 객체화 시켰습니다.\n");
		
		System.out.println("------------- [ 새 기능 추가 ] -------------\n");
		// [ 추가 : 타이머 기능 ] - 오늘 방문자 초기화 기능 //
		System.out.println("다음은 타이머 객체를 생성하고, \n오늘 방문자 초기화 기능에 필요한 준비합니다.\n");
		
		Calendar startTime = Calendar.getInstance();
		System.out.println("서버 가동 시간 : " + startTime.getTime());
		startTime.set(Calendar.HOUR_OF_DAY, 23);
		startTime.set(Calendar.MINUTE, 59);
		startTime.set(Calendar.SECOND, 59);
		startTime.set(Calendar.MILLISECOND, 0);
		System.out.println("Task 시작 시간 : " + startTime.getTime());
		Timer todayTimer = new Timer();
		TimerTask todayTask = new TimerTask() {
			@Override
			public void run() {
				dbPro = StatisticsDao.getInstance();
				dbPro.todayCountReset();
				System.out.println("\n(알림!)오늘 방문자 수를 초기화하였습니다!\n");
			}
		};
		
		// 서버 시작 날(day)의 23시 59분 59초부터 하루에 한번씩 Task 작업
		todayTimer.schedule(todayTask, startTime.getTime(), 1000*60*60*24);
		System.out.println("위의 시간부터 하루에 한번씩 오늘 방문자 수를 초기화합니다. \n");
	}
	
	//Get방식 서비스 메서드
	public void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException{
		System.out.println("===== [진입] 컨트롤러(서블릿) : ControllerAction.doGet() =====\n");
		System.out.println("get방식의 서비스를 가동합니다.\n");
		requestPro(request, response);
	}
	
	//Post방식 서비스 메서드
	public void doPost(
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException{
		System.out.println("===== [진입] 컨트롤러(서블릿) : ControllerAction.doPost() =====\n");
		System.out.println("post방식의 서비스를 가동합니다.\n");
		requestPro(request, response);
	}
	
	//사용자의 요청에 따라 분석하여 해당 작업을 처리
	private void requestPro(
			HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException{
		System.out.println("===== [진입] 컨트롤러(서블릿) : ControllerAction.requestPro() =====\n");
		String view = null;
		CommandAction com = null;
		try{
			String command = request.getRequestURI();
			System.out.println("request.getRequestURI 정보 : "+request.getRequestURI());
			if(command.indexOf(request.getContextPath()) == 0){
				System.out.println("위의 정보가 [ "+request.getContextPath()+" ]의 요청이 맞다면,");
				command = command.substring(
						request.getContextPath().length());
				System.out.println("substring을 이용, 다음과 같은 값을 추출합니다. => [ "+command+" ]");
			}
			com = (CommandAction)commandMap.get(command);
			System.out.println("위의 명령어가 무엇과 매핑되어있는지 봅니다 => [ " + com +" ]");
			System.out.println("해당 객체의 requestPro()메서드를 호출합니다.\n");
			view = com.requestPro(request, response);
			System.out.println("===== [재진입] 컨트롤러(서블릿) : ControllerAction.requestPro() =====\n");
			System.out.println("해당 명령어에 대한 requestPro() 처리를 모두 완료했습니다!! "
					+ "\n반환 값(해당 뷰 페이지 경로)은 view 변수에 담습니다.\n");
		} catch(Throwable e){
//			명령어를 해석하여 매핑된 객체의 메서드를 호출하고, 반환받아 해당 뷰로 포워드
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		System.out.println("해당 VIEW의 JSP페이지 = [ "+view+" ]으로\n request, response 정보와 함께 '포워드' 시킵니다.\n");
	}
}
