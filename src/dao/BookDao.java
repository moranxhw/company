package dao;

import java.sql.*;
import java.util.*;

import domain.Book;

public class BookDao {
    private Connection conn;

    @SuppressWarnings("static-access")
    public BookDao() {
        conn = new DBConnection().getConnection();
    }

    public List<Book> selectAllBook() {
        List<Book> BookList = new ArrayList<Book>();//�����յ�list
        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from book order by id");
            ResultSet rs = pStmt.executeQuery();//ִ�в�ѯ�����ؽ����rs
            BookList = rs2List(rs);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return BookList;
    }

    public Book searchBookById(int id) {
        Book book = new Book();//�����յ�list
        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from book where id=?");
            pStmt.setInt(1, id);
            ResultSet rs = pStmt.executeQuery();//ִ�в�ѯ�����ؽ����rs
            while (rs.next()) {
                book.setId(rs.getInt(1));
                book.setBookName(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setCategory(rs.getString(4));//ͬ��
                book.setBookArea(rs.getString(5));//ͬ��
                book.setCreatetime(rs.getString(6));//ͬ��
                book.setBookNum(rs.getInt(7));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    public List<Book> searchBook(String search) {
        List<Book> bookSearch = new ArrayList<Book>();//�����յ�list
        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from book where id like ? or bookName like ? or author like ? or category like ? or bookArea like ?");
            pStmt.setString(1, "%" + search + "%");
            pStmt.setString(2, "%" + search + "%");
            pStmt.setString(3, "%" + search + "%");
            pStmt.setString(4, "%" + search + "%");
            pStmt.setString(5, "%" + search + "%");

            ResultSet rs = pStmt.executeQuery();//ִ�в�ѯ�����ؽ����rs
            bookSearch = rs2List(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookSearch;
    }


    public List<Book> rs2List(ResultSet rs) {
        List<Book> List = new ArrayList<Book>();//�����յ�list
        try {
            while (rs.next()) {
                Book e = new Book();
                e.setId(rs.getInt(1));
                e.setBookName(rs.getString(2));
                e.setAuthor(rs.getString(3));
                e.setCategory(rs.getString(4));//ͬ��
                e.setBookArea(rs.getString(5));//ͬ��
                e.setCreatetime(rs.getString(6));//ͬ��
                e.setBookNum(rs.getInt(7));
                List.add(e);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List;
    }

    public boolean addNewBook(Book book) {
        try {
            PreparedStatement pStmt = conn.prepareStatement("insert into book(bookName,author,category,bookArea,createtime,bookNum) values(?,?,?,?,NOW(),?)");
            pStmt.setString(1, book.getBookName());
            pStmt.setString(2, book.getAuthor());
            pStmt.setString(3, book.getCategory());
            pStmt.setString(4, book.getBookArea());
            pStmt.setInt(5, book.getBookNum());
            pStmt.executeUpdate();
            pStmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBookNum(String id, String num) {
        try {
            PreparedStatement pStmt = conn.prepareStatement("UPDATE book SET bookNum=? WHERE id=?");
            pStmt.setString(1, num);
            pStmt.setString(2, id);
            pStmt.executeUpdate();
            pStmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
