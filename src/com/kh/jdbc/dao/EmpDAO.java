package com.kh.jdbc.dao;
import com.kh.jdbc.util.Common;
import com.kh.jdbc.vo.EmpVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Query 문으로 DB의 정보를 가져 옴
public class EmpDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);
    public List<EmpVO> empSelect() {
        // ArrayList : 검색과 조회가 빈번할 때 사용.
        List<EmpVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            // DB에 SQL문을 전달하여 실행시키고 결과 값을 반환 받기 위해 사용
            stmt = conn.createStatement();
            String sql = "SELECT * FROM EMP";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int empNO = rs.getInt("EMPNO");
                String name = rs.getString("ENAME");
                String job = rs.getString("JOB");
                int mgr = rs.getInt("MGR");
                Date date = rs.getDate("HIREDATE");
                double sal = rs.getDouble("SAL");
                double comm = rs.getDouble("COMM");
                int dept = rs.getInt("DEPTNO");
                EmpVO vo = new EmpVO(empNO, name, job, mgr, date, sal, comm, dept);
                list.add(vo); // 생성된 객체를 list에 저장
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void empInsert() {
        System.out.println("사원 정보를 입력 하세요");
        System.out.print("사원번호(4자리) : ");
        int no = sc.nextInt();
        System.out.print("이름 : ");
        String name = sc.next();
        System.out.print("직책 : ");
        String job = sc.next();
        System.out.print("상관 사원번호(4자리) : ");
        int mgr = sc.nextInt();
        System.out.print("입사일 : ");
        String date = sc.next();
        System.out.print("급여 : ");
        int sal = sc.nextInt();
        System.out.print("성과급 : ");
        int comm = sc.nextInt();
        System.out.print("부서번호 : ");
        int dept = sc.nextInt();

//      String sql = "INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES("
//                + no + ", '" + name + "', '" + job + "', " + mgr + ", '" + date + "', " + sal + ", " + comm + ", " + dept + ")";
        String sql = "INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) " +
                "VALUES(?,?,?,?,?,?,?,?)";

        try {
            conn = Common.getConnection();
//            stmt = conn.createStatement();
//            int ret = stmt.executeUpdate(sql);
//            System.out.println("Return : " + ret);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
            pstmt.setString(2, name);
            pstmt.setString(3, job);
            pstmt.setInt(4, mgr);
            pstmt.setString(5, date);
            pstmt.setInt(6, sal);
            pstmt.setInt(7, comm);
            pstmt.setInt(8, dept);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }
    public void empUpdate() {
        System.out.println("변경할 사원 정보 입력");
        System.out.print("이름 : ");
        String name = sc.next();
        System.out.print("직책 : ");
        String job = sc.next();
        System.out.print("급여 : ");
        int sal = sc.nextInt();
        System.out.print("성과급 : ");
        int comm = sc.nextInt();

//        String sql = "UPDATE EMP "
//                + "SET JOB = " + "'" + job + "', "
//                + "SAL = " + sal + ", "
//                + "COMM = " + comm + " "
//                + "WHERE ENAME = " + "'" + name + "'";
        String sql = "UPDATE EMP SET JOB = ?, SAL = ?, COMM = ? WHERE ENAME = ?";

        try {
            conn = Common.getConnection();
//            stmt = conn.createStatement();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, job);
            pstmt.setInt(2, sal);
            pstmt.setInt(3, comm);
            pstmt.setString(4, name);
//            int ret = stmt.executeUpdate(sql);
            int ret = pstmt.executeUpdate();
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);

    }
    public void empDelete() {
        System.out.print("삭제할 이름을 입력 하세요 : ");
        String name = sc.next();
//        String sql = "DELETE FROM EMP WHERE ENAME = '" + name + "'";
        String sql = "DELETE FROM EMP WHERE ENAME = ?";
        try {
            conn = Common.getConnection();
//            stmt = conn.createStatement();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
//            int ret = stmt.executeUpdate(sql);
            int ret = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }
    public void empSelectRst(List<EmpVO> list) {
        for(EmpVO e : list) {
            System.out.print(e.getEmpNO() + " ");
            System.out.print(e.getName() + " ");
            System.out.print(e.getJob() + " ");
            System.out.print(e.getMgr() + " ");
            System.out.print(e.getDate() + " ");
            System.out.print(e.getSal() + " ");
            System.out.print(e.getComm() + " ");
            System.out.println(e.getDeptNO() + " ");
        }
    }
}
























