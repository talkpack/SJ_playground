package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDao {
	// [기본 셋팅]
	private static MemberDao instance = null;

	private MemberDao() {
	}

	private static DataSource ds = null;
	{
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/myOracle");
		} catch (Exception e) {
			System.err.println("Connection 에러");
		}
	}

	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	public static MemberDao getInstance() {
		if (instance == null) {
			synchronized (MemberDao.class) {
				if (instance == null) {
					instance = new MemberDao();
				}
			}
		}
		return instance;
	}

	// [ 1. 아이디 중복확인 (idCheck) ] //
	// [ 2. 회원 저장 (memberInsert)] //
	// [ 3. 로그인 여부 (loginCheck)] //
	// [ 4. 회원 삭제 (memberDelete)] //
	// [ 5. 마이페이지 (mypageSelect)] //
	// [ 6. 회원 수정 (memberModify)] //
	// [ 7. 아이디 찾기 (searchId)] //
	// [ 8. 비밀번호 찾기 (searchPw)] //
	// [ 9. 회원 수 (memberCount)] //
	// [ 10. 추천 수 (getGoodCount)] //

	// [ 1. 아이디 중복확인 ] //
	public int idCheck(String id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 1;
		String sql = "select * from MEMBER where id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				result = 0;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
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
		return result;
	}

	// [ 2. 회원 저장 ] //
	public boolean memberInsert(MemberDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		String sql = "insert into MEMBER values(MEMBER_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPwd());
			pstmt.setString(4, dto.getGender());
			pstmt.setString(5, dto.getYear());
			pstmt.setString(6, dto.getMonth());
			pstmt.setString(7, dto.getDay());
			pstmt.setString(8, dto.getPh1());
			pstmt.setString(9, dto.getPh2());
			pstmt.setString(10, dto.getPh3());
			pstmt.setString(11, dto.getEmail1());
			pstmt.setString(12, dto.getEmail2());
			pstmt.setString(13, dto.getQuestion());
			pstmt.setString(14, dto.getAnswer());
			pstmt.setInt(15, 0);
			int res = pstmt.executeUpdate();
			if (res > 0)
				result = true;
		} catch (Exception e) {
			System.out.println("Exception" + e);
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
		return result;
	}

	// [ 3. 로그인 여부 ] //
	public int loginCheck(String id, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select pwd from MEMBER where id=?";
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbpwd = rs.getString(1);
				if (pwd.equals(dbpwd)) {
					result = 1;// ok
				} else {
					result = 0;// not pw
				}
			} else {
				result = -1;// not id
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// [ 4. 회원 삭제 ] //
	public boolean memberDelete(String id, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSql = "select PWD from MEMBER where ID=?";
		String deleteSql = "delete from MEMBER where ID=?";
		String dbpwd = "";
		boolean result = false;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(selectSql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbpwd = rs.getString("pwd");
				if (dbpwd.equals(pwd)) {
					pstmt.close();
					pstmt = conn.prepareStatement(deleteSql);
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					result = true;
				} else {
					result = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle1) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle3) {
				}
		}
		return result;
	}

	// [ 5. 마이페이지 ] //
	public MemberDto mypageSelect(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto MemberDto = new MemberDto();
		String sql = "select * from MEMBER where ID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				MemberDto.setName(rs.getString("name"));
				MemberDto.setId(rs.getString("id"));
				MemberDto.setPwd(rs.getString("pwd"));
				MemberDto.setYear(rs.getString("year"));
				MemberDto.setMonth(rs.getString("month"));
				MemberDto.setDay(rs.getString("day"));
				MemberDto.setPh1(rs.getString("ph1"));
				MemberDto.setPh2(rs.getString("ph2"));
				MemberDto.setPh3(rs.getString("ph3"));
				MemberDto.setGender(rs.getString("gender"));
				MemberDto.setEmail1(rs.getString("email1"));
				MemberDto.setEmail2(rs.getString("email2"));
				MemberDto.setJoinDate(rs.getTimestamp("joindate"));
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
		return MemberDto;
	}

	// [ 6. 회원 수정 ] //
	public int memberModify(MemberDto MemberDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update MEMBER set PWD=?, PH1=?, PH2=?, PH3=?, EMAIL1=?, EMAIL2=?, QUESTION=?, ANSWER=? where ID=?";
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberDto.getPwd());
			pstmt.setString(2, MemberDto.getPh1());
			pstmt.setString(3, MemberDto.getPh2());
			pstmt.setString(4, MemberDto.getPh3());
			pstmt.setString(5, MemberDto.getEmail1());
			pstmt.setString(6, MemberDto.getEmail2());
			pstmt.setString(7, MemberDto.getQuestion());
			pstmt.setString(8, MemberDto.getAnswer());
			pstmt.setString(9, MemberDto.getId());
			result = pstmt.executeUpdate();
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
				} catch (SQLException sqle1) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle1) {
				}
		}
		return result;
	}

	// [ 7. 아이디 찾기 ] //
	public String searchId(String name, String year, String month, String day) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id from MEMBER where NAME=? and YEAR=? and MONTH=? and DAY=?";
		String id = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, year);
			pstmt.setString(3, month);
			pstmt.setString(4, day);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getString("id");
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
		return id;
	}

	// [ 8. 비밀번호 찾기 ] //
	public String searchPw(String id, String ph1, String ph2, String ph3, String question, String answer) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select PWD from MEMBER where ID=? and PH1=? and PH2=? and PH3=? and QUESTION=? and ANSWER=?";
		String pwd = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, ph1);
			pstmt.setString(3, ph2);
			pstmt.setString(4, ph3);
			pstmt.setString(5, question);
			pstmt.setString(6, answer);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pwd = rs.getString("pwd");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle1) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle2) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle3) {
				}
		}
		return pwd;
	}

	// [ 9. 회원 수 ] //
	public int memberCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from MEMBER";
		int count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
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
	
	public int getGoodCount(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select WRITERGOOD from MEMBER where ID=?";
		int count = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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

}
