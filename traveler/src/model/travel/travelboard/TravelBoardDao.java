package model.travel.travelboard;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnUtil;

public class TravelBoardDao {
	// [기본 셋팅]
	private static TravelBoardDao instance = null;

	private TravelBoardDao() {
	}

	public static TravelBoardDao getInstance() {
		if (instance == null) {
			synchronized (TravelBoardDao.class) {
				if (instance == null) {
					instance = new TravelBoardDao();
				}
			}
		}
		return instance;
	}

	// [ 1. 해당 국가의 도시 목록을 불러오는 메서드 (getCityList)] //
	// [ 2. 전체 글 개수를 알아오기 (getArticleCount)] //
	// [ 3. 검색된 글 개수를 알아오기 (getArticleCount)] //
	// [ 4. 키워드 검색된 글 개수를 알아오기 (getArticleCount)] //
	// [ 4. 작성자로 검색된 글 개수를 알아오기 (getArticleCount)] // 추가됨
	// [ 5. 전체 글 목록 불러오기 (getArticles)] //
	// [ 6. 검색된 글 목록 불러오기 (getArticles)] //
	// [ 7. 키워드로 검색된 글 목록 불러오기 (getArticles)] //
	// [ 7. 작성자로 검색된 검색된 글 목록 불러오기 (getArticles)] // 추가됨
	// [ 8. 글 수정 및 삭제 패스 절차 (passProcess)] //
	// [ 9. 글 수정 메서드 (writeUpdate)] //
	// [ 10. 글 삭제 메서드 (deleteArticle)] //
	// [ 11. 글 내용 저장 메서드 (writeInsert)] //
	// [ 12. 해당 글의 모든 컬럼 불러오기 메서드 (getContent)] //
	// [ 13. 글 내용 보기 메서드 (content)] //
	// [ 14. 게시글 좋아요 메서드 (favoriteBoard)] //
	// [ 15. 작성자 좋아요 메서드 (favoriteWriter)] //
	// [ 16. 키워드 셋팅 (keywordSetting)] //

	// [ 1. 해당 국가의 도시 목록을 불러오는 메서드 ] //
	public ArrayList<String> getCityList(String regionName) {
		System.out.println("국가 이름(regionName)");
		System.out.println("[ " + regionName + " ]");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> cities = null;
		String sql = "select * from CITYLIST where COUNTRY = ?";
		int i = 0;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, regionName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cities = new ArrayList<String>();
				do {
					cities.add(rs.getString("city" + (i + 1)));
					System.out.println("[" + (i + 1) + "] " + cities.get(i));
					i++;
				} while (i <= 9);
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
		return cities;
	}

	// [ 2. 전체 글 개수를 알아오기 ] //
	public int getArticleCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from TRAVELBOARD");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}

	// [ 3. 검색된 글 개수를 알아오기 ] //
	public int getArticleCount(int how, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "";
		String inputText = "%" + text + "%";

		System.out.println("[검색 글 개수 메서드]\n-> how : " + how);
		if (how == 1) {
			sql = "select count(*) from TRAVELBOARD where SUBJECT LIKE ?";
		} else if (how == 2) {
			sql = "select count(*) from TRAVELBOARD where WRITER LIKE ?";
		}
		System.out.println("사용된 SQL문 : " + sql);
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputText);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}

	// [ 4. 키워드 검색된 글 개수를 알아오기 (getArticleCount)] //
	public int getArticleCount(String country, String city, String term, String money, String route) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		System.out.println("Travel Dao에 불러온 값 money : \"" + money+"\"");
		String sql = "select count(*) from TRAVELBOARD where country=? and city=? and term=? and money=? and route=?";
		System.out.println("사용된 SQL문 : " + sql);
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, country);
			pstmt.setString(2, city);
			pstmt.setString(3, term);
			pstmt.setString(4, money);
			pstmt.setString(5, route);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}

	// [ 4. 작성자로 검색된 글 개수를 알아오기 (getArticleCount)] //
	public int getArticleCount(String loginId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select count(*) from TRAVELBOARD where writer=?";
	
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}

	// [ 5. 전체 글 목록 불러오기 ]
	public List<TravelBoardDto> getArticles(int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TravelBoardDto> articleList = null;
		System.out.println("start : " + start + ", end : " + end + "\n");
		try {
			conn = ConnUtil.getConnection();
			System.out.println("===== [진입] 모델(자바 빈즈) : TravelBoardDao.getArticles() =====\n");
			System.out.println("< DB에서 글 목록을 가져와 List 타입으로 세팅하기 > ");
			String sql = "select * from " + "(select rownum as RNUM, NUM, SUBJECT, WRITER, WRITEPWD, REGDATE,"
					+ " READCOUNT, CONTENT, BOARDGOOD, FILENAME from (select * from TRAVELBOARD order by NUM desc)) where RNUM >= ? and RNUM <= ?";
			pstmt = conn.prepareStatement(sql);
			System.out.println("\n사용된 Query 문은 다음과 같습니다.");
			System.out.println(sql + "\n");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			System.out.println("쿼리문 실행 결과를 반환받았습니다.\n");
			System.out.println("반환값으로 Dto를 이용하여 '글 목록'을 'List 타입'에 세팅합니다.\n");
			if (rs.next()) {
				articleList = new ArrayList<TravelBoardDto>(5);
				do {
					TravelBoardDto article = new TravelBoardDto();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setSubject(rs.getString("subject"));
					article.setWritePwd(rs.getString("writepwd"));
					article.setRegDate(rs.getTimestamp("regdate"));
					article.setReadCount(rs.getInt("readcount"));
					article.setContent(rs.getString("content"));
					article.setBoardGood(rs.getInt("boardgood"));
					if (rs.getString("filename") != null) {
						article.setFileName(rs.getString("filename"));
					} else {
						article.setFileName("basic.jpg");
					}
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		return articleList;
	}

	// [ 6. 검색된 글 목록 불러오기 ] //
	public List<TravelBoardDto> getArticles(int start, int end, int how, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TravelBoardDto> articleList = null;
		String sql = "";
		String inputText = "%" + text + "%";
		System.out.println(inputText);
		System.out.println("start : " + start + ", end : " + end);
		System.out.println("how : " + how + ", text : " + text + "\n");
		try {
			conn = ConnUtil.getConnection();
			System.out.println("===== [진입] 모델(자바 빈즈) : TravelBoardDao.getArticles() =====\n");
			System.out.println("< DB에서 검색 후 글 목록을 가져와 List 타입으로 세팅하기 > ");

			if (how == 1) {
				sql = "select * from (select rownum as RNUM, NUM, SUBJECT, WRITER, WRITEPWD, REGDATE, READCOUNT, CONTENT, BOARDGOOD, FILENAME from (select * from TRAVELBOARD where SUBJECT LIKE ? order by NUM desc)) where RNUM >= ? and RNUM <= ?";
			} else if (how == 2) {
				sql = "select * from (select rownum as RNUM, NUM, SUBJECT, WRITER, WRITEPWD, REGDATE, READCOUNT, CONTENT, BOARDGOOD, FILENAME from (select * from TRAVELBOARD where WRITER LIKE ? order by NUM desc)) where RNUM >= ? and RNUM <= ?";
			}

			pstmt = conn.prepareStatement(sql);
			System.out.println("\n사용된 Query 문은 다음과 같습니다.");
			System.out.println(sql + "\n");
			System.out.println("start:" + start);
			System.out.println("end:" + end);
			pstmt.setString(1, inputText);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			System.out.println("쿼리문 실행 결과를 반환받았습니다.\n");
			System.out.println("반환값으로 Dto를 이용하여 '글 목록'을 'List 타입'에 세팅합니다.\n");
			if (rs.next()) {
				articleList = new ArrayList<TravelBoardDto>(5);
				do {
					TravelBoardDto article = new TravelBoardDto();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setSubject(rs.getString("subject"));
					article.setWritePwd(rs.getString("writepwd"));
					article.setRegDate(rs.getTimestamp("regdate"));
					article.setReadCount(rs.getInt("readcount"));
					article.setContent(rs.getString("content"));
					article.setBoardGood(rs.getInt("boardgood"));
					if (rs.getString("filename") != null) {
						article.setFileName(rs.getString("filename"));
					} else {
						article.setFileName("basic.jpg");
					}
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		return articleList;
	}

	// [ 7. 키워드로 검색된 글 목록 불러오기 (getArticles)] //
	public List<TravelBoardDto> getArticles(String country, String city, String term, String money, String route,
			int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TravelBoardDto> articleList = null;
		String sql = "select * from " + "(select rownum as RNUM, NUM, SUBJECT, WRITER, WRITEPWD, REGDATE, "
				+ "READCOUNT, CONTENT, BOARDGOOD, FILENAME from "
				+ "(select * from TRAVELBOARD where country=? and city=? and term=? and money=? and route=? order by NUM desc)) "
				+ "where RNUM >= ? and RNUM <= ?";
		System.out.println("start : " + start + ", end : " + end);
		try {
			conn = ConnUtil.getConnection();
			System.out.println("===== [진입] 모델(자바 빈즈) : TravelBoardDao.getArticles() =====\n");
			System.out.println("< DB에서 키워드로 검색 후 글 목록을 가져와 List 타입으로 세팅하기 > ");
			pstmt = conn.prepareStatement(sql);
			System.out.println("\n사용된 Query 문은 다음과 같습니다.");
			System.out.println(sql + "\n");
			System.out.println("start:" + start);
			System.out.println("end:" + end);
			pstmt.setString(1, country);
			pstmt.setString(2, city);
			pstmt.setString(3, term);
			pstmt.setString(4, money);
			pstmt.setString(5, route);
			pstmt.setInt(6, start);
			pstmt.setInt(7, end);
			rs = pstmt.executeQuery();
			System.out.println("쿼리문 실행 결과를 반환받았습니다.\n");
			System.out.println("반환값으로 Dto를 이용하여 '글 목록'을 'List 타입'에 세팅합니다.\n");
			if (rs.next()) {
				articleList = new ArrayList<TravelBoardDto>(5);
				do {
					TravelBoardDto article = new TravelBoardDto();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setSubject(rs.getString("subject"));
					article.setWritePwd(rs.getString("writepwd"));
					article.setRegDate(rs.getTimestamp("regdate"));
					article.setReadCount(rs.getInt("readcount"));
					article.setContent(rs.getString("content"));
					article.setBoardGood(rs.getInt("boardgood"));
					if (rs.getString("filename") != null) {
						article.setFileName(rs.getString("filename"));
					} else {
						article.setFileName("basic.jpg");
					}
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		return articleList;
	}
	// [ 7. 작성자로 검색된  목록 불러오기 ]
		public List<TravelBoardDto> getArticles(String loginId) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<TravelBoardDto> articleList = null;
			try {
				conn = ConnUtil.getConnection();
				String sql = "select rownum as RNUM, NUM, SUBJECT, WRITER, WRITEPWD, REGDATE,"
						+ " READCOUNT, CONTENT, BOARDGOOD, FILENAME from "
						+ "((select * from TRAVELBOARD where writer=?) order by NUM desc) ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, loginId);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					articleList = new ArrayList<TravelBoardDto>(5);
					do {
						TravelBoardDto article = new TravelBoardDto();
						article.setNum(rs.getInt("num"));
						article.setWriter(rs.getString("writer"));
						article.setSubject(rs.getString("subject"));
						article.setWritePwd(rs.getString("writepwd"));
						article.setRegDate(rs.getTimestamp("regdate"));
						article.setReadCount(rs.getInt("readcount"));
						article.setContent(rs.getString("content"));
						article.setBoardGood(rs.getInt("boardgood"));
						if (rs.getString("filename") != null) {
							article.setFileName(rs.getString("filename"));
						} else {
							article.setFileName("basic.jpg");
						}
						articleList.add(article);
					} while (rs.next());
				}
			} catch (Exception e) {
				e.printStackTrace();
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
			return articleList;
		}
	// [ 8. 글 수정 및 삭제 권한 절차 ] //
	public String passWriter(int num, String writer) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPwd = "";
		boolean result = false;
		System.out.println("\n<<< 작성자 >>> : ["+writer+"]");
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select WRITEPWD from TRAVELBOARD where NUM = ? and WRITER=?");
			pstmt.setInt(1, num);
			pstmt.setString(2, writer);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPwd = rs.getString("writePwd");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle1) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle2) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle3) {
				}
		}
		return dbPwd;
	}
	

	// [ 9. 글 수정 메서드 ] //
	public boolean writeUpdate(TravelBoardDto travelBoardDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean check = false;
		String sql = "update TRAVELBOARD set SUBJECT=?, CONTENT=?, FILENAME=?, REGDATE=sysdate where NUM=?";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, travelBoardDto.getSubject());
			pstmt.setString(2, travelBoardDto.getContent());
			pstmt.setString(3, travelBoardDto.getFileName());
			pstmt.setInt(4, travelBoardDto.getNum());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				check = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle1) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle2) {
				}
		}
		return check;
	}

	// [ 10. 글 삭제 메서드 ] //
	public boolean deleteArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean check = false;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("delete from TRAVELBOARD where NUM = ?");
			pstmt.setInt(1, num);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				check = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle1) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle2) {
				}
		}
		return check;
	}

	// [ 11. 글 내용 저장 메서드 ] //
	public boolean writeInsert(TravelBoardDto travelBoardDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		String sql = "insert into TRAVELBOARD " + "(NUM,COUNTRY,CITY,MONEY,ROUTE,TERM,WRITER,"
				+ "SUBJECT,WRITEPWD,REGDATE," + "CONTENT,FILENAME," + "BOARDGOOD,READCOUNT) "
				+ "values(TRAVELBOARD_SEQ.nextval,?,?,?,?,?,?," + "?,?,sysdate," + "?,?,?,?)";
		System.out.println("Travel Dao 에 불러온 country : "+travelBoardDto.getCountry());
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, travelBoardDto.getCountry());
			pstmt.setString(2, travelBoardDto.getCity());
			pstmt.setString(3, travelBoardDto.getMoney());
			pstmt.setString(4, travelBoardDto.getRoute());
			pstmt.setString(5, travelBoardDto.getTerm());
			pstmt.setString(6, travelBoardDto.getWriter());
			pstmt.setString(7, travelBoardDto.getSubject());
			pstmt.setString(8, travelBoardDto.getWritePwd());
			pstmt.setString(9, travelBoardDto.getContent());
			pstmt.setString(10, travelBoardDto.getFileName());
			pstmt.setInt(11, 0);
			pstmt.setInt(12, 0);
			int res = pstmt.executeUpdate();
			if (res > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle1) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle2) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle3) {
				}
		}
		return result;
	}

	// [ 12. 해당 글의 모든 컬럼 불러오기 메서드 ] //
	public TravelBoardDto getContent(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TravelBoardDto travelBoardDto = null;
		String sql1 = "select * from TRAVELBOARD where num=?";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				travelBoardDto = new TravelBoardDto();
				travelBoardDto.setNum(rs.getInt("num"));
				travelBoardDto.setCountry(rs.getString("country"));
				travelBoardDto.setCity(rs.getString("city"));
				travelBoardDto.setMoney(rs.getString("money"));
				travelBoardDto.setRoute(rs.getString("route"));
				travelBoardDto.setTerm(rs.getString("term"));
				travelBoardDto.setWriter(rs.getString("writer"));
				travelBoardDto.setSubject(rs.getString("subject"));
				travelBoardDto.setBoardGood(rs.getInt("boardgood"));
				travelBoardDto.setWritePwd(rs.getString("writePwd"));
				travelBoardDto.setReadCount(rs.getInt("readcount"));
				travelBoardDto.setRegDate(rs.getTimestamp("regdate"));
				travelBoardDto.setContent(rs.getString("content"));
				travelBoardDto.setFileName(rs.getString("filename"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle1) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle3) {
				}
		}
		return travelBoardDto;
	}

	// [ 13. 글 내용 보기 메서드 ] //
	public TravelBoardDto content(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TravelBoardDto travelBoardDto = null;
		String sql1 = "update TRAVELBOARD set READCOUNT=READCOUNT+1 where NUM=?";
		String sql2 = "select * from TRAVELBOARD where num=?";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				travelBoardDto = new TravelBoardDto();
				travelBoardDto.setNum(rs.getInt("num"));
				travelBoardDto.setContent(rs.getString("content"));
				travelBoardDto.setSubject(rs.getString("subject"));
				travelBoardDto.setWriter(rs.getString("writer"));
				travelBoardDto.setCountry(rs.getString("country"));
				travelBoardDto.setCity(rs.getString("city"));
				travelBoardDto.setTerm(rs.getString("term"));
				travelBoardDto.setMoney(rs.getString("money"));
				travelBoardDto.setRoute(rs.getString("route"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle1) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle3) {
				}
		}
		return travelBoardDto;
	}

	// [ 14. 게시글 좋아요 메서드 ] //
	public void favoriteBoard(int flag, int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		if (flag == 1) {
			System.out.println("flag 1일 때 num :" + num);
			sql = "update TRAVELBOARD set BOARDGOOD=BOARDGOOD+1 where NUM=?";
		} else {
			sql = "update TRAVELBOARD set BOARDGOOD=BOARDGOOD-1 where NUM=?";
		}
		try {
			System.out.println("좋아요 sql문 : " + sql);
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			int res = pstmt.executeUpdate();
			if (res > 0) {
				System.out.println("업데이트 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle1) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle3) {
				}
		}
	}

	// [ 15. 작성자 좋아요 메서드 ] //
	public void favoriteWriter(int flag, String writer) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		if (flag == 1) {
			sql = "update MEMBER set WRITERGOOD=WRITERGOOD+1 where id=?";
		} else {
			sql = "update MEMBER set WRITERGOOD=WRITERGOOD-1 where id=?";
		}
		System.out.println("wrtier : "+writer);
		System.out.println("sql writer: "+sql);
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle1) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle3) {
				}
		}
	}

	// [ 16. 키워드 셋팅 (keywordSetting)] //
	public TravelBoardDto keywordSetting(String t, String m, String r) {
		TravelBoardDto travelBoardDto = new TravelBoardDto();
		// 기간 셋팅
		int term = Integer.parseInt(t);
		if (term == 1) {
			travelBoardDto.setTerm("무박");
		} else if (term == 2) {
			travelBoardDto.setTerm("1박 2일");
		} else if (term == 3) {
			travelBoardDto.setTerm("2박 3일");
		} else if (term == 30) {
			travelBoardDto.setTerm("한달");
		} else if (term == 180) {
			travelBoardDto.setTerm("6개월");
		} else {
			travelBoardDto.setTerm(t+"일");
		}
		
		// 비용 셋팅
		int money=Integer.parseInt(m);
		if(money==100000) {
			travelBoardDto.setMoney("100,000원");
		}else if(money==200000) {
			travelBoardDto.setMoney("200,000원");
		}else if(money==300000) {
			travelBoardDto.setMoney("300,000원");
		}else if(money==500000) {
			travelBoardDto.setMoney("500,000원");
		}else if(money==1000000) {
			travelBoardDto.setMoney("1,000,000원");
		}else {
			travelBoardDto.setMoney(String.format("%,d", money)+"원");
		}
		
		// 루트 셋팅
		int route=Integer.parseInt(r);
		if(route==2) {
			travelBoardDto.setRoute("2곳 (A → B)");
		}else if(route==3) {
			travelBoardDto.setRoute("3곳 (A → B → C)");
		}else if(route==4) {
			travelBoardDto.setRoute("4곳 (A → B → C → D)");
		}else if(route==5) {
			travelBoardDto.setRoute("5곳 (A → B → C → D → E)");
		}

		return travelBoardDto;
	}
}
