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
	public ScheduleVO getSchedule(String sdate, String id ) {
		ScheduleVO vo = new ScheduleVO();
		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();
			// 2. 쿼리 준비
			String sql = "select * from schedule where sdate=? and id=?";
			pstmt = conn.prepareStatement(sql);
			// 3. statment 실행, 내가 넘겨주는 id값으로 찾을거임
			pstmt.setString(1, sdate);
			pstmt.setString(2, id);
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
	public ArrayList<ScheduleVO> getScheduleList() {
		ArrayList<ScheduleVO> list = new ArrayList<ScheduleVO>();
		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();
			// 2. 쿼리 준비
			String sql = "select * from schedule order by seq desc, sdate asc";
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
			String sql = "update Schedule set sdate=?, schedule =?, memo=?" + "where id =? ";

			pstmt = conn.prepareStatement(sql);

			// 3. 실행
			pstmt.setString(1, schedule.getSdate());
			pstmt.setString(2, schedule.getSchedule());
			pstmt.setString(3, schedule.getMemo());
			pstmt.setString(4, schedule.getId());

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
	public int memberDelete(ScheduleVO schedule) {
		int r = 0;
		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();
			// 2. sql구문 준비
			String sql = "delete schedule where id = ? and sdate = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schedule.getId());
			pstmt.setString(1, schedule.getSdate());
			
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
}