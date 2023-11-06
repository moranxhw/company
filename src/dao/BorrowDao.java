package dao;

import java.sql.*;
import java.util.*;

import domain.Book;
import domain.Borrow;

public class BorrowDao {
    private Connection conn;

    @SuppressWarnings("static-access")
    public BorrowDao() {
        conn = new DBConnection().getConnection();
    }

    /*ɸѡѧ�ŵĽ��ĵļ�¼*/
    public List<Borrow> borrowSearch(String StudentId) {
        List<Borrow> Borrow = new ArrayList<Borrow>();//�����յ�list
        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from borrow where studentId = ? AND isBorrow=1");
            pStmt.setString(1, StudentId);
            ResultSet rs = pStmt.executeQuery();//ִ�в�ѯ�����ؽ����rs
            Borrow = rsBorrowList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Borrow;
    }

    /*ͨ���������ҽ��ĵ�ͼ��*/
    public Borrow searchByMainId(String id) {
        Borrow borrow = new Borrow();//�����յ�list
        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from borrow where mainId=?");
            pStmt.setString(1, id);
            ResultSet rs = pStmt.executeQuery();//ִ�в�ѯ�����ؽ����rs
            borrow = rsBorrow(rs);
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return borrow;
    }

    /*ͨ��id��ѧ�Ų��ҽ��ĵ�ͼ��*/
    public Borrow searchById(int id, String studentId) {
        Borrow borrow = new Borrow();//�����յ�borrow
        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from borrow where id=? and studentId=? and isBorrow=1");
            pStmt.setInt(1, id);
            pStmt.setString(2, studentId);
            ResultSet rs = pStmt.executeQuery();//ִ�в�ѯ�����ؽ����rs
            borrow = rsBorrow(rs);
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return borrow;
    }

    /*����
     * ��ӻ���ʱ��
     * ��isBorrow��Ϊ0*/
    public boolean huanshu(String mainId) {
        try {
            PreparedStatement pStmt = conn.prepareStatement("UPDATE borrow SET returntime=NOW() WHERE mainId=?");
            PreparedStatement pStmt1 = conn.prepareStatement("UPDATE borrow SET isBorrow=0 WHERE mainId=?");
            pStmt.setString(1, mainId);
            pStmt1.setString(1, mainId);
            pStmt.executeUpdate();
            pStmt1.executeUpdate();
            pStmt.close();
            pStmt1.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*���飬��borrow������Ӽ�¼*/
    public boolean jieshu(Borrow borrow) {
        try {
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO borrow VALUES(?,?,?,?,NOW(),NULL,1,NULL)");
            pStmt.setInt(1, borrow.getStudentId());
            pStmt.setString(2, borrow.getStudentName());
            pStmt.setInt(3, borrow.getId());
            pStmt.setString(4, borrow.getBookName());
            pStmt.executeUpdate();
            pStmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*���������и��������+1*/
    public boolean huanshuUpdate(Book book) {
        try {
            PreparedStatement pStmt = conn.prepareStatement("UPDATE book SET bookNum=? WHERE id=?");
            int bookNum = book.getBookNum() + 1;
            pStmt.setInt(1, bookNum);
            pStmt.setInt(2, book.getId());
            pStmt.executeUpdate();
            pStmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*�����������������-1*/
    public boolean jieshuUpdate(Book book) {
        try {
            PreparedStatement pStmt = conn.prepareStatement("UPDATE book SET bookNum=? WHERE id=?");
            int bookNum = book.getBookNum() - 1;
            pStmt.setInt(1, bookNum);
            pStmt.setInt(2, book.getId());
            pStmt.executeUpdate();
            pStmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*��ѯ���еĽ����¼*/
    public List<Borrow> selectAllBorrow() {
        List<Borrow> BorrowList = new ArrayList<Borrow>();//�����յ�list
        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from borrow order by createtime desc");
            ResultSet rs = pStmt.executeQuery();//ִ�в�ѯ�����ؽ����rs
            BorrowList = rsBorrowList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BorrowList;
    }

    /*ͨ���ؼ��ʲ��ҽ����¼*/
    public List<Borrow> searchBorrowByKeywords(String search) {
        List<Borrow> BorrowList = new ArrayList<Borrow>();//�����յ�list
        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from borrow where mainId like ? or studentId like ? or studentName like ? or id like ? or bookName like ? order by createtime desc");
            pStmt.setString(1, "%" + search + "%");
            pStmt.setString(2, "%" + search + "%");
            pStmt.setString(3, "%" + search + "%");
            pStmt.setString(4, "%" + search + "%");
            pStmt.setString(5, "%" + search + "%");
            ResultSet rs = pStmt.executeQuery();//ִ�в�ѯ�����ؽ����rs
            BorrowList = rsBorrowList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BorrowList;
    }

    /*��ѯδ�黹ͼ��*/
    public List<Borrow> searchBorrowNoReturn() {
        List<Borrow> BorrowList = new ArrayList<Borrow>();//�����յ�list
        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from borrow where isBorrow=1 order by createtime desc");
            ResultSet rs = pStmt.executeQuery();//ִ�в�ѯ�����ؽ����rs
            BorrowList = rsBorrowList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BorrowList;
    }

    /*�Ѳ�ѯ���Ľ��������List<Borrow>*/
    public List<Borrow> rsBorrowList(ResultSet rs) {
        List<Borrow> Borrow = new ArrayList<Borrow>();//�����յ�list
        try {
            while (rs.next()) {
                Borrow e = new Borrow();
                e.setStudentId(rs.getInt(1));
                e.setStudentName(rs.getString(2));
                e.setId(rs.getInt(3));
                e.setBookName(rs.getString(4));
                e.setCreatetime(rs.getString(5));
                e.setReturntime(rs.getString(6));
                e.setIsBorrow(rs.getInt(7));
                e.setMainId(rs.getInt(8));
                Borrow.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Borrow;
    }

    /*�Ѳ�ѯ���Ľ��������Borrow*/
    public Borrow rsBorrow(ResultSet rs) {
        Borrow borrow = new Borrow();//�����յ�borrow
        try {
            while (rs.next()) {
                borrow.setStudentId(rs.getInt(1));
                borrow.setStudentName(rs.getString(2));
                borrow.setId(rs.getInt(3));
                borrow.setBookName(rs.getString(4));
                borrow.setCreatetime(rs.getString(5));
                borrow.setReturntime(rs.getString(6));
                borrow.setIsBorrow(rs.getInt(7));
                borrow.setMainId(rs.getInt(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return borrow;
    }
}
