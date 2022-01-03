package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	// 필드
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	// 생성자
	public PhoneDao() {
	}

	// 메소드 g,s

	// 메소드 일반
	private void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	private void closeTry() {

		// 5. 자원정리
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// [#insert]-------------------------------------------------------
	public void phoneInsert(PersonVo personvo) {

		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 생성
			String query = "";
			query += " insert into person ";
			query += " values(seq_person_id.nextval, ?,?,?) ";

			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, personvo.getName());
			pstmt.setString(2, personvo.getPh());
			pstmt.setString(3, personvo.getCompany());

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.closeTry();

	}

	// [#update]-------------------------------------------------------
	public void phoneUpdate(PersonVo personvo) {

		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 생성
			String query = "";
			query += " update person ";
			query += " set name = ?, ";
			query += "     hp = ?, ";
			query += "     company = ? ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, personvo.getName());
			pstmt.setString(2, personvo.getPh());
			pstmt.setString(3, personvo.getCompany());
			pstmt.setInt(4, personvo.getPersonId());

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.closeTry();

	}

	// [#delete]-------------------------------------------------------

	public void phoneDelete(int index) {

		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 생성
			String query = "";
			query += " delete from person ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setInt(1, index);

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.closeTry();

	}

	// [#select]-------------------------------------------------------
	public List<PersonVo> phoneSelect() {

		List<PersonVo> personList = new ArrayList<PersonVo>();

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 생성
			String query = "";
			query += " select  person_id, ";
			query += "         name, ";
			query += "         hp, ";
			query += "         company ";
			query += " from person ";

			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				// 객체 생성
				PersonVo vo = new PersonVo(personId, name, hp, company);

				// 리스트 생성
				personList.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.closeTry();

		return personList;
	}

	// [#search scanner]-------------------------------------------------------
	public List<PersonVo> phoneScanner(String search) {

		List<PersonVo> scannerList = new ArrayList<PersonVo>();

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 불러오기
			String query = "";
			query += " select person_id, ";
			query += "         name, ";
			query += "         hp, ";
			query += "         company ";
			query += " from person ";
			query += " where name like ? ";
			query += " or hp like ? ";
			query += " or company like ? ";

			// 문자열 쿼리문으로 변경
			pstmt = conn.prepareStatement(query);

			// 바인딩
			String input = '%' + search + '%';
			pstmt.setString(1, input);
			pstmt.setString(2, input);
			pstmt.setString(3, input);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				// 객체생성
				PersonVo svo = new PersonVo(personId, name, hp, company);
				scannerList.add(svo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.closeTry();

		return scannerList;

	}

	// ----------------------------------------
}
