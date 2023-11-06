package servlet.book;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import domain.*;

@SuppressWarnings("serial")
@WebServlet("/book/add")
public class AddBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BookDao dao = new BookDao();
        String bookName = request.getParameter("bookName");//����
        String author = request.getParameter("author");//����
        String category = request.getParameter("category");//���
        String bookArea = request.getParameter("bookArea");//������
        int bookNum = Integer.parseInt(request.getParameter("bookNum"));//����
        Book book = new Book(0, bookName, author, category, bookArea, "", bookNum);
        List<Book> bookList = dao.selectAllBook();
        for (int i = 0; i < bookList.size(); i++) {
            Book bk = bookList.get(i);
            if (bk.getBookName().equals(book.getBookName()) && bk.getAuthor().equals(book.getAuthor()) && bk.getBookArea().equals(book.getBookArea())) {
                request.setAttribute("message", "�Բ���ͼ��������д˱��飡");
                request.getRequestDispatcher("/pages/bookmodify/addBook.jsp").forward(request, response);
                return;
            }
        }
        boolean success = dao.addNewBook(book);
        if (success) {
            request.setAttribute("message", "�鼮��ӳɹ���");
        } else {
            request.setAttribute("message", "�鼮���ʧ�ܣ�");
        }
        request.getRequestDispatcher("/book/list?action=all").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
