package servlet.book;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import domain.Admin;
import domain.Book;


@SuppressWarnings("serial")
@WebServlet("/book/list")
public class BookServlet extends HttpServlet {
    private final int RECORD_PER_PAGE = 12;//ÿҳ��ʾ��������

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Admin admin = (Admin) request.getSession().getAttribute("user");
        if (admin == null) {
            request.setAttribute("message", "�Բ�������δ��¼");
            request.getRequestDispatcher("/pages/admin/login.jsp").forward(request, response);
            return;
        }
        List<Book> bookList = null;
        BookDao dao = new BookDao();
        String keywords = request.getParameter("keywords");
        String action = request.getParameter("action");//��������
        if ("all".equals(action)) {//����ǲ鿴������Ʒ
            bookList = dao.selectAllBook();
        } else if ("search".equals(action)) {
            bookList = dao.searchBook(keywords);
        }
        request.setAttribute("bookList", bookList);//��������б�洢��request��bookList������
        //��ҳ
        int totalRecords = bookList.size();//��ѯ�����ܼ�¼��
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

        request.getRequestDispatcher("/pages/showBooks.jsp").forward(request, response);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
