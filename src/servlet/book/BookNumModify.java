package servlet.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;

@SuppressWarnings("serial")
@WebServlet("/book/modify")
public class BookNumModify extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String num = request.getParameter(id);
        BookDao dao = new BookDao();
        if (dao.updateBookNum(id, num)) {
            request.setAttribute("message", "�޸ĳɹ���");
        } else {
            request.setAttribute("message", "�޸�ʧ�ܣ�");
        }
        request.getRequestDispatcher("/book/showNum?action=all").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
