package servlet.borrow;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import domain.*;

@SuppressWarnings("serial")
@WebServlet("/borrow/borrow")
public class BorrowServlet extends HttpServlet {
    private final int RECORD_PER_PAGE = 12;//ÿҳ��ʾ������Ʒ

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int totalRecords = 0;//ͼ������Ĭ��Ϊ0
        String action = request.getParameter("action");
        List<Borrow> borrow = null;
        List<Book> book = null;
        BorrowDao dao = new BorrowDao();
        BookDao bookDao = new BookDao();
        if ("jieshu".equals(action)) {//ֱ�ӵ����ͼ����ġ�����ִ���κζ������
            book = bookDao.selectAllBook();//��ѯ����ͼ��
            request.setAttribute("book", book);
            totalRecords = book.size();//��ѯ����ͼ���������ܼ�¼��
        } else {
            HttpSession session = request.getSession();
            String studentId = ((Student) session.getAttribute("student")).getStudentId();//��session�л�ȡѧ����Ϣ����ȡѧ��
            if ("huanshu".equals(action)) {//����㻹�鰴ť
                borrow = dao.borrowSearch(studentId);//����studentId��borrow�����ѽ��ĵ���
                request.setAttribute("borrow", borrow);
                totalRecords = borrow.size();//��ѯ����ͼ���������ܼ�¼��
            } else if ("search".equals(action)) {
                String keywords = (String) request.getParameter("keywords");//��ȡ�����ؼ���
                book = bookDao.searchBook(keywords);
                request.setAttribute("book", book);
                totalRecords = book.size();//��ѯ����ͼ���������ܼ�¼��
            }
        }
        //��ҳ
        int maxPageNum = 0;//��ҳ��(���ҳ��)
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

        request.getRequestDispatcher("/pages/bookBorrow.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
