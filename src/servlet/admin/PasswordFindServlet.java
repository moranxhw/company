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
@WebServlet("/admin/passwordFind")
public class PasswordFindServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        AdminDao dao = new AdminDao();
        String action = request.getParameter("action");//��������
        String userName = request.getParameter("userName");//��ñ�����û���
        Admin user = dao.selectCustomerByUserName(userName);//����selectCustomerByUserName����
        if ("checkUserName".equals(action)) {
            if (user == null) {//�����ѯ�����û�����ʾ�û�������
                request.setAttribute("message", "�Բ����û�������");
                request.getRequestDispatcher("/pages/passwordFind/passwordFind_name.jsp").forward(request, response);
                return;
            }
            request.getSession().setAttribute("user", user);//��session�д洢�û�����
            request.getRequestDispatcher("/pages/passwordFind/passwordFind_answer.jsp").forward(request, response);
        } else if ("checkAnswer".equals(action)) {
            String answer = request.getParameter("answer");
            if (!answer.equals(user.getPasswordHintAnswer())) {//����ܱ�����𰸲�һ��
                request.setAttribute("message", "�Բ�����������ش����");
                request.getRequestDispatcher("/pages/passwordFind/passwordFind_answer.jsp").forward(request, response);
                return;
            }
            request.getRequestDispatcher("/pages/passwordFind/passwordFind_password.jsp").forward(request, response);
        } else if ("updatePassword".equals(action)) {
            int id = user.getId();
            String password = request.getParameter("password1");//����
            String realName = user.getRealName();
            String mobile = user.getMobile();
            String email = user.getEmail();
            String passwordQuestion = user.getPasswordQuestion();
            String passwordHintAnswer = user.getPasswordHintAnswer();
            boolean success = dao.updateCustomer(new Admin(id, userName, password, realName, mobile, email, passwordQuestion, passwordHintAnswer));
            if (success) {
                request.setAttribute("message", "�����޸ĳɹ���");
                user = dao.selectCustomerByUserName(userName);
                request.getSession().setAttribute("user", user);//��session�д洢�û�����
            } else {
                request.setAttribute("message", "�Բ����޸�ʧ�ܣ������³��ԣ�");
                request.getRequestDispatcher("/pages/passwordFind/passwordFind_password.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/pages/admin/login.jsp").forward(request, response);
        }
    }

}
