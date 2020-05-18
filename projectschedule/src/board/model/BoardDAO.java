package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.ConnectionManager;

public class BoardDAO {
		int r = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		public int boardInsert(BoardVO board) {

			try {
				// 1. DB 연결
				conn = ConnectionManager.getConnnect();

				// 2. sql구문 준비, seq_board.nextval 대신 서브쿼리 사용 -> 빈 번호 없애려고
				String sql = "insert into board (seq, title, contents, regdt, id)"
						+ " values ((select nvl(max(seq),0) +1 from board), ?, ?, sysdate, ?)";

				pstmt = conn.prepareStatement(sql);

				// 3. 실행
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getContents());
				pstmt.setString(3, board.getId());

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

		// 단건조회
		public BoardVO getBorad(String id) {
			BoardVO vo = new BoardVO();
			try {
				// 1. DB 연결
				conn = ConnectionManager.getConnnect();
				// 2. 쿼리 준비
				String sql = "select * from board where id = ?";
				pstmt = conn.prepareStatement(sql);
				// 3. statment 실행, 내가 넘겨주는 id값으로 찾을거임
				pstmt.setString(1, id);
				ResultSet rs = pstmt.executeQuery(); // rs: 결과 집합.
				if (rs.next()) {
					vo.setId(rs.getString("id"));
					vo.setTitle(rs.getString("title"));
					vo.setContents(rs.getString("contents"));
					vo.setRegdt(rs.getString("regdt"));
					vo.setSeq(rs.getInt("seq"));
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
		public ArrayList<BoardVO> getBoardList() {
			ArrayList<BoardVO> list = new ArrayList<BoardVO>();
			try {
				// 1. DB 연결
				conn = ConnectionManager.getConnnect();
				// 2. 쿼리 준비
//				String sql = "select * from board order by seq desc";
				String sql = "select board.*, nmember.name from board join nmember on board.id = nmember.id order by seq desc";
				pstmt = conn.prepareStatement(sql);
				// 3. statment 실행
				ResultSet rs = pstmt.executeQuery(); // rs: 결과 집합.
				while (rs.next()) { // 조회된 건수만큼 while 돈다.
					BoardVO vo = new BoardVO();
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setTitle(rs.getString("title"));
					vo.setContents(rs.getString("contents"));
					vo.setRegdt(rs.getString("regdt"));
					vo.setSeq(rs.getInt("seq"));
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
		public int boardUpdate(BoardVO board) {

			try {
				// 1. DB 연결
				conn = ConnectionManager.getConnnect();

				// 2. sql구문 준비
				String sql = "update board set title=?, contents=?"
						+ "where id =? ";

				pstmt = conn.prepareStatement(sql);

				// 3. 실행
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getContents());

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
		public int boardDelete(BoardVO borad) {
			int r = 0;
			try {
				// 1. DB 연결
				conn = ConnectionManager.getConnnect();
				// 2. sql구문 준비
				String sql = "delete borad where seq = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, borad.getSeq());
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

