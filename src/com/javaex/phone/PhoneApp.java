package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {

		// List 정의, dao 생성, scanner
		List<PersonVo> list;
		PhoneDao phonedao = new PhoneDao();
		Scanner sc = new Scanner(System.in);

		// 시작화면
		System.out.println("##################################");
		System.out.println("#        전화번호 관리 프로그램        #");
		System.out.println("##################################");

		boolean action = true;
		while (action) {
			System.out.println();
			System.out.println("1.리스트  2.등록  3.수정  4.삭제  5.검색  6.종료");
			System.out.println("----------------------------------");
			System.out.print(">메뉴번호:");
			int inputNum = sc.nextInt();

			switch (inputNum) {
			case 1: // 리스트 출력 (화면 출력) select
				System.out.println("<1.리스트>");

				list = phonedao.phoneSelect();
				for (int i = 0; i < list.size(); i++) {
					PersonVo vo = list.get(i);
					System.out.println(
							vo.getPersonId() + "\t" + vo.getName() + "\t" + vo.getPh() + "\t" + vo.getCompany());
				}

				break;

			case 2: // 리스트 추가 등록 insert
				System.out.println("<2.등록>");

				System.out.print(">이름: ");
				String nameAdd = sc.next();
				System.out.print(">휴대전화: ");
				String phAdd = sc.next();
				System.out.print(">회사전화: ");
				String companyAdd = sc.next();

				PersonVo psInsert = new PersonVo(nameAdd, phAdd, companyAdd);
				phonedao.phoneInsert(psInsert);

				System.out.println("[1건 등록되었습니다.]");
				break;

			case 3: // 수정 update
				System.out.println("<3.수정>");

				System.out.print(">번호: ");
				int idUpdate = sc.nextInt();
				System.out.print(">이름: ");
				String nameUpdate = sc.next();
				System.out.print(">휴대전화: ");
				String phUpdate = sc.next();
				System.out.print(">회사전화: ");
				String companyUpdate = sc.next();

				PersonVo vo = new PersonVo(idUpdate, nameUpdate, phUpdate, companyUpdate);
				phonedao.phoneUpdate(vo);

				System.out.println("[1건 수정되었습니다.]");

				break;

			case 4: // 선택 제거 delete
				System.out.println("<4.삭제>");
				System.out.print(">번호: ");
				int removeInput = sc.nextInt();

				phonedao.phoneDelete(removeInput);
				sc.nextLine();

				System.out.println("[삭제되었습니다.]");
				break;

			case 5: // 검색 scanner
				System.out.println("<5.검색>");
				System.out.print(">검색어:");
				String search = sc.next();

				list = phonedao.phoneScanner(search);

				for (int i = 0; i < list.size(); i++) {
					PersonVo scannervo = list.get(i);
					System.out.println(scannervo.getPersonId() + "\t" + scannervo.getName() + "\t" + scannervo.getPh()
							+ "\t" + scannervo.getCompany());
				}

				break;

			case 6: // 종료 (탈출)
				action = false;
				System.out.println("[종료되었습니다]");
				break;

			default:// 그 외 메뉴 작성시
				System.out.println("!다시 입력해 주세요!");
				break;

			}// ---------switch문 종료-------------------

		} // -------------while문 종료---------------------

		sc.close();

	}

}
