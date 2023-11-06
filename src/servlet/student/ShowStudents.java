package servlet.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import domain.Student;

@SuppressWarnings("serial")
@WebServlet("/student/show")
public class ShowStudents extends HttpServlet {
    private final int RECORD_PER_PAGE = 12;//ÿҳ��ʾ������Ʒ

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        List<Student> studentList = null;
        StudentDao dao = new StudentDao();
        String keywords = request.getParameter("keywords");
        String action = request.getParameter("action");
        if ("all".equals(action)) {
            studentList = dao.selectAllStudent();
        } else if ("search".equals(action)) {
            studentList = dao.searchStudent(keywords);
        } else if ("modify".equals(action)) {
            String studentId = request.getParameter("studentId");
            Student student = dao.checkStudentId(studentId);
            request.setAttribute("student", student);
            request.getRequestDispatcher("/pages/student/studentModifyByAdmin.jsp").forward(request, response);
            return;
        }
        request.setAttribute("studentList", studentList);
        //��ҳ
        int totalRecords = studentList.size();//��ѯ������Ʒ�������ܼ�¼��
        int maxPageNum;//��ҳ��(���ҳ��)
        if (totalRecords % RECORD_PER_PAGE == 0) {//�պ�������
            maxPageNum = totalRecords / RECORD_PER_PAGE;
        } else {//��������
            maxPageNum = totalRecords / RECORD_PER_PAGE + 1;
        }
        String pageNumStr = request.getParameter("pageNum");//�ַ������͵�ҳ�ţ��������ֵ�����ַ���
        int pageNum;//����ҳ�ţ��������ڼ���
        if (pageNumStr == null) {//������û��ҳ�ţ���һ�η���
            pageNum = 1;//�״η��ʣ�Ĭ��Ϊ��һҳ
        } else {
            pageNum = Integer.parseInt(pageNumStr);//�����е�ҳ��ת��Ϊ����
        }
        int start = (pageNum - 1) * RECORD_PER_PAGE;//��ʼ��¼��
        int end = Math.min(totalRecords, pageNum * RECORD_PER_PAGE);//������¼��
        //����ֹ�±ꡢ�ܼ�¼������ҳ������Ϣ�洢��request��������
        request.setAttribute("start", start);
        request.setAttribute("end", end);
        request.setAttribute("totalRecords", totalRecords);
        request.setAttribute("pageNum", pageNum);
        request.setAttribute("maxPageNum", maxPageNum);

        request.getRequestDispatcher("/pages/student/showStudents.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
