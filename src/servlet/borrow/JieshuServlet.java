package servlet.borrow;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.BorrowDao;
import domain.Book;
import domain.Borrow;
import domain.Student;

@SuppressWarnings("serial")
@WebServlet("/borrow/jieshu")
public class JieshuServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("bookId"));//ȡ��bookIdֵ
        BorrowDao dao = new BorrowDao();
        BookDao bookDao = new BookDao();
        Book book = bookDao.searchBookById(id);//ͨ��id��book���в��ҵ��鼮��Ϣ
        Student student = (Student) (request.getSession().getAttribute("student"));
        String studentId = student.getStudentId();
        Borrow yiyou = dao.searchById(id, studentId);
        if (yiyou.getBookName() != null && yiyou.getReturntime() == null) {//���borrow�����и�����Ϣ����û�л���ʱ�䣬˵������Ȿ�黹û��
            request.setAttribute("message", "��ѧ���Ѿ���������ˣ�");
        } else {//���borrow����û�и�����Ϣ���������ѻ������Խ���
            //ͨ��studentȡ��studentId��studentName��bookȡ��id��bookName����borrow����
            Borrow borrow = new Borrow(Integer.parseInt(student.getStudentId()), student.getStudentName(), book.getId(), book.getBookName(), null, null, 1, 0);
            boolean jieshuSuccess = dao.jieshu(borrow);//��borrow���в��������Ϣborrow
            boolean bookUpdateSuccess = dao.jieshuUpdate(book);//��book���и����鼮ʣ������
            if (jieshuSuccess && bookUpdateSuccess) {//����ɹ�
                request.setAttribute("message", "����ɹ���");
            } else {//���ʧ��
                request.setAttribute("message", "����ʧ�ܣ�");
            }
        }
        request.getRequestDispatcher("/borrow/borrow?action=jieshu").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
