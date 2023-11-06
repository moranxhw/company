package servlet.borrow;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import domain.*;


@SuppressWarnings("serial")
@WebServlet("/borrow/huanshu")
public class HuanshuServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String mainId = request.getParameter("mainId");//ȡ�øý�����Ϣ������
        BorrowDao dao = new BorrowDao();
        BookDao bookDao = new BookDao();
        Borrow borrow = dao.searchByMainId(mainId);//ͨ���������ҽ����¼
        Book book = bookDao.searchBookById(borrow.getId());//ͨ�������¼���鼮��Ų����鼮��Ϣ
        boolean huanshuSuccess = dao.huanshu(mainId);//ͨ�������¼��������
        boolean bookUpdateSuccess = dao.huanshuUpdate(book);//��book���е�ʣ������
        if (huanshuSuccess && bookUpdateSuccess) {//����ɹ�
            request.setAttribute("message", "����ɹ���");
        } else {//���ʧ��
            request.setAttribute("message", "����ʧ�ܣ�");
        }

        request.getRequestDispatcher("/borrow/borrow?action=huanshu").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
