package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        String dbDriver = "com.mysql.jdbc.Driver";//mysql�����ַ���
        String url = "jdbc:mysql://localhost:3306/librarysystem?useUnicode=true&characterEncoding=UTF-8";//����estoredb���ݿ��url
        Connection conn = null;
        try {
            Class.forName(dbDriver).newInstance();//�������ݿ��JDBC����
            conn = DriverManager.getConnection(url, "root", "root");//������ݿ�����

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}
