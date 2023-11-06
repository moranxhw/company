package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowDao;
import domain.Borrow;

@SuppressWarnings("serial")
@WebServlet("/borrow/list")
public class BorrowListServlet extends HttpServlet {
    private final int RECORD_PER_PAGE = 10;//ÿҳ��ʾ������Ʒ

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        List<Borrow> borrowList = null;
        BorrowDao dao = new BorrowDao();
        String keywords = request.getParameter("keywords");
        String action = request.getParameter("action");//��������
        if ("borrowList".equals(action)) {//����ǲ鿴���м�¼
            borrowList = dao.selectAllBorrow();
        } else if ("search".equals(action)) {
            borrowList = dao.searchBorrowByKeywords(keywords);
        } else if ("noReturn".equals(action)) {
            borrowList = dao.searchBorrowNoReturn();
        }
        //��ҳ
        int totalRecords = borrowList.size();//��ѯ������Ʒ�������ܼ�¼��
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

        request.setAttribute("borrowList", borrowList);//���������Ʒ�б�洢��request��bookList������

        request.getRequestDispatcher("/pages/borrowList.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
