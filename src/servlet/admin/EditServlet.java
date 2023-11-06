package servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import domain.Admin;

@SuppressWarnings("serial")
@WebServlet("/admin/edit")
public class EditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        AdminDao dao = new AdminDao();
        String userName = request.getParameter("userName");//��ñ�����û���
        Admin user = dao.selectCustomerByUserName(userName);//����selectCustomerByUserName����
        if (user == null) {//�����ѯ�����û�����ʾ�û�������
            request.setAttribute("message", "�Բ����û�������");
            request.getRequestDispatcher("/pages/showBooks.jsp").forward(request, response);
            return;
        }
        int id = user.getId();
        String password = request.getParameter("password1");//����
        String realName = request.getParameter("realName");//ʵ��
        String mobile = request.getParameter("mobile");//�ֻ�
        String email = request.getParameter("email");//����
        String passwordQuestion = request.getParameter("passwordQuestion");//��������
        String passwordHintAnswer = request.getParameter("passwordHintAnswer");//���������
        boolean success = dao.updateCustomer(new Admin(id, userName, password, realName, mobile, email, passwordQuestion, passwordHintAnswer));
        if (success) {
            request.setAttribute("message", "��Ϣ�޸ĳɹ���");
            user = dao.selectCustomerByUserName(userName);
            request.getSession().setAttribute("user", user);//��session�д洢�û�����
        } else {
            request.setAttribute("message", "�Բ����޸�ʧ�ܣ������³��ԣ�");
        }
        request.getRequestDispatcher("/book/list?action=all").forward(request, response);
    }

}
