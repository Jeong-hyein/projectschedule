package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionManager;

public class MemberDAO {
	int r = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;

	public int memberInsert(MemberVO member) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "insert into nmember (id, pwd, name,gender, introduction, regdt)"
					+ "values (?,?,?,?,?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getIntroduction());
			r = pstmt.executeUpdate();
			System.out.println(r + " 건이 등록됨");
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		return r;
	}

	// 단건조회
	public MemberVO getMember(String id) {
		MemberVO vo = new MemberVO();
		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();
			// 2. 쿼리 준비
			String sql = "select * from nmember where id = ?";
			pstmt = conn.prepareStatement(sql);
			// 3. statment 실행, 내가 넘겨주는 id값으로 찾을거임
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); // rs: 결과 집합.
			if (rs.next()) {
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setIntroduction(rs.getString("introduction"));
				vo.setRegdt(rs.getString("regdt"));
			}
			// 4. 결과 저정
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5.연결 해제
			ConnectionManager.close(conn);
		}
		return vo;
	}

	// 전체조회
	public ArrayList<MemberVO> getMemberList(int start, int end, String name) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();
			
			String strWhere = "where 1 = 1"; //조건이 있던없던 true
			if(name != null && !name.isEmpty()) {
				strWhere += " and name = ?";  //where뒤에 and가 붙는다
			}
			
			String sql = "select B.* from (select A.*, rownum RN from(" 
					+"select * from nmember " + strWhere +"order by id"
					+") A) B where RN BETWEEN ? AND ?";
			
			pstmt = conn.prepareStatement(sql);
			int post = 1;
			if(name != null && !name.isEmpty()) {
				pstmt.setString(post++, name); //1넣고 나서 1을 더한다: post++
			}
			pstmt.setInt(post++, start);
			pstmt.setInt(post++, end);
			// 3. statment 실행
			ResultSet rs = pstmt.executeQuery(); // rs: 결과 집합.
			while (rs.next()) { // 조회된 건수만큼 while 돈다.
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setIntroduction(rs.getString("introduction"));
				vo.setRegdt(rs.getString("regdt"));
				list.add(vo);
			}
			// 4. 결과 저정
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5.연결 해제
			ConnectionManager.close(conn);
		}
		return list;
	}

	// 수정
	public int memberUpdate(MemberVO member) {

		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();

			// 2. sql구문 준비
			String sql = "update nmember set pwd=?, name =?" + ",gender=?, introduction=?" + "where id =? ";

			pstmt = conn.prepareStatement(sql);

			// 3. 실행
			pstmt.setString(1, member.getPwd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getGender());
			pstmt.setString(4, member.getIntroduction());
			pstmt.setString(5, member.getId());

			r = pstmt.executeUpdate();

			// 4. 결과처리
			System.out.println(r + " 건이 등록됨.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. 연결해제
			ConnectionManager.close(conn);
		}
		return r;
	}

	// 삭제
	public int memberDelete(MemberVO member) {
		int r = 0;
		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();
			
			
			// 2. sql구문 준비
			String sql = "delete nmember where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			// 3. 실행
			r = pstmt.executeUpdate();
			
			
			// 2. sql구문 준비
			
			sql = "delete board where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			// 3. 실행
			r = pstmt.executeUpdate();
			// 4. 결과처리
			System.out.println(r + " 건이 삭제됨.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. 연결해제
			ConnectionManager.close(conn);
		}
		return r;
	}
	//전체건수	
		public int getCount(String name) {
				int cnt = 0;
				ResultSet rs = null;
				try {
					conn = ConnectionManager.getConnnect();
					String strWhere = " where 1 = 1"; //조건이 있던없던 true

					if(name != null && ! name.isEmpty()) {
						strWhere += " and name like '%' || ? || '%' " ;
					}
					String sql = "select count(*) as cnt from member" + strWhere;
					pstmt = conn.prepareStatement(sql);
					
					int post = 1;

					if(name != null && !name.isEmpty()) {
						pstmt.setString(post++, name);
					}
					
					rs = pstmt.executeQuery();
					if(rs.next()) {
						cnt = rs.getInt("cnt"); //컬럼명을 주거나, 인덱스를 주거나(1)
					}			
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					ConnectionManager.close(rs, pstmt, conn);
				}		
				return cnt;
			}

}// close of class
