package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Admin;


public class AdminDao {
    private Connection conn;

    @SuppressWarnings("static-access")
    public AdminDao() {
        conn = new DBConnection().getConnection();
    }

    public Admin checkUserNameAndPassword(String userName, String password) {
        Admin user = null;
        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from admin where user_name = ? AND password = ?");
            pStmt.setString(1, userName);
            pStmt.setString(2, password);
            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {//�����û��������ظ��������ѯ���ֻ�ܲ��һ�м�¼�����Բ���Ҫѭ������
                user = new Admin();
                user.setId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRealName(rs.getString(4));
                user.setMobile(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setPasswordQuestion(rs.getString(7));
                user.setPasswordHintAnswer(rs.getString(8));
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    /*�����û�����ѯ�û���Ϣ
    �ɹ�����Customer���󣬲鲻������null*/
    public Admin selectCustomerByUserName(String userName) {
        Admin user = null;
        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from admin where user_name = ?");
            pStmt.setString(1, userName);
            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {//�����û��������ظ��������ѯ���ֻ�ܲ��һ�м�¼�����Բ���Ҫѭ������
                user = new Admin();
                user.setId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRealName(rs.getString(4));
                user.setMobile(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setPasswordQuestion(rs.getString(7));
                user.setPasswordHintAnswer(rs.getString(8));
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    //�����û�����
    public boolean updateCustomer(Admin user) {
        try {
            PreparedStatement pStmt = conn.prepareStatement("update admin set user_name = ?,password=?,real_name=?,mobile=?,email=?,password_question=?,password_hint_answer=? where id=?");
            pStmt.setString(1, user.getUserName());
            pStmt.setString(2, user.getPassword());
            pStmt.setString(3, user.getRealName());
            pStmt.setString(4, user.getMobile());
            pStmt.setString(5, user.getEmail());
            pStmt.setString(6, user.getPasswordQuestion());
            pStmt.setString(7, user.getPasswordHintAnswer());
            pStmt.setInt(8, user.getId());
            pStmt.executeUpdate();
            pStmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
