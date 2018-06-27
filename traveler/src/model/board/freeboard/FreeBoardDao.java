package model.board.freeboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnUtil;

public class FreeBoardDao {
	private static FreeBoardDao instance = null;
	private FreeBoardDao() {}
	public static FreeBoardDao getInstance() {
		if(instance == null) {
			synchronized (FreeBoardDao.class) {
				instance = new FreeBoardDao();
			}
		}
		return instance;
	}
	public int getArticleCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select count(*) from FREEBOARD";
		try {
			conn = ConnUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}
	public List<FreeBoardDto> getArticles(int start, int end){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FreeBoardDto> articleList = null;
		try {
			conn = ConnUtil.getConnection();
			conn.setAutoCommit(false);
			String sql = "select * from "
					+ "(select rownum RNUM, NUM, WRITER, SUBJECT, WRITEPWD, READCOUNT, REF, STEP, DEPTH, REGDATE, CONTENT, FILENAME from "
					+ "(select * from FREEBOARD order by REF desc, STEP asc)) "
					+ "where RNUM >= ? and RNUM <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				articleList = new ArrayList<FreeBoardDto>(5);
				do {
					FreeBoardDto article = new FreeBoardDto();
					article.setNum(rs.getInt("num"));
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
		return articleList;
	}
	public void insertArticle(FreeBoardDto article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		int number = 0;
		String sql = "";
		try {
			conn = ConnUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("select max(NUM) from FREEBOARD");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				number = rs.getInt(1) + 1;
			}else {
				number = 1;
			}
			if(num != 0) {
				sql = "update FREEBOARD set STEP = STEP+1 where REF = ? and STEP > ?";
				pstmt.close();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				step = step + 1;
				depth = depth + 1;
				pstmt.executeUpdate();
			}else {
				ref = number;
				step = 0;
				depth = 0;
			}
			sql = "insert into FREEBOARD (NUM, WRITER, SUBJECT, WRITEPWD, REF, STEP, DEPTH, REGDATE, CONTENT, FILENAME) "
			+ "values(FREEBOARD_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getWritepwd());
			pstmt.setInt(4, ref);
			pstmt.setInt(5, step);
			pstmt.setInt(6, depth);
			pstmt.setTimestamp(7, article.getRegdate());
			pstmt.setString(8, article.getContent());
			pstmt.setString(9, article.getFilename());
			pstmt.executeUpdate();
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
	}
	public FreeBoardDto getArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreeBoardDto article = null;
		try {
			conn = ConnUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("update FREEBOARD set READCOUNT=READCOUNT+1 where NUM = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = conn.prepareStatement("select * from FREEBOARD where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new FreeBoardDto();
				article.setNum(rs.getInt("num"));
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
	public FreeBoardDto modifyGetArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreeBoardDto article = null;
		try {
			conn = ConnUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("select * from FREEBOARD where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new FreeBoardDto();
				article.setNum(rs.getInt("num"));
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
	public int modifyArticle(FreeBoardDto article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String pwd = "";
		String sql = "";
		int result = -1;
		try {
			conn = ConnUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("select WRITEPWD from FREEBOARD where NUM = ?");
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pwd = rs.getString("writepwd");
				if(pwd.equals(article.getWritepwd())){
					sql = "update FREEBOARD set WRITER=?, SUBJECT=?, CONTENT=? where NUM = ?";
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, article.getWriter());
					pstmt.setString(2, article.getSubject());
					pstmt.setString(3, article.getContent());
					pstmt.setInt(4, article.getNum());
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
	public FreeBoardDto deleteGetArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreeBoardDto article = null;
		try {
			conn = ConnUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("select * from FREEBOARD where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new FreeBoardDto();
				article.setNum(rs.getInt("num"));
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
			pstmt = conn.prepareStatement("select WRITEPWD, DEPTH, REF from FREEBOARD where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pwd = rs.getString("writepwd");
				dep = rs.getInt("depth");
				rf = rs.getInt("ref");
				if(pwd.equals(writepwd) && dep==0) {
					pstmt.close();
					pstmt = conn.prepareStatement("delete from FREEBOARD where REF = ?");
					pstmt.setInt(1, rf);
					pstmt.executeUpdate();
					result = 1;
				}
				else if(pwd.equals(writepwd) && dep > 0) {
					pstmt.close();
					pstmt = conn.prepareStatement("delete from FREEBOARD where NUM = ?");
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
