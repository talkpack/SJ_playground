package model.statistics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.javafx.webkit.KeyCodeMap.Entry;

import connection.ConnUtil;
import oracle.net.aso.e;

public class StatisticsDao {
	// [ 기본 셋팅 ] //
	private static StatisticsDao instance = null;

	private StatisticsDao() {
	}

	public static StatisticsDao getInstance() {
		if (instance == null) {
			synchronized (StatisticsDao.class) {
				if (instance == null) {
					instance = new StatisticsDao();
				}
			}
		}
		return instance;
	}

	// [ 1. 모든 방문자 수 읽어오기 (getAllCount)] //
	// [ 2. 모든 방문자 수 갱신 메서드 (updateVisitorCount)] //
	// [ 3. 오늘 방문자 수 초기화 메서드 (todayCountReset)] //
	// [ 4. 작성자 랭킹 메서드(writerRank)] //
	// [ 5. 내용 랭킹 메서드(contentRank)] //

	// [ 1. 모든 방문자 수 읽어오기 ] //
	public int[] getAllCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int countInfo[] = null;
		String sql = "select TODAYCOUNT, TOTALCOUNT from STATISTICS";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 수정2 : 배열 크기 수정
				countInfo = new int[2];
				countInfo[0] = rs.getInt("TODAYCOUNT");
				countInfo[1] = rs.getInt("TOTALCOUNT");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return countInfo;

	}

	// [ 2. 모든 방문자 수 갱신 메서드 ] //
	public void updateVisitorCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql1 = "update STATISTICS set TOTALCOUNT = TOTALCOUNT+1";
		String sql2 = "update STATISTICS set TODAYCOUNT = TODAYCOUNT+1";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.executeUpdate();
			pstmt.close();

			pstmt = conn.prepareStatement(sql2);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// [ 3. 오늘 방문자 수 초기화 메서드 ] //
	public void todayCountReset() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update STATISTICS set TODAYCOUNT = 1";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// [ 4. 작성자 랭킹 메서드(writerRank)] //
	public List<String> writerRank() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = null;
		List<String> rank = null;
		int index = 0;
		int overlap = -1;
		boolean countResult = false;
		String sql = "select ID, WRITERGOOD from (select ID, WRITERGOOD from MEMBER order by WRITERGOOD desc)";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<String>(5);
				rank = new ArrayList<String>(5);
				do {
//					System.out.println("///////////////" + index + "일 때의 값" + "///////////////");
					list.add(index, rs.getString("writergood"));
					rank.add(index, rs.getString("id"));
					if (overlap != -1) { // 2번째 데이터부터 비교
						countResult = list.get(index).equals(list.get(overlap)); // 이전 boardGood값과 현재 boardGood값 비교
//						System.out.println("writerGood 값이 같은가요?  : " + countResult);
						if (countResult == true) {// 이전 boardGood값과 현재 boardGood값이 같으면 (중복)
//							System.out.println("---------> YES");
							String nameAppend = rank.get(overlap) + "," + rank.get(index);
//							System.out.println("=> nameAppend : " + nameAppend);
							rank.set(overlap, nameAppend);
							rank.remove(index);
							index--;
						} else {
//							System.out.println("---------> NO");
						}
					}
//					System.out.println("=> list [" + index + "] : " + list.get(index));
//					System.out.println("=> rank [" + index + "] : " + rank.get(index));
					overlap = index;
					index++;
//					System.out.println();
//					System.out.println();
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return rank;
	}

	// [ 5. 내용 랭킹 메서드(contentRank)] //
	public List<String> contentRank() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		List<String> rank = null;
		int ranking;
		String sql = "select * from (select SUBJECT, BOARDGOOD, rank() over (order by BOARDGOOD desc) as RANK from TRAVELBOARD) where rownum <=5";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rank = new ArrayList<String>(5);
			while (rs.next()) {
				ranking = Integer.parseInt(rs.getString("rank"))-1;	// LIST 인덱스를 고려하여 -1
				rank.add(ranking, rs.getString("subject"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return rank;
	}

	// [ 6. 키워드 랭킹 메서드(keywordRank)] //
	public List<Keyword> keywordRank(String regionName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Keyword> rank = null;
		String sql = "select city from TRAVELBOARD where country=?";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, regionName);
			rs = pstmt.executeQuery();
			Map<String, String> list = new HashMap<String, String>(5);
			String city = "";
			int count = 0;
			if (rs.next()) {
				do {
					System.out.println("-----------------------");
					city = rs.getString("city");
					System.out.print("DB에서 가져온 도시 : " + city);
					// k=new Keyword();
					for (int i = 0; i < 10; i++) {
						if (list.containsKey(city)) { // key값이 중복된 것이 있으면
							count = Integer.parseInt(list.get(city));// city값에 해당하는 count값을 가져옴
							count++;// count증가
							list.put(city, String.valueOf(count));// 중복된 city에 count값 다시 넣어주기
							break;
						} else { // 처음
							count = 1;
							list.put(city, String.valueOf(count));
							break;
						}
					}
					System.out.println(", 해당 도시의 count : " + count);
				} while (rs.next());
			}
			System.out.println("-----------------------");
			System.out.println("[정렬 전]");
			// map에서 key값과 value값 가져오기
			Set<String> key = list.keySet();
			String getCity = "";
			int getCount = 0;
			Keyword k;
			int total=0;
			rank = new ArrayList<Keyword>();
			for (Iterator it = key.iterator(); it.hasNext();) {
				getCity = (String) it.next();
				getCount = Integer.parseInt(list.get(getCity));
				System.out.println("city : " + getCity + ", value :" + getCount);
				k = new Keyword();
				k.setCity(getCity);
				k.setCount(getCount);
				total+=getCount;
				rank.add(k);
			}
			// 정렬하기
			int size = rank.size();
			Keyword temp = new Keyword();
			for (int j = 0; j < size; j++) {
				for (int h = 0; h < size-1 ; h++) {
					
					if ((rank.get(h)).getCount() < (rank.get(h + 1)).getCount()) {
						temp = rank.get(h);
						rank.set(h, rank.get(h + 1));
						rank.set(h + 1, temp);
					} else if ((rank.get(h)).getCount() == (rank.get(h + 1)).getCount()) {
						temp = rank.get(h);
						rank.set(h, rank.get(h + 1));
						rank.set(h + 1, temp);
						String append = (rank.get(h)).getCity() + "/" + (rank.get(h + 1)).getCity();
						(rank.get(h)).setCity(append);
						rank.remove(h + 1);
						size--;
					}
				}
			}
			//정렬 후 사이즈가 5개 미만일 때 
			System.out.println("size : "+size);
			for (int i = size; i < 5; i++) {
				if (size<5) {
					System.out.println("예외처리 들어옴");
					Keyword ex=new Keyword();
					ex.setCity("기타");
					ex.setCount(0);
					rank.add(ex);
				}
			}
			//총 개수 
			Keyword totalSet=rank.get(0);
			totalSet.setTotal(total);
			rank.set(0, totalSet);
			// console 확인
			System.out.println("-----------------------");
			System.out.println("[정렬 후]");
			for (int i = 0; i < 5; i++) {

				System.out.println("city : " + (rank.get(i)).getCity() + ", value : " + (rank.get(i)).getCount());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return rank;
	}
}
