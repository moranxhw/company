package domain;

public class Book {
    private int id;//��id
    private String bookName;//����
    private String author;//����
    private String category;//���
    private String bookArea;//������
    private String createtime;//���ʱ��
    private int bookNum;//ʣ������

    public Book(int id, String bookName, String author, String category, String bookArea, String createtime,
                int bookNum) {
        super();
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.bookArea = bookArea;
        this.createtime = createtime;
        this.bookNum = bookNum;
    }

    public Book() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBookArea() {
        return bookArea;
    }

    public void setBookArea(String bookArea) {
        this.bookArea = bookArea;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

}
