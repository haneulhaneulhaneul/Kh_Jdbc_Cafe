package com.kh.jdbc;
import com.kh.jdbc.dao.EmpDAO;
import com.kh.jdbc.vo.EmpVO;
import java.util.List;
import java.util.Scanner;

// github 테스트 중

public class JdbcMain {
    public static void main(String[] args) {
        menuSelect();
    }
    public static void menuSelect() {
        Scanner sc = new Scanner(System.in);
        EmpDAO dao = new EmpDAO();
        while (true) {
            System.out.println("===== [EMP TABLE] =====");
            System.out.println("메뉴를 조회 하세요");
            System.out.print("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]EXIT : ");
            int sel = sc.nextInt();
            switch (sel) {
                case 1 :
                    List<EmpVO> list = dao.empSelect();
                    dao.empSelectRst(list);
                    break;
                case 2 :
                    dao.empInsert();
                    break;
                case 3 :
                    dao.empUpdate();
                    break;
                case 4 :
                    dao.empDelete();
                    break;
                case 5 :
                    System.out.println("메뉴를 종료 합니다.");
                    return;
            }
        }
    }
}