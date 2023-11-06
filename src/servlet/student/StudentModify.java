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
@WebServlet("/student/modify")
public class StudentModify extends HttpServlet {
    private final int RECORD_PER_PAGE = 12;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        StudentDao dao = new StudentDao();
        List<Student> studentList = null;
        String studentId = request.getParameter("studentId");
        String studentName = request.getParameter("studentName");
        String sex = request.getParameter("sex");
        String tel = request.getParameter("tel");
        String enrolldate = request.getParameter("enrolldate");
        String classid = request.getParameter("classid");
        String college = request.getParameter("college");
        boolean success = dao.updateStudent(new Student(studentId, studentName, sex, tel, enrolldate, classid, college));
        if (success) {
            request.setAttribute("message", "��Ϣ�޸ĳɹ���");
            studentList = dao.selectAllStudent();
        } else {
            request.setAttribute("message", "�Բ����޸�ʧ�ܣ������³��ԣ�");
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
        request.getRequestDispatcher("/student/show?action=all").forward(request, response);
    }
}


