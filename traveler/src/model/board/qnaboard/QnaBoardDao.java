package model.board.qnaboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnUtil;

public class QnaBoardDao {
	private static QnaBoardDao instance = null;
	private QnaBoardDao() {}
	public static QnaBoardDao getInstance() {
		if(instance == null) {
			synchronized(QnaBoardDao.class) {
				instance = new QnaBoardDao();
			}
		}
		return instance;
	}
	
	public int getArticleCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from QNABOARD");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		return count;
	}
	
	public int getArticleCount(String category) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		category = "%"+category+"%";
		int count = 0;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from QNABOARD where CATEGORY like ?");
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		return count;
	}
	
	public List<QnaBoardDto> getArticles(String cat, int start, int end){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QnaBoardDto> articleList = null;
		try {
			conn = ConnUtil.getConnection();
			
			if(cat == null||cat.equals("")||cat.equals("카테고리(전체)")) {
			String sql = "select * from "
					+ "(select rownum RNUM, NUM, CATEGORY,"
					+ "WRITER, SUBJECT, WRITEPWD, READCOUNT,"
					+ "REF, STEP, DEPTH, REGDATE, CONTENT, FILENAME from "
					+ "(select * from QNABOARD order by REF desc, STEP asc)) "
					+ "where RNUM >= ? and RNUM <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			} else if(cat.equals("일반 문의")) {
				String sql = "select * from "
						+ "(select rownum RNUM, NUM, CATEGORY,"
						+ "WRITER, SUBJECT, WRITEPWD, READCOUNT,"
						+ "REF, STEP, DEPTH, REGDATE, CONTENT, FILENAME from "
						+"(select * from QNABOARD where CATEGORY like ? )"
						+"order BY REF desc, STEP asc)"
						+"where RNUM >= ? and RNUM <= ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cat);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();
			}
			else if(cat.equals("게시물 관련 문의")) {
				String sql = "select * from "
						+ "(select rownum RNUM, NUM, CATEGORY,"
						+ "WRITER, SUBJECT, WRITEPWD, READCOUNT,"
						+ "REF, STEP, DEPTH, REGDATE, CONTENT, FILENAME from "
						+"(select * from QNABOARD where CATEGORY like ? )"
						+"order BY REF desc, STEP asc)"
						+"where RNUM >= ? and RNUM <= ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cat);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();	
			}
			else if(cat.equals("회원 관련 문의")) {
				String sql = "select * from "
						+ "(select rownum RNUM, NUM, CATEGORY,"
						+ "WRITER, SUBJECT, WRITEPWD, READCOUNT,"
						+ "REF, STEP, DEPTH, REGDATE, CONTENT, FILENAME from "
						+"(select * from QNABOARD where CATEGORY like ? )"
						+"order BY REF desc, STEP asc)"
						+"where RNUM >= ? and RNUM <= ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cat);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();
			}
			else if(cat.equals("버그 문의")) {
				String sql = "select * from "
						+ "(select rownum RNUM, NUM, CATEGORY,"
						+ "WRITER, SUBJECT, WRITEPWD, READCOUNT,"
						+ "REF, STEP, DEPTH, REGDATE, CONTENT, FILENAME from "
						+"(select * from QNABOARD where CATEGORY like ? )"
						+"order BY REF desc, STEP asc)"
						+"where RNUM >= ? and RNUM <= ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cat);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();
			}
			else if(cat.equals("기타 문의")) {
				String sql = "select * from "
						+ "(select rownum RNUM, NUM, CATEGORY,"
						+ "WRITER, SUBJECT, WRITEPWD, READCOUNT,"
						+ "REF, STEP, DEPTH, REGDATE, CONTENT, FILENAME from "
						+"(select * from QNABOARD where CATEGORY like ? )"
						+"order BY REF desc, STEP asc)"
						+"where RNUM >= ? and RNUM <= ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cat);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();	
			}
			if(rs.next()) {
				articleList = new ArrayList<QnaBoardDto>(10);
				do {
					QnaBoardDto article = new QnaBoardDto();
					article.setNum(rs.getInt("num"));
					article.setCategory(rs.getString("category"));
					article.setWriter(rs.getString("writer"));
					article.setSubject(rs.getString("subject"));
					article.setWritepwd(rs.getString("writepwd"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setContent(rs.getString("content"));
					article.setFilename(rs.getString("filename"));
					articleList.add(article);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		return articleList;
		}
	
	public void insertArticle(QnaBoardDto article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = article.getNum();		int ref = article.getRef();
		int step = article.getStep();	int depth = article.getDepth();
		int number = 0;		String sql = "";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(num) from QNABOARD");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				number = rs.getInt(1) + 1;
			} else { number = 1; }
			if(num != 0) {
				sql = "update QNABOARD set STEP = STEP+1 where REF = ? and STEP > ?";
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, ref);
					pstmt.setInt(2, step);
					pstmt.executeQuery();
					step = step + 1;
					depth = depth + 1;
			} else {
				ref = number;
				step = 0;
				depth = 0;
			}
			sql = "insert into QNABOARD (NUM, CATEGORY, "
					+ "WRITER, SUBJECT, WRITEPWD, "
					+ "REF, STEP, DEPTH, REGDATE, CONTENT, FILENAME) "
					+ "values(QNABOARD_SEQ.nextval,? ,? ,? ,? ,? ,? ,? ,? ,?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getCategory());
			pstmt.setString(2, article.getWriter());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getWritepwd());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, step);
			pstmt.setInt(7, depth);
			pstmt.setTimestamp(8, article.getRegdate());
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getFilename());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
	}
	
	public QnaBoardDto getArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnaBoardDto article = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("update QNABOARD set READCOUNT = READCOUNT+1 where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			pstmt.close();
			pstmt = conn.prepareStatement("select * from QNABOARD where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new QnaBoardDto();
				article.setNum(rs.getInt("num"));
				article.setCategory(rs.getString("category"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setWritepwd(rs.getString("writepwd"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setContent(rs.getString("content"));
				article.setFilename(rs.getString("filename"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		return article;
	}
	
	public QnaBoardDto updateGetArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnaBoardDto article = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from QNABOARD where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new QnaBoardDto();
				article.setNum(rs.getInt("num"));
				article.setCategory(rs.getString("category"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setWritepwd(rs.getString("writepwd"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setContent(rs.getString("content"));
				article.setFilename(rs.getString("filename"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		return article;
	}
	
	public int updateArticle(QnaBoardDto article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd = "";
		String sql = "";
		int result = -1;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select writepwd from QNABOARD where NUM = ?");
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dbpasswd = rs.getString("writepwd");
				if(dbpasswd.equals(article.getWritepwd())) {
					sql = "update QNABOARD set WRITER=?, SUBJECT=?,"
							+"CATEGORY=?, CONTENT=? where NUM=?";
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getSubject());
					pstmt.setString(3, article.getCategory());
					pstmt.setString(4, article.getContent());
					pstmt.setInt(5, article.getNum());
					pstmt.executeUpdate();
					result = 1;
				} else {
					result = 0;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		return result;
	}
	
	public QnaBoardDto deletegetArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnaBoardDto article = null;
		try {
			conn = ConnUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("select * from QNABOARD where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new QnaBoardDto();
				article.setNum(rs.getInt("num"));
				article.setCategory(rs.getString("category"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setWritepwd(rs.getString("writepwd"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setContent(rs.getString("content"));
				article.setFilename(rs.getString("filename"));
			}
			conn.commit();
		}catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		return article;
	}
	
	public int deleteArticle(int num, String writepwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String pwd = "";
		int dep, rf;
		int result = -1;
		try {
			conn = ConnUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("select WRITEPWD, DEPTH, REF from QNABOARD where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pwd = rs.getString("writepwd");
				dep = rs.getInt("depth");
				rf = rs.getInt("ref");
				if(pwd.equals(writepwd) && dep==0) {
					pstmt.close();
					pstmt = conn.prepareStatement("delete from QNABOARD where REF = ?");
					pstmt.setInt(1, rf);
					pstmt.executeUpdate();
					result = 1;
				}
				else if(pwd.equals(writepwd) && dep > 0) {
					pstmt.close();
					pstmt = conn.prepareStatement("delete from QNABOARD where NUM = ?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					result = 1;
				}else {
					result = 0;
				}
			}
			conn.commit();
		}catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		return result;
	}
}

