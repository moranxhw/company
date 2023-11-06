package servlet.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Admin;
import dao.AdminDao;

@SuppressWarnings("serial")
@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String AdminsuserName = request.getParameter("AdminsuserName");
        String Adminspassword = request.getParameter("Adminspassword");
        AdminDao dao = new AdminDao();
        Admin Aduser = dao.checkUserNameAndPassword(AdminsuserName, Adminspassword);
        if (Aduser != null) {//�ܲ鵽�û�����¼�ɹ�
            request.setAttribute("message", "��¼�ɹ���");
            request.getSession().setAttribute("user", Aduser);//��session�д洢�û�����
            SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = now.format(new Date());//��õ�¼ʱ��
            request.getSession().setAttribute("dateStr", nowTime);
            request.getRequestDispatcher("/book/list?action=all").forward(request, response);
        } else {//�鲻���û���������û�������ȷ
            request.setAttribute("message", "�û��������벻��ȷ��");
            request.getRequestDispatcher("/pages/admin/login.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
