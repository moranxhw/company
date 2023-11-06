package servlet.book;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import domain.Book;

@SuppressWarnings("serial")
@WebServlet("/book/showNum")
public class ShowBookNumServlet extends HttpServlet {
    private final int RECORD_PER_PAGE = 12;//ÿҳ��ʾ������Ʒ

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        List<Book> bookList = null;
        String action = request.getParameter("action");
        String keywords = request.getParameter("keywords");
        BookDao dao = new BookDao();
        if ("all".equals(action)) {
            bookList = dao.selectAllBook();
        } else if ("search".equals(action)) {
            bookList = dao.searchBook(keywords);
        }

        request.setAttribute("bookList", bookList);//���������Ʒ�б�洢��request��bookList������
        //��ҳ
        int totalRecords = bookList.size();//��ѯ������Ʒ�������ܼ�¼��
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

        request.getRequestDispatcher("/pages/bookmodify/bookNumModify.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
