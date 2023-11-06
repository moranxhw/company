package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Student;


public class StudentDao {
    private Connection conn;

    @SuppressWarnings("static-access")
    public StudentDao() {
        conn = new DBConnection().getConnection();
    }

    //ͨ��id����ѧ��
    public Student checkStudentId(String studentId) {
        Student student = null;
        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from student where studentid = ?");
            pStmt.setString(1, studentId);
            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {//�����û��������ظ��������ѯ���ֻ�ܲ��һ�м�¼�����Բ���Ҫѭ������
                student = new Student();
                student.setStudentId(rs.getString(1));
                student.setStudentName(rs.getString(2));
                student.setSex(rs.getString(3));
                student.setTel(rs.getString(4));
                student.setEnrolldate(rs.getString(5));
                student.setClassid(rs.getString(6));
                student.setCollege(rs.getString(7));
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return student;
    }

    //��������ѧ��
    public List<Student> selectAllStudent() {
        List<Student> studentList = new ArrayList<Student>();//�����յ�list
        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from student");
            ResultSet rs = pStmt.executeQuery();//ִ�в�ѯ�����ؽ����rs
            studentList = rs2List(rs);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    //����ѧ��
    public List<Student> searchStudent(String search) {
        List<Student> studentList = new ArrayList<Student>();//�����յ�list
        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from student where studentId like ? or studentname like ? or enrolldate like ? or classid like ? or college like ?");
            pStmt.setString(1, "%" + search + "%");
            pStmt.setString(2, "%" + search + "%");
            pStmt.setString(3, "%" + search + "%");
            pStmt.setString(4, "%" + search + "%");
            pStmt.setString(5, "%" + search + "%");

            ResultSet rs = pStmt.executeQuery();//ִ�в�ѯ�����ؽ����rs
            studentList = rs2List(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public List<Student> rs2List(ResultSet rs) {
        List<Student> List = new ArrayList<Student>();//�����յ�list
        try {
            while (rs.next()) {
                Student e = new Student();
                e.setStudentId(rs.getString(1));
                e.setStudentName(rs.getString(2));
                e.setSex(rs.getString(3));
                e.setTel(rs.getString(4));
                e.setEnrolldate(rs.getString(5));
                e.setClassid(rs.getString(6));
                e.setCollege(rs.getString(7));
                List.add(e);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List;
    }

    //�����û�����
    public boolean updateStudent(Student stu) {
        try {
            PreparedStatement pStmt = conn.prepareStatement("update student set studentname=?,sex=?,tel=?,enrolldate=?,classid=?,college=? where studentid=?");
            pStmt.setString(1, stu.getStudentName());
            pStmt.setString(2, stu.getSex());
            pStmt.setString(3, stu.getTel());
            pStmt.setString(4, stu.getEnrolldate());
            pStmt.setString(5, stu.getClassid());
            pStmt.setString(6, stu.getCollege());
            pStmt.setString(7, stu.getStudentId());
            pStmt.executeUpdate();
            pStmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
