package model.travel.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connection.ConnUtil;

public class CommentDao {
	// [ 기본셋팅 ] //
	private static CommentDao instance = null;
	private CommentDao() {}
	public static CommentDao getInstance() {
		if (instance == null) {
			synchronized (CommentDao.class) {
				if (instance == null) {
					instance = new CommentDao();
				}
			}
		}
		return instance;
	}
	// [ 1. 댓글 저장 ] //
	
	// [ 1. 댓글 저장 ] //
	public void commentInsert(CommentDto commentDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into COMMENTS " + "(NUM,LISTNUM,WRITER,CONTENT,COMMENTDATE)" + "values(COMMENTS_SEQ.nextval,?,?,?,sysdate)";
		System.out.println("commentDto listnum : " + commentDto.getListNum());
		System.out.println("commentDto writer : " + commentDto.getWriter());
		System.out.println("commentDto comment : " + commentDto.getContent());
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentDto.getListNum());
			pstmt.setString(2, commentDto.getWriter());
			pstmt.setString(3, commentDto.getContent());
			int res = pstmt.executeUpdate();
			if (res > 0) {
				System.out.println("댓글 저장 성공 ");
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
	}
	// [ 2. 댓글 목록 ] //
	public List<CommentDto> commentList(int listNum) {
		System.out.println("listNum db : "+listNum);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CommentDto> list = null;
		CommentDto commentDto = null;
		
		String sql = "select * from (select * from COMMENTS where LISTNUM=? )order by NUM asc";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, listNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<CommentDto>(10);
				do {
					commentDto = new CommentDto();
					commentDto.setWriter(rs.getString("writer"));
					commentDto.setContent(rs.getString("content"));
					commentDto.setCommentDate(rs.getTimestamp("commentDate"));
					list.add(commentDto);
				}while(rs.next());
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
		return list;
	}
	
	// [ 3. 댓글 수 ] //
	public int commentCount(int listNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;
		String sql = "select count(*) from COMMENTS where LISTNUM=?";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, listNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count=rs.getInt(1);
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
		return count;
	}
}
