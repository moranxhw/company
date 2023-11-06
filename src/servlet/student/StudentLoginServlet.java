package servlet.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import domain.Student;

@SuppressWarnings("serial")
@WebServlet("/student/login")
public class StudentLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String studentId = request.getParameter("studentId");
        StudentDao dao = new StudentDao();
        Student student = dao.checkStudentId(studentId);
        if (student != null) {//�ܲ鵽�û�����¼�ɹ�
            request.getSession().setAttribute("student", student);//��session�д洢�û�����
        } else {//�鲻���û�
            request.setAttribute("message", "�鲻����ѧ����");
        }
        request.getRequestDispatcher("/borrow/borrow?action=jieshu").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
