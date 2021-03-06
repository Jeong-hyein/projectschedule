package schedule.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionManager;

public class ScheduleDAO {
	int r = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	public int scheduleInsert(ScheduleVO schedule) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "insert into schedule (seq, sdate, id, schedule, memo)"
					+ "values ((select nvl(max(seq),0) +1 from schedule),?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schedule.getSdate());
			pstmt.setString(2, schedule.getId());
			pstmt.setString(3, schedule.getSchedule());
			pstmt.setString(4, schedule.getMemo());
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
	public ScheduleVO getSchedule(String seq) {
		ScheduleVO vo = new ScheduleVO();
		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();
			// 2. 쿼리 준비
			String sql = "select * from schedule where seq=?";
			pstmt = conn.prepareStatement(sql);
			// 3. statment 실행, 내가 넘겨주는 id값으로 찾을거임
			pstmt.setString(1, seq);
			ResultSet rs = pstmt.executeQuery(); // rs: 결과 집합.
			if (rs.next()) {
				vo.setSeq(rs.getInt("seq"));
				vo.setId(rs.getString("id"));
				vo.setSdate(rs.getString("sdate"));
				vo.setSchedule(rs.getString("schedule"));
				vo.setMemo(rs.getString("memo"));
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
	public ArrayList<ScheduleVO> getScheduleList(int start, int end, String id, String sdate,String schedule) {
		ArrayList<ScheduleVO> list = new ArrayList<ScheduleVO>();
		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();
			String strWhere = " where 1 = 1 "; //조건이 있던없던 true
			if(id != null && !id.isEmpty()) {
				strWhere += " and id = ? ";  //where뒤에 and가 붙는다
			}
			if(sdate != null && !sdate.isEmpty()) {
				strWhere += " and sdate = ? ";  //where뒤에 and가 붙는다
			}
			if(schedule != null && !schedule.isEmpty()) {
				strWhere += " and schedule = ? ";  //where뒤에 and가 붙는다
			}
			// 2. 쿼리 준비
			String sql = "select B.* from (select A.*, rownum RN from " 
					+"(select * from schedule " + strWhere +" order by sdate, id"
					+") A) B where RN BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			// 3. statment 실행
			int post = 1;
			if(id != null && !id.isEmpty()) {
				pstmt.setString(post++, id); //1넣고 나서 1을 더한다: post++
			}
			if(sdate != null && !sdate.isEmpty()) {
				pstmt.setString(post++, sdate); //1넣고 나서 1을 더한다: post++
			}
			if(schedule != null && !schedule.isEmpty()) {
				pstmt.setString(post++, schedule); //1넣고 나서 1을 더한다: post++
			}
			pstmt.setInt(post++, start);
			pstmt.setInt(post++, end);
			
			ResultSet rs = pstmt.executeQuery(); // rs: 결과 집합.
			while (rs.next()) { // 조회된 건수만큼 while 돈다.
				ScheduleVO vo = new ScheduleVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setId(rs.getString("id"));
				vo.setSdate(rs.getString("sdate"));
				vo.setSchedule(rs.getString("schedule"));
				vo.setMemo(rs.getString("memo"));
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
	
	public ArrayList<ScheduleVO> getCal() {
		ArrayList<ScheduleVO> list = new ArrayList<ScheduleVO>();
		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();
			
			// 2. 쿼리 준비
			String sql = "select seq, id, to_char(sdate,'yyyymmdd') as sdate, schedule, memo from schedule order by sdate";
			pstmt = conn.prepareStatement(sql);
			// 3. statment 실행

			ResultSet rs = pstmt.executeQuery(); // rs: 결과 집합.
			while (rs.next()) { // 조회된 건수만큼 while 돈다.
				ScheduleVO vo = new ScheduleVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setId(rs.getString("id"));
				vo.setSdate(rs.getString("sdate"));
				vo.setSchedule(rs.getString("schedule"));
				vo.setMemo(rs.getString("memo"));
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
	public int scheduleUpdate(ScheduleVO schedule) {

		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();

			// 2. sql구문 준비
			String sql = "update Schedule set sdate=?, schedule =?, memo=?" + " where id =? and seq=?";

			pstmt = conn.prepareStatement(sql);

			// 3. 실행
			pstmt.setString(1, schedule.getSdate());
			pstmt.setString(2, schedule.getSchedule());
			pstmt.setString(3, schedule.getMemo());
			pstmt.setString(4, schedule.getId());
			pstmt.setInt(5, schedule.getSeq());
			
			r = pstmt.executeUpdate();

			// 4. 결과처리
			System.out.println(r + " 건이 수정됨.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. 연결해제
			ConnectionManager.close(conn);
		}
		return r;
	}

	// 삭제
	public int scheduleDelete(ScheduleVO schedule) {
		int r = 0;
		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();
			// 2. sql구문 준비
			String sql = "delete schedule where seq=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schedule.getSeq());
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
	
	public int getCount(String id, String sdate, String schedule) {
		int cnt = 0;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnnect();
			String strWhere = " where 1 = 1"; //조건이 있던없던 true

			if(id != null && ! id.isEmpty()) {
				strWhere += " and id= ? " ;
			}
			if(sdate != null && ! sdate.isEmpty()) {
				strWhere += " and to_char(sdate,'yyyymmdd')= ? " ;
			}
			if(schedule != null && ! schedule.isEmpty()) {
				strWhere += " and schedule= ? " ;
			}
			String sql = "select count(*) as cnt from schedule " + strWhere;
			pstmt = conn.prepareStatement(sql);
			
			int post = 1;

			if(id != null && !id.isEmpty()) {
				pstmt.setString(post++, id);
			}
			if(sdate != null && !sdate.isEmpty()) {
				pstmt.setString(post++, sdate);
			}
			if(schedule != null && !schedule.isEmpty()) {
				pstmt.setString(post++, schedule);
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
}
